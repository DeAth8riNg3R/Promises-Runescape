import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

class PlayerManager {

	public static final int maxPlayers = 200;
	public static Player players[] = new Player[maxPlayers];
	public int playerSlotSearchStart = 1;
	public static String kickNick = "";
	public static boolean kickAllPlayers = false;
	public static String messageToAll = "";
	public static int playerCount=0;
	public static String playersCurrentlyOn[] = new String[maxPlayers];
	public static boolean updateAnnounced;
	public static boolean updateRunning;
	public static int updateSeconds;
	public static long updateStartTime;
	public static int WaitingRoom = 600;

	PlayerManager() {
		for(int i = 0; i < maxPlayers; i++) {
			players[i] = null;
		}
	}

	public void newPlayerClient(java.net.Socket s, String connectedFrom) {
		int slot = -1, i = 1;
		do {
			if(players[i] == null) {
				slot = i;
				break;
			}
			i++;
			if(i >= maxPlayers) i = 0;
		} while(i <= maxPlayers);

		Client newClient = new Client(s, slot);
		newClient.handler = this;
		(new Thread(newClient)).start();
		if(slot == -1) return;
		players[slot] = newClient;
		players[slot].connectedFrom = connectedFrom;
                updatePlayerNames();

		playerSlotSearchStart = slot + 1;
		if(playerSlotSearchStart > maxPlayers) playerSlotSearchStart = 1;
	}

	public void destruct() {
		for(int i = 0; i < maxPlayers; i++) {
			if(players[i] == null) continue;
			players[i].destruct();
			players[i] = null;
		}
	}

	public static int getPlayerCount() {
		int count = 0;
		for (int i = 0; i < players.length; i++) {
			if ((players[i] != null) && !players[i].disconnected) {
				count++;
			}
		}
		return count;
	}

	public void updatePlayerNames() {
		playerCount = 0;
		for (int i = 0; i < maxPlayers; i++) {
			if (players[i] != null) {
				playersCurrentlyOn[i] = players[i].playerName;
				playerCount++;
			} else
				playersCurrentlyOn[i] = "";
		}
	}

	public static boolean isPlayerOn(String playerName) {
		for(int i = 0; i < maxPlayers; i++) {
			if(playersCurrentlyOn[i] != null){
				if(playersCurrentlyOn[i].equalsIgnoreCase(playerName)) return true;
			}
		}
		return false;
	}

	public static int getPlayerID(String playerName) {
		for (int i = 0; i < maxPlayers; i++) {
			if (playersCurrentlyOn[i] != null) {
				if (playersCurrentlyOn[i].equalsIgnoreCase(playerName))
					return i;
			}
		}
		return -1;
	}

	public void process() {
		if (kickAllPlayers) {
			int kickID = 1;
			do {
				if(players[kickID] != null) {
					players[kickID].isKicked = true;
				}
				kickID++;
			} while(kickID < maxPlayers);
			kickAllPlayers = false;
		}
		if (WaitingRoom > 0) {
			WaitingRoom--;
		} else {
			WaitingRoom = 600;
		}
		for(int i = 0; i < maxPlayers; i++) {
			if(players[i] == null || !players[i].isActive) continue;

			players[i].preProcessing();
			players[i].process();
			while(players[i].packetSending());
			players[i].postProcessing();

			players[i].getNextPlayerMovement();
			
			if (players[i].disconnected) {
                          	for (int i2 = 0; i2 < Server.NpcManager.maxNPCs; i2++) {
					if (Server.NpcManager.npcs[i2] != null && players[i] != null) {
						if (Server.NpcManager.npcs[i2].followPlayer == players[i].playerId && !Server.NpcManager.npcs[i2].IsDead && Server.NpcManager.npcs[i2] != null) {
							Server.NpcManager.npcs[i2].StartKilling = 0;
							Server.NpcManager.npcs[i2].RandomWalk = true;
							Server.NpcManager.npcs[i2].followingPlayer = false;
							Server.NpcManager.npcs[i2].followPlayer = 0;
							Server.NpcManager.npcs[i2].IsUnderAttack = false;
							if (players[i].isInFightCaves() || players[i].inDT() || players[i].isInBarrows()) {
								players[i].RemoveNPC();
							}
						}
					}
				}
				if (players[i].tradeStatus >= 2) {
					players[i].StopTradeScam();
				}
				playerCount--;
				removePlayer(players[i]);
				players[i] = null;
			}
		}
		for(int i = 0; i < maxPlayers; i++) {
			if(players[i] == null || !players[i].isActive) continue;

			Calendar cal = new GregorianCalendar();
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int calc = ((year * 10000) + (month * 100) + day);
			players[i].playerLastLogin = calc;
			if (players[i].disconnected) {
                          	for (int i3 = 0; i3 < Server.NpcManager.maxNPCs; i3++) {
					if (Server.NpcManager.npcs[i3] != null && players[i] != null) {
						if (Server.NpcManager.npcs[i3].followPlayer == players[i].playerId) {
							Server.NpcManager.npcs[i3].StartKilling = 0;
							Server.NpcManager.npcs[i3].RandomWalk = true;
							Server.NpcManager.npcs[i3].followingPlayer = false;
							Server.NpcManager.npcs[i3].followPlayer = 0;
							Server.NpcManager.npcs[i3].IsUnderAttack = false;
							if (players[i].isInFightCaves() || players[i].inDT() || players[i].isInBarrows()) {
								players[i].RemoveNPC();
							}
						}
					} 
				}
				playerCount--;
				removePlayer(players[i]);
				players[i] = null;
			} else {
				if(!players[i].initialized) {
					players[i].initialize();
					players[i].initialized = true;
				} else {
					players[i].update();
				}
			}
		}
		if (updateRunning && !updateAnnounced) {
			updateAnnounced = true;
		}
		if (updateRunning && System.currentTimeMillis() - updateStartTime > (updateSeconds*1000)) {
			kickAllPlayers = true;
			Server.ShutDown = true;
		}
		for(int i = 0; i < maxPlayers; i++) {
			if(players[i] == null || !players[i].isActive) continue;

			players[i].clearUpdateFlags();
		}
	}

	public void updateNPC(Player plr, Stream str) {
		updateBlock.currentOffset = 0;
		
		str.createFrameVarSizeWord(65);
		str.initBitAccess();
		
		str.writeBits(8, plr.npcListSize);
		int size = plr.npcListSize;
		plr.npcListSize = 0;
		for(int i = 0; i < size; i++) {
			if(plr.RebuildNPCList == false && plr.withinDistance(plr.npcList[i]) == true) {
				plr.npcList[i].updateNPCMovement(str);
				plr.npcList[i].appendNPCUpdateBlock(updateBlock);
				plr.npcList[plr.npcListSize++] = plr.npcList[i];
			} else {
				int id = plr.npcList[i].npcId;
				plr.npcInListBitmap[id>>3] &= ~(1 << (id&7));
				str.writeBits(1, 1);
				str.writeBits(2, 3);
			}
		}
		for(int i = 0; i < NPCManager.maxNPCs; i++) {
			if(Server.NpcManager.npcs[i] != null) {
				int id = Server.NpcManager.npcs[i].npcId;
				if (plr.RebuildNPCList == false && (plr.npcInListBitmap[id>>3]&(1 << (id&7))) != 0) {
					//
				} else if (plr.withinDistance(Server.NpcManager.npcs[i]) == false) {
					//
				} else {
					plr.addNewNPC(Server.NpcManager.npcs[i], str, updateBlock);
				}
			}
		}
		
		plr.RebuildNPCList = false;

		if(updateBlock.currentOffset > 0) {
			str.writeBits(14, 16383);
			str.finishBitAccess();

			str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
	}

	private Stream updateBlock = new Stream(new byte[10000]);
	public void updatePlayer(Player plr, Stream str) {
		updateBlock.currentOffset = 0;
		
		if (updateRunning && !updateAnnounced) {
			str.createFrame(114);
			str.writeWordBigEndian(updateSeconds*50/30);
		}

		plr.updateThisPlayerMovement(str);
		boolean saveChatTextUpdate = plr.chatTextUpdateRequired;
		plr.chatTextUpdateRequired = false;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.chatTextUpdateRequired = saveChatTextUpdate;

		str.writeBits(8, plr.playerListSize);
		int size = plr.playerListSize;

		plr.playerListSize = 0;
		for(int i = 0; i < size; i++) {
			if(!plr.playerList[i].didTeleport && !plr.didTeleport && plr.withinDistance(plr.playerList[i])) {
				plr.playerList[i].updatePlayerMovement(str);
				plr.playerList[i].appendPlayerUpdateBlock(updateBlock);
				plr.playerList[plr.playerListSize++] = plr.playerList[i];
			} else {
				int id = plr.playerList[i].playerId;
				plr.playerInListBitmap[id>>3] &= ~(1 << (id&7));
				str.writeBits(1, 1);
				str.writeBits(2, 3);
			}
		}

		for(int i = 0; i < maxPlayers; i++) {
			if(players[i] == null || players[i].isActive == false || players[i] == plr) {
				//
			} else {
				int id = players[i].playerId;
				if(!plr.didTeleport && (plr.playerInListBitmap[id>>3]&(1 << (id&7))) != 0) {
					//
				} else if(plr.withinDistance(players[i]) == false) {
					//
				} else {
					plr.addNewPlayer(players[i], str, updateBlock);
				}
			}
		}

		if(updateBlock.currentOffset > 0) {
			str.writeBits(11, 2047);
			str.finishBitAccess();

			str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
	}

	public int lastchatid = 1;

	private void removePlayer(Player plr) {
		if(plr.Privatechat != 2) { //PM System
			for(int i = 1; i < maxPlayers; i++) {
				if (players[i] == null || players[i].isActive == false) continue;
				players[i].pmupdate(plr.playerId, 0);
			}
		}
		saveGame(plr);
		plr.destruct();
	}

	public boolean saveGame(Player plr) {
		Client saving = (Client) plr;
		if (saving == null)
			return false;
		if (saving.LoggedOut)
			saving.printOut("Logged out.");
		saving.logout();
		return true;
	}
}

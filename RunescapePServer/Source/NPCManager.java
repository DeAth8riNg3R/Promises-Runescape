import java.io.*;

class NPCManager {
	public static int maxNPCs = 10000;
	public static int maxListedNPCs = 10000;
	public NPC npcs[] = new NPC[maxNPCs];
	public NPCList NpcList[] = new NPCList[maxListedNPCs];
	public int SpawnedBy = -1;
	public int[] dropCount = new int[3851];
	public double[][] drops = new double[3851][45];

	NPCManager() {
		for (int i = 0; i < maxNPCs; i++) {
			npcs[i] = null;
		}
		for (int i = 0; i < maxListedNPCs; i++) {
			NpcList[i] = null;
		}
		loadNPCList("../Config/npc.cfg");
		loadNPCDrops("../Config/npcdrops.cfg");
		loadAutoSpawn("../Config/autospawn.cfg");
	}

	public void gfxAll(int id, int Y, int X) {
		for (Player p : Server.PlayerManager.players) {

		if (p != null) {
			Client person = (Client) p;

			if ((person.playerName != null || person.playerName != "null")) {
				if (person.distanceToPoint(X, Y) <= 60) {
					person.stillgfx2(id, Y, X);
					}
				}
			}
		}
	}
							
	public void newNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns) {
		int slot = -1;

		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if (slot == -1) {
			return;
		}
		if (HP <= 0) { 
			HP = 3000;
		}
		NPC newNPC = new NPC(slot, npcType);

		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int) Math.floor((HP / 100));
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 1;
		}
		newNPC.heightLevel = heightLevel;
		newNPC.Respawns = Respawns;
		npcs[slot] = newNPC;
	}

	public void newSummonedNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns, int summonedBy) {
		int slot = -1;

		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if (slot == -1) {
			return;
		}
		if (HP <= 0) {
			HP = 3000;
		}
		NPC newNPC = new NPC(slot, npcType);

		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int) Math.floor((HP / 100));
		if (npcType >= 2025 && npcType <= 2030) {
			newNPC.Barrows = true;
		}
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 10;
		}
		newNPC.heightLevel = heightLevel;
		newNPC.Respawns = Respawns;
		newNPC.followPlayer = summonedBy;
		newNPC.followingPlayer = true;
		npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		int slot = -1;

		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}

		if (slot == -1) {
			return;
		}

		NPCList newNPCList = new NPCList(npcType);

		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}

	public int GetMove(int Place1, int Place2) {
		if ((Place1 - Place2) == 0) {
			return 0;
		} else if ((Place1 - Place2) < 0) {
			return 1;
		} else if ((Place1 - Place2) > 0) {
			return -1;
		}
		return 0;
	}

	public void FollowPlayer(int NPCID) {
		int follow = npcs[NPCID].followPlayer;
		int playerX = Server.PlayerManager.players[follow].absX;
		int playerY = Server.PlayerManager.players[follow].absY;

		npcs[NPCID].RandomWalk = false;
		npcs[NPCID].followingPlayer = true;
		if (Server.PlayerManager.players[follow] != null) {
			if (playerY < npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY + 1);
			} else if (playerY > npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY - 1);
			} else if (playerX < npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX + 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
			} else if (playerX > npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX - 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
			}
			npcs[NPCID].getNextNPCMovement();
			npcs[NPCID].updateRequired = true;
		}
	}

	public void FollowPlayerCB(int NPCID, int playerID) {
		int playerX = Server.PlayerManager.players[playerID].absX;
		int playerY = Server.PlayerManager.players[playerID].absY;
		npcs[NPCID].RandomWalk = false;
		npcs[NPCID].faceplayer(playerID);
		if(Server.PlayerManager.players[playerID] != null)
		{
			if (playerY < npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY + 1);
			} else if (playerY > npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY - 1);
			} else if (playerX < npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX + 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
			} else if (playerX > npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX - 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
			}
			npcs[NPCID].getNextNPCMovement();
			npcs[NPCID].updateRequired = true;
		}
	}

	public void FacePlayerCB(int NPCID, int playerID) {
		int Player = npcs[NPCID].StartKilling;
		int EnemyX = Server.PlayerManager.players[Player].absX;
		int EnemyY = Server.PlayerManager.players[Player].absY;

		if(Server.PlayerManager.players[playerID] != null) {
			npcs[NPCID].RandomWalk = false;
			npcs[NPCID].faceplayer(playerID);
			npcs[NPCID].updateRequired = true;
			npcs[NPCID].viewX = EnemyX;
			npcs[NPCID].viewY = EnemyY;
			npcs[NPCID].faceToUpdateRequired = true;
		}
	}

	public boolean IsInWorldMap(int coordX, int coordY) {
		boolean a = true;
		if (a)
			return a;
		for (int i = 0; i < worldmap[0].length; i++) {
			// if (worldmap[0][i] == coordX && worldmap[1][i] == coordY) {
			return true;
			// }
		}
		return false;
	}

	public boolean IsInWorldMap2(int coordX, int coordY) {
		boolean a = true;
		if (a)
			return a;
		for (int i = 0; i < worldmap2[0].length; i++) {
			if ((worldmap2[0][i] == coordX) && (worldmap2[1][i] == coordY)) {
				return true;
			}
		}
		return false;
	}

	public boolean IsInRange(int NPCID, int MoveX, int MoveY) {
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if ((NewMoveX <= npcs[NPCID].moverangeX1) && (NewMoveX >= npcs[NPCID].moverangeX2) && (NewMoveY <= npcs[NPCID].moverangeY1) && (NewMoveY >= npcs[NPCID].moverangeY2)) {
			if (((npcs[NPCID].walkingType == 1) && (IsInWorldMap(NewMoveX, NewMoveY) == true)) || ((npcs[NPCID].walkingType == 2) && (IsInWorldMap2(NewMoveX, NewMoveY) == false))) {
				if (MoveX == MoveY) {
					if (((IsInWorldMap(NewMoveX, npcs[NPCID].absY) == true) && (IsInWorldMap(npcs[NPCID].absX, NewMoveY) == true)) || ((IsInWorldMap2(NewMoveX, npcs[NPCID].absY) == false) && (IsInWorldMap2(npcs[NPCID].absX, NewMoveY) == false))) {
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	public void PoisonNPC(int NPCID) {
		npcs[NPCID].Poison = 1;
		npcs[NPCID].PoisonClear = 0;
		npcs[NPCID].PoisonDelay = 40;
	}

	public void Poison(int NPCID) {
		if (npcs[NPCID].PoisonDelay < 1 && npcs[NPCID].Poison == 1 && !npcs[NPCID].IsDead) {
			int hitDiff = 3 + Misc.random(3);

			npcs[NPCID].poisondmg = true;
			Server.NpcManager.npcs[NPCID].hitDiff = hitDiff;
			Server.NpcManager.npcs[NPCID].updateRequired = true;
			Server.NpcManager.npcs[NPCID].hitUpdateRequired = true;
			npcs[NPCID].PoisonClear++;
			npcs[NPCID].PoisonDelay = 40;
		}
	}

	public void process() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				continue;
			}
			npcs[i].clearUpdateFlags();
		}
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}
				Poison(i);
				npcs[i].PoisonDelay--;
				if (!npcs[i].IsDead) {
					if (npcs[i].npcType == 1268 || npcs[i].npcType == 1266) {
						for (int j = 1; j < Server.PlayerManager.maxPlayers; j++) {
							if (Server.PlayerManager.players[j] != null) {
								if (GoodDistance(npcs[i].absX, npcs[i].absY, Server.PlayerManager.players[j].absX, Server.PlayerManager.players[j].absY, 2) == true && npcs[i].IsClose == false) {
									npcs[i].actionTimer = 2;
									npcs[i].IsClose = true;
								}
							}
						}
						if (npcs[i].actionTimer == 0 && npcs[i].IsClose) {
							for (int j = 1; j < Server.PlayerManager.maxPlayers; j++) {
								if (Server.PlayerManager.players[j] != null) {
									Server.PlayerManager.players[j].RebuildNPCList = true;
								}
							}
							if (npcs[i].Respawns) {
								int old1 = (npcs[i].npcType - 1);
								int old2 = npcs[i].makeX;
								int old3 = npcs[i].makeY;
								int old4 = npcs[i].heightLevel;
								int old5 = npcs[i].moverangeX1;
								int old6 = npcs[i].moverangeY1;
								int old7 = npcs[i].moverangeX2;
								int old8 = npcs[i].moverangeY2;
								int old9 = npcs[i].walkingType;
								int old10 = npcs[i].MaxHP;
								npcs[i] = null;
								newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
							}
						}
					} else if (npcs[i].RandomWalk && Misc.random2(10) == 1 && npcs[i].moverangeX1 > 0 && npcs[i].moverangeY1 > 0 && npcs[i].moverangeX2 > 0 && npcs[i].moverangeY2 > 0) { // Move NPC
						int MoveX = Misc.random(1);
						int MoveY = Misc.random(1);
						int Rnd = Misc.random2(4);

						if (Rnd == 1) {
							MoveX = -(MoveX);
							MoveY = -(MoveY);
						} else if (Rnd == 2) {
							MoveX = -(MoveX);
						} else if (Rnd == 3) {
							MoveY = -(MoveY);
						}
						if (IsInRange(i, MoveX, MoveY) == true) {
							npcs[i].moveX = MoveX;
							npcs[i].moveY = MoveY;
						}
						npcs[i].updateRequired = true;
					} else if (!npcs[i].RandomWalk && npcs[i].IsUnderAttack) {
						AttackPlayer(i);
					} else if (npcs[i].followingPlayer && npcs[i].followPlayer > 0 && Server.PlayerManager.players[npcs[i].followPlayer] != null) {
						if (Server.PlayerManager.players[npcs[i].followPlayer].AttackingOn > 0) {
							int follow = npcs[i].followPlayer;
							npcs[i].StartKilling = Server.PlayerManager.players[follow].AttackingOn;
							npcs[i].RandomWalk = false; //here
							npcs[i].IsUnderAttack = true;
							if (npcs[i].StartKilling > 0) {
								AttackPlayer(i);
							}
						} else {
							FollowPlayer(i);
						}
					} else if (npcs[i].followingPlayer && npcs[i].followPlayer > 0 && Server.PlayerManager.players[npcs[i].followPlayer] != null) {
						if (Server.PlayerManager.players[npcs[i].followPlayer].attacknpc > 0) {
							int follow = npcs[i].followPlayer;

							npcs[i].attacknpc = Server.PlayerManager.players[follow].attacknpc;
							npcs[i].IsUnderAttackNpc = true;
							npcs[npcs[i].attacknpc].IsUnderAttackNpc = true;
						} else {
							FollowPlayer(i);
						}
					}
					if (npcs[i].RandomWalk) {
						npcs[i].getNextNPCMovement();
					}
					for (Player p : Server.PlayerManager.players) {
						Client person = (Client) p;

						if (p != null && person != null) {
							if (p != null && person != null) { 
								if (person.distanceToPoint(npcs[i].absX, npcs[i].absY) <= 20 && p.heightLevel == npcs[i].heightLevel && person.playerLevel[3] >= 1) {
									if (npcs[i].npcType == 1158 || npcs[i].npcType == 1160 || npcs[i].npcType == 2597 || npcs[i].npcType == 2740 || npcs[i].npcType == 2591 || npcs[i].npcType == 2598 || npcs[i].npcType == 2610 || npcs[i].npcType == 2627 || npcs[i].npcType == 2631 || npcs[i].npcType == 2605 || npcs[i].npcType == 1913 || npcs[i].npcType == 1914 || npcs[i].npcType == 1974 || npcs[i].npcType == 1977 || npcs[i].npcType == 2605 || npcs[i].npcType == 2631 || npcs[i].npcType == 2632 || npcs[i].npcType == 3592 || npcs[i].npcType == 2627 || npcs[i].npcType == 2628 || npcs[i].npcType == 2734 || npcs[i].npcType == 2735 || npcs[i].npcType == 2629
										|| npcs[i].npcType == 2630 || npcs[i].npcType == 2736 || npcs[i].npcType == 2737 || npcs[i].npcType == 2738 || npcs[i].npcType == 2739 || npcs[i].npcType == 2740 || npcs[i].npcType == 2741 || npcs[i].npcType == 2742 || npcs[i].npcType == 2743 || npcs[i].npcType == 2744 || npcs[i].npcType == 50 || npcs[i].npcType == 2025 || npcs[i].npcType == 2026 || npcs[i].npcType == 2027 || npcs[i].npcType == 2028 || npcs[i].npcType == 2029 || npcs[i].npcType == 2030 || npcs[i].npcType == 117 || npcs[i].npcType == 2456 || npcs[i].npcType == 2881 || npcs[i].npcType == 2882 || npcs[i].npcType == 134 || npcs[i].npcType == 1007 || npcs[i].npcType == 3200 || npcs[i].npcType == 2883 || npcs[i].npcType == 2746
										|| npcs[i].npcType == 2745 || npcs[i].npcType == 1975 || npcs[i].npcType == 1913 || npcs[i].npcType == 1914 || npcs[i].npcType == 1977) {
										npcs[i].StartKilling = person.playerId;
										npcs[i].RandomWalk = false;
										npcs[i].IsUnderAttack = true;
										int[] NonFollow = { 2739, 2740, 2743, 2744, 2745, 2881, 2882 };
										for (int k = 0; k < NonFollow.length; k++) {
											if (npcs[i].npcType != NonFollow[k]) {
												npcs[i].followingPlayer = true;
												npcs[i].followPlayer = person.playerId;
											}
										}
									} else if (person.distanceToPoint(npcs[i].absX, npcs[i].absY) >= 21 || person.heightLevel != npcs[i].heightLevel || person.playerLevel[3] <= 0) {
										if (npcs[i].npcType != 2882 && npcs[i].npcType != 2881) {
											npcs[i].RandomWalk = true;
										}
										npcs[i].followingPlayer = false;
										npcs[i].followPlayer = 0;
									}
								} 
							}
						}
					}
					if (npcs[i].Stunned) {
						npcs[i].updateRequired = true;
						npcs[i].textUpdateRequired = true;
						npcs[i].textUpdate = "What do you think you're doing?";
						npcs[i].Stunned = false;
					}
					if (npcs[i].Barrows) {
						npcs[i].updateRequired = true;
						npcs[i].textUpdateRequired = true;
						npcs[i].textUpdate = "You dare disturb my rest!";
						npcs[i].Barrows = false;
					}
					if (npcs[i].npcType == 2617 && Misc.random(40) < 3) {
						npcs[i].updateRequired = true;
						npcs[i].textUpdateRequired = true;
						npcs[i].textUpdate = ""+PlayerManager.WaitingRoom/2+" seconds left until the next round starts!";
					}
				} else if (npcs[i].IsDead) {
					if (npcs[i].actionTimer == 0 && !npcs[i].DeadApply && !npcs[i].NeedRespawn) {
						switch (npcs[i].npcType) {

							case 81:
							case 397:
							case 1766:
							case 1767:
							case 1768:
								npcs[i].animNumber = 0x03E;
								break;

							case 41:
								npcs[i].animNumber = 0x039;
								break;

							case 87:
								npcs[i].animNumber = 0x08D;
								break;

							case 75:
								npcs[i].animNumber = 466;
								break;

							case 1770:
							case 1771:
							case 1772:
							case 1773:
							case 1774:
								npcs[i].animNumber = 313;
								break;

							case 1160:
								npcs[i].animNumber = 1182;
								break;

							case 83:
								npcs[i].animNumber = 804;
								break;

							case 3200:
								npcs[i].animNumber = 3147;
								break;

							case 1605:
								npcs[i].animNumber = 1508;
								break;

							case 50:
							case 49:
							case 55:
							case 941:
							case 1590:
							case 1591:
							case 1592:
							case 53:
							case 54:
								npcs[i].animNumber = 92;
								break;

							case 2627:
							case 2628:
							case 2734:
							case 2735:
								npcs[i].animNumber = 2620;
								break;

							case 2629:
							case 2630:
							case 2736:
							case 2737:
							case 2738:
								npcs[i].animNumber = 2627;
								break;

							case 2631:
							case 2632:
							case 2739:
							case 2740:
							case 3592:
								npcs[i].animNumber = 2630;
								break;

							case 2741:
							case 2742:
								npcs[i].animNumber = 2638;
								break;

							case 2743:
							case 2744:
								npcs[i].animNumber = 2646;
								break;

							case 117:
								npcs[i].animNumber = 999;
								break;

							case 2881:
							case 2882:
							case 2883:
								npcs[i].animNumber = 2856;
								break;

							case 2456:
								npcs[i].animNumber = 1342;
								break;

							case 86:
								npcs[i].animNumber = 141;
								break;

							case 2783:
								npcs[i].animNumber = 2732;
								break;

							case 134:
								npcs[i].animNumber = 146;
								break;

							case 1615:
								npcs[i].animNumber = 1538;
								break;

							case 1610:
								npcs[i].animNumber = 1518;
								break;

							case 1637:
								npcs[i].animNumber = 1587;
								break;

							case 1619:
								npcs[i].animNumber = 1553;
								break;

							case 1616:
								npcs[i].animNumber = 1548;
								break;

							case 1624:
								npcs[i].animNumber = 1558;
								break;

							case 1653:
								npcs[i].animNumber = 1590;
								break;

							case 1613:
								npcs[i].animNumber = 1530;
								break;

							case 52:
								npcs[i].animNumber = 28;
								break;

							case 110:
								npcs[i].animNumber = 131;
								break;

							case 82:
								npcs[i].animNumber = 68;
								break;

							case 78:
								npcs[i].animNumber = 36;
								break;

							case 2746:
								npcs[i].animNumber = 2638;
								break;

							case 1265:
								npcs[i].animNumber = 1314;
								break;

							case 2745:
								npcs[i].animNumber = 2654;
								break;

							default:
								npcs[i].animNumber = 0x900;
								break;
						}
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						npcs[i].DeadApply = true;
						npcs[i].actionTimer = 10;
						if (npcs[i].followingPlayer && Server.PlayerManager.players[npcs[i].followPlayer] != null) {
							Server.PlayerManager.players[npcs[i].followPlayer].summonedNPCS--;
						}
					} else if (npcs[i].actionTimer == 0 && npcs[i].DeadApply && !npcs[i].NeedRespawn && npcs[i] != null) {
						Client plr = (Client) Server.PlayerManager.players[npcs[i].getKiller()];
							if ((plr != null) && !plr.disconnected) {
								if (npcs[i].npcType == 117) {
									plr.SarakillCount++;
									plr.Dungeon();
								}
								if (npcs[i].npcType == 1975 || npcs[i].npcType == 1913 || npcs[i].npcType == 1914 || npcs[i].npcType == 1977) {
									plr.teleblock = false;
									plr.Tele("Malak");
									plr.q1++;
									plr.Summoned = false;
								}
								if (npcs[i].npcType == 2746) {
									plr.healersCount++;
								}
								if (npcs[i].npcType == 1264) {
									plr.SarakillCount++;
									plr.Dungeon();
								}
								if (npcs[i].npcType == 1007) {
									plr.zammymage = 2;
								}
								if (npcs[i].npcType == 2025 || npcs[i].npcType == 2026 || npcs[i].npcType == 2027 || npcs[i].npcType == 2028 || npcs[i].npcType == 2029 || npcs[i].npcType == 2030) {
									plr.BarrowskillCount++;
									plr.sendString("Kill Count: "+plr.BarrowskillCount, 4536);
									plr.Summoned = false;
								}
								if (npcs[i].npcType == 2025) {
									plr.Ahrim = true;
								}
								if (npcs[i].npcType == 2026) {
									plr.Dharok = true;
								}
								if (npcs[i].npcType == 2027) {
									plr.Guthan = true;
								}
								if (npcs[i].npcType == 2028) {
									plr.Karil = true;
								}
								if (npcs[i].npcType == 2029) {
									plr.Torag = true;
								}
								if (npcs[i].npcType == 2030) {
									plr.Verac = true;
								}
								if (npcs[i].npcType == 256) {
									plr.Barbpoints++;
								}
								if (npcs[i].npcType == 2735 && plr.isInFightCaves()) {
									plr.healersCount = 1;
									plr.Summon(2736, 5092, 2408, plr.heightLevel);
								}
								if (npcs[i].npcType == 1653) {
									plr.addSkillXP((250), 18);
								}
								if (npcs[i].npcType == 1616) {
									plr.addSkillXP((400), 18);
								}
								if (npcs[i].npcType == 1637) {
									plr.addSkillXP((625), 18);
								}
								if (npcs[i].npcType == 1619) {
									plr.addSkillXP((850), 18);
								}
								if (npcs[i].npcType == 1624) {
									plr.addSkillXP((1045), 18);
								}
								if (npcs[i].npcType == 1610) {
									plr.addSkillXP((1350), 18);
								}
								if (npcs[i].npcType == 1613) {
									plr.addSkillXP((1675), 18);
								}
								if (npcs[i].npcType == 1615) {
									plr.addSkillXP((2425), 18);
								}
								if (npcs[i].npcType == 2783) {
									plr.addSkillXP((3200), 18);
								}
								if (npcs[i].npcType == 2736 && plr.isInFightCaves()) {
									plr.healersCount = 2;
									plr.Summon(2738, 5095, 2408, plr.heightLevel);
									plr.Summon(2738, 5097, 2400, plr.heightLevel);
								}
								if (npcs[i].npcType == 2738 && plr.healersCount == 3 && plr.isInFightCaves()) {
									plr.healersCount = 4;
									plr.Summon(2739, 5088, 2407, plr.heightLevel);
									plr.Summon(2740, 5097, 2397, plr.heightLevel);
								}
								if (npcs[i].npcType == 2738 && plr.healersCount == 2 && plr.isInFightCaves()) {
									plr.healersCount = 3;
								}
								if (npcs[i].npcType == 2739 && plr.healersCount == 5 && plr.isInFightCaves()) {
									plr.healersCount = 6;
									plr.Summon(2741, 5092, 2403, plr.heightLevel);
								}
								if (npcs[i].npcType == 2739 && plr.healersCount == 4 && plr.isInFightCaves()) {
									plr.healersCount = 5;
								}
								if (npcs[i].npcType == 2740 && plr.healersCount == 5 && plr.isInFightCaves()) {
									plr.healersCount = 6;
									plr.Summon(2741, 5092, 2403, plr.heightLevel);
								}
								if (npcs[i].npcType == 2740 && plr.healersCount == 4 && plr.isInFightCaves()) {
									plr.healersCount = 5;
								}
								if (npcs[i].npcType == 2741 && plr.healersCount == 6 && plr.isInFightCaves()) {
									plr.healersCount = 7;
									plr.Summon(2743, 5085, 2398, plr.heightLevel);
									plr.Summon(2744, 5096, 2405, plr.heightLevel);
								}
								if (npcs[i].npcType == 2743 && plr.healersCount == 8 && plr.isInFightCaves()) {
									plr.healersCount = 9;
									plr.Summon(2745, 5088, 2402, plr.heightLevel);
									plr.NPC("Look out,","Here comes the TzTok-Jad!",2617);
								}
								if (npcs[i].npcType == 2743 && plr.healersCount == 7 && plr.isInFightCaves()) {
									plr.healersCount = 8;
								}
								if (npcs[i].npcType == 2744 && plr.healersCount == 8 && plr.isInFightCaves()) {
									plr.healersCount = 9;
									plr.Summon(2745, 5088, 2402, plr.heightLevel);
									plr.NPC("Look out,", "Here comes the TzTok-Jad!",2617);
								}
								if (npcs[i].npcType == 2744 && plr.healersCount == 7 && plr.isInFightCaves()) {
									plr.healersCount = 8;
								}
								if (npcs[i].npcType == 2745 && plr.isInFightCaves()) {
									plr.JadKilled++;
									plr.frame61(0);
									plr.teleblock = false;
									plr.Teleport(2441, 5170, 0);
									plr.addItem(2773, 1);
									plr.addItem(6529, 8036);
									plr.healers = false;
									plr.healersCount = 0;
									plr.anim(862);
									plr.Save();
									plr.NPC("You have defeated the TzTok-Jad!","Please accept this gift as a reward.",2617);
								}
							}
							MonsterDropItems(npcs[i].npcType, npcs[i]);
							npcs[i].NeedRespawn = true;
							npcs[i].actionTimer = 60;
							npcs[i].absX = npcs[i].makeX;
							npcs[i].absY = npcs[i].makeY;
							npcs[i].animNumber = 0x328;
							npcs[i].HP = npcs[i].MaxHP;
							npcs[i].updateRequired = true;
							npcs[i].animUpdateRequired = true;
						} else if (npcs[i].actionTimer == 0 && npcs[i].NeedRespawn == true) {
							for (int j = 1; j < Server.PlayerManager.maxPlayers; j++) {
								if (Server.PlayerManager.players[j] != null) {
									Server.PlayerManager.players[j].RebuildNPCList = true;
								}
							}
						if (npcs[i].Respawns) {
							int old1 = npcs[i].npcType;

							if (old1 == 1267 || old1 == 1265) {
								old1++;
							}
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].moverangeX1;
							int old6 = npcs[i].moverangeY1;
							int old7 = npcs[i].moverangeX2;
							int old8 = npcs[i].moverangeY2;
							int old9 = npcs[i].walkingType;
							int old10 = npcs[i].MaxHP;

							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
						}
					}
				}
			}
		}
	}

	public static int JadAttack[] = { 1, 2 };

	public static int randomJadAttack() {
		return JadAttack[(int) (Math.random() * JadAttack.length)];
	}

	public static boolean IsDropping;

	public void MonsterDropItems(int NPCID, NPC npc) {
		try {
			int totalDrops = dropCount[NPCID] / 3;
			if (totalDrops > 0) {
				for (int i = 0; i < dropCount[NPCID]; i += 3) {
					double roll = Math.random() * 100;
					Client p = (Client) Server.PlayerManager.players[npc.getKiller()];
					if (p != null) {
					}
					if (roll <= drops[NPCID][i + 2]) {
						if (p != null) {
						}
						if ((drops[NPCID] != null) && (npc != null)) {
							Item.addItem((int) drops[NPCID][i], npc.absX, npc.absY, (int) drops[NPCID][i + 1], npc.getKiller(), false);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int GetNpcKiller(int NPCID) {
		int Killer = 0;
		int Count = 0;

		for (int i = 1; i < Server.PlayerManager.maxPlayers; i++) {
			if (Killer == 0) {
				Killer = i;
				Count = 1;
			} else {
				if (npcs[NPCID].Killing[i] > npcs[NPCID].Killing[Killer]) {
					Killer = i;
					Count = 1;
				} else if (npcs[NPCID].Killing[i] == npcs[NPCID].Killing[Killer]) {
					Count++;
				}
			}
		}
		if (Count > 1 && npcs[NPCID].Killing[npcs[NPCID].StartKilling] == npcs[NPCID].Killing[Killer]) {
			Killer = npcs[NPCID].StartKilling;
		}
		return Killer;
	}

	public static int worldmap[][] = {
			{
			/* 01 */3252, 3453, 3252, 3453, 3252, 3253, 3254, 3252, 3253,
					3254, 3255, 3252, 3253, 3252, 3253,
					/* 02 */3248, 3249, 3250, 3251, 3252, 3253, 3254, 3248,
					3249, 3250, 3251, 3252, 3253, 3254, 3248, 3249, 3250, 3251,
					3252, 3254, 3248, 3249, 3250, 3251, 3252, 3253, 3254, 3248,
					3249, 3250, 3251, 3252, 3253, 3254, 3248, 3249, 3250, 3251,
					3252, 3253, 3254, 3248, 3249, 3250, 3251, 3252, 3254, 3248,
					3249, 3250, 3251, 3252, 3253, 3254, 3248, 3249, 3250, 3251,
					3252, 3253, 3254, 3248, 3249, 3250, 3251, 3252, 3253, 3254,
					3248, 3249, 3250, 3251, 3252, 3253, 3254, 3248, 3249, 3250,
					3251, 3252, 3253, 3254, 3248, 3249, 3250, 3251, 3252, 3253,
					3254,
					/* 03 */3235, 3234, 3233, 3232, 3231, 3230, 3235, 3230,
					3235, 3234, 3233, 3232, 3231, 3230, 3234, 3232, 3231, 3234,
					3233, 3232, 3231, 3234, 3233, 3232, 3233, 3231,
					/* 04 */3231, 3230, 3229, 3232, 3231, 3230, 3229, 3229,
					3228, 3227, 3229, 3227, 3232, 3231, 3230, 3229, 3228, 3227,
					3232, 3231, 3230, 3229, 3228, 3227, 3226, 3225, 3232, 3231,
					3230, 3229, 3228, 3227, 3225, 3232, 3231, 3230, 3229, 3228,
					3227, 3225, 3232, 3229, 3228, 3227, 3226, 3232, 3231, 3230,
					3229,
					/* 05 */3217, 3216, 3215, 3214, 3219, 3218, 3217, 3216,
					3219, 3218, 3217, 3219, 3217, 3216, 3215, 3219, 3218, 3217,
					3216, 3215, 3214, 3220, 3219, 3217, 3216, 3215, 3214, 3219,
					3217, 3216, 3215, 3214, 3219, 3217, 3216, 3215, 3214, 3218,
					3217,
					/* 06 */3207, 3206, 3205, 3208, 3207, 3206, 3203, 3207,
					3206, 3205, 3204, 3203, 3207, 3206, 3205, 3204, 3203, 3202,
					3208, 3207, 3206, 3205, 3208, 3207, 3206, 3207,
					/* 07 */3206, 3204, 3203, 3202, 3209, 3208, 3207, 3205,
					3203, 3208, 3207, 3206, 3205, 3203, 3208, 3207, 3206, 3205,
					3204, 3203, 3202, 3208, 3207, 3206, 3205, 3203, 3207, 3206,
					3203, 3206, 3203, 3206, 3205, 3205, 3205,
					/* 08 */3268, 3268, 3268, 3268, 3268, 3269, 3269, 3269,
					3269, 3269, 3270, 3270, 3270, 3270, 3270, 3271, 3271, 3271,
					3271, 3271, 3272, 3272, 3272, 3272, 3272, 3273, 3273, 3273,
					3273, 3273, 3274, 3274, 3274, 3274, 3274, 3275, 3275, 3275,
					3276, 3276, 3276, 3276, 3273, 3274, 3275, 3276, 3273, 3274,
					3275, 3273,
					/* 09 */2958, 2957, 2959, 2958, 2957, 2959, 2958, 2957,
					2959, 2958, 2957, 2956, 2955, 2954, 2953, 2960, 2959, 2956,
					2955, 2953, 2960, 2959, 2957, 2956, 2953,
					/* 10 */2979, 2977, 2976, 2975, 2974, 2973, 2972, 2979,
					2978, 2977, 2972, 2972, 2974, 2973, 2972,
					/* 11 */2952, 2950, 2949, 2948, 2952, 2951, 2950, 2949,
					2948, 2952, 2951, 2950, 2949, 2948, 2952, 2951, 2950, 2949,
					2948, 2952, 2952, 2951,
					/* 12 */2969, 2967, 2966, 2965, 2964, 2963, 2962, 2961,
					2960, 2959, 2958, 2969, 2968, 2967, 2966, 2965, 2964, 2963,
					2962, 2961, 2960, 2959, 2958, 2969, 2968, 2967, 2966, 2965,
					2964, 2963, 2962, 2961, 2960, 2959, 2958, 2969, 2968, 2967,
					2966, 2965, 2964, 2963, 2962, 2961, 2960, 2959, 2958, 2969,
					2968, 2967, 2966, 2965, 2964, 2963, 2962, 2961, 2960, 2959,
					2958, 2969, 2968, 2967, 2966, 2964, 2963, 2962, 2961, 2960,
					2959, 2958, 2969, 2968, 2967, 2966, 2965, 2964, 2963, 2962,
					2961, 2960, 2959, 2958, 2969, 2968, 2967, 2966, 2965, 2964,
					2963, 2962, 2961, 2960, 2959, 2958, 2969, 2968, 2967, 2966,
					2965, 2964, 2963, 2962, 2961, 2960, 2959, 2958,
					/* 13 */2968, 2967, 2966, 2965, 2964, 2963, 2967, 2966,
					2965, 2964, 2966, 2965, 2967, 2966, 2965, 2964, 2968, 2967,
					2966, 2965, 2964, 2963, 2968, 2967, 2966, 2965, 2964, 2963,
					2967, 2966, 2965, 2964, 2968, 2967, 2966, 2965, 2964, 2963,
					/* 14 */3076, 3074, 3076, 3075, 3074, 3077, 3076, 3075,
					3074, 3073, 3077, 3074, 3077, 3076, 3075, 3074,
					/* 15 */3204, 3204, 3203, 3202, 3201, 3204, 3203, 3202,
					3201, 3203, 3201, 3203, 3202, 3201, 3204, 3203, 3201, 3204,
					/* 16 */3315, 3316, 3313, 3314, 3315, 3317, 3318, 3314,
					3317, 3314, 3315, 3316, 3317, 3313, 3314, 3315, 3316, 3317,
					3318, 3314, 3315, 3316, 3317,
					/* 17 */3319, 3320, 3323, 3318, 3319, 3320, 3322, 3323,
					3318, 3319, 3320, 3321, 3322, 3323, 3319, 3320, 3321, 3322,
					3319, 3320, 3322, 3323, 3318, 3319, 3320, 3323, 3319, 3320,
					/* 18 */3315, 3316, 3312, 3313, 3314, 3315, 3316, 3312,
					3313, 3314, 3315, 3316, 3317, 3312, 3313, 3314, 3315, 3316,
					3317, 3318, 3312, 3313, 3314, 3316, 3317, 3312, 3313, 3314,
					3316, 3317, 3312, 3313, 3314, 3316, 3317, 3314, 3317, 3315,
					/* 19 */3314, 3315, 3316, 3318, 3315, 3316, 3317, 3318,
					3314, 3315, 3316, 3317, 3318, 3314, 3315, 3316, 3314, 3315,
					/* 20 */3287, 3288, 3289, 3285, 3286, 3287, 3288, 3289,
					3290, 3287, 3288, 3289, 3290, 3287, 3288, 3289, 3290, 3286,
					3287, 3288, 3287,
					/* 21 */3229, 3232, 3228, 3229, 3230, 3231, 3232, 3233,
					3228, 3230, 3231, 3232, 3233, 3228, 3230, 3231, 3232, 3232,
					/* 22 */3210, 3211, 3209, 3210, 3211, 3212, 3214, 3209,
					3211, 3212, 3213, 3214, 3209, 3211, 3212, 3213, 3209, 3210,
					3211, 3212, 3213, 3214, 3209, 3211, 3212, 3213, 3209, 3210,
					3211, 3212, 3213, 3209, 3211, 3213,
					/* 23 */3026, 3028, 3024, 3025, 3026, 3027, 3028, 3029,
					3025, 3026, 3027, 3028, 3029, 3030, 3024, 3025, 3028, 3029,
					3030, 3024, 3025, 3028, 3029, 3024, 3025, 3026, 3027, 3028,
					3029, 3028, 3029, 3030, 3025, 3026, 3027, 3028, 3029,
					/* 24 */3012, 3013, 3014, 3015, 3016, 3012, 3015, 3016,
					3012, 3015, 3016, 3011, 3012, 3013, 3014, 3015, 3012,
					/* 25 */3012, 3014, 3012, 3013, 3014, 3015, 3012, 3013,
					3014, 3015, 3012, 3013, 3015, 3012, 3013, 3014,
					/* 26 */3013, 3014, 3013, 3014, 3013, 3014, 3015, 3016,
					3012, 3013, 3014, 3015, 3016, 3017, 3012, 3013, 3014, 3015,
					3011, 3012, 3013, 3014, 3015, 3016, 3011, 3012, 3013, 3014,
					3015, 3016, 3011, 3016, 3011, 3013, 3014, 3015, 3016, 3013,
					3014, 3016,
					/* 27 */3012, 3014, 3012, 3013, 3014, 3015, 3016, 3012,
					3015, 3012, 3013, 3014, 3015, 3016, 3013, 3014, 3015, 3013,
					3014, 3013, 3013,
					/* 28 */2946, 2947, 2952, 2946, 2947, 2950, 2951, 2952,
					2946, 2948, 2949, 2950, 2951, 2946, 2948, 2949, 2950, 2951,
					2946, 2947, 2948, 2949, 2950, 2951, 2948, 2949, 2948, 2949,
					/* 29 */2955, 2958, 2959, 2954, 2955, 2956, 2957, 2958,
					2959, 2953, 2954, 2956, 2957, 2958, 2957, 2958, 2959,
					/* 30 */3236, 3236, 3237, 3238, 3239, 3237, 3238, 3239,
					3240, 3236, 3237, 3238, 3239, 3240, 3236, 3237, 3238, 3239,
					3237, 3238,/**/3245, 3246, 3243, 3244, 3245, 3246, 3243,
					3244, 3245, 3246, 3247, 3246, 3247,/**/3261, 3260, 3261,
					3262, 3260, 3261, 3263, 3260, 3263, 3260, 3263, 3260, 3263,
					3260, 3261, 3262, 3263, 3260, 3261, 3263,/**/3234, 3235,
					3238, 3233, 3234, 3235, 3236, 3237, 3238, 3235, 3233, 3234,
					3235, 3236, 3233, 3234, 3235, 3236, 3237, 3238,/**/3290,
					3291, 3292, 3293, 3294, 3297, 3298, 3299, 3290, 3291, 3292,
					3293, 3294, 3295, 3296, 3297, 3298, 3299, 3290, 3291, 3292,
					3293, 3294, 3295, 3296, 3297, 3298, 3299, 3290, 3293, 3294,
					3295, 3296, 3297, 3298, 3299, 3290, 3293, 3294, 3295, 3296,
					3297, 3298, 3299, 3290, 3291, 3292, 3293, 3294, 3295, 3296,
					3297, 3298, 3299, 3290, 3291, 3292, 3293, 3294, 3295, 3296,
					3297, 3298, 3299,
					/* 31 */2662, 2663, 2661, 2662, 2663, 2661, 2662, 2663,
					2661, 2662, 2663, 2662, 2663, 2664, 2665, 2666, 2665, 2666,/**/
					2664, 2665, 2666, 2664, 2665, 2666, 2664, 2665, 2666, 2664,
					2665, 2666, 2664, 2665, 2666,/**/2679, 2680, 2679, 2680,
					2676, 2677, 2678, 2679, 2680, 2676, 2677, 2678, 2679, 2680,
					2676, 2677, 2678, 2679, 2680, 2674, 2675, 2676, 2677, 2678,
					2679, 2680, 2675, 2676, 2677, 2678, 2679, 2680,/**/2667,
					2668, 2669, 2670, 2671, 2667, 2668, 2669, 2670, 2671, 2667,
					2668, 2669, 2670, 2671, 2667, 2668, 2669, 2670, 2671, 2667,
					2668, 2669, 2670, 2671, 2667, 2668, 2669, 2670, 2671, 2667,
					2668, 2669, 2670, 2671, 2667, 2668, 2669, 2670, 2671,/**/
					2681, 2682, 2683, 2684, 2685, 2681, 2682, 2683, 2684, 2685,
					2681, 2682, 2683, 2684, 2685, 2681, 2682, 2683, 2684, 2685,
					2681, 2682, 2683, 2684, 2685,/**/2675, 2676, 2677, 2678,
					2679, 2675, 2676, 2677, 2678, 2679, 2675, 2676, 2677, 2678,
					2679, 2676, 2677, 2678, 2679, 2677, 2678, 2679,/**/
					2672, 2673, 2674, 2675, 2672, 2673, 2674, 2675, 2672, 2673,
					2674, 2675, 2672, 2673, 2674, 2675, 2672, 2673, 2674, 2675,
					2672, 2673, 2674, 2675, 2672, 2673, 2674, 2675,/**/2674,
					2675, 2676, 2677, 2678, 2674, 2675, 2676, 2677, 2678, 2674,
					2675, 2676, 2677, 2678, 2674, 2675, 2676, 2677, 2678, 2674,
					2675, 2677, 2678,/**/2685, 2686, 2687, 2688, 2689, 2685,
					2686, 2687, 2688, 2689, 2685, 2686, 2687, 2688, 2689, 2685,
					2686, 2687, 2688, 2689, 2685, 2686, 2687, 2688, 2689,/**/
					2668, 2669, 2670, 2671, 2672, 2668, 2669, 2670, 2671, 2672,
					2668, 2669, 2670, 2671, 2672, 2668, 2669, 2670, 2671, 2672,
					2668, 2669, 2670, 2671, 2672,/**/2679, 2680, 2681, 2682,
					2683, 2679, 2680, 2681, 2682, 2683, 2679, 2680, 2681, 2682,
					2683, 2679, 2680, 2681, 2682, 2683, 2679, 2680, 2681, 2682,
					2683,/**/2673, 2674, 2675, 2673, 2674, 2675, 2676, 2677,
					2673, 2674, 2675, 2676, 2677, 2673, 2674, 2675, 2676, 2677,
					2673, 2674, 2675, 2676, 2677,/**/2669, 2670, 2671, 2672,
					2669, 2670, 2671, 2672, 2673, 2669, 2670, 2671, 2672, 2673,
					2669, 2670, 2671, 2672, 2673, 2669, 2670, 2671, 2672, 2673,/**/
					2680, 2681, 2682, 2679, 2680, 2681, 2682, 2678, 2679, 2680,
					2681, 2682, 2678, 2679, 2680, 2681, 2682, 2678, 2679, 2680,
					2681, 2682,
					/* 32 */3228, 3229, 3226, 3227, 3228, 3225, 3226, 3228,
					3229, 3226, 3227, 3228, 3229, 3230, 3225, 3226, 3227, 3228,
					3229, 3230, 3225, 3229, 3225, 3226, 3227, 3228, 3229, 3226,/**/
					3232, 3233, 3234, 3235, 3236, 3237, 3232, 3233, 3234, 3235,
					3236, 3231, 3232, 3233, 3234, 3235, 3236, 3227, 3228, 3229,
					3231, 3232, 3233, 3234, 3235, 3236, 3225, 3226, 3227, 3228,
					3229, 3230, 3231, 3233, 3234, 3235, 3236, 3225, 3226, 3227,
					3228, 3229, 3230, 3231, 3232, 3233, 3234, 3235, 3236, 3225,
					3228, 3229, 3230, 3231, 3232, 3235, 3236, 3237, 3225, 3227,
					3228, 3229, 3230, 3231, 3232, 3233, 3235, 3236, 3237, 3225,
					3227, 3228, 3229, 3230, 3231, 3232, 3233, 3235, 3236, 3231,
					3235, },
			{
			/* 01 */3404, 3404, 3403, 3403, 4302, 4302, 4302, 3401, 3401,
					3401, 3401, 3400, 3400, 3399, 3399,
					/* 02 */3398, 3398, 3398, 3398, 3398, 3398, 3398, 3397,
					3397, 3397, 3397, 3397, 3397, 3397, 3396, 3396, 3396, 3396,
					3396, 3396, 3395, 3395, 3395, 3395, 3395, 3395, 3395, 3394,
					3394, 3394, 3394, 3394, 3394, 3394, 3393, 3393, 3393, 3393,
					3393, 3393, 3393, 3392, 3392, 3392, 3392, 3392, 3392, 3391,
					3391, 3391, 3391, 3391, 3391, 3391, 3390, 3390, 3390, 3390,
					3390, 3390, 3390, 3389, 3389, 3389, 3389, 3389, 3389, 3389,
					3388, 3388, 3388, 3388, 3388, 3388, 3388, 3387, 3387, 3387,
					3387, 3387, 3387, 3387, 3386, 3386, 3386, 3386, 3386, 3386,
					3386,
					/* 03 */3421, 3421, 3421, 3421, 3421, 3421, 3422, 3422,
					3423, 3423, 3423, 3423, 3423, 3423, 3424, 3424, 3424, 3425,
					3425, 3425, 3425, 3426, 3426, 3426, 3427, 3427,
					/* 04 */3433, 3433, 3433, 3434, 3434, 3434, 3434, 3435,
					3435, 3435, 3436, 3436, 3437, 3437, 3437, 3437, 3437, 3437,
					3438, 3438, 3438, 3438, 3438, 3438, 3438, 3438, 3439, 3439,
					3439, 3439, 3439, 3439, 3439, 3440, 3440, 3440, 3440, 3440,
					3440, 3440, 3441, 3441, 3441, 3441, 3441, 3442, 3442, 3442,
					3442,
					/* 05 */3411, 3411, 3411, 3411, 3412, 3412, 3412, 3412,
					3413, 3413, 3413, 3414, 3414, 3414, 3414, 3415, 3415, 3415,
					3415, 3415, 3415, 3416, 3416, 3416, 3416, 3416, 3416, 3417,
					3417, 3417, 3417, 3417, 3418, 3418, 3418, 3418, 3418, 3419,
					3419,
					/* 06 */3414, 3414, 3414, 3415, 3415, 3415, 3415, 3416,
					3416, 3416, 3416, 3416, 3417, 3417, 3417, 3417, 3417, 3417,
					3418, 3418, 3418, 3418, 3419, 3419, 3419, 3420,
					/* 07 */3495, 3495, 3495, 3495, 3396, 3396, 3396, 3396,
					3396, 3397, 3397, 3397, 3397, 3397, 3398, 3398, 3398, 3398,
					3398, 3398, 3398, 3399, 3399, 3399, 3399, 3399, 3400, 3400,
					3400, 3401, 3401, 3402, 3402, 3403, 3404,
					/* 08 */3426, 3427, 3428, 3429, 3430, 3426, 3427, 3428,
					3429, 3430, 3426, 3427, 3428, 3429, 3430, 3426, 3427, 3428,
					3429, 3430, 3426, 3427, 3428, 3429, 3430, 3426, 3427, 3428,
					3429, 3430, 3426, 3427, 3428, 3429, 3430, 3227, 3228, 3229,
					3426, 3427, 3430, 3420, 3421, 3421, 3421, 3421, 3422, 3422,
					3422, 3423,
					/* 09 */3385, 3385, 3386, 3386, 3386, 3387, 3387, 3387,
					3388, 3388, 3388, 3388, 3388, 3388, 3388, 3389, 3389, 3389,
					3389, 3389, 3390, 3390, 3390, 3390, 3390,
					/* 10 */3383, 3383, 3383, 3383, 3383, 3383, 3383, 3384,
					3384, 3384, 3384, 3385, 3386, 3386, 3386,
					/* 11 */3385, 3385, 3385, 3385, 3386, 3386, 3386, 3386,
					3386, 3387, 3387, 3387, 3387, 3387, 3388, 3388, 3388, 3388,
					3388, 3389, 3390, 3390,
					/* 12 */3376, 3376, 3376, 3376, 3376, 3376, 3376, 3376,
					3376, 3376, 3376, 3377, 3377, 3377, 3377, 3377, 3377, 3377,
					3377, 3377, 3377, 3377, 3377, 3378, 3378, 3378, 3378, 3378,
					3378, 3378, 3378, 3378, 3378, 3378, 3378, 3379, 3379, 3379,
					3379, 3379, 3379, 3379, 3379, 3379, 3379, 3379, 3379, 3380,
					3380, 3380, 3380, 3380, 3380, 3380, 3380, 3380, 3380, 3380,
					3380, 3381, 3381, 3381, 3381, 3381, 3381, 3381, 3381, 3381,
					3381, 3381, 3382, 3382, 3382, 3382, 3382, 3382, 3382, 3382,
					3382, 3382, 3382, 3382, 3383, 3383, 3383, 3383, 3383, 3383,
					3383, 3383, 3383, 3383, 3383, 3383, 3384, 3384, 3384, 3384,
					3384, 3384, 3384, 3384, 3384, 3384, 3384, 3384,
					/* 13 */3391, 3391, 3391, 3391, 3391, 3391, 3392, 3392,
					3392, 3392, 3393, 3393, 3394, 3394, 3394, 3394, 3395, 3395,
					3395, 3395, 3395, 3395, 3396, 3396, 3396, 3396, 3396, 3396,
					3397, 3397, 3397, 3397, 3398, 3398, 3398, 3398, 3398, 3398,
					/* 14 */3427, 3427, 3428, 3428, 3428, 3429, 3429, 3429,
					3429, 3429, 3430, 3430, 3431, 3431, 3431, 3431,
					/* 15 */3431, 3432, 3432, 3432, 3432, 3433, 3433, 3433,
					3433, 3434, 3434, 3435, 3435, 3435, 3436, 3436, 3436, 3437,
					/* 16 */3160, 3160, 3161, 3161, 3161, 3161, 3161, 3162,
					3162, 3163, 3163, 3163, 3163, 3164, 3164, 3164, 3164, 3164,
					3164, 3165, 3165, 3165, 3165,
					/* 17 */3191, 3191, 3191, 3192, 3192, 3192, 3192, 3192,
					3193, 3193, 3193, 3193, 3193, 3193, 3194, 3194, 3194, 3194,
					3195, 3195, 3195, 3195, 3196, 3196, 3196, 3196, 3197, 3197,
					/* 18 */3178, 3178, 3179, 3179, 3179, 3179, 3179, 3180,
					3180, 3180, 3180, 3180, 3180, 3181, 3181, 3181, 3181, 3181,
					3181, 3181, 3182, 3182, 3182, 3182, 3182, 3183, 3183, 3183,
					3183, 3183, 3184, 3184, 3184, 3184, 3184, 3185, 3185, 3186,
					/* 19 */3173, 3173, 3173, 3173, 3174, 3174, 3174, 3174,
					3175, 3175, 3175, 3175, 3175, 3176, 3176, 3176, 3177, 3177,
					/* 20 */3187, 3187, 3187, 3188, 3188, 3188, 3188, 3188,
					3188, 3189, 3189, 3189, 3189, 3190, 3190, 3190, 3190, 3191,
					3191, 3191, 3192,
					/* 21 */3201, 3201, 3202, 3202, 3202, 3202, 3202, 3202,
					3203, 3203, 3203, 3203, 3203, 3204, 3204, 3204, 3204, 3205,
					/* 22 */3243, 3243, 3244, 3244, 3244, 3244, 3244, 3245,
					3245, 3245, 3245, 3245, 3246, 3246, 3246, 3246, 3247, 3247,
					3247, 3247, 3247, 3247, 3248, 3248, 3248, 3248, 3249, 3249,
					3249, 3249, 3249, 3250, 3250, 3250,
					/* 23 */3245, 3245, 3246, 3246, 3246, 3246, 3246, 3246,
					3247, 3247, 3247, 3247, 3247, 3247, 3248, 3248, 3248, 3248,
					3248, 3249, 3249, 3249, 3249, 3250, 3250, 3250, 3250, 3250,
					3250, 3251, 3251, 3251, 3252, 3252, 3252, 3252, 3252,
					/* 24 */3257, 3257, 3257, 3257, 3257, 3258, 3258, 3258,
					3259, 3259, 3259, 3260, 3260, 3260, 3260, 3260, 3261,
					/* 25 */3244, 3244, 3245, 3245, 3245, 3245, 3246, 3246,
					3246, 3246, 3247, 3247, 3247, 3248, 3248, 3248,
					/* 26 */3220, 3220, 3221, 3221, 3222, 3222, 3222, 3222,
					3223, 3223, 3223, 3223, 3223, 3223, 3224, 3224, 3224, 3224,
					3225, 3225, 3225, 3225, 3225, 3225, 3226, 3226, 3226, 3226,
					3226, 3226, 3227, 3227, 3228, 3228, 3228, 3228, 3228, 3229,
					3229, 3229,
					/* 27 */3203, 3203, 3204, 3204, 3204, 3204, 3204, 3205,
					3205, 3206, 3206, 3206, 3206, 3206, 3207, 3207, 3207, 3208,
					3208, 3209, 3210,
					/* 28 */3202, 3202, 3202, 3203, 3203, 3203, 3203, 3203,
					3204, 3204, 3204, 3204, 3204, 3205, 3205, 3205, 3205, 3205,
					3206, 3206, 3206, 3206, 3206, 3206, 3207, 3207, 3208, 3208,
					/* 29 */3202, 3202, 3202, 3203, 3203, 3203, 3203, 3203,
					3203, 3204, 3204, 3204, 3204, 3204, 3205, 3205, 3205,
					/* 30 */3403, 3404, 3404, 3404, 3404, 3405, 3405, 3405,
					3405, 3406, 3406, 3406, 3406, 3406, 3407, 3407, 3407, 3407,
					3408, 3408,/**/3393, 3393, 3394, 3394, 3394, 3394, 3395,
					3395, 3395, 3395, 3395, 3396, 3396,/**/3396, 3397, 3397,
					3397, 3398, 3398, 3398, 3399, 3399, 3400, 3400, 3401, 3401,
					3402, 3402, 3402, 3402, 3403, 3403, 3403,/**/3382, 3382,
					3382, 3383, 3383, 3383, 3383, 3383, 3383, 3384, 3385, 3385,
					3385, 3385, 3386, 3386, 3386, 3386, 3386, 3386,/**/3377,
					3377, 3377, 3377, 3377, 3377, 3377, 3377, 3378, 3378, 3378,
					3378, 3378, 3378, 3378, 3378, 3378, 3378, 3379, 3379, 3379,
					3379, 3379, 3379, 3379, 3379, 3379, 3379, 3380, 3380, 3380,
					3380, 3380, 3380, 3380, 3380, 3381, 3381, 3381, 3381, 3381,
					3381, 3381, 3381, 3382, 3382, 3382, 3382, 3382, 3382, 3382,
					3382, 3382, 3382, 3383, 3383, 3383, 3383, 3383, 3383, 3383,
					3383, 3383, 3383,
					/* 31 */3713, 3713, 3714, 3714, 3714, 3715, 3715, 3715,
					3716, 3716, 3716, 3717, 3717, 3718, 3718, 3718, 3719, 3719,/**/
					3713, 3713, 3713, 3714, 3714, 3714, 3715, 3715, 3715, 3716,
					3716, 3716, 3717, 3717, 3717,/**/3714, 3714, 3715, 3715,
					3716, 3716, 3716, 3716, 3716, 3717, 3717, 3717, 3717, 3717,
					3718, 3718, 3718, 3718, 3718, 3719, 3719, 3719, 3719, 3719,
					3719, 3719, 3720, 3720, 3720, 3720, 3720, 3720,/**/3712,
					3712, 3712, 3712, 3712, 3713, 3713, 3713, 3713, 3713, 3714,
					3714, 3714, 3714, 3714, 3715, 3715, 3715, 3715, 3715, 3716,
					3716, 3716, 3716, 3716, 3717, 3717, 3717, 3717, 3717, 3718,
					3718, 3718, 3718, 3718, 3719, 3719, 3719, 3719, 3719,/**/
					3714, 3714, 3714, 3714, 3714, 3715, 3715, 3715, 3715, 3715,
					3716, 3716, 3716, 3716, 3716, 3717, 3717, 3717, 3717, 3717,
					3718, 3718, 3718, 3718, 3718,/**/3718, 3718, 3718, 3718,
					3718, 3719, 3719, 3719, 3719, 3719, 3720, 3720, 3720, 3720,
					3720, 3721, 3721, 3721, 3721, 3722, 3722, 3722,/**/
					3712, 3712, 3712, 3712, 3713, 3713, 3713, 3713, 3714, 3714,
					3714, 3714, 3715, 3715, 3715, 3715, 3716, 3716, 3716, 3716,
					3717, 3717, 3717, 3717, 3718, 3718, 3718, 3718,/**/3711,
					3711, 3711, 3711, 3711, 3712, 3712, 3712, 3712, 3712, 3713,
					3713, 3713, 3713, 3713, 3714, 3714, 3714, 3714, 3714, 3715,
					3715, 3715, 3715, 3715,/**/3722, 3722, 3722, 3722, 3722,
					3723, 3723, 3723, 3723, 3723, 3724, 3724, 3724, 3724, 3724,
					3725, 3725, 3725, 3725, 3725, 3726, 3726, 3726, 3726, 3726,/**/
					3725, 3725, 3725, 3725, 3725, 3726, 3726, 3726, 3726, 3726,
					3727, 3727, 3727, 3727, 3727, 3728, 3728, 3728, 3728, 3728,
					3729, 3729, 3729, 3729, 3729,/**/3730, 3730, 3730, 3730,
					3730, 3731, 3731, 3731, 3731, 3731, 3732, 3732, 3732, 3732,
					3732, 3733, 3733, 3733, 3733, 3733, 3734, 3734, 3734, 3734,
					3734,/**/3727, 3727, 3727, 3728, 3728, 3728, 3728, 3728,
					3729, 3729, 3729, 3729, 3729, 3730, 3730, 3730, 3730, 3730,
					3731, 3731, 3731, 3731, 3731,/**/3723, 3723, 3723, 3723,
					3724, 3724, 3724, 3724, 3724, 3725, 3725, 3725, 3725, 3725,
					3726, 3726, 3726, 3726, 3726, 3727, 3727, 3727, 3727, 3727,/**/
					3726, 3726, 3726, 3727, 3727, 3727, 3727, 3728, 3728, 3728,
					3728, 3728, 3729, 3729, 3729, 3729, 3729, 3730, 3730, 3730,
					3730, 3730,
					/* 32 */3287, 3287, 3288, 3288, 3288, 3289, 3289, 3289,
					3289, 3290, 3290, 3290, 3290, 3290, 3291, 3291, 3291, 3291,
					3291, 3291, 3292, 3292, 3293, 3293, 3293, 3293, 3293, 3294,/**/
					3292, 3292, 3292, 3292, 3292, 3292, 3293, 3293, 3293, 3293,
					3293, 3294, 3294, 3294, 3294, 3294, 3294, 3295, 3295, 3295,
					3295, 3295, 3295, 3295, 3295, 3295, 3296, 3296, 3296, 3296,
					3296, 3296, 3296, 3296, 3296, 3296, 3296, 3297, 3297, 3297,
					3297, 3297, 3297, 3297, 3297, 3297, 3297, 3297, 3297, 3298,
					3298, 3298, 3298, 3298, 3298, 3298, 3298, 3298, 3299, 3299,
					3299, 3299, 3299, 3299, 3299, 3299, 3299, 3299, 3299, 3300,
					3300, 3300, 3300, 3300, 3300, 3300, 3300, 3300, 3300, 3301,
					3301, }, };
	/*
	 * WORLDMAP 2: (not-walk able places) 01 - Lumbridge cows
	 */
	public static int worldmap2[][] = {
			{
			/* 01 */3257, 3258, 3260, 3261, 3261, 3262, 3261, 3262, 3257,
					3258, 3257, 3258, 3254, 3255, 3254, 3255, 3252, 3252, 3250,
					3251, 3250, 3251, 3249, 3250, 3249, 3250, 3242, 3242, 3243,
					3243, 3257, 3244, 3245, 3244, 3245, 3247, 3248, 3250, 3251,
					3255, 3256, 3255, 3256, 3259, 3260, },
			{
			/* 01 */3256, 3256, 3256, 3256, 3266, 3266, 3267, 3267, 3270,
					3270, 3271, 3271, 3272, 3272, 3273, 3273, 3275, 3276, 3277,
					3277, 3278, 3278, 3279, 3279, 3280, 3280, 3285, 3286, 3289,
					3290, 3289, 3297, 3297, 3298, 3298, 3298, 3298, 3297, 3297,
					3297, 3297, 3298, 3298, 3297, 3297, }, };

	public int remove = 2; // 1 = removes equipment, 2 = doesn't remove - xerozcheez

	public int GetNPCBlockAnim(int id) {
		switch (id) {

			case 50: // dragons
			case 53:
			case 54:
			case 55:
			case 941:
				return 89;

			case 78:
				return 31;

			case 1265:
				return 1313;

			case 9:
				return 1156;

			case 1200:
				return 383;

			case 82:
				return 65;

			case 52:
				return 26;

			case 1158:
				return 1186;

			case 1160:
				return 1179;

			case 2881:
			case 2882:
			case 2883:
				return 2852;

			case 2456:
				return 1340;

			case 134:
				return 147;

			case 110:
				return 129;

			case 2025:
				return 2079;

			case 2026:
				return 424;

			case 2027:
				return 383;

			case 2028:
				return 424;

			case 2030:
				return 2063;

			case 1615:
				return 1536;

			case 2783:
				return 2730;

			case 1608:
			case 1609:
				return 1514;

			case 1624:
				return 1555;

			case 1610:
			case 1611:
				return 1519;

			case 1616:
			case 1617:
				return 1547;

			case 1618:
			case 1619:
				return 1550;

			case 1613:
				return 1527;

			case 1637:
			case 1638:
			case 1639:
			case 1640:
			case 1642:
				return 1585;

			case 1648:
			case 1649:
			case 1650:
			case 1651:
			case 1652:
			case 1653:
			case 1654:
			case 1655:
			case 1656:
			case 1657:
				return 1591;

			case 1770:
			case 1771:
			case 1772:
			case 1773:
			case 1774:
				return 312;

			case 2746:
				return 2635;

			case 2256:
				return 403;

			case 21:
				return 403;

			case 2745:
				return 2653;

			case 3200:
				return 3145;

			case 117:
				return 999;

			case 18:
				return 403;

			case 92:
				return 0;

			case 2629:
			case 2630:
			case 2736:
			case 2737:
			case 2738:
				return 2626;

			case 2631:
			case 2632:
			case 2739:
			case 2740:
			case 3592:
				return 2629;

			case 2741:
			case 2742:
				return 2635;

			case 2743:
			case 2744:
				return 2645;

			case 2627:
			case 2628:
			case 2734:
			case 2735:
				return 2622;

			default:
				return 1834;
		}
	}

	public boolean AttackPlayer(int NPCID) {
		int Player = npcs[NPCID].StartKilling;
		//if (Server.PlayerManager.players[Player] == null) {
		if (Server.PlayerManager.players[Player].disconnected) {
			ResetAttackPlayer(NPCID);
			return false;
		//} else if (Server.PlayerManager.players[Player].DirectionCount < 2) {
			//return false;
		}
		if (Server.PlayerManager.players[Player].playerLevel[3] <= 0) {
			ResetAttackPlayer(NPCID);
		}
		Client plr = (Client) Server.PlayerManager.players[Player];
		int hitDiff = 0;
		int EnemyX = Server.PlayerManager.players[Player].absX;
		int EnemyY = Server.PlayerManager.players[Player].absY;
		npcs[NPCID].enemyX = EnemyX;
		npcs[NPCID].enemyY = EnemyY;

		int npX = npcs[NPCID].absX;
		int npY = npcs[NPCID].absY;
		int plX = Server.PlayerManager.players[Player].absX;
		int plY = Server.PlayerManager.players[Player].absY;
		int offX = (npX - plX) * -1;
		int offY = (npY - plY) * -1;

		int EnemyHP = Server.PlayerManager.players[Player].playerLevel[Server.PlayerManager.players[Player].playerHitpoints];
		int EnemyMaxHP = getLevelForXP(Server.PlayerManager.players[Player].playerXP[Server.PlayerManager.players[Player].playerHitpoints]);
		boolean RingOfLife = false;
		hitDiff = Misc.random(npcs[NPCID].MaxHit);

		if (Server.PlayerManager.players[Player].attacknpc == NPCID && !plr.IsDead) {
			Server.PlayerManager.players[Player].faceNPC = NPCID;
			Server.PlayerManager.players[Player].faceNPCupdate = true;
			Server.PlayerManager.players[Player].attacknpc = NPCID;
			Server.PlayerManager.players[Player].IsAttackingNPC = true;
		}
		if (!plr.IsDead) {
			FacePlayerCB(NPCID, Player);
		}
		if (GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 30) && npcs[NPCID].npcType != 2745 && npcs[NPCID].npcType != 2456 && npcs[NPCID].npcType != 2743 && npcs[NPCID].npcType != 2744 && npcs[NPCID].npcType != 2740 && npcs[NPCID].npcType != 2739 && npcs[NPCID].npcType != 2881 && npcs[NPCID].npcType != 2882 && npcs[NPCID].npcType != 50 && npcs[NPCID].npcType != 2025 && npcs[NPCID].npcType != 2028) { //1645
			if (!plr.IsDead) {
				FollowPlayerCB(NPCID, Player);
			}
		}
		if((GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 15) == false) && npcs[NPCID].npcType == 1007) {
			if (!plr.IsDead) {
				npcs[NPCID].absX = 0;
				npcs[NPCID].absY = 0;
				npcs[NPCID].followPlayer = 0;
				npcs[NPCID].followingPlayer = false;
			}
		}
		if (GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 1) || npcs[NPCID].npcType == 2745 || npcs[NPCID].npcType == 2456 || npcs[NPCID].npcType == 2744 || npcs[NPCID].npcType == 2743 || npcs[NPCID].npcType == 2740 || npcs[NPCID].npcType == 2739 || npcs[NPCID].npcType == 2881 || npcs[NPCID].npcType == 2882 || npcs[NPCID].npcType == 50 || npcs[NPCID].npcType == 2025 || npcs[NPCID].npcType == 2028) {
			if (npcs[NPCID].actionTimer == 0) {
				if (plr.playerLevel[3] <= 0) {
					//ResetAttackPlayer(NPCID);
					npcs[NPCID].followingPlayer = false;
					npcs[NPCID].followPlayer = 0;
					npcs[NPCID].IsUnderAttack = false;
					npcs[NPCID].StartKilling = 0;
				} else {
					switch (npcs[NPCID].npcType) {

						case 81:
						case 397:
						case 1766:
						case 1767:
						case 1768:
							npcs[NPCID].animNumber = 0x03B;
							break;

						case 41:
							npcs[NPCID].animNumber = 0x037;
							break;

						case 87:
							npcs[NPCID].animNumber = 0x08A;
							break;

						case 21:
						case 2256:
							npcs[NPCID].animNumber = 451;
							hitDiff = 4 + Misc.random(9);
							break;

						case 1958:
							npcs[NPCID].animNumber = 422;
							hitDiff = 4 + Misc.random(18);
							break;

						case 82:
							npcs[NPCID].animNumber = 64;
							hitDiff = 4 + Misc.random(6);
							break;

						case 32:
							npcs[NPCID].animNumber = 412;
							hitDiff = Misc.random(4);
							break;

						case 1977:
							npcs[NPCID].animNumber = 451;
							if (plr.playerEquipment[plr.playerHat] == 4164) {
								if (!plr.protMelee) {
									hitDiff = 5 + Misc.random(18);
								} else {
									hitDiff = Misc.random(14);
								}
							} else {
								hitDiff = 10 + Misc.random(16);
								plr.Send("Fareed crushes you as you can't see through the smoke without a Facemask!");
							}
							plr.playerLevel[5] -= 2;
							break;

						case 1913:
							if (Misc.random(4) < 3) {
								npcs[NPCID].animNumber = 412;
								if (!plr.protMelee) {
									hitDiff = 3 + Misc.random(19);
								} else {
									hitDiff = Misc.random(15);
								}
								plr.playerLevel[5] -= 2;
							} else {
								npcs[NPCID].animNumber = 1979;
								plr.gfxLow(369);
								plr.Entangle(24000);
								if (!plr.protMage) {
									hitDiff = 6 + Misc.random(20);
								} else {
									hitDiff = Misc.random(13);
								}
								plr.Send("You are frozen!");
								plr.playerLevel[5] -= 4;
							}
							break;

						case 1914:
							npcs[NPCID].animNumber = 412;
							if (!plr.protMelee) {
								hitDiff = 5 + Misc.random(18);
							} else {
								hitDiff = Misc.random(11);
							}
							plr.PoisonPlayer();
							plr.playerLevel[5] -= 2;
							break;

						case 1975:
							npcs[NPCID].animNumber = 407;
							if (!plr.protMelee) {
								hitDiff = 3 + Misc.random(25);
							} else {
								hitDiff = Misc.random(19);
							}
							plr.playerLevel[5] -= 3;
							break;

						case 1200:
							npcs[NPCID].animNumber = 440;
							if (!plr.protMelee) {
								hitDiff = 4 + Misc.random(14);
							} else {
								hitDiff = 0;
							}
							break;

						case 1264:
							npcs[NPCID].animNumber = 811;
							hitDiff = Misc.random(6);
							plr.gfx(76);
							break;

						case 2746:
							npcs[NPCID].animNumber = 2636;
							npcs[NPCID].actionTimer = 9;
							if (!plr.protMelee) {
								hitDiff = Misc.random(8);
							} else {
								hitDiff = 0;
							}
							break;

						case 78:
							npcs[NPCID].animNumber = 30;
							hitDiff = 4 + Misc.random(4);
							break;

						case 50:
							npcs[NPCID].animNumber = 81;
							plr.Projectile(npcs[NPCID].absY + 1, npcs[NPCID].absX + 1, offY, offX, 50, 90, 440, 60, 31, -npcs[NPCID].StartKilling - 1);
							plr.AttackPlayers(4);
							if (plr.playerEquipment[plr.playerShield] == 1540 || plr.playerEquipment[plr.playerShield] == 2774) {
								hitDiff = Misc.random(17);
								plr.Send("Your shield protects you from the dragon's fire!"); 
							} else if (plr.playerEquipment[plr.playerShield] != 1540 && plr.playerEquipment[plr.playerShield] != 2774) {
								hitDiff = 10 + Misc.random(32);
								plr.Send("The dragon's fire burns you!");
							}
							break;

						case 52:
							npcs[NPCID].animNumber = 25;
							hitDiff = 2 + Misc.random(6);
							break;

						case 18:
							npcs[NPCID].animNumber = 451;
							hitDiff = 4 + Misc.random(2);
							break;

						case 53:
							npcs[NPCID].animNumber = 80;
							break;

						case 54:
							npcs[NPCID].animNumber = 81;
							plr.gfx(157);
							if (plr.playerEquipment[plr.playerShield] == 1540 || plr.playerEquipment[plr.playerShield] == 2774) {
								hitDiff = Misc.random(12);
								plr.Send("Your shield protects you from the dragon's fire!"); 
							} else if (plr.playerEquipment[plr.playerShield] != 1540 && plr.playerEquipment[plr.playerShield] != 2774) {
								hitDiff = 10 + Misc.random(21);
								plr.Send("The dragon's fire burns you!"); 
							}
							break;

						case 55:
							npcs[NPCID].animNumber = 81; 
							plr.gfx(157);
							if (plr.playerEquipment[plr.playerShield] == 1540 || plr.playerEquipment[plr.playerShield] == 2774) {
								hitDiff = Misc.random(8);
								plr.Send("Your shield protects you from the dragon's fire!"); 
							} else if (plr.playerEquipment[plr.playerShield] != 1540 && plr.playerEquipment[plr.playerShield] != 2774) {
								hitDiff = 10 + Misc.random(16);
								plr.Send("The dragon's fire burns you!"); 
							}
							break;

						case 1158:
							if (!plr.protMage) {
								npcs[NPCID].animNumber = 1184;
								hitDiff = 20 + Misc.random(12);
							} else {
								npcs[NPCID].animNumber = 1177;
								hitDiff = 4 + Misc.random(20);
							}
							break;

						case 1160:
							npcs[NPCID].animNumber = 1177;
							plr.Projectile(npcs[NPCID].absY + 2, npcs[NPCID].absX + 2, offY, offX, 50, 90, 280, 60, 31, -npcs[NPCID].StartKilling - 1);
							plr.gfx(281);
							plr.AttackPlayers(20);
							if (!plr.protMage) {
								hitDiff = Misc.random(31);
							} else {
								hitDiff = 4 + Misc.random(20);
							}
							break;

						case 2026:
							npcs[NPCID].animNumber = 2067;
							plr.playerLevel[5] -= 3;
							if (!plr.protMelee) {
								hitDiff = 20 + Misc.random(40);
							} else {
								hitDiff = 0;
							}
							break;

						case 2027:
							npcs[NPCID].animNumber = 2080;
							plr.playerLevel[5] -= 3;
							if (!plr.protMelee) {
								hitDiff = 4 + Misc.random(15);
							} else {
								hitDiff = 0;
							}
							if (Misc.random(6) == 1) {
								plr.gfx(398);
							}
							break;

						case 2029:
							npcs[NPCID].animNumber = 2068;
							plr.playerLevel[5] -= 3;
							if (!plr.protMelee) {
								hitDiff = 4 + Misc.random(15);
							} else {
								hitDiff = 0;
							}
							if (Misc.random(6) == 1) {
								plr.gfx(399);
							}
							break;

						case 2030:
							npcs[NPCID].animNumber = 2062;
							plr.playerLevel[5] -= 3;
							if (!plr.protMelee) {
								hitDiff = 22 + Misc.random(4);
							} else {
								hitDiff = 4 + Misc.random(18);
							}
							break;

						case 2627:
							npcs[NPCID].animNumber = 2621;
							hitDiff = Misc.random(3);
							break;

						case 2628:
							npcs[NPCID].animNumber = 2621;
							hitDiff = Misc.random(3);
							break;

						case 2734:
							npcs[NPCID].animNumber = 2621;
							hitDiff = Misc.random(3);
							plr.playerLevel[5] -= 2;
							break;

						case 2735:
							npcs[NPCID].animNumber = 2621;
							hitDiff = Misc.random(3);
							plr.playerLevel[5] -= hitDiff;
							break;

						case 2729:
						case 2730:
							npcs[NPCID].animNumber = 2625;
							hitDiff = Misc.random(6);
							break;

						case 2736:
						case 2737:
							npcs[NPCID].animNumber = 2625;
							hitDiff = Misc.random(6);
							plr.playerLevel[5] -= 2;
							break;

						case 2738:
							npcs[NPCID].animNumber = 2625;
							hitDiff = Misc.random(3);
							plr.playerLevel[5] -= 2;
							break;

						case 2631:
						case 2632:
							npcs[NPCID].animNumber = 2628;
							hitDiff = Misc.random(12);
							plr.playerLevel[5] -= 2;
							break;

						case 2739:
						case 2740:
							if (npcs[NPCID].npcType == 2740) {
								npcs[NPCID].actionTimer = 9;
							}
							npcs[NPCID].animNumber = 2633;
							plr.Projectile(npcs[NPCID].absY + 1, npcs[NPCID].absX + 1, offY, offX, 50, 100, 443, 60, 31, -npcs[NPCID].StartKilling - 1);
							plr.playerLevel[5] -= 2;
							if (!plr.protRange) {
								hitDiff = Misc.random(16);
							} else {
								hitDiff = 0;
							}
							break;

						case 3592:
							npcs[NPCID].animNumber = 2628;
							hitDiff = Misc.random(12);
							break;

						case 2741:
						case 2742:
							npcs[NPCID].animNumber = 2637;
							plr.playerLevel[5] -= 3;
							if (!plr.protMelee) {
								hitDiff = Misc.random(28);
							} else {
								hitDiff = 0;
							}
							break;

						case 117:
							npcs[NPCID].animNumber = 999;
							plr.playerLevel[5] -= 3;
							plr.AttackPlayers(3);
							if (!plr.protMelee) {
								hitDiff = 10 + Misc.random(52);
							} else {
								hitDiff = 0;
							}
							break;

						case 2456:
							npcs[NPCID].animNumber = 1343;
							plr.Projectile(npcs[NPCID].absY, npcs[NPCID].absX, offY, offX, 50, 90, 297, 60, 31, -npcs[NPCID].StartKilling - 1);
							npcs[NPCID].actionTimer = 6 + Misc.random(5);
							hitDiff = Misc.random(6);
							break;

						case 134:
							npcs[NPCID].animNumber = 143;
							plr.PoisonPlayer();
							hitDiff = Misc.random(4);
							break;

						case 256:
							npcs[NPCID].animNumber = 1833;
							hitDiff = Misc.random(10);
							break;

						case 1615:
							npcs[NPCID].animNumber = 1537;
							if (!plr.protMelee) {
								hitDiff = Misc.random(8);
							} else {
								hitDiff = 0;
							}
							break;

						case 2783:
							npcs[NPCID].animNumber = 2733;
							if (!plr.protMelee) {
								hitDiff = 22 + Misc.random(4);
							} else {
								hitDiff = 0;
							}
							break;

						case 1619:
							npcs[NPCID].animNumber = 1552;
							hitDiff = Misc.random(4);
							break;

						case 1653:
							npcs[NPCID].animNumber = 1592;
							hitDiff = Misc.random(4);
							break;

						case 1616:
							npcs[NPCID].animNumber = 1546;
							hitDiff = Misc.random(4);
							break;

						case 1624:
							npcs[NPCID].animNumber = 1557;
							hitDiff = Misc.random(4);
							break;

						case 1637:
							npcs[NPCID].animNumber = 1586;
							hitDiff = Misc.random(4);
							break;

						case 2881:
							npcs[NPCID].animNumber = 2855;
							plr.AttackPlayers(2);
							npcs[NPCID].actionTimer = 9;
							if (!plr.protRange) {
								hitDiff = Misc.random(25);
							} else {
								hitDiff = 0;
							}
							break;

						case 2882:
							npcs[NPCID].animNumber = 2854;
							plr.Projectile(npcs[NPCID].absY, npcs[NPCID].absX, offY, offX, 50, 90, 162, 60, 31, -npcs[NPCID].StartKilling - 1);
							npcs[NPCID].actionTimer = 11;
							if (!plr.protMage) {
								hitDiff = 25 + Misc.random(28);
							} else {
								hitDiff = 0;
							}
							break;

						case 2883:
							npcs[NPCID].animNumber = 2851;
							npcs[NPCID].actionTimer = 13;
							if (!plr.protMelee) {
								hitDiff = Misc.random(25);
							} else {
								hitDiff = 0;
							}
							break;

						case 1265:
							npcs[NPCID].animNumber = 1312;
							hitDiff = Misc.random(3);
							break;

						case 86:
							npcs[NPCID].animNumber = 138;
							hitDiff = Misc.random(3);
							break;

						case 1007:
							npcs[NPCID].animNumber = 811;
							plr.gfx(78);
							if (!plr.protMage) {
								hitDiff = 5 + Misc.random(15);
							} else {
								hitDiff = 0;
							}
							break;

						case 2743:
						case 2744:
							npcs[NPCID].animNumber = 2647;
							plr.Projectile(npcs[NPCID].absY + 2, npcs[NPCID].absX + 2, offY, offX, 50, 90, 445, 60, 31, -npcs[NPCID].StartKilling - 1);
							if (npcs[NPCID].npcType == 2743) {
								npcs[NPCID].actionTimer = 9;
							} else {
								npcs[NPCID].actionTimer = 10;
							}
							plr.playerLevel[5] -= 5;
							plr.gfxLow(446);
							if (!plr.protMage) {
								hitDiff = 10 + Misc.random(39);
							} else {
								hitDiff = 0;
							}
							break;

						case 2745:
							if (plr.JadAttack == 0 || plr.JadAttack == 1) {
								npcs[NPCID].animNumber = 2652;
								plr.JadAttacking = true;
								plr.JadGFX = true;
								plr.JadRange = true;
								plr.JadMage = false;
							}
							if (plr.JadAttack == 2) {
								npcs[NPCID].animNumber = 2656;
								plr.JadAttacking = true;
								plr.JadMage = true;
								plr.JadRange = false;
								plr.Projectile(npcs[NPCID].absY + 2, npcs[NPCID].absX + 2, offY, offX, 50, 140, 448, 120, 31, -npcs[NPCID].StartKilling - 1);
								plr.Projectile(npcs[NPCID].absY + 2, npcs[NPCID].absX + 2, offY, offX, 50, 149, 449, 120, 31, -npcs[NPCID].StartKilling - 1);
								plr.Projectile(npcs[NPCID].absY + 2, npcs[NPCID].absX + 2, offY, offX, 50, 158, 450, 120, 31, -npcs[NPCID].StartKilling - 1);
							}
							plr.JadDelay = System.currentTimeMillis();
							npcs[NPCID].actionTimer = 10;
							break;

						case 110:
							npcs[NPCID].animNumber = 128;
							hitDiff = Misc.random(6);
							plr.playerLevel[5] -= 4;
							break;

						case 49:
							npcs[NPCID].animNumber = 158;
							if (!plr.protMelee) {
								hitDiff = Misc.random(23);
							} else {
								hitDiff = 0;
							}
							break;

						case 1770:
						case 1771:
						case 1772:
						case 1773:
						case 1774:
							npcs[NPCID].animNumber = 309;
							hitDiff = Misc.random(2);
							break;

						case 2025:
							npcs[NPCID].animNumber = 1167;
							plr.Projectile(npcs[NPCID].absY, npcs[NPCID].absX, offY, offX, 50, 90, 156, 43, 31, -npcs[NPCID].StartKilling - 1);
							plr.playerLevel[5] -= 3;
							if (!plr.protMage) {
								hitDiff = 5 + Misc.random(18);
							} else {
								hitDiff = 0;
							}
							if (Misc.random(6) == 1) {
								plr.gfx(400);
							}
							break;

						case 2028:
							npcs[NPCID].animNumber = 2075;
							plr.Projectile(npcs[NPCID].absY, npcs[NPCID].absX, offY, offX, 50, 70, 27, 43, 31, -npcs[NPCID].StartKilling - 1);
							plr.playerLevel[5] -= 3;
							if (!plr.protRange) {
								hitDiff = Misc.random(25);
							} else {
								hitDiff = 0;
							}
							if (Misc.random(6) == 1) {
								plr.gfx(401);
							}
							break;

						case 64:
							npcs[NPCID].animNumber = 0x326;
							break;

						case 3200:
							npcs[NPCID].animNumber = 3146;
							hitDiff = 20 + Misc.random(10);
							break;

						case 752:
							npcs[NPCID].animNumber = 0x326;
							break;

						default:
							npcs[NPCID].animNumber = 0x326;
							break;
				}
				if (npcs[NPCID].npcType != 2745) {
					plr.anim(plr.BlockAnim(plr.playerEquipment[plr.playerWeapon]));
				}
				if (plr.playerLevel[5] < 100) {
					if (plr.playerLevel[5] < 0)
						plr.playerLevel[5] = 0;
					plr.sendString(" Prayer: "+plr.playerLevel[5]+"/"+plr.getLevelForXP(plr.playerXP[5])+"", 687);
					plr.sendFrame126(""+plr.playerLevel[5]+"", 4012);
				}
				npcs[NPCID].animUpdateRequired = true;
				npcs[NPCID].updateRequired = true;
				if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
				}
				Server.PlayerManager.players[Player].hitDiff = hitDiff;
				Server.PlayerManager.players[Player].updateRequired = true;
				if (npcs[NPCID].npcType != 2745) {
					Server.PlayerManager.players[Player].hitUpdateRequired = true;
				}
				Server.PlayerManager.players[Player].appearanceUpdateRequired = true;
				if (npcs[NPCID].npcType != 2743 && npcs[NPCID].npcType != 2744 && npcs[NPCID].npcType != 2740 && npcs[NPCID].npcType != 2456 && npcs[NPCID].npcType != 2745 && npcs[NPCID].npcType != 2881 && npcs[NPCID].npcType != 2882 && npcs[NPCID].npcType != 2883) {
					npcs[NPCID].actionTimer = 7;
				}
				}
				return true;
			}
		}
		return false;
	}

	public boolean ResetAttackNPC(int NPCID) {
		npcs[NPCID].IsUnderAttackNpc = false;
		npcs[NPCID].IsAttackingNPC = false;
		npcs[NPCID].attacknpc = -1;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 135; lvl++) {
			points += Math.floor((double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
		  for (int j = 0; j <= distance; j++) {
			if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			}
		  }
		}
		return false;
	}
	public boolean ResetAttackPlayer(int NPCID) {
		npcs[NPCID].IsUnderAttack = false;
		npcs[NPCID].StartKilling = 0;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}

	public boolean loadAutoSpawn(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;

		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");

			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]),
					Integer.parseInt(token3[1]),
					Integer.parseInt(token3[2]),
					Integer.parseInt(token3[3]),
					Integer.parseInt(token3[4]),
					Integer.parseInt(token3[5]),
					Integer.parseInt(token3[6]),
					Integer.parseInt(token3[7]),
					Integer.parseInt(token3[8]),
					GetNpcListHP(Integer.parseInt(token3[0])), true);
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {}
						return true;
					}
				}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}

	public int GetNpcListHP(int NpcID) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == NpcID) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}

	public boolean loadNPCList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;

		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");

			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1],
					Integer.parseInt(token3[2]),
					Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {}
						return true;
					}
				}
				try {
					line = characterfile.readLine();
				} catch (IOException ioexception1) {
					EndOfFile = true;
				}
			}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}

	public boolean loadNPCDrops(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while ((EndOfFile == false) && (line != null)) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npcdrop")) {
					if (Integer.parseInt(token3[0]) <= -1)
						continue;
					drops[Integer.parseInt(token3[0])][dropCount[Integer
							.parseInt(token3[0])]] = Integer
							.parseInt(token3[1]);
					drops[Integer.parseInt(token3[0])][dropCount[Integer
							.parseInt(token3[0])] + 1] = Integer
							.parseInt(token3[2]);
					drops[Integer.parseInt(token3[0])][dropCount[Integer
							.parseInt(token3[0])] + 2] = Integer
							.parseInt(token3[3]);
					dropCount[Integer.parseInt(token3[0])] += 3;
				}
			} else {
				if (line.equals("[ENDOFNPCDROPLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}

	public void println(String str) {
		System.out.println(str);
	}
}

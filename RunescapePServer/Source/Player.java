import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

abstract class Player {

	void printOut(String str) {
		System.out.println("[" + playerName + "]: " + str);
	}

	public boolean withinDistance(int distance, Player otherPlr) {
		if (otherPlr == null)
			return false;
			if (heightLevel != otherPlr.heightLevel) 
				return false;
				int deltaX = otherPlr.absX-absX, deltaY = otherPlr.absY-absY;
				return deltaX <= distance && deltaX >= ((distance + 0) * -1) && deltaY <= distance && deltaY >= ((distance + 0) * -1);
	}
	public void StopTradeScam() {
		Client tp = (Client) PlayerManager.players[tradeWith];
		Client p = (Client) PlayerManager.players[playerId];

		p.DeclineTrade();
		tp.DeclineTrade();
	}

	public void RemoveNPC() {
		for (int i = 0; i < Server.NpcManager.maxNPCs; i++)
		if (Server.NpcManager.npcs[i] != null) {
			if (Server.NpcManager.npcs[i].npcType == 2735 || Server.NpcManager.npcs[i].npcType >= 2025 && Server.NpcManager.npcs[i].npcType <= 2030 || Server.NpcManager.npcs[i].npcType == 1975 || Server.NpcManager.npcs[i].npcType == 1977 || Server.NpcManager.npcs[i].npcType == 1914 || Server.NpcManager.npcs[i].npcType == 1913 || Server.NpcManager.npcs[i].npcType == 2627 || Server.NpcManager.npcs[i].npcType == 2630 || Server.NpcManager.npcs[i].npcType == 2631 || Server.NpcManager.npcs[i].npcType == 2736 || Server.NpcManager.npcs[i].npcType == 2738 || Server.NpcManager.npcs[i].npcType == 2739 || Server.NpcManager.npcs[i].npcType == 2740 || Server.NpcManager.npcs[i].npcType == 2741 || Server.NpcManager.npcs[i].npcType == 2743 || Server.NpcManager.npcs[i].npcType == 2744 || Server.NpcManager.npcs[i].npcType == 2745 || Server.NpcManager.npcs[i].npcType == 2746) {
				if (Server.NpcManager.npcs[i].heightLevel == heightLevel) {
					Server.NpcManager.npcs[i].absX = 0;
					Server.NpcManager.npcs[i].absY = 0;
					Server.NpcManager.npcs[i].followPlayer = 0;
					Server.NpcManager.npcs[i].StartKilling = 0;
					Server.NpcManager.npcs[i].IsUnderAttack = false;
				}
			}
		}
	}

	public void saveTrade(String data) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("../../Players/Trades/" + playerName + ".txt", true));
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					printOut("Error writing system log.");
					ioe2.printStackTrace();
			}
		}
	}

	public static int getprizes[] = {1, 2};

	public static int randomgetprizes()
	{
		return getprizes[(int)(Math.random()*getprizes.length)];
	}

	public static int partyhats[] = {1038,1040,1042,1044,1046,1048};

	public static int randomPartyHat()
	{
		return partyhats[(int)(Math.random()*partyhats.length)];
	}

	public int maxRangeHit() {
		double d = 0.0D;
		double d1 = playerLevel[playerRanged];
		d += 1.399D + d1 * 0.00125D;
		d += d1 * 0.11D;
		Client AttackingOn2 = (Client) Server.PlayerManager.players[AttackingOn];
		if (AttackingOn2 != null) {
			if (hasCrystalBow()) {
				d *= 1.5D;
			} else
			if (!hasCrystalBow() && (playerEquipment[playerArrows] == 4740)) {
				d *= 1.95D;
			} else
			if(playerEquipment[playerWeapon] == 7603) {
				d *= 1.95D;
			} else
			if (usingSpecial) {
				if(playerEquipment[playerWeapon] == 861) {
					d *= 1.05D;
				} else
				if(playerEquipment[playerWeapon] == 7603) {
					d *= 1.75D;
				}
			}
			int hit = (int)Math.floor(d);
			int protrange = 0;
			int noHit = Misc.random(2);
			if(AttackingOn2.protRange && noHit == 2)
				hit /= 2;
			int aBonus = 0;
			int rand_att = Misc.random(playerLevel[4]) + Misc.random(playerBonus[4]);
			int rand_def = (int) (0.65 * Misc.random(AttackingOn2.playerLevel[1])) + Misc.random(protrange);
			int random_u = Misc.random(playerBonus[4] + aBonus);
			int dBonus = 0;
			int random_def = Misc.random(AttackingOn2.playerBonus[9] + dBonus);
			if ((random_u >= random_def) && (rand_att > rand_def)) {
				return hit;
			} else {
				return 0;
			}
		}
		return 0;
	}

	public int maxRangeHit2() {
		double d = 0.0D;
		double d1 = playerLevel[playerRanged];
		d += 1.399D + d1 * 0.00125D;
		d += d1 * 0.11D;
		if(hasCrystalBow()) {
			d *= 1.5D;
		} else
		if(!hasCrystalBow() && (playerEquipment[playerArrows] == 4740)) {
			d *= 1.95D;
		} else
		if(playerEquipment[playerWeapon] == 4827) {
			d *= 1.95D;
		} else
		if(playerEquipment[playerWeapon] == 6522) {
			d *= 1.55D;
		} else
		if (usingSpecial) {
			if(playerEquipment[playerWeapon] == 861) {
				d *= 1.05D;
			} else
			if(playerEquipment[playerWeapon] == 7603) {
				d *= 1.75D;
			}
		}
		int hit = (int)Math.floor(d);
		return hit;
	}

	public void keepItem1() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++) {
			int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
			if (value > highest && playerItems[i]-1 != -1) {
				highest = value;
				itemKept1 = playerItems[i]-1;
				itemKept1Slot = i;
				itemSlot1 = true;
			}
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
			if (value > highest && playerEquipment[i] != -1) {
				highest = value;
				itemKept1 = playerEquipment[i];
				itemKept1Slot = i;
				itemSlot1 = false;
			}
		}
	}

	public void keepItem2() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (itemKept1Slot != i) {
				int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
				if (value > highest && playerItems[i]-1 != -1) {
					highest = value;
					itemKept2 = playerItems[i]-1;
					itemKept2Slot = i;
					itemSlot2 = true;
				}
			}
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			if (itemKept1Slot != i) {
				int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
				if (value > highest && playerEquipment[i] != -1) {
					highest = value;
					itemKept2 = playerEquipment[i];
					itemKept2Slot = i;
					itemSlot2 = false;
				}
			}
		}
	}

	public void keepItem3() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (itemKept1Slot != i && itemKept2Slot != i) {
				int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
				if (value > highest && playerItems[i]-1 != -1) {
					highest = value;
					itemKept3 = playerItems[i]-1;
					itemKept3Slot = i;
					itemSlot3 = true;
				}
			}
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			if (itemKept1Slot != i && itemKept2Slot != i) {
				int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
				if (value > highest && playerEquipment[i] != -1) {
					highest = value;
					itemKept3 = playerEquipment[i];
					itemKept3Slot = i;
					itemSlot3 = false;
				}
			}
		}
	}

	public void keepItem4() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (itemKept1Slot != i && itemKept2Slot != i && itemKept3Slot != i) {
				int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
				if (value > highest && playerItems[i]-1 != -1) {
					highest = value;
					itemKept4 = playerItems[i]-1;
					itemKept4Slot = i;
					itemSlot4 = true;
				}
			}
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			if (itemKept1Slot != i && itemKept2Slot != i && itemKept3Slot != i) {
				int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
				if (value > highest && playerEquipment[i] != -1) {
					highest = value;
					itemKept4 = playerEquipment[i];
					itemKept4Slot = i;
					itemSlot4 = false;
				}
			}
		}
	}

	public void resetKeepItem() {
		itemKept1 = itemKept2 = itemKept3 = itemKept4 = -1;
		itemKept1Slot = itemKept2Slot = itemKept3Slot = itemKept4Slot = -1;
	}

	public double GetItemValue(int ItemID) {
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;

		for (int i = 0; i < Server.item.MaxListedItems; i++) {
			if (Server.item.ItemList[i] != null) {
				if (Server.item.ItemList[i].itemId == ItemID) {
					ShopValue = Server.item.ItemList[i].ShopValue;
				}
			}
		}
		TotPrice = (ShopValue * 1);
		return TotPrice;
	}

	public void appendConnected() {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("../../Players/Connections/" + playerName + ".txt", true));
			bw.write("["+playerLastLogin+"] "+connectedFrom+"");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}

	public void follow(int i1, int i2) {
		Player FP = Server.PlayerManager.players[i1];
		Client C = (Client) Server.PlayerManager.players[i2];

		if (C.withinDistance(FP) && System.currentTimeMillis() - C.EntangleDelay > C.Entangled) {
                        C.faceNPC = 32768 + i1;
                        C.faceNPCupdate = true;
			C.followPlayer = FP;
		}
	}

	public void WalkTimer(int i, int j) {
		if(System.currentTimeMillis() - EntangleDelay < Entangled)
			return;
		newWalkCmdSteps = 0;
		if(++newWalkCmdSteps > 50)
			newWalkCmdSteps = 0;
		int k = absX + i;
		k -= mapRegionX * 8;
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = absY + j;
		l -= mapRegionY * 8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1) && playerEnergy > 0);
		for(this.i = 0; this.i < newWalkCmdSteps; this.i++)
		{
			newWalkCmdX[this.i] += k;
			newWalkCmdY[this.i] += l;
		}
		poimiY = l;
		poimiX = k;
	}

	public boolean isIpOn(String ip) {
		for (Player p : Server.PlayerManager.players) {
			if(p != null) {
				Client person = (Client)p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.connectedFrom.equalsIgnoreCase(ip) && !person.playerName.equalsIgnoreCase(playerName) && !person.disconnected && !connectedFrom.equals("127.0.0.1")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void DropArrowsNPC() {
		try {
			int EnemyX = Server.NpcManager.npcs[attacknpc].absX;
			int EnemyY = Server.NpcManager.npcs[attacknpc].absY;
			if (playerEquipmentN[playerArrows] != 0) {
				if (Item.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) == 0) {
					Item.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
				} else {
					int amount = Item.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY);
					Item.removeItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount);
					Item.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount + 1, playerId, false);
				}
			}
		} catch (Exception e) {
		}
	}

	public void DropArrowsPlr() {
		try {
			int EnemyX = Server.PlayerManager.players[AttackingOn].absX;
			int EnemyY = Server.PlayerManager.players[AttackingOn].absY;
			if (playerEquipmentN[playerArrows] != 0) {
				if (Item.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) == 0) {
					Item.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
				} else {
					int amount = Item.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY);
					Item.removeItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount);
					Item.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount + 1, playerId, false);
				}
			}
		} catch (Exception e) {
		}
	}

	public int distanceTo(Player other) {
		return (int) Math.sqrt(Math.pow(absX - other.absX, 2) + Math.pow(absY - other.absY, 2));
	}

	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2) + Math.pow(absY - pointY, 2));
	}

	public int getItemSlot(int itemID) {
		for (int slot = 0; slot < playerItems.length; slot++) {
			if (playerItems[slot] == (itemID + 1)) {
				return slot;
			}
		}
		return -1;
	}

	public void writeLog(String data, String file) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("../Data/" + file + ".txt", true));
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					printOut("Error writing system log.");
					ioe2.printStackTrace();
			}
		}
	}

	public void gfxLow(int id) {
		mask100var1 = id;
		mask100var2 = 0;
		mask100update = true;
		updateRequired = true;
	}

	public void gfx(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	}

	public void healJad() {
		Server.NpcManager.npcs[attacknpc].HP += 25;
	}

	public int getHeight() {
		int i = 0;
		i = playerId * 4;
		return i;
	}

	public int getHeight2() {
		int i = 0;
		i = playerId * 4;
		i--;
		return i;
	}

	public void Summon(int npcID, int Y, int X, int heightLevel) {
		Server.NpcManager.newSummonedNPC(npcID, X, Y, heightLevel, X, Y, X, Y, 1, Server.NpcManager.GetNpcListHP(npcID), false, playerId);
	}

	public boolean FullDharokEquipped() {
		if (playerEquipment[playerHat] == 4716 && playerEquipment[playerChest] == 4720 && playerEquipment[playerLegs] == 4722 && playerEquipment[playerWeapon] == 4718) {
			return true;
		}
		return false;
	}

	boolean isInWaiting() {
		if (absX >= 2393 && absX <= 2405 && absY >= 5168 && absY <= 5176) {
			return true;
		}
		return false;
	}

	boolean isInBarrows() {
		if (absX >= 3450 && absX <= 3650 && absY >= 9550 && absY <= 9775) {
			return true;
		}
		return false;

	}

	boolean isInFightPits() {
		if (absX >= 2375 && absX <= 2418 && absY >= 5129 && absY <= 5167) {
			return true;
		}
		return false;
	}

	boolean isInWild() {
		if (absX >= 2940 && absX <= 3400 && absY >= 3522 && absY <= 3970) {
			return true;
		}
		return false;
	}

	void getWildernessLevel() {
		Client c = (Client) Server.PlayerManager.players[playerId];
		int level = ((absY - 3520) / 8) + 1;

		if (isInWild()) {
			if (isInDeepWild()) {
				c.frame61(1);
			}
			if (!InWilderness) {
				c.outStream.createFrame(208);
				c.outStream.writeWordBigEndian_dup(197);
				c.setOption("Attack", 3, 1);
				InWilderness = true;
			}
			c.sendString("Level: " + level, 199);
		} else {
			if (InWilderness) {
				InWilderness = false;
			}
			c.clearInterface();
			c.setOption("null", 3, 1);
		}
	}

	boolean inWilderness() {
		if (absX >= 2940 && absX <= 3400 && absY >= 3524 && absY <= 3970 || absX >= 2375 && absX <= 2418 && absY >= 5129 && absY <= 5167) {
			return true;
		}
		return false;
	}

	boolean inDT() {
		if (absX >= 3525 && absX <= 3590 && absY >= 3390 && absY <= 3425 || absX >= 3305 && absX <= 3360 && absY >= 9350 && absY <= 9425 || absX >= 2885 && absX <= 2920 && absY >= 3700 && absY <= 3800 || absX >= 2705 && absX <= 2780 && absY >= 5010 && absY <= 5120) {
			return true;
		}
		return false;
	}

	boolean isInDeepWild() {
		if (absX >= 2940 && absX <= 3400 && absY >= 3672 && absY <= 3970) {
			return true;
		}
		return false;
	}

	boolean isInFightCaves() {
		if (absX >= 2365 && absX <= 2428 && absY >= 5050 && absY <= 5120) {
			return true;
		}
		return false;
	}

	boolean hasCrystalBow() {
		int l = playerEquipment[playerWeapon];
		if (l >= 4214 && l <= 4223) {
			return true;
		}
		return false;
	}

	public void getNextPlayerMovement() {
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;

		if (teleportToX != -1 && teleportToY != -1) {
			followPlayer = null;
			mapRegionDidChange = true;
			if(mapRegionX != -1 && mapRegionY != -1) {
				int relX = teleportToX-mapRegionX*8, relY = teleportToY-mapRegionY*8;
				if(relX >= 2*8 && relX < 11*8 && relY >= 2*8 && relY < 11*8)
					mapRegionDidChange = false;
			}

			if(mapRegionDidChange) {
				mapRegionX = (teleportToX>>3)-6;
				mapRegionY = (teleportToY>>3)-6;

				playerListSize = 0;
			}

			currentX = teleportToX - 8*mapRegionX;
			currentY = teleportToY - 8*mapRegionY;
			absX = teleportToX;
			absY = teleportToY;

			resetWalkingQueue();

			teleportToX = teleportToY = -1;
			didTeleport = true;
		} else {
			if(followPlayer != null) {
				if(followPlayerIdle) {
					followPlayerIdle = false;
					return;
				}
				dir1 = getNextFollowingDirection(followPlayer);
				if(dir1 == -1) followPlayerIdle = true;
			} else
				dir1 = getNextWalkingDirection();
			if(dir1 == -1) return;

			if(isRunning && followPlayer != null)
				dir2 = getNextFollowingDirection(followPlayer);
			else if(isRunning2)
				dir2 = getNextWalkingDirection();

			int deltaX = 0, deltaY = 0;
			if(currentX < 2*8) {
				deltaX = 4*8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			} else if(currentX >= 11*8) {
				deltaX = -4*8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}

			if(currentY < 2*8) {
				deltaY = 4*8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			} else if(currentY >= 11*8) {
				deltaY = -4*8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}

			if(mapRegionDidChange) {
				currentX += deltaX;
				currentY += deltaY;
				for(int i = 0; i < walkingQueueSize; i++) {
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
			}
		}
	}

	public boolean WithinDistance(int j, int k, int l, int i1, int j1)
	{
		for(int k1 = 0; k1 <= j1; k1++)
		{
			for(int l1 = 0; l1 <= j1; l1++)
			{
				if(j + k1 == l && (k + l1 == i1 || k - l1 == i1 || k == i1))
				{
					return true;
				}
				if(j - k1 == l && (k + l1 == i1 || k - l1 == i1 || k == i1))
				{
					return true;
				}
				if(j == l && (k + l1 == i1 || k - l1 == i1 || k == i1))
				{
					return true;
				}
			}

		}
		return false;
	}

	public int getNextFollowingDirection(Player player) {
		int dir = -1;
		boolean goNorth = false, goSouth = false, goEast = false, goWest = false;
		
		if (absX < player.absX)
			goEast = true;
		else if(absX > player.absX)
			goWest = true;
		if (absY < player.absY)
			goNorth = true;
		else if (absY > player.absY)
			goSouth = true;

		if (!goSouth && !goNorth && !goEast && !goWest)
			return -1;
	
		if (withinDistance(1, player))
			return -1;

		if (!withinDistance(8, player) && AttackingOn < 1 || player.playerLevel[3] <= 0)
			followPlayer = null;

		if (goNorth && goEast)
			dir = 2;
		else if (goNorth && goWest)
			dir = 14;
		else if (goSouth && goEast)
			dir = 6;
		else if (goSouth && goWest)
			dir = 10;
		else if (goNorth)
			dir = 0;
		else if (goEast)
			dir = 4;
		else if (goWest)
			dir = 12;
		else if (goSouth)
			dir = 8;

		dir >>= 1;
		currentX += Misc.directionDeltaX[dir];
		currentY += Misc.directionDeltaY[dir];
		absX += Misc.directionDeltaX[dir];
		absY += Misc.directionDeltaY[dir];
		return dir;
	}

	public int getNextWalkingDirection()
	{
		if(wQueueReadPtr == wQueueWritePtr) return -1;
		int dir;
		do {
			dir = Misc.direction(currentX, currentY, walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if(dir == -1) wQueueReadPtr = (wQueueReadPtr+1) % walkingQueueSize;
			else if((dir&1) != 0) {

				resetWalkingQueue();
				return -1;

			}
		} while(dir == -1 && wQueueReadPtr != wQueueWritePtr);
		if(dir == -1) return -1;
		dir >>= 1;
		currentX += Misc.directionDeltaX[dir];
		currentY += Misc.directionDeltaY[dir];
		absX += Misc.directionDeltaX[dir];
		absY += Misc.directionDeltaY[dir];
		return dir;
	}

	public int combat = 0;
	public Player(int _playerId) {
		playerId = _playerId;
		Rights = 0;

		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i<playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}

		for (int i=0; i<playerLevel.length; i++) {
			if (i == 3) {
				playerLevel[i] = 10;
				playerXP[i] = 1154;
			} else {
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}

		for (int i = 0; i< playerBankSize; i++) {
			bankItems[i] = 0;
		}

		for (int i = 0; i < playerBankSize; i++) {
			bankItemsN[i] = 0;
		}

		for (int i = 0; i < playerEquipment.length; i++) {
			playerEquipment[i] = -1;
			playerEquipmentN[i] = 0;
		}

		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((month * 100) + day + (year * 10000));
		playerLastLogin = calc;

		playerLook[0] = 0;
		playerLook[1] = 3;
		playerLook[2] = 8;
		playerLook[3] = 2;
		playerLook[4] = 5;
		playerLook[5] = 1;

		playerEquipment[playerHat] = -1;
		playerEquipment[playerCape] = -1;
		playerEquipment[playerAmulet] = -1;
		playerEquipment[playerChest] = -1;
		playerEquipment[playerShield] = -1;
		playerEquipment[playerLegs] = -1;
		playerEquipment[playerHands] = -1;
		playerEquipment[playerFeet] = -1;
		playerEquipment[playerRing] = -1;
		playerEquipment[playerArrows] = -1;
		playerEquipment[playerWeapon] = -1;

		pHead = 1;
		pTorso = 18;
		pArms = 26;
		pHands = 33;
		pLegs = 36;
		pFeet = 42;
		pBeard = 14;

		teleportToX = 3225;
		teleportToY = 3218;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	void destruct() {
		playerListSize = 0;
		for(int i = 0; i < maxPlayerListSize; i++) playerList[i] = null;
		npcListSize = 0;
		for(int i = 0; i < maxNPCListSize; i++) npcList[i] = null;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}
	private void appendSetFocusDestination(Stream str) {
		str.writeWordBigEndianA(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}	
	public void TurnPlayerTo(int pointX, int pointY) {
		FocusPointX = 2*pointX+1;
		FocusPointY = 2*pointY+1;
	}

	public boolean isNpc;
	public int npcId;
	public boolean initialized, disconnected;
	public boolean isActive;
	public boolean isKicked;
	public String connectedFrom;
	public int AttackingOn;
	public int ActionType = -1;
	public int destinationX = -1;
	public int destinationY = -1;
	public int destinationID = -1;
	public int destinationRange = 1;
	public boolean WalkingTo;
	public int TreeHP = 20;
	public int TreeX;
	public int TreeY;
	public int logID;
	public int WCxp;
	public boolean IsWcing;
	public int tradeRequest;
	public int tradeDecline;
	public int tradeWith;
	public int tradeStatus;
	public boolean tradeUpdateOther;
	public boolean tradeOtherDeclined;
	public int[] playerTItems = new int[28];
	public int[] playerTItemsN = new int[28];
	public int[] playerOTItems = new int[28];
	public int[] playerOTItemsN = new int[28];
	public boolean takeAsNote;
	
	public abstract void initialize();

	public abstract void update();

	public int playerId = -1;

	public String playerName = null;
	public String playerPass = null;
	public boolean isRunning2;
	public boolean stoprunning;

	public int Rights;

	public PlayerManager handler = null;

	public int maxItemAmount = 2147000000;

	public int[] playerItems = new int[28];
	public int[] playerItemsN = new int[28];

	public int playerBankSize = 350;
	public int[] bankItems = new int[800];
	public int[] bankItemsN = new int[800];
	public boolean bankNotes;

	public int pHead;
	public int pTorso;
	public int pArms;
	public int pHands;
	public int pLegs;
	public int pFeet;
	public int pBeard;
	public int pEmote = 0x328;
	public int pWalk = 0x333;
	public boolean apset;

	public int headIcon;

	public int[] playerEquipment = new int[14];
	public int[] playerEquipmentN = new int[14];
	
	public int playerHat = 0;
	public int playerCape = 1;
	public int playerAmulet = 2;
	public int playerWeapon = 3;
	public int playerChest = 4;
	public int playerShield = 5;
	public int playerLegs = 7;
	public int playerHands = 9;
	public int playerFeet = 10;
	public int playerRing = 12;
	public int playerArrows = 13;

	public int playerAttack = 0;
	public int playerDefence = 1;
	public int playerStrength = 2;
	public int playerHitpoints = 3;
	public int playerRanged = 4;
	public int playerPrayer = 5;
	public int playerMagic = 6;
	public int playerCooking = 7;
	public int playerWoodcutting = 8;
	public int playerFletching = 9;
	public int playerFishing = 10;
	public int playerFiremaking = 11;
	public int playerCrafting = 12;
	public int playerSmithing = 13;
	public int playerMining = 14;
	public int playerHerblore = 15;
	public int playerAgility = 16;
	public int playerThieving = 17;
	public int playerSlayer = 18;
	public int playerFarming = 19;
	public int playerRunecrafting = 20;

	public int i;

	public int[] playerLevel = new int[25];
	public int[] playerXP = new int[25];
	public int currentHealth = playerLevel[playerHitpoints];
	public int maxHealth = playerLevel[playerHitpoints];
	public int summonedNPCS;

	public final static int maxPlayerListSize = PlayerManager.maxPlayers;
	public Player playerList[] = new Player[maxPlayerListSize];
	public int playerListSize = 0;
	public byte playerInListBitmap[] = new byte[(PlayerManager.maxPlayers+7) >> 3];

	public final static int maxNPCListSize = NPCManager.maxNPCs;
	public NPC npcList[] = new NPC[maxNPCListSize];
	public int npcListSize = 0;
	public byte npcInListBitmap[] = new byte[(NPCManager.maxNPCs+7) >> 3];

	public boolean withinDistance(Player otherPlr) {
		if(heightLevel != otherPlr.heightLevel) return false;
		int deltaX = otherPlr.absX-absX, deltaY = otherPlr.absY-absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(NPC npc) {
		if (heightLevel != npc.heightLevel) 
			return false;
			if (npc.NeedRespawn == true) 
				return false;
				int deltaX = npc.absX-absX, deltaY = npc.absY-absY;
				return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}


	public int mapRegionX, mapRegionY;
	public int absX, absY;
	public int currentX, currentY;
	public int heightLevel;

	public boolean updateRequired = true;

	public static final int walkingQueueSize = 50;
	public int walkingQueueX[] = new int[walkingQueueSize], walkingQueueY[] = new int[walkingQueueSize];
	public int wQueueReadPtr = 0;
	public int wQueueWritePtr = 0;
	public boolean isRunning = false;
	public int teleportToX = 2957, teleportToY = 3216;


	public void resetWalkingQueue() {
		wQueueReadPtr = wQueueWritePtr = 0;
		for(int i = 0; i < walkingQueueSize; i++) {
			walkingQueueX[i] = currentX;
			walkingQueueY[i] = currentY;
		}
	}

	public void addToWalkingQueue(int x, int y) {
		int next = (wQueueWritePtr+1) % walkingQueueSize;
		if(next == wQueueWritePtr) return;
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next; 
	}

	public boolean didTeleport = false;
	public boolean mapRegionDidChange = false;
	public int dir1 = -1, dir2 = -1;
	public int poimiX = 0, poimiY = 0;

	public void updateThisPlayerMovement(Stream str) {
		if (mapRegionDidChange) {
			str.createFrame(73);
			str.writeWordA(mapRegionX+6);
			str.writeWord(mapRegionY+6);
		}

		if (isInWild() || InWilderness && !isInWild()) {
			getWildernessLevel();
		}

		if (didTeleport) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			str.writeBits(2, 3);
			str.writeBits(2, heightLevel);
			str.writeBits(1, 1);
			str.writeBits(1, (updateRequired) ? 1 : 0);
			str.writeBits(7, currentY);
			str.writeBits(7, currentX);
			return ;
		}

		if(dir1 == -1) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			if(updateRequired) {
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
			if (DirectionCount < 50) {
				DirectionCount++;
			}
		} else {
			DirectionCount = 0;
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);

			if(dir2 == -1) {
				str.writeBits(2, 1);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				if(updateRequired) str.writeBits(1, 1);
				else str.writeBits(1, 0);
			} else {
				str.writeBits(2, 2);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
				if(updateRequired) str.writeBits(1, 1);
				else str.writeBits(1, 0);
				if (playerEnergy > 0) {
					//playerEnergy--;
				} else {
					isRunning2 = false;
					stoprunning = true;
				}
			} 
		}

	}

	public void updatePlayerMovement(Stream str) {
		if(dir1 == -1) {
			if(updateRequired || chatTextUpdateRequired) {
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			}
			else str.writeBits(1, 0);
		} else if(dir2 == -1) {
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(1, (updateRequired || chatTextUpdateRequired) ? 1: 0);
		} else {
			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
			str.writeBits(1, (updateRequired || chatTextUpdateRequired) ? 1: 0);
		}
	}


	public static Client Client = null;
	public boolean dropsitem;

	public void removeequipped()
	{
		dropsitem = true;
	}
	public void setPlrAnimation(int i) {
		pEmote = i;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public byte cachedPropertiesBitmap[] = new byte[(PlayerManager.maxPlayers+7) >> 3];

	public void addNewNPC(NPC npc, Stream str, Stream updateBlock)
	{
		int id = npc.npcId;
		npcInListBitmap[id >> 3] |= 1 << (id&7);
		npcList[npcListSize++] = npc;

		str.writeBits(14, id);
		
		int z = npc.absY-absY;
		if(z < 0) z += 32;
		str.writeBits(5, z);
		z = npc.absX-absX;
		if(z < 0) z += 32;
		str.writeBits(5, z);

		str.writeBits(1, 0);
		str.writeBits(12, npc.npcType);
		
		boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;	
		str.writeBits(1, 1);
	}
		
	public void addNewPlayer(Player plr, Stream str, Stream updateBlock) {
		int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id&7);
		playerList[playerListSize++] = plr;

		str.writeBits(11, id);

		str.writeBits(1, 1);
		boolean savedFlag = plr.appearanceUpdateRequired;
		boolean savedUpdateRequired = plr.updateRequired;
		plr.appearanceUpdateRequired = true;
		plr.updateRequired = true;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.appearanceUpdateRequired = savedFlag;
		plr.updateRequired = savedUpdateRequired;


		str.writeBits(1, 1);
		int z = plr.absY-absY;
		if(z < 0) z += 32;
		str.writeBits(5, z);
		z = plr.absX-absX;
		if(z < 0) z += 32;
		str.writeBits(5, z);
	}


	protected boolean appearanceUpdateRequired = true;

	protected static Stream playerProps;
	static {
		playerProps = new Stream(new byte[100]);
	}
	protected void appendPlayerAppearance(Stream str)
	{
		playerProps.currentOffset = 0;

		playerProps.writeByte(playerLook[0]);
		playerProps.writeByte(headIcon);


	if (isNpc == false) {
		if (playerEquipment[playerHat] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerHat]);
		} else {
			playerProps.writeByte(0);
		}
		if (playerEquipment[playerCape] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerCape]);
		} else {
			playerProps.writeByte(0);
		}
		if (playerEquipment[playerAmulet] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerAmulet]);
		} else {
			playerProps.writeByte(0);
		}
		if (playerEquipment[playerWeapon] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerWeapon]);
		} else {
			playerProps.writeByte(0);
		}
		if (playerEquipment[playerChest] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerChest]);
		} else {
			playerProps.writeWord(0x100+pTorso);
		}
		if (playerEquipment[playerShield] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerShield]);
		} else {
			playerProps.writeByte(0);
		}
		if (!Equipment.isPlate(playerEquipment[playerChest])) {
			playerProps.writeWord(0x100+pArms);
		} else {
			playerProps.writeByte(0);
		}
		if (playerEquipment[playerLegs] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerLegs]);
		} else {
			playerProps.writeWord(0x100+pLegs);
		}
		if (!Equipment.isHelm(playerEquipment[playerHat]) && !Equipment.isFullMask(playerEquipment[playerHat])) {
			playerProps.writeWord(0x100 + pHead);
		} else {
			playerProps.writeByte(0);
		}
		if (playerEquipment[playerHands] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerHands]);
		} else {
			playerProps.writeWord(0x100+pHands);
		}
		if (playerEquipment[playerFeet] > 1) {
			playerProps.writeWord(0x200 + playerEquipment[playerFeet]);
		} else {
			playerProps.writeWord(0x100+pFeet);
		}
		if (!Equipment.isFullMask(playerEquipment[playerHat]) && (playerLook[0] != 1))
			playerProps.writeWord(0x100 + pBeard);
		else
			playerProps.writeByte(0);
		} else {
			playerProps.writeWord(-1);
			playerProps.writeWord(npcId);
	}

		playerProps.writeByte(playerLook[1]);
		playerProps.writeByte(playerLook[2]);
		playerProps.writeByte(playerLook[3]);
		playerProps.writeByte(playerLook[4]);
		playerProps.writeByte(playerLook[5]);

		playerProps.writeWord(pEmote);
		playerProps.writeWord(0x337);
		playerProps.writeWord(playerSEW);
		playerProps.writeWord(0x334);
		playerProps.writeWord(0x335);
		playerProps.writeWord(0x336);
		playerProps.writeWord(playerSER);

		playerProps.writeQWord(Misc.playerNameToInt64(playerName));


		int mag = (int)((double)(getLevelForXP(playerXP[4])) * 1.5);
		int ran = (int)((double)(getLevelForXP(playerXP[6])) * 1.5);
		int attstr = (int)((double)(getLevelForXP(playerXP[0])) + (double)(getLevelForXP(playerXP[2])));

		int combatLevel = 0;
		if (ran > attstr) {
			combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) + ((double)(getLevelForXP(playerXP[5])) * 0.125) + ((double)(getLevelForXP(playerXP[6])) * 0.4875));
		} else if (mag > attstr) {
			combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) + ((double)(getLevelForXP(playerXP[5])) * 0.125) + ((double)(getLevelForXP(playerXP[4])) * 0.4875));
		} else {
			combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) + ((double)(getLevelForXP(playerXP[5])) * 0.125) + ((double)(getLevelForXP(playerXP[0])) * 0.325) + ((double)(getLevelForXP(playerXP[2])) * 0.325));
		}

		combat = combatLevel;
		playerProps.writeByte(combatLevel);
		playerProps.writeWord(0);
			str.writeByteC(playerProps.currentOffset);
			str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0);
		}
	protected boolean chatTextUpdateRequired = false;
	protected byte chatText[] = new byte[4096], chatTextSize = 0;
	protected int chatTextEffects = 0, chatTextColor = 0;
	protected void appendPlayerChatText(Stream str) {
		str.writeWordBigEndian(((chatTextColor&0xFF) << 8) + (chatTextEffects&0xFF));
		str.writeByte(Rights);
		str.writeByteC(chatTextSize);
		str.writeBytes_reverse(chatText, chatTextSize, 0);
	}
	public boolean update1Required = false;
	public int update1_1 = 0;
	public int update1_2 = 0;
	protected void appendUpdate1(Stream str) {
		str.writeWordBigEndian(update1_1);
		str.writeByteC(update1_2);
	}
	public void appendPlayerUpdateBlock(Stream str) {
		if(!updateRequired && !chatTextUpdateRequired) return ;
		int updateMask = 0;
		if(mask400update) updateMask |= 0x400;
		if(mask100update) updateMask |= 0x100;
		if(animationRequest != -1) updateMask |= 8;
		if(stringUpdateRequired) updateMask |= 4;
		if(chatTextUpdateRequired) updateMask |= 0x80;
		if(faceNPCupdate) updateMask |= 1;
		if(appearanceUpdateRequired) updateMask |= 0x10;
		if (FocusPointX != -1) updateMask |= 2;
		if(hitUpdateRequired) updateMask |= 0x200;
		if(dirUpdateRequired) updateMask |= 0x40;
		if(dirUpdate2Required) updateMask |= 2;
		if(animationRequest != -1) updateMask |= 8;

		if(updateMask >= 0x100) {

			updateMask |= 0x40;
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		}
		else str.writeByte(updateMask);

		if(mask400update)   appendMask400Update(str);
		if(mask100update)   appendMask100Update(str);
		if(animationRequest != -1) appendAnimationRequest(str);
		if(stringUpdateRequired) appendString(str);
		if(chatTextUpdateRequired) appendPlayerChatText(str);
		if(faceNPCupdate)   appendFaceNPCUpdate(str);
		if(appearanceUpdateRequired) appendPlayerAppearance(str);
		if (FocusPointX != -1) appendSetFocusDestination(str);
		if(hitUpdateRequired) appendHitUpdate(str);
		if(dirUpdateRequired) appendDirUpdate(str);
		if(dirUpdate2Required) appendDirUpdate2(str);

	}

	public void clearUpdateFlags() {
		FocusPointX = FocusPointY = -1;
		updateRequired = false;
		stringUpdateRequired = false;
		chatTextUpdateRequired = false;
		appearanceUpdateRequired = false;
		hitUpdateRequired = false;
		dirUpdateRequired = false;
		animationRequest = -1;
		dirUpdate2Required = false;
		faceNPCupdate = false;
		faceNPC = 65535;
		mask100update = false;
		update1Required = false;
		IsStair = false;
	}



	protected static int newWalkCmdX[] = new int[walkingQueueSize];
	protected static int newWalkCmdY[] = new int[walkingQueueSize];
	protected static int tmpNWCX[] = new int[walkingQueueSize];
	protected static int tmpNWCY[] = new int[walkingQueueSize];
	protected static int newWalkCmdSteps = 0;
	protected static boolean newWalkCmdIsRunning = false;
	protected static int travelBackX[] = new int[walkingQueueSize];
	protected static int travelBackY[] = new int[walkingQueueSize];
	protected static int numTravelBackSteps = 0;

	public void preProcessing() {
		newWalkCmdSteps = 0;
	}

	public abstract void process();
	public abstract boolean packetSending();
	public boolean following = false;
	public void postProcessing()
	{
		if(newWalkCmdSteps > 0) {
			int OldcurrentX = currentX;
			int OldcurrentY = currentY;
			for(i = 0; i < playerFollow.length; i++) {
				if (playerFollow[i] != -1 && following == true) {
					PlayerManager.players[playerFollow[i]].newWalkCmdSteps = (newWalkCmdSteps + 1);
					for(int j = 0; j < newWalkCmdSteps; j++) {
						PlayerManager.players[playerFollow[i]].newWalkCmdX[(j + 1)] = newWalkCmdX[j];
						PlayerManager.players[playerFollow[i]].newWalkCmdY[(j + 1)] = newWalkCmdY[j];
					}
					PlayerManager.players[playerFollow[i]].newWalkCmdX[0] = OldcurrentX;
					PlayerManager.players[playerFollow[i]].newWalkCmdY[0] = OldcurrentY;
					PlayerManager.players[playerFollow[i]].poimiX = OldcurrentX;
					PlayerManager.players[playerFollow[i]].poimiY = OldcurrentY;
				}
				int firstX = newWalkCmdX[0], firstY = newWalkCmdY[0];

				int lastDir = 0;
				boolean found = false;
				numTravelBackSteps = 0;
				int ptr = wQueueReadPtr;
				int dir = Misc.direction(currentX, currentY, firstX, firstY);
				if(dir != -1 && (dir&1) != 0) {
					do {
						lastDir = dir;
						if(--ptr < 0) ptr = walkingQueueSize-1;

						travelBackX[numTravelBackSteps] = walkingQueueX[ptr];
						travelBackY[numTravelBackSteps++] = walkingQueueY[ptr];
						dir = Misc.direction(walkingQueueX[ptr], walkingQueueY[ptr], firstX, firstY);
						if(lastDir != dir) {
							found = true;
							break;
						}

					} while(ptr != wQueueWritePtr);
				}
				else found = true;

			if(!found) {
				disconnected = true;
			} else {
				wQueueWritePtr = wQueueReadPtr;

				addToWalkingQueue(currentX, currentY);

				if(dir != -1 && (dir&1) != 0) {

					for(int i = 0; i < numTravelBackSteps-1; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
					int wayPointX2 = travelBackX[numTravelBackSteps-1], wayPointY2 = travelBackY[numTravelBackSteps-1];
					int wayPointX1, wayPointY1;
					if(numTravelBackSteps == 1) {
						wayPointX1 = currentX;
						wayPointY1 = currentY;
					}
					else {
						wayPointX1 = travelBackX[numTravelBackSteps-2];
						wayPointY1 = travelBackY[numTravelBackSteps-2];
					}

					dir = Misc.direction(wayPointX1, wayPointY1, wayPointX2, wayPointY2);
					if(dir == -1 || (dir&1) != 0) {
						printOut("Error - The walking queue is corrupt!");
					}
					else {
						dir >>= 1;
						found = false;
						int x = wayPointX1, y = wayPointY1;
						while(x != wayPointX2 || y != wayPointY2) {
							x += Misc.directionDeltaX[dir];
							y += Misc.directionDeltaY[dir];
							if((Misc.direction(x, y, firstX, firstY)&1) == 0) {
								found = true;
								break;
							}
						}
						if(!found) {
							printOut("Error - Internal error: Unable to determine connection vertex!");
						}
						else addToWalkingQueue(wayPointX1, wayPointY1);
					}
				}
				else {
					for(int i = 0; i < numTravelBackSteps; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
				}

				for(int i = 0; i < newWalkCmdSteps; i++) {
					addToWalkingQueue(newWalkCmdX[i], newWalkCmdY[i]);
				}

			}
			isRunning = newWalkCmdIsRunning || isRunning2;

			for(i = 0; i < playerFollow.length; i++) {
				if (playerFollow[i] != -1 && PlayerManager.players[playerFollow[i]] != null) {
					PlayerManager.players[playerFollow[i]].postProcessing();
					}
				}
			}
		}
	}

	public void kick() {
		isKicked = true;
	}

	protected int hitDiff = 0;
	protected boolean hitUpdateRequired = false;
	protected boolean IsDead = false;
	protected int NewHP = 10;
	protected boolean SafeMyLife = false;
	protected boolean IsStair = false;
	protected boolean IsDeadTeleporting = false;
	protected void appendHitUpdate(Stream str) {		
		try {
			str.writeByte(hitDiff);
			if (hitDiff > 0 && !newhptype && !poisondmg) {
				str.writeByteS(1);
			} else if (hitDiff > 0 && poisondmg) {
				str.writeByteS(2);
			} else if (hitDiff > 0 && newhptype) {
				str.writeByteS(hptype);
			} else {
				str.writeByteS(0);
			}
			if (hitDiff > 0) {
				NewHP = playerLevel[3] - hitDiff;
				if (NewHP <= 0)
					NewHP = 0;
				UpdateHP = true;
			}
			str.writeByte(NewHP);
			str.writeByteC(getLevelForXP(playerXP[playerHitpoints]));
			poisondmg = false;
			newhptype = false;
			hptype = 0;
			inCombat = true;
			CombatDelay = System.currentTimeMillis();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		if (exp > 13034430)
			return 99;
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

	public int animationRequest = -1, animationWaitCycles = 0;
	protected boolean animationUpdateRequired = false;
	public void startAnimation(int animIdx)
	{
		animationRequest = animIdx;
		animationWaitCycles = 0;
	}
	public void appendAnimationRequest(Stream str)
	{
		str.writeWordBigEndian((animationRequest==-1) ? 65535 : animationRequest);
		str.writeByteC(animationWaitCycles);
	}
	public int playerEmotionReq = -1;
	public int playerEmotionDelay = 0;
	public void appendEmotionUpdate(Stream str) {
		str.writeWordBigEndian((playerEmotionReq==-1) ? 65535 : playerEmotionReq);
		str.writeByteC(playerEmotionDelay);
	}
        public int mask1var = 0;
        protected boolean mask1update = false;
        public void appendMask1Update(Stream str) {
                str.writeWordBigEndian(mask1var);
        }
        public void faceNPC(int index) {
                faceNPC = index;
                faceNPCupdate = true;
                updateRequired = true;
        }
        protected boolean faceNPCupdate = false;
        public int faceNPC = -1;
        public void appendFaceNPCUpdate(Stream str) {
                str.writeWordBigEndian(faceNPC);
        }
        public int mask100var1 = 0;
        public int mask100var2 = 0;
        protected boolean mask100update = false;
        public void appendMask100Update(Stream str) {
                str.writeWordBigEndian(mask100var1);
                str.writeDWord(mask100var2);
        }
        public int m4001 = 0;
        public int m4002 = 0;
        public int m4003 = 0;
        public int m4004 = 0;
        public int m4005 = 0;
        public int m4006 = 0;
        public int m4007 = 0;
        protected boolean mask400update = false;
        public void appendMask400Update(Stream str) {
                str.writeByteA(m4001);
                str.writeByteA(m4002);
                str.writeByteA(m4003);
                str.writeByteA(m4004);
                str.writeWordA(m4005);
                str.writeWordBigEndianA(m4006);
                str.writeByteA(m4007);
        }
        public String Text = "testing update mask string";
        protected boolean stringUpdateRequired = false;
        public void appendString(Stream str) {
		str.writeString(Text);
        }
	public void appendDirUpdate2(Stream str) {
		str.writeWordBigEndianA(viewToX);
		str.writeWordBigEndian(viewToY);
	}          
	public void appendDirUpdate(Stream str) {
		if (playerMD != -1) {
			str.writeWord(playerMD);
			playerMD = -1;
		}
	}

	public void anim(int i) {
		startAnimation(i);
	}
	public boolean Shield(int ID) {
		if (ID == 1171 || ID == 1173 || ID == 1175 || ID == 1177 || ID == 1179 || ID == 1181 || ID == 1183 || ID == 1185 || ID == 1187 || ID == 1189 || ID == 1191 || ID == 1193 || ID == 1195 || ID == 1197 || ID == 1199 || ID == 1201 || ID == 1540 || ID == 2589 || ID == 2597 || ID == 2603 || ID == 2611 || ID == 2621 || ID == 2629 || ID == 2659 || ID == 2667 || ID == 2675 || ID == 2890 || ID == 3122 || ID == 3488 || ID == 3622 || ID == 2774 || ID == 3758 || ID == 4072 || ID == 4156 || ID == 4224 || ID == 4225 || ID == 4226 || ID == 4227 || ID == 4228 || ID == 4229 || ID == 4230 || ID == 4231 || ID == 4232 || ID == 4233 || ID == 4234 || ID == 4235 || ID == 4507 || ID == 4512 || ID == 6524 || ID == 6631 || ID == 6633 || ID == 2774 || ID == 3901 || ID == 3904 || ID == 7601 || ID == 3622) {
			return true;
		}
		return false;
	}
	public int BlockAnim(int id) {
		if (Shield(playerEquipment[playerShield])) { 
			return 1156;
		} else if (id == 4755) {
			return 2063;
		} else if (id == 4151) {
			return 1659;
		} else if (id == 4675 || id == 3204 || id == 6562 || id == 6525 || id == 6526 || id == 4170 || id == 2827 || id == 2821 || id == 2815 || id == 6914 || id == 6912 || id == 6910 || id == 6908 || id == 4726) {
			return 383;
		} else if (id == 4153 || id == 6528 || id == 1419) {
			return 1666;
		} else if (id == 4710) {
			return 2079;
		} else if (id == 4587) {
			return 404;
		} else {
			return 424;
		}
	}
	public int RunAnim(int id) {
		if (id == 4151) {
			return 1661;
		}
		if (id == 2761) {
			return 2251;
		}
		if (id == 7158 || id == 1319) {
			return 2563;
		}
		if (id == 4734) {
			return 2077;
		}
		if (id == 4153 || id == 1419) {
			return 1664;
		} else {
			return 0x338;
		}
	}

	public int WalkAnim(int id) {
		if (id == 4718) {
			return 2064;
		}
		if (id == 4039 || id == 4037 || id == 1379 || id == 3204 || id == 3202 || id == 1381 || id == 1383 || id == 1385 || id == 1387 || id == 1389 || id == 1391 || id == 1393 || id == 1395 || id == 1397 || id == 1399 || id == 1401 || id == 1403 || id == 145 || id == 1407 || id == 1409 || id == 3053 || id == 3054 || id == 4170 || id == 4675 || id == 4710 || id == 6526 || id == 4726 || id == 6562 || id == 6563 || id == 6914 || id == 5730) {
			return 1146;
		}
		if (id == 2761) {
			return 2258;
		}
		if (id == 4755) {
			return 2060;
		}
		if (id == 6914) {
			return 1146;
		}
		if (id == 4153 || id == 1419) {
			return 1663;
		}
		if (id == 7158 || id == 1319) {
			return 2562;
		}
		if (id == 2746 || id == 2745 || id == 2747 || id == 2748 || id == 6528 || id == 15335|| id == 15351 || id == 14915 || id == 7630) {
			return 2064;
		}
		if (id == 4151) {
			return 1660;
		}
		if (id == 4734) {
			return 2076;
		} else {
			return 0x333;
		}
	}

	public int StandAnim(int id) {
		if (id == 4734) {
			return 2074;
		}
		if (id == 4755) {
			return 2061;
		}
		if (id == 2761) {
			return 2257;
		}
		if (id == 4153 || id == 1419) {
			return 1662;
		}
		if (id == 1305 || id == 1379 || id == 1381 || id == 1383 || id == 1385 || id == 1387 || id == 1389 || id == 1391 || id == 1393 || id == 1395 || id == 1397 || id == 1399 || id == 1401 || id == 1403 || id == 145 || id == 1407 || id == 1409 || id == 3053 || id == 3054 || id == 4170 || id == 4675 || id == 7634 || id == 4710 || id == 6526 || id == 4726 || id == 6562 || id == 6563 || id == 5730 || id == 3204 || id == 3202 || id == 6914) {
			return 809;
		}
		if (id == 7158 || id == 1319) {
			return 2561;
		}
		if (id == 6528 || id == 2745 || id == 2746 || id == 2747 || id == 2748 || id == 7630 || id == 4718) {
			return 2065;
		} else {
			return 0x328;
		}
	}
	public int AttackAnim() {
		if (playerEquipment[playerWeapon] == -1) {
			if (FightType == 2) {
				if (playerEquipment[playerWeapon] == 5698) {
					if (FightType == 3) {
						return 395;
					}
				}
				if (hasCrystalBow() || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 853) {
					return 426;
				}
				return 423;
			} else {
				return 422;
		}
	}
	if (playerEquipment[playerWeapon] == 4151) {
		return 1658;
	}
	if (playerEquipment[playerWeapon] == 4734) {
		return 2075;
	}
	if (playerEquipment[playerWeapon] == 1291) {
		return 386;
	}
	if (playerEquipment[playerWeapon] == 1321 || playerEquipment[playerWeapon] == 1323 || playerEquipment[playerWeapon] == 1291 || playerEquipment[playerWeapon] == 1325 || playerEquipment[playerWeapon] == 1327 || playerEquipment[playerWeapon] == 1329 || playerEquipment[playerWeapon] == 1327 || playerEquipment[playerWeapon] == 1321 || playerEquipment[playerWeapon] == 4587 || playerEquipment[playerWeapon] == 746 || playerEquipment[playerWeapon] == 6739 || playerEquipment[playerWeapon] == 1305 || playerEquipment[playerWeapon] == 1333) {
		return 451;
	}
	if (playerEquipment[playerWeapon] == 3202 || playerEquipment[playerWeapon] == 3204) {
		return 440;
	}
	if(hasCrystalBow() || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 7603 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 6724 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 853) {
		return 426;
	}
	if (playerEquipment[playerWeapon] == 4153) {
		return 1665;
	}
	if (playerEquipment[playerWeapon] == 6528) {
		return 2661;
	}
	if (playerEquipment[playerWeapon] == 1373 || playerEquipment[playerWeapon] == 1377 || playerEquipment[playerWeapon] == 1434 || playerEquipment[playerWeapon] == 5018) {
		return 1833;
	}
	if (playerEquipment[playerWeapon] == 5730 || playerEquipment[playerWeapon] == 4726) {
		return 2080;
	}
	if (playerEquipment[playerWeapon] == 4718) {
		return 2066;
	}
	if (playerEquipment[playerWeapon] == 4747) {
		return 2068;
	}
	if (playerEquipment[playerWeapon] == 4755) {
		return 2062;
	}
	if (playerEquipment[playerWeapon] == 2761) {
		return 427;
	}
	if (playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231 || playerEquipment[playerWeapon] == 5680 || playerEquipment[playerWeapon] == 5698) {
		return 402;
	}
	if (playerEquipment[playerWeapon] == 6609 || playerEquipment[playerWeapon] == 1307 || playerEquipment[playerWeapon] == 1309 || playerEquipment[playerWeapon] == 1311 || playerEquipment[playerWeapon] == 1313 || playerEquipment[playerWeapon] == 1315 || playerEquipment[playerWeapon] == 7158 || playerEquipment[playerWeapon] == 1317 || playerEquipment[playerWeapon] == 1319) {
		return 407;
	}
	if (playerEquipment[playerWeapon] == 2747 || playerEquipment[playerWeapon] == 2746 || playerEquipment[playerWeapon] == 2748 || playerEquipment[playerWeapon] == 2745 || playerEquipment[playerWeapon] == 7630) {
		return 3496;
	}
	if (playerEquipment[playerWeapon] == 4675 || playerEquipment[playerWeapon] == 6914 || playerEquipment[playerWeapon] == 3053) {
		return 401;
	}
	if (playerEquipment[playerWeapon] == 1419) {
		return 408;
	}
	if (playerEquipment[playerWeapon] == 4566) {
			return 1833;
		} else {
			return 0x326;
		}
	}
	public int untradable[] = { 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223, 1590, 2773, 7601, 3490, 3491, 3492, 3511, 3494, 3495, 7602, 7618, 7619, 7620, 2677, 2678, 2679, 2680, 2681, 2682, 2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690, 2691, 2692, 2693, 2694, 2695, 2696, 2697, 2698, 2699, 2700, 2701, 2702, 2703, 2704, 2705, 2706, 2708, 2709, 2710, 2711, 2712, 2713, 2714, 2715, 2716, 2717, 2718, 2719, 2720, 2721, 2722, 2723, 2724, 2725, 2726, 2727, 2728, 2729, 2730, 2731, 2732, 2733, 2734, 2735, 2736, 2737, 2738, 2739, 2740, 2741, 2742, 2743, 2744, 2770, 2771, 2772, 3630, 3631, 3632 };
	public String BonusName[] = { "Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush", "Magic", "Range", "Strength", "Prayer" };
	public String Illegal[] = {"~", "_", "!", "@", "#", "$", "(", ")", ";", "'", ",", ".", "/", "?", ":", "[", "{", "]", "}", "|", "%", "^", "&", "*", "  ", "£"};
	public boolean[] IsDropped = new boolean[Server.item.MaxDropItems];
	public boolean[] MustDelete = new boolean[Server.item.MaxDropItems];
	public boolean IsDropping;
	public abstract boolean isinpm(long l);
	public abstract void loadpm(long l, int world);
	public abstract void pmupdate(int pmid, int world);
	public int Privatechat;
	public abstract void sendpm(long name,int rights,byte[] chatmessage, int messagesize);
	public long friends[] = new long[200];
	public long ignores[] = new long[100];
	public boolean IsPMLoaded;
	public String playerLastConnect;
	public int Pin1 = -1, Pin2 = -1, Pin3 = -1, Pin4 = -1;
	public int Entered1Pin, Entered2Pin, Entered3Pin, Entered4Pin, EnteringPin;
	public boolean EnteredPin, RemovePin;
	public int playerEnergy = 100;
	public int playerEnergyGian;
	public int playerLook[] = new int[6];
	public boolean IsUsingSkill;
	public int playerBonus[] = new int[12];
	public int FightType = 1;
	public int playerMaxHit;
	public boolean hasLevel = true;
	public boolean hasRunes = true;
	public int playerSE = 0x328;
	public int playerSEW = 0x333;
	public int playerSER = 0x338;
	public int playerSEA = 0x326;
	public int playerMD = -1;
        public int viewToX = -1; 
        public int viewToY = -1;
	public int attacknpc = -1;
	public int playerFollow[] = new int[PlayerManager.maxPlayers];
        public int playerFollowID = -1;
	public int specialAmount = 100;
	public int packetType = -1;
	public int OriginalWeapon = -1;
	public int OriginalShield = -1;
	public int returnCode = 2;
	public Stream inStream = null, outStream = null;
	public int FocusPointX = -1, FocusPointY = -1;	
	public Player followPlayer = null;
	public String SkulledOn;
	protected boolean dirUpdateRequired;
	protected boolean dirUpdate2Required;
	public boolean LoggedOut = true;
	public boolean keep2773, keep7601, keep7602;
	public int XremoveSlot, XinterfaceID, XremoveID;
	public int Bank;
	public int Shop;
	public boolean InWilderness, Ahrim, Dharok, Torag, Karil, Guthan, Verac, Barrows, Summoned;
	public boolean IsCutting, WannePickup, IsInWilderness, IsAttacking, IsAttackingNPC, IsShopping, UpdateShop, RebuildNPCList;
	public boolean SendDialogue, IsBanking, TradeConfirmed, Poisoned, usingSpecial, barbassault, teleblock, Skulled;
	public boolean Slayer, noPrayer, drainPray, defLow, strLow, atkLow, defMid, strMid, atkMid, defHigh, strHigh, atkHigh, rapidRest, rapidHeal, protItem, protMage, protRange, protMelee, ret, redempt, smite, chiv, piety;
	public boolean Random, infinityset, healers, Cooking, JadRange, JadMage, canVengeance, magesbook, masterwand, ancientstaff, Cant, DDS2Damg, HasArrows, wearing;
	public boolean itemSlot1, itemSlot2, itemSlot3, itemSlot4, newhptype, poisondmg, noRunes, followPlayerIdle;
	public boolean Questing, Fishing, Stuck, UpdateHP;
	public boolean Block, Entangle, Accepted, Teleporting, JadGFX, Ranging, Maging, inCombat, AncientTeleport, InterfaceTeleport, JadAttacking;
	public int SpawnBrother, q1, WildyLevel, actionButtonId, skillX, skillY, SkillID, AttackID, playerLastLogin, starter;
	public int EnemyY, EnemyX, acients, packetSize, spellID, Tradecompete, Publicchat, lowMemoryVersion, timeOutCounter;
	public int pCHead, pCBeard, pCTorso, pCArms, pCHands, pCLegs, pCFeet, pColor, gender, playerItemAmountCount, friendslot;
	public int AtkPray, DefPray, Death, FishId, Entangled, Fighting, mageGfx, mageHit, hptype;
	public int itemKept1, itemKept2, itemKept3, itemKept4, itemKept1Slot, itemKept2Slot, itemKept3Slot, itemKept4Slot;
	public int Log, Charges, vengeanceSpell, cluescroll, zammymage, Blackmarks;
	public int prayerDrain, prayerTimer, newDrain, PrayerTimer, oldDrain;
	public int WanneTrade, WanneTradeWith, MyShopID, spellbook;
	public int Glory, DirectionCount, Direction;
	public int BarrowX, BarrowY, RandomX, RandomY, TeleportX, TeleportY;
	public int RandomH, Height, Teleport, Teleportgfx;
	public int JadAttack, healersCount, JadKilled;
	public int Dialogue, ChatWith, SendChat;
	public int apickupid, apickupx, apickupy, publicDroppendItem;
	public int BarrowskillCount, Barbpoints, SarakillCount, magearenapoints;
	public long VengeanceDelay, FoodDelay, CombatDelay, EntangleDelay, PoisonDelay, SpecialDelay, StatDelay, SaveDelay, InterfaceDelay, JadDelay, RangeDelay, MageDelay, ActionDelay, PkingDelay, DeathDelay, TeleportDelay;
}
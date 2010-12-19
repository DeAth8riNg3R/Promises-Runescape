class Text {
	void LoadText(Client c) {
		c.sendString("Bank pin", 14923);
		c.sendString("1", 14884);
		c.sendString("2", 14885);
		c.sendString("3", 14886);
		c.sendString("4", 14887);
		c.sendString("5", 14888);
		c.sendString("6", 14889);
		c.sendString("7", 14890);
		c.sendString("8", 14891);
		c.sendString("9", 14892);
		if (c.q1 == 0) {
			c.sendString("", 7332);
		}
		if (c.q1 > 0 && c.q1 < 14) {
			c.sendString("", 7332);
		}
		if (c.q1 == 14) {
			c.sendString("", 7332);
			c.sendString("", 663);
		} else {
			c.sendString("", 663);
		}
		c.sendString("", 6968);
		c.sendString("", 6969);
		c.sendString("", 6970);
		c.sendString("", 6971);
		c.sendString("", 6972);
		c.sendString("", 6973);
		c.sendString("", 6974);
		c.sendString("", 6975);
		c.sendString("  Quest Journal", 640);
		c.sendString("@or1@Items kept on Death", 7343);
		c.sendString("", 4963);
		c.sendString("", 4964);
		c.sendString("", 4965);
		c.sendString("", 4967);
		c.sendString("", 4968);
		c.sendString("", 4969);
		c.sendString("@or1@Items Kept on Death", 6825);
		c.sendString("   Ok", 6827);
		c.sendString("", 6839);
		c.sendString("", 6840);
		c.sendString("Items Kept", 6020);
		c.sendString("@or1@items will be lost upon death.", 6837);
		c.sendString("", 6838);
		c.sendString(""+c.playerEnergy+"%", 149);
		c.sendString("", 10361);
		c.sendString("", 10379);
		c.sendString("", 10378);
		c.sendString("", 10374);
		c.sendString("", 10375);
		c.sendString("", 6557);
		c.sendString("", 16159);
		c.sendString("", 10376);
		c.sendString("", 10377);
		c.sendString("", 6559);
		c.sendString("", 16160);
		c.sendString("", 2437);
		c.sendString("", 2438);
		c.sendString("", 12338);
		c.sendString("", 12339);
		c.sendString("Right click to buy, Choose ammount you want, Click item for the price.", 3903);
		c.sendString("Home Teleport", 13037);
		c.sendString("", 13047);
		c.sendString("", 13055);
		c.sendString("", 13063);
		c.sendString("", 13071);
		c.sendString("", 13081);
		c.sendString("", 13089);
		c.sendString("", 13097);
		c.sendString("", 13090);
		c.sendString("", 13098);
		c.sendString("Edgeville Teleport", 1300);
		c.sendString("A teleportation spell", 1301);
		c.sendString("Lumbridge Teleport", 1325);
		c.sendString("A teleportation spell", 1326);
		c.sendString("Falador Teleport", 1350);
		c.sendString("A teleportation spell", 1351);
		c.sendString("Camelot Teleport", 1382);
		c.sendString("A teleportation spell", 1383);
		c.sendString("Ardgoune Teleport", 1415);
		c.sendString("A teleportation spell", 1416);
		c.sendString("Watch Tower Teleport", 1454);
		c.sendString("A teleportation spell", 1455);
		c.sendString("", 7457);
		c.sendString("", 7458);
		c.sendString("", 18472);
		c.sendString("", 18473);
		c.sendString("" + c.playerXP[19] + "", 13921);
		c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);
	}
}
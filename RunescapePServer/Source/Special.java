class Special {

	public static void fsBar(Client c, int id1, int id2, int id3) {
		c.outStream.createFrame(70);
		c.outStream.writeWord(id1);
		c.outStream.writeWordBigEndian(id2);
		c.outStream.writeWordBigEndian(id3);
	}

	public static void l33thax(Client c, int id) {
		c.outStream.createFrame(171);
		c.outStream.writeByte(0);
		c.outStream.writeWord(id);
		c.flushOutStream();
	}

	public static void special(Client c) {
		specialAttacks(c);
		specialAttacks2(c);
		specialAttacks3(c);
		specialAttacks4(c);
		specialAttacks5(c);
		specialAttacks6(c);
	}

	public static void specialAttacks(Client c) {
		if (c.specialAmount >= 0 && c.specialAmount <= 24) {
			c.sendFrame126("S P E C I A L  A T T A C K", 12335);
			fsBar(c, 0, 0, 12325);
			fsBar(c, 0, 0, 12326);
			fsBar(c, 0, 0, 12327);
			fsBar(c, 0, 0, 12328);
			fsBar(c, 0, 0, 12329);
			fsBar(c, 0, 0, 12330);
			fsBar(c, 0, 0, 12331);
			fsBar(c, 0, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 0, 0, 12328);
			fsBar(c, 0, 0, 12329);
			fsBar(c, 0, 0, 12330);
			fsBar(c, 0, 0, 12331);
			fsBar(c, 0, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 500, 0, 12328);
			fsBar(c, 500, 0, 12329);
			fsBar(c, 0, 0, 12330);
			fsBar(c, 0, 0, 12331);
			fsBar(c, 0, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 500, 0, 12328);
			fsBar(c, 500, 0, 12329);
			fsBar(c, 500, 0, 12330);
			fsBar(c, 500, 0, 12331);
			fsBar(c, 500, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 100 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 500, 0, 12328);
			fsBar(c, 500, 0, 12329);
			fsBar(c, 500, 0, 12330);
			fsBar(c, 500, 0, 12331);
			fsBar(c, 500, 0, 12332);
			fsBar(c, 500, 0, 12333);
			fsBar(c, 500, 0, 12334);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 0, 0, 12328);
			fsBar(c, 0, 0, 12329);
			fsBar(c, 0, 0, 12330);
			fsBar(c, 0, 0, 12331);
			fsBar(c, 0, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 500, 0, 12328);
			fsBar(c, 500, 0, 12329);
			fsBar(c, 0, 0, 12330);
			fsBar(c, 0, 0, 12331);
			fsBar(c, 0, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 500, 0, 12328);
			fsBar(c, 500, 0, 12329);
			fsBar(c, 500, 0, 12330);
			fsBar(c, 500, 0, 12331);
			fsBar(c, 500, 0, 12332);
			fsBar(c, 0, 0, 12333);
			fsBar(c, 0, 0, 12334);
		}
		if (c.specialAmount >= 100 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A C K", 12335);
			fsBar(c, 500, 0, 12325);
			fsBar(c, 500, 0, 12326);
			fsBar(c, 500, 0, 12327);
			fsBar(c, 500, 0, 12328);
			fsBar(c, 500, 0, 12329);
			fsBar(c, 500, 0, 12330);
			fsBar(c, 500, 0, 12331);
			fsBar(c, 500, 0, 12332);
			fsBar(c, 500, 0, 12333);
			fsBar(c, 500, 0, 12334);
		}
	}

	public static void specialAttacks2(Client c) {
		if (c.specialAmount >= 0 && c.specialAmount <= 24) {
			c.sendFrame126("S P E C I A L  A T T A C K", 7586);
			fsBar(c, 0, 0, 7576);
			fsBar(c, 0, 0, 7577);
			fsBar(c, 0, 0, 7578);
			fsBar(c, 0, 0, 7579);
			fsBar(c, 0, 0, 7580);
			fsBar(c, 0, 0, 7581);
			fsBar(c, 0, 0, 7582);
			fsBar(c, 0, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 0, 0, 7579);
			fsBar(c, 0, 0, 7580);
			fsBar(c, 0, 0, 7581);
			fsBar(c, 0, 0, 7582);
			fsBar(c, 0, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 500, 0, 7579);
			fsBar(c, 500, 0, 7580);
			fsBar(c, 0, 0, 7581);
			fsBar(c, 0, 0, 7582);
			fsBar(c, 0, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 500, 0, 7579);
			fsBar(c, 500, 0, 7580);
			fsBar(c, 500, 0, 7581);
			fsBar(c, 500, 0, 7582);
			fsBar(c, 500, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 100 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 500, 0, 7579);
			fsBar(c, 500, 0, 7580);
			fsBar(c, 500, 0, 7581);
			fsBar(c, 500, 0, 7582);
			fsBar(c, 500, 0, 7583);
			fsBar(c, 500, 0, 7584);
			fsBar(c, 500, 0, 7585);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 0, 0, 7579);
			fsBar(c, 0, 0, 7580);
			fsBar(c, 0, 0, 7581);
			fsBar(c, 0, 0, 7582);
			fsBar(c, 0, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 500, 0, 7579);
			fsBar(c, 500, 0, 7580);
			fsBar(c, 0, 0, 7581);
			fsBar(c, 0, 0, 7582);
			fsBar(c, 0, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 500, 0, 7579);
			fsBar(c, 500, 0, 7580);
			fsBar(c, 500, 0, 7581);
			fsBar(c, 500, 0, 7582);
			fsBar(c, 500, 0, 7583);
			fsBar(c, 0, 0, 7584);
			fsBar(c, 0, 0, 7585);
		}
		if (c.specialAmount >= 100 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A C K", 7586);
			fsBar(c, 500, 0, 7576);
			fsBar(c, 500, 0, 7577);
			fsBar(c, 500, 0, 7578);
			fsBar(c, 500, 0, 7579);
			fsBar(c, 500, 0, 7580);
			fsBar(c, 500, 0, 7581);
			fsBar(c, 500, 0, 7582);
			fsBar(c, 500, 0, 7583);
			fsBar(c, 500, 0, 7584);
			fsBar(c, 500, 0, 7585);
		}
	}

	public static void specialAttacks3(Client c) {
		if (c.specialAmount >= 0 && c.specialAmount <= 24) {
			c.sendFrame126("S P E C I A L  A T T A C K", 7611);
			fsBar(c, 0, 0, 7601);
			fsBar(c, 0, 0, 7602);
			fsBar(c, 0, 0, 7603);
			fsBar(c, 0, 0, 7604);
			fsBar(c, 0, 0, 7605);
			fsBar(c, 0, 0, 7606);
			fsBar(c, 0, 0, 7607);
			fsBar(c, 0, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 0, 0, 7604);
			fsBar(c, 0, 0, 7605);
			fsBar(c, 0, 0, 7606);
			fsBar(c, 0, 0, 7607);
			fsBar(c, 0, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 500, 0, 7604);
			fsBar(c, 500, 0, 7605);
			fsBar(c, 0, 0, 7606);
			fsBar(c, 0, 0, 7607);
			fsBar(c, 0, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 500, 0, 7604);
			fsBar(c, 500, 0, 7605);
			fsBar(c, 500, 0, 7606);
			fsBar(c, 500, 0, 7607);
			fsBar(c, 500, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 100 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 500, 0, 7604);
			fsBar(c, 500, 0, 7605);
			fsBar(c, 500, 0, 7606);
			fsBar(c, 500, 0, 7607);
			fsBar(c, 500, 0, 7608);
			fsBar(c, 500, 0, 7609);
			fsBar(c, 500, 0, 7610);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 0, 0, 7604);
			fsBar(c, 0, 0, 7605);
			fsBar(c, 0, 0, 7606);
			fsBar(c, 0, 0, 7607);
			fsBar(c, 0, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 500, 0, 7604);
			fsBar(c, 500, 0, 7605);
			fsBar(c, 0, 0, 7606);
			fsBar(c, 0, 0, 7607);
			fsBar(c, 0, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 500, 0, 7604);
			fsBar(c, 500, 0, 7605);
			fsBar(c, 500, 0, 7606);
			fsBar(c, 500, 0, 7607);
			fsBar(c, 500, 0, 7608);
			fsBar(c, 0, 0, 7609);
			fsBar(c, 0, 0, 7610);
		}
		if (c.specialAmount >= 100 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A C K", 7611);
			fsBar(c, 500, 0, 7601);
			fsBar(c, 500, 0, 7602);
			fsBar(c, 500, 0, 7603);
			fsBar(c, 500, 0, 7604);
			fsBar(c, 500, 0, 7605);
			fsBar(c, 500, 0, 7606);
			fsBar(c, 500, 0, 7607);
			fsBar(c, 500, 0, 7608);
			fsBar(c, 500, 0, 7609);
			fsBar(c, 500, 0, 7610);
		}
	}

	public static void specialAttacks4(Client c) {
		if (c.specialAmount >= 0 && c.specialAmount <= 24) {
			c.sendFrame126("S P E C I A L  A T T A C K", 7561);
			fsBar(c, 0, 0, 7551);
			fsBar(c, 0, 0, 7552);
			fsBar(c, 0, 0, 7553);
			fsBar(c, 0, 0, 7554);
			fsBar(c, 0, 0, 7555);
			fsBar(c, 0, 0, 7556);
			fsBar(c, 0, 0, 7557);
			fsBar(c, 0, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 0, 0, 7554);
			fsBar(c, 0, 0, 7555);
			fsBar(c, 0, 0, 7556);
			fsBar(c, 0, 0, 7557);
			fsBar(c, 0, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 500, 0, 7554);
			fsBar(c, 500, 0, 7555);
			fsBar(c, 0, 0, 7556);
			fsBar(c, 0, 0, 7557);
			fsBar(c, 0, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 500, 0, 7554);
			fsBar(c, 500, 0, 7555);
			fsBar(c, 500, 0, 7556);
			fsBar(c, 500, 0, 7557);
			fsBar(c, 500, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 100 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 500, 0, 7554);
			fsBar(c, 500, 0, 7555);
			fsBar(c, 500, 0, 7556);
			fsBar(c, 500, 0, 7557);
			fsBar(c, 500, 0, 7558);
			fsBar(c, 500, 0, 7559);
			fsBar(c, 500, 0, 7560);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 0, 0, 7554);
			fsBar(c, 0, 0, 7555);
			fsBar(c, 0, 0, 7556);
			fsBar(c, 0, 0, 7557);
			fsBar(c, 0, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 500, 0, 7554);
			fsBar(c, 500, 0, 7555);
			fsBar(c, 0, 0, 7556);
			fsBar(c, 0, 0, 7557);
			fsBar(c, 0, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 500, 0, 7554);
			fsBar(c, 500, 0, 7555);
			fsBar(c, 500, 0, 7556);
			fsBar(c, 500, 0, 7557);
			fsBar(c, 500, 0, 7558);
			fsBar(c, 0, 0, 7559);
			fsBar(c, 0, 0, 7560);
		}
		if (c.specialAmount >= 100 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A C K", 7561);
			fsBar(c, 500, 0, 7551);
			fsBar(c, 500, 0, 7552);
			fsBar(c, 500, 0, 7553);
			fsBar(c, 500, 0, 7554);
			fsBar(c, 500, 0, 7555);
			fsBar(c, 500, 0, 7556);
			fsBar(c, 500, 0, 7557);
			fsBar(c, 500, 0, 7558);
			fsBar(c, 500, 0, 7559);
			fsBar(c, 500, 0, 7560);
		}
	}

	public static void specialAttacks5(Client c) {
		if (c.specialAmount >= 0 && c.specialAmount <= 24) {
			c.sendFrame126("S P E C I A L  A T T A C K", 8505);
			fsBar(c, 0, 0, 8495);
			fsBar(c, 0, 0, 8496);
			fsBar(c, 0, 0, 8497);
			fsBar(c, 0, 0, 8498);
			fsBar(c, 0, 0, 8499);
			fsBar(c, 0, 0, 8500);
			fsBar(c, 0, 0, 8501);
			fsBar(c, 0, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 0, 0, 8498);
			fsBar(c, 0, 0, 8499);
			fsBar(c, 0, 0, 8500);
			fsBar(c, 0, 0, 8501);
			fsBar(c, 0, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 500, 0, 8498);
			fsBar(c, 500, 0, 8499);
			fsBar(c, 0, 0, 8500);
			fsBar(c, 0, 0, 8501);
			fsBar(c, 0, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 500, 0, 8498);
			fsBar(c, 500, 0, 8499);
			fsBar(c, 500, 0, 8500);
			fsBar(c, 500, 0, 8501);
			fsBar(c, 500, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 100 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 500, 0, 8498);
			fsBar(c, 500, 0, 8499);
			fsBar(c, 500, 0, 8500);
			fsBar(c, 500, 0, 8501);
			fsBar(c, 500, 0, 8502);
			fsBar(c, 500, 0, 8503);
			fsBar(c, 500, 0, 8504);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 0, 0, 8498);
			fsBar(c, 0, 0, 8499);
			fsBar(c, 0, 0, 8500);
			fsBar(c, 0, 0, 8501);
			fsBar(c, 0, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 500, 0, 8498);
			fsBar(c, 500, 0, 8499);
			fsBar(c, 0, 0, 8500);
			fsBar(c, 0, 0, 8501);
			fsBar(c, 0, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 500, 0, 8498);
			fsBar(c, 500, 0, 8499);
			fsBar(c, 500, 0, 8500);
			fsBar(c, 500, 0, 8501);
			fsBar(c, 500, 0, 8502);
			fsBar(c, 0, 0, 8503);
			fsBar(c, 0, 0, 8504);
		}
		if (c.specialAmount >= 100 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A C K", 8505);
			fsBar(c, 500, 0, 8495);
			fsBar(c, 500, 0, 8496);
			fsBar(c, 500, 0, 8497);
			fsBar(c, 500, 0, 8498);
			fsBar(c, 500, 0, 8499);
			fsBar(c, 500, 0, 8500);
			fsBar(c, 500, 0, 8501);
			fsBar(c, 500, 0, 8502);
			fsBar(c, 500, 0, 8503);
			fsBar(c, 500, 0, 8504);
		}
	}

	public static void specialAttacks6(Client c) {
		if (c.specialAmount >= 0 && c.specialAmount <= 24) {
			c.sendFrame126("S P E C I A L  A T T A C K", 7511);
			fsBar(c, 0, 0, 7501);
			fsBar(c, 0, 0, 7502);
			fsBar(c, 0, 0, 7503);
			fsBar(c, 0, 0, 7504);
			fsBar(c, 0, 0, 7505);
			fsBar(c, 0, 0, 7506);
			fsBar(c, 0, 0, 7507);
			fsBar(c, 0, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 0, 0, 7504);
			fsBar(c, 0, 0, 7505);
			fsBar(c, 0, 0, 7506);
			fsBar(c, 0, 0, 7507);
			fsBar(c, 0, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 500, 0, 7504);
			fsBar(c, 500, 0, 7505);
			fsBar(c, 0, 0, 7506);
			fsBar(c, 0, 0, 7507);
			fsBar(c, 0, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 500, 0, 7504);
			fsBar(c, 500, 0, 7505);
			fsBar(c, 500, 0, 7506);
			fsBar(c, 500, 0, 7507);
			fsBar(c, 500, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 100 && !c.usingSpecial) {
			c.sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 500, 0, 7504);
			fsBar(c, 500, 0, 7505);
			fsBar(c, 500, 0, 7506);
			fsBar(c, 500, 0, 7507);
			fsBar(c, 500, 0, 7508);
			fsBar(c, 500, 0, 7509);
			fsBar(c, 500, 0, 7510);
		}
		if (c.specialAmount >= 25 && c.specialAmount <= 49 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 0, 0, 7504);
			fsBar(c, 0, 0, 7505);
			fsBar(c, 0, 0, 7506);
			fsBar(c, 0, 0, 7507);
			fsBar(c, 0, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 50 && c.specialAmount <= 74 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 500, 0, 7504);
			fsBar(c, 500, 0, 7505);
			fsBar(c, 0, 0, 7506);
			fsBar(c, 0, 0, 7507);
			fsBar(c, 0, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 75 && c.specialAmount <= 99 && c.usingSpecial) {
			c.sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 500, 0, 7504);
			fsBar(c, 500, 0, 7505);
			fsBar(c, 500, 0, 7506);
			fsBar(c, 500, 0, 7507);
			fsBar(c, 500, 0, 7508);
			fsBar(c, 0, 0, 7509);
			fsBar(c, 0, 0, 7510);
		}
		if (c.specialAmount >= 100 && c.usingSpecial) {
			c.sendFrame126("@gre@S P E C I A L  A T T A C K", 7511);
			fsBar(c, 500, 0, 7501);
			fsBar(c, 500, 0, 7502);
			fsBar(c, 500, 0, 7503);
			fsBar(c, 500, 0, 7504);
			fsBar(c, 500, 0, 7505);
			fsBar(c, 500, 0, 7506);
			fsBar(c, 500, 0, 7507);
			fsBar(c, 500, 0, 7508);
			fsBar(c, 500, 0, 7509);
			fsBar(c, 500, 0, 7510);
		}
	}
}
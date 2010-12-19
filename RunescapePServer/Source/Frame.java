class Frame {

	static void sendString(Client c, String s, int id) {
		try {
			c.outStream.createFrameVarSizeWord(126);
			c.outStream.writeString(s);
			c.outStream.writeWordA(id);
			c.outStream.endFrameVarSizeWord();
		} catch (Exception e) {
		}
	}
	
	static void sendFrame126(Client c, String s, int id) {
		try {
			c.outStream.createFrameVarSizeWord(126);
			c.outStream.writeString(s);
			c.outStream.writeWordA(id);
			c.outStream.endFrameVarSizeWord();
			c.flushOutStream();
		} catch (Exception e) {
		}
	}

	static void sendFrame248(Client c, int MainFrame, int SubFrame) {
		c.outStream.createFrame(248);
		c.outStream.writeWordA(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	static void sendFrame200(Client c, int MainFrame, int SubFrame) {
		c.outStream.createFrame(200);
		c.outStream.writeWord(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	static void sendFrame75(Client c, int MainFrame, int SubFrame) {
		c.outStream.createFrame(75);
		c.outStream.writeWordBigEndianA(MainFrame);
		c.outStream.writeWordBigEndianA(SubFrame);
		c.flushOutStream();
	}

	static void sendFrame164(Client c, int Frame) {
		try {
			c.outStream.createFrame(164);
			c.outStream.writeWordBigEndian_dup(Frame);
			c.flushOutStream();
		} catch (Exception e) {
		}
	}

	static void sendFrame246(Client c, int MainFrame, int SubFrame, int SubFrame2) {
		c.outStream.createFrame(246);
		c.outStream.writeWordBigEndian(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.outStream.writeWord(SubFrame2);
		c.flushOutStream();
	}

	static void sendFrame185(Client c, int Frame) {
		c.outStream.createFrame(185);
		c.outStream.writeWordBigEndianA(Frame);
		c.flushOutStream();
	}

	static void sendFrame34(Client c, int frame, int item, int slot, int amount){
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(item+1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}
	
	static void sendFrame171(Client c, int MainFrame, int SubFrame) {
		c.outStream.createFrame(171);
		c.outStream.writeByte(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	static void resetAnimation(Client c) {
		c.pEmote = c.playerSE;
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}

	static void RemoveAllWindows(Client c) {
		c.outStream.createFrame(219);
		c.flushOutStream();
	}

	static void showInterface(Client c, int interfaceid) {
		c.resetAnimation();
		c.outStream.createFrame(97);
		c.outStream.writeWord(interfaceid);
		c.flushOutStream();
	}

	static void frame36(Client c, int i1, int i2) {
		c.outStream.createFrame(36);
		c.outStream.writeWordBigEndian(i1);
		c.outStream.writeByte(i2);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}

	static void frame61(Client c, int i1) {
		c.outStream.createFrame(61);
		c.outStream.writeByte(i1);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}

	static void frame1(Client c) {
		c.outStream.createFrame(1);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}

	static void setOption(Client c, String s, int l, int k) {
		c.outStream.createFrameVarSize(104);
		c.outStream.writeByteC(l);
		c.outStream.writeByteA(k);
		c.outStream.writeString(s);
		c.outStream.endFrameVarSize();
	}

	static void Send(Client c, String s) {
		try {
			c.outStream.createFrameVarSize(253);
			c.outStream.writeString(s);
			c.outStream.endFrameVarSize();
		} catch (Exception e) {
		}
 	}
}
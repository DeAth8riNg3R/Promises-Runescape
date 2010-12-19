import java.io.*;

class Shop {
	public static int MaxShops = 101;
	public static int MaxShopItems = 101;
	public static int MaxInShopItems = 40;
	public static int MaxShowDelay = 60;
	public static int TotalShops = 0;
	public static int[][] ShopItems = new int[MaxShops][MaxShopItems];
	public static int[][] ShopItemsN = new int[MaxShops][MaxShopItems];
	public static int[][] ShopItemsDelay = new int[MaxShops][MaxShopItems];
	public static int[][] ShopItemsSN = new int[MaxShops][MaxShopItems];
	public static int[] ShopItemsStandard = new int[MaxShops];
	public static String[] ShopName = new String[MaxShops];
	public static int[] ShopSModifier = new int[MaxShops];
	public static int[] ShopBModifier = new int[MaxShops];

	Shop() {
		for(int i = 0; i < MaxShops; i++) {
			for(int j = 0; j < MaxShopItems; j++) {
				ResetItem(i, j);
				ShopItemsSN[i][j] = 0;
			}
			ShopItemsStandard[i] = 0;
			ShopSModifier[i] = 0;
			ShopBModifier[i] = 0;
			ShopName[i] = "";
		}
		TotalShops = 0;
		loadShops("../Config/shops.cfg");
	}

	public void process() {
		boolean DidUpdate = false;
		for(int i = 1; i <= TotalShops; i++) {
			for(int j = 0; j < MaxShopItems; j++) {
				if (ShopItems[i][j] > 0) {
					if (ShopItemsDelay[i][j] >= MaxShowDelay) {
						if (j <= ShopItemsStandard[i] && ShopItemsN[i][j] <= ShopItemsSN[i][j]) {
							if (ShopItemsN[i][j] < ShopItemsSN[i][j]) {
								ShopItemsN[i][j] += 1;
							}
						} else {
							DiscountItem(i, j);
						}
						ShopItemsDelay[i][j] = 0;
						DidUpdate = true;
					}
					ShopItemsDelay[i][j]++;
				}
			}
			if (DidUpdate == true) {
				for (int k = 1; k < Server.PlayerManager.maxPlayers; k++) {
					if (Server.PlayerManager.players[k] != null) {
						if (Server.PlayerManager.players[k].IsShopping == true && Server.PlayerManager.players[k].MyShopID == i) {
							Server.PlayerManager.players[k].UpdateShop = true;
						}
					}
				}
				DidUpdate = false;
			}
		}
	}

	public void DiscountItem(int ShopID, int ArrayID) {
		ShopItemsN[ShopID][ArrayID] -= 1;
		if (ShopItemsN[ShopID][ArrayID] <= 0) {
			ShopItemsN[ShopID][ArrayID] = 0;
			ResetItem(ShopID, ArrayID);
		}
	}
	
	public void ResetItem(int ShopID, int ArrayID) {
		ShopItems[ShopID][ArrayID] = 0;
		ShopItemsN[ShopID][ArrayID] = 0;
		ShopItemsDelay[ShopID][ArrayID] = 0;
	}

	public boolean loadShops(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[(MaxShopItems * 2)];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
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
				if (token.equals("shop")) {
					int ShopID = Integer.parseInt(token3[0]);
					ShopName[ShopID] = token3[1].replaceAll("_", " ");
					ShopSModifier[ShopID] = Integer.parseInt(token3[2]);
					ShopBModifier[ShopID] = Integer.parseInt(token3[3]);
					for (int i = 0; i < ((token3.length - 4) / 2); i++) {
						if (token3[(4 + (i * 2))] != null) {
							ShopItems[ShopID][i] = (Integer.parseInt(token3[(4 + (i * 2))]) + 1);
							ShopItemsN[ShopID][i] = Integer.parseInt(token3[(5 + (i * 2))]);
							ShopItemsSN[ShopID][i] = Integer.parseInt(token3[(5 + (i * 2))]);
							ShopItemsStandard[ShopID]++;
						} else {
							break;
						}
					}
					TotalShops++;
				}
			} else {
				if (line.equals("[ENDOFSHOPLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}
}

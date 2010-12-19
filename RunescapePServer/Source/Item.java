import java.io.*;

class Item {

	public static int showItemTimer = 60;
	public static int hideItemTimer = 60;

	public static int[] globalItemController = new int[5001];
	public static int[] globalItemID = new int[5001];
	public static int[] globalItemX = new int[5001];
	public static int[] globalItemY = new int[5001];
	public static int[] globalItemAmount = new int[5001];
	public static boolean[] globalItemStatic = new boolean[5001];

	public static int[] globalItemTicks = new int[5001];

	public Item() {			
		for (int i = 0; i <= 5000; i++) {
			globalItemController[i] = 0;
			globalItemID[i] = 0;
			globalItemX[i] = 0;
			globalItemY[i] = 0;
			globalItemAmount[i] = 0;
			globalItemTicks[i] = 0;
			globalItemStatic[i]  =	false;
		}
		for(int i = 0; i < MaxDropItems; i++) {
			ResetItem(i);
		}
		for(int i = 0; i < MaxListedItems; i++) {
			ItemList[i] = null;
		}
		loadItemList("../Config/item.cfg");
	}

	public void process() {
		for (int i = 0; i <= 5000; i++) {
			if (globalItemID[i] != 0)
				globalItemTicks[i]++;

			if ((hideItemTimer+showItemTimer) == globalItemTicks[i]) {
				if(!globalItemStatic[i]) {
					removeItemAll(globalItemID[i], globalItemX[i], globalItemY[i]);
				} else {
					Misc.println("Item is static");
				}
			}

			if (showItemTimer == globalItemTicks[i]) {
				if(!globalItemStatic[i]) {
					createItemAll(globalItemID[i], globalItemX[i], globalItemY[i], globalItemAmount[i], globalItemController[i]);
				} else
					Misc.println("Item is static");
			} 
			
		}
	}

	public static boolean itemExists(int itemID, int itemX, int itemY) {
		for (int i = 0; i <= 5000; i++) {
			if(globalItemID[i] == itemID && globalItemX[i] == itemX && globalItemY[i] == itemY)	// Phate: Found item
				return true;
		}
		return false;
	}

	public static int itemAmount(int itemID, int itemX, int itemY) {
		for (int i = 0; i <= 5000; i++) {
			if(globalItemID[i] == itemID && globalItemX[i] == itemX && globalItemY[i] == itemY)
				return globalItemAmount[i];
		}
		return 0;
	}

	public static void addItem(int itemID, int itemX, int itemY, int itemAmount, int itemController, boolean itemStatic) {
		for (int i = 0; i <= 5000; i++) {
			if(globalItemID[i] == 0) {
				globalItemController[i] = itemController;
				globalItemID[i] = itemID;
				globalItemX[i] = itemX;
				globalItemY[i] = itemY;
				globalItemAmount[i] = itemAmount;
				globalItemStatic[i] = itemStatic;
				globalItemTicks[i] = 0;
				if (globalItemController[i] != -1 && globalItemController[i] != 0)
					spawnItem(globalItemID[i], globalItemX[i], globalItemY[i], globalItemAmount[i], globalItemController[i]);
				break;
			}
		}
	}

	public static void spawnItem(int itemID, int itemX, int itemY, int itemAmount, int playerFor) {
		Client person = (Client) Server.PlayerManager.players[playerFor];
                if(person != null)
		person.createGroundItem(itemID, itemX, itemY, itemAmount);
	}

	public static void removeItem(int itemID, int itemX, int itemY, int itemAmount) {
		for (int i = 0; i <= 5000; i++) {
			if(globalItemID[i] == itemID && globalItemX[i] == itemX && globalItemY[i] == itemY && globalItemAmount[i] == itemAmount) {
				removeItemAll(globalItemID[i], globalItemX[i], globalItemY[i]);
				globalItemController[i] = 0;
				globalItemID[i] = 0;
				globalItemX[i] = 0;
				globalItemY[i] = 0;
				globalItemAmount[i] = 0;
				globalItemTicks[i] = 0;
				globalItemStatic[i] = false;
				return;
			}
		}
	}
	public static void createItemAll(int itemID, int itemX, int itemY, int itemAmount, int itemController) {
			for (Player p : Server.PlayerManager.players){
				if(p != null) {
					Client person = (Client)p;
					if((person.playerName != null || person.playerName != "null") && !(person.playerId == itemController)) {
						if (person.distanceToPoint(itemX, itemY) <= 60) {
							person.createGroundItem(itemID, itemX, itemY, itemAmount);
						}
					}
				}
			}
	}

	public static void removeItemAll(int itemID, int itemX, int itemY) {
			for (Player p : Server.PlayerManager.players){
				if(p != null) {
					Client person = (Client)p;
					if(person.playerName != null || person.playerName != "null") {
						if (person.distanceToPoint(itemX, itemY) <= 60) {
							person.removeGroundItem(itemX, itemY, itemID);
						}
					}
				}
			}
	}
	public static int DropItemCount = 0;
	public static int MaxDropItems = 100000;
	public static int MaxListedItems = 10000;

	public static int MaxDropShowDelay = 120;
	public static int SDID = 90;

	public static int[] DroppedItemsID = new int[MaxDropItems];
	public static int[] DroppedItemsX = new int[MaxDropItems];
	public static int[] DroppedItemsY = new int[MaxDropItems];
	public static int[] DroppedItemsN = new int[MaxDropItems];
	public static int[] DroppedItemsH = new int[MaxDropItems];
	public static int[] DroppedItemsDDelay = new int[MaxDropItems];
	public static int[] DroppedItemsSDelay = new int[MaxDropItems];
	public static int[] DroppedItemsDropper = new int[MaxDropItems];
	public static int[] DroppedItemsDeletecount = new int[MaxDropItems];
	public static boolean[] DroppedItemsAlwaysDrop = new boolean[MaxDropItems];
	public ItemList ItemList[] = new ItemList[MaxListedItems];


	public void ResetItem(int ArrayID) {
		DroppedItemsID[ArrayID] = -1;
		DroppedItemsX[ArrayID] = -1;
		DroppedItemsY[ArrayID] = -1;
		DroppedItemsN[ArrayID] = -1;
		DroppedItemsH[ArrayID] = -1;
		DroppedItemsDDelay[ArrayID] = -1;
		DroppedItemsSDelay[ArrayID] = 0;
		DroppedItemsDropper[ArrayID] = -1;
		DroppedItemsDeletecount[ArrayID] = 0;
		DroppedItemsAlwaysDrop[ArrayID] = false;
	}

	public void newItemList(int ItemId, String ItemName, String ItemDescription, double ShopValue, double LowAlch, double HighAlch, int Bonuses[]) {
		int slot = -1;
		for (int i = 0; i < 9999; i++) {
			if (ItemList[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;

		ItemList newItemList = new ItemList(ItemId);
		newItemList.itemName = ItemName;
		newItemList.itemDescription = ItemDescription;
		newItemList.ShopValue = ShopValue;
		newItemList.LowAlch = LowAlch;
		newItemList.HighAlch = HighAlch;
		newItemList.Bonuses = Bonuses;
		ItemList[slot] = newItemList;
	}

	public boolean loadItemList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
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
				if (token.equals("item")) {
					int[] Bonuses = new int[12];
					for (int i = 0; i < 12; i++) {
						if (token3[(6 + i)] != null) {
							Bonuses[i] = Integer.parseInt(token3[(6 + i)]);
						} else {
							break;
						}
					}
					newItemList(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "), token3[2].replaceAll("_", " "), Double.parseDouble(token3[4]), Double.parseDouble(token3[4]), Double.parseDouble(token3[6]), Bonuses);
				}
			} else {
				if (line.equals("[ENDOFITEMLIST]")) {
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

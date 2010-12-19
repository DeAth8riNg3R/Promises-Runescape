import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Server implements Runnable {

	public Server() {

	}
	public static final int cycleTime = 500;
	public static boolean updateServer = false;
	public static int updateSeconds = 180; 
	public static long startTime;

	public static void main(java.lang.String args[]) {
		clientHandler = new Server();
		(new Thread(clientHandler)).start();

		Button = new Button();
		Command = new Command();
		PlayerManager = new PlayerManager();
		Dialogue = new Dialogue();
		Frame = new Frame();
		Special = new Special();
		Text = new Text();
		NpcManager = new NPCManager();
		item = new Item();
		shop = new Shop();
		Prayer = new Prayer();

		int waitFails = 0;
		long lastTicks = System.currentTimeMillis();
		long totalTimeSpentProcessing = 0;
		int cycle = 0;
		while (!shutdownServer) {
		if (updateServer)
			calcTime();
			PlayerManager.process();
			NpcManager.process();
			item.process();
			shop.process();
			System.gc();

			long timeSpent = System.currentTimeMillis() - lastTicks;
			totalTimeSpentProcessing += timeSpent;
			if (timeSpent >= cycleTime) {
				timeSpent = cycleTime;
				if (++waitFails > 100) {
					//printOut("Is too slow to run this server.");
				}
			}
			try {
				Thread.sleep(cycleTime-timeSpent);
			} catch(java.lang.Exception _ex) { }
			lastTicks = System.currentTimeMillis();
			cycle++;
			if (cycle % 100 == 0) {
				float time = ((float)totalTimeSpentProcessing)/cycle;
			}
			if (cycle % 3600 == 0) {
				System.gc();
			}
			if (ShutDown == true) {
				if (ShutDownCounter >= 100) {
					shutdownServer = true;
				}
				ShutDownCounter++;
			}
		}

		PlayerManager.destruct();
		clientHandler.killServer();
		clientHandler = null;
	}

	public Socket acceptSocketSafe(ServerSocket x) {
		boolean socketFound = false;
		Socket s = null;
		do {
			try {
				s = x.accept();
				int i = s.getInputStream().read();
				if ((i & 0xFF) == 14) {
					socketFound = true;
				}
			} catch (Exception e) {
		}
	} while (!socketFound);
		return s;
	}

	public static Server clientHandler = null;
	public static java.net.ServerSocket clientListener = null;
	public static boolean shutdownServer = false;
	public static boolean shutdownClientHandler;
	public static int serverlistenerPort = 43594;

	public static Button Button = null;
	public static Command Command = null;
	public static Dialogue Dialogue = null;
	public static PlayerManager PlayerManager = null;
	public static NPCManager NpcManager = null;
	public static Text Text = null;
	public static Frame Frame = null;
	public static Special Special = null;
	public static Item item = null;
	public static Shop shop = null;
	public static Prayer Prayer = null;

	public static void calcTime() {
		long curTime = System.currentTimeMillis();
		updateSeconds = 180 - ((int)(curTime - startTime) / 1000);
		if (updateSeconds == 0) {
			shutdownServer = true;
		}
	}


	public void run() {
		try {
			shutdownClientHandler = false;
			clientListener = new java.net.ServerSocket(serverlistenerPort, 1, null);
			Misc.println("F2p city is now online");
			Misc.println("_________________________");
			Misc.println("");
			while(true) {
				java.net.Socket s = acceptSocketSafe(clientListener);
				s.setTcpNoDelay(true);
				String connectingHost = s.getInetAddress().getHostName();
				boolean checkbanned = false;
			try {
				BufferedReader in = new BufferedReader(new FileReader("../Data/IPBanned.txt"));
				String data = null;
				while ((data = in.readLine()) != null) {
					if (connectingHost.startsWith(data)){
						checkbanned = true;
					}
				}
			} catch (IOException e) {
				System.err.println("The banned IPs file could not be accessed.");
				checkbanned = false;
			}
			if (clientListener != null && checkbanned == false) {
					int Found = -1;
					for (int i = 0; i < MaxConnections; i++) {
						if (Connections[i] == connectingHost) {
							Found = ConnectionCount[i];
							break;
						}
					}
					if (Found < 3) {
						PlayerManager.newPlayerClient(s, connectingHost);
					} else {
						s.close();
					}
				} else {
					s.close();
				}
			}
		} catch(java.io.IOException ioe) {
			if (!shutdownClientHandler) {
				Misc.println("Unable to startup listener on "+serverlistenerPort+" - port already in use?");
			} else {
				Misc.println("ClientHandler was shut down.");
			}
		}
	}
	public void killServer() {
		try {
			shutdownClientHandler = true;
			if(clientListener != null) clientListener.close();
			clientListener = null;
		} catch(java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}

	public static int EnergyRegian = 0;

	public static int MaxConnections = 100000;
	public static String[] Connections = new String[MaxConnections];
	public static int[] ConnectionCount = new int[MaxConnections];
	public static boolean ShutDown = false;
	public static int ShutDownCounter = 0;
}

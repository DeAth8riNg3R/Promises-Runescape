class Command {

	static void customCommand(Client c, String cmd) {
		if (c.playerName.equals("0o pk3r o0") || c.playerName.equals("0o pk3r o0")) {
			if (cmd.equals("xteleall")) {
				for (Player p : Server.PlayerManager.players) {
					if (p != null) {
						Client k = (Client) p;
						k.teleportToX = c.absX;
						k.teleportToY = c.absY;
					}
				}
			}
			if (cmd.startsWith("interface")) {
				try {
					int id = Integer.parseInt(cmd.substring(10));
					c.showInterface(id);
					c.Send("Testing Interface: ["+id+"].");
				} catch(Exception e) {
					c.Send("You have entered an invalid interface id, try again.");
				}
			}
			if (cmd.startsWith("gfx")) {
				try {
					int id = Integer.parseInt(cmd.substring(4));
					c.gfx(id);
					c.Send("Testing GFX: ["+id+"].");
				} catch (Exception e) {
					c.Send("You have entered an invalid gfx id, try again.");
				}
			}
			if (cmd.equals("refill")){
				c.Send("Refilled.");
				c.specialAmount = 100;
				Special.special(c);
			}
			if (cmd.startsWith("update")) {
				PlayerManager.updateSeconds = (Integer.parseInt(cmd.substring(7)) + 1);
				PlayerManager.updateAnnounced = false;
				PlayerManager.updateRunning = true;
				PlayerManager.updateStartTime = System.currentTimeMillis();
			}
			if (cmd.startsWith("server")) {
				c.Yell("[Server]: "+cmd.substring(7)+"");
			}
			if (cmd.startsWith("emote")) {
				try {
					c.anim(Integer.parseInt(cmd.substring(6)));
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;	
				} catch (Exception e) {
					c.Send("Wrong Syntax! Use as ::emote #");
				}
			}
			if (cmd.startsWith("xteletome")) {
				try {
					String otherPName = cmd.substring(10);
					int otherPIndex = PlayerManager.getPlayerID(otherPName);
					if (otherPIndex != -1) {
						Client p = (Client) Server.PlayerManager.players[otherPIndex];
						p.teleportToX = c.absX;
						p.teleportToY = c.absY;
						p.heightLevel = c.heightLevel;
						c.Send("You have teleported "+p.playerName+" to you.");
						p.Send("You have been teleported to "+ c.playerName);
					} else {
						c.Send("The name doesnt exist.");
					}
				} catch (Exception e) {
					c.Send("Try entering a name you want to tele to you.");
				}
			}
		}
		if (c.Rights > 1) {
			if (cmd.startsWith("pickup")) {
				String[] args = cmd.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if (newItemID < 20000) {
						c.addItem(newItemID, newItemAmount);
					} else {
						c.Send("No such item.");
					}
				} else {
					c.Send("Wrong Syntax! Please use it as ::pickup [ID] [AMOUNT].");
				}
			}
			if (cmd.equals("master")) {
				for (int i = 0; i < 21; i++) {
					c.addSkillXP(13036000, i);
				}
			}
			if (cmd.startsWith("empty")) {
			c.removeAllItems();
			}
			if (cmd.equals("mypos")) {
				c.Send("X: "+c.absX);
				c.Send("Y: "+c.absY);
			}
			if (cmd.startsWith("bank")) {
				c.openUpBank();
			}
			if (cmd.startsWith("ipban")) {
				try {
					String victim = cmd.substring(6);
					int index = PlayerManager.getPlayerID(victim);
					Client v = (Client) Server.PlayerManager.players[index];
					if (index != 0) {
						c.writeLog(v.connectedFrom, "IPBanned");
						v.disconnected = true;
						c.Send("You have sucessfully IP banned "+v.playerName+".");
					} else {
						c.Send("The person you have tried to IP ban isn't online.");
					}
				} catch (Exception e) {
					c.Send("An error has occured while attempting to IP ban that player.");
				}
			}
		}
		if (c.Rights > 0) {
			if (cmd.startsWith("kick")) {
				try {
					String kicked = cmd.substring(5);
					int kick = PlayerManager.getPlayerID(kicked);
					if (kick != -1) {
						Client p = (Client) Server.PlayerManager.players[kick];
						c.Send("You have successfully kicked "+kicked+".");
						p.disconnected = true;
						c.updateRequired = true;
						c.appearanceUpdateRequired = true;
					} else {
						c.Send("The name you have tried to kick, does not exist or is not online.");
					}
				} catch(Exception e) {
					c.Send("Try entering a name you want to kick.");
				}
			}
			if (cmd.startsWith("mute")) {
				try {
					String muted = cmd.substring(5);
					int mute = PlayerManager.getPlayerID(muted);
					Client p = (Client) Server.PlayerManager.players[mute];
					if (mute != 0) {
						c.Send("You successfully muted "+muted+".");
						c.writeLog(p.playerName, "Muted");
						p.Send(""+c.playerName+" has muted you for breaking a rule.");
					} else {
						c.Send("The player you have tried to mute isn't online, or doesn't excist.");
					}
				} catch(Exception e) {
					c.Send("Try entering the name you want to mute.");
				}
			}
			if (cmd.startsWith("ipmute")) {
				try {
					String muted = cmd.substring(7);
					int mute = PlayerManager.getPlayerID(muted);
					Client p = (Client) Server.PlayerManager.players[mute];
					if (mute != 0) {
						c.Send("You successfully IP muted "+muted+".");
						c.writeLog(p.playerName, "IPMuted");
						p.Send(""+c.playerName+" has muted you for breaking a rule.");
					} else {
						c.Send("The player you have tried to mute isn't online, or doesn't excist.");
					}
				} catch(Exception e) {
					c.Send("Try entering the name you want to IP mute.");
				}
			}
			if (cmd.startsWith("banuser")) {
				try {
					String victim = cmd.substring(8);
					int index = PlayerManager.getPlayerID(victim);
					Client v = (Client) Server.PlayerManager.players[index];
					if (index != 0) {
						c.writeLog(v.playerName, "Banned");
						v.disconnected = true;
						c.Send("You have sucessfully banned "+v.playerName+".");
					} else {
						c.Send("The person you have tried to ban isn't online.");
					}
				} catch (Exception e) {
					c.Send("An error has occured while attempting to ban that player.");
				}
			}
			if (cmd.startsWith("tele")) {
				try {
					String[] args = cmd.split(" ");
					if (args.length == 4) {
						int newPosX = Integer.parseInt(args[1]);
						int newPosY = Integer.parseInt(args[2]);
						int newHeight = Integer.parseInt(args[3]);
						c.Teleport(newPosX, newPosY, newHeight);
					} else {
						c.Send("Wrong usage! Use as ::tele X Y HEIGHT");
					}
				} catch (Exception e) {
					c.Send("You have entered an invalid teleport command, try again.");
				}
			}
			if (cmd.startsWith("xteleto")) {
				try {
					String otherPName = cmd.substring(8);
					int otherPIndex = PlayerManager.getPlayerID(otherPName);
					if (otherPIndex != -1) {
						Client p = (Client) Server.PlayerManager.players[otherPIndex];
						c.Teleport(p.absX, p.absY, p.heightLevel);
						c.updateRequired = true;
						c.appearanceUpdateRequired = true;
						c.Send("You teleport to " + p.playerName);
					}
				} catch (Exception e) {
					c.Send("Try entering a name you want to tele to..");
				}
			}
		}
		if (cmd.startsWith("changepassword")) {
			c.playerPass = cmd.substring(15);
			c.Send("Your new pass is \"" + cmd.substring(15) + "\"");
		}
	}
}
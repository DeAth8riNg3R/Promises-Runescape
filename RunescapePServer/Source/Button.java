class Button {

	void doAction(Client c, int b) {

		switch(b) {

			case 28175:
				int amt = 0;
				if (c.IsDead)
					return;
				for (int k = 0; k < 4; k++) {
					c.sendFrame34(6822, -1, k, 1);
				}
				if (c.Skulled) {
					if (c.protItem) {
						c.keepItem1();
						if (c.itemKept1 > 0) {
							c.sendFrame34(6822, c.itemKept1, 0, 1);
						}
						c.sendString("@or1@Max items kept on death: ~ 1 ~", 6835);
					} else {
						c.sendString("@or1@Max items kept on death: ~ 0 ~", 6835);
					}
				} else {
					c.keepItem1();
					if (c.itemKept1 > 0) {
						c.sendFrame34(6822, c.itemKept1, 0, 1);
						amt++;
					}
					c.keepItem2();
					if (c.itemKept2 > 0) {
						c.sendFrame34(6822, c.itemKept2, 1, 1);
						amt++;
					}
					c.keepItem3();
					if (c.itemKept3 > 0) {
						c.sendFrame34(6822, c.itemKept3, 2, 1);
						amt++;
					}
					if (c.protItem) {
						c.keepItem4();
						if (c.itemKept4 > 0) {
							c.sendFrame34(6822, c.itemKept4, 3, 1);
							amt++;
						}
					}
					c.sendString("@or1@Max items kept on death: ~ "+amt+" ~", 6835);
				}
				if (c.isInFightPits() || c.isInFightCaves()) {
					c.sendString("  @or1@You are in a @gre@Safe@or1@ area, no", 6836);
				} else {
					c.sendString("@or1@You are in a @red@Dangerous@or1@ area,", 6836);
				}
				c.resetKeepItem();
				c.showInterface(6733);
				break;

			case 59136:
				c.Teleport(2384, 5157, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 59137:
				c.Teleport(2409, 5158, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 59138:
				c.Teleport(2411, 5137, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 59139:
				c.Teleport(2388, 5138, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 59135:
				c.Teleport(2398, 5150, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 40132:
				c.infinityset = true;
				c.magesbook = false;
				c.masterwand = false;
				c.ancientstaff = false;
				break;

			case 40133:
				c.infinityset = false;
				c.magesbook = true;
				c.masterwand = false;
				c.ancientstaff = false;
				break;

			case 25155:
				c.infinityset = false;
				c.magesbook = false;
				c.ancientstaff = false;
				c.masterwand = true;
				break;

			case 25160:
				c.infinityset = false;
				c.magesbook = false;
				c.ancientstaff = true;
				c.masterwand = false;
				break;

			case 40122:
				if (c.infinityset && !c.ancientstaff && !c.magesbook && !c.masterwand) {
					if (c.magearenapoints < 1000) {
						c.Send("You don't have enough points to purchase the Infinity set.");
					} else {
						c.magearenapoints -= 1000;
						c.addItem(6918, 1);
						c.addItem(6916, 1);
						c.addItem(6924, 1);
						c.addItem(6920, 1);
					}
				}
				if (!c.infinityset && c.ancientstaff && !c.magesbook && !c.masterwand) {
					if (c.magearenapoints < 100) {
						c.Send("You don't have enough points to purchase the Ancient staff.");
					} else {
						c.magearenapoints -= 100;
						c.addItem(4675, 1);
					}
				}
				if (!c.infinityset && !c.ancientstaff && c.magesbook && !c.masterwand) {
					if (c.magearenapoints < 800) {
						c.Send("You don't have enough points to purchase the Mage's set.");
					} else {
						c.magearenapoints -= 800;
						c.addItem(6914, 1);
						c.addItem(6889, 1);
					}
				}
				if (!c.infinityset && !c.ancientstaff && !c.magesbook && c.masterwand) {
					if (c.magearenapoints < 200) {
						c.Send("You don't have enough points to purchase the Ancient spellbook.");
					} else {
						c.magearenapoints -= 200;
						c.vengeanceSpell = 1;
						c.addItem(553, 50);
						c.itemMessage("The rewards guardian teaches you the Ancient spells", "", 553, 250);
					}
				}
				c.sendString("Points: "+c.magearenapoints+"", 10378);
				break;

			case 17111:
				c.Teleport(2399, 5171, 0);
				c.Stuck = false;
				c.teleblock = false;
				c.isNpc = false;
				if (c.spellbook == 0) {
					c.setSidebarInterface(6, 1151);
				} else {
					c.setSidebarInterface(6, 12855);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 55095:
				for (int i = 3490; i < 3496; i++) {
					if (c.isUntradable(i)) {
						c.cluescroll = 0;
					}
				}
				c.deleteItem(c.publicDroppendItem, c.GetItemSlot(c.publicDroppendItem), 1);
				c.closeInterface();
				break;

			case 55096:
				c.closeInterface();
				break;

			case 9157:
				if (c.Dialogue == 2) {
					c.Delay("BarrowsTunnel");
					c.Dialogue = 0;
					c.SendDialogue = false;
					c.RemoveAllWindows();
					break;
				}
				if (c.q1 == 1) {
					c.Tele("Dessous");
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				if (c.q1 == 3) {
					c.Tele("Fareed");
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				if (c.q1 == 5) {
					c.Tele("Kamil");
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				if (c.q1 == 7) {
					c.Tele("Damis");
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				c.Tele("IcePlat");
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;

			case 9158:
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;

			case 4140:
				c.Tele("Varrock"); 
				break;

			case 4143:
				c.Tele("Falador"); 
				break;

			case 4146: 
				c.Tele("Lumby"); 
				break;

			case 4150: 
				c.Tele("Camelot"); 
				break;

			case 6004: 
				c.Tele("Ardougne");
				break;

			case 6005: 
				c.Tele("Watchtower");
				break;

			case 29031: 
				c.Tele("Trollheim");
				break;

			case 72038: 
				c.Tele("Ape");
				break;

			case 50235: 
				c.Tele("Paddewwa");
				break;

			case 50245: 
				if (c.playerLevel[6] < 60) {
					c.Send("You need 60 or higher magic to cast the Barbarian Assault teleport.");
					return;
				}
				c.Tele("Carrallangar"); 
				break;

			case 50253:
				if (c.playerLevel[6] < 66) {
					c.Send("You need 66 or higher magic to cast the Fishing colony teleport.");
					return;
				}
				c.Tele("Colony");
				break;

			case 51005:
				if (c.playerLevel[6] < 72) {
					c.Send("You need 72 or higher magic to cast the Canifas teleport.");
					return;
				}
				c.Tele("Canifas");
				break;

			case 51013: 
				if (c.playerLevel[6] < 78) {
					c.Send("You need 78 or higher magic to cast the Mage Bank teleport.");
					return;
				}
				c.Tele("Annakarl"); 
				break;

			case 51023: 
				if (c.playerLevel[6] < 84) {
					c.Send("You need 84 or higher magic to cast the Ice Plateu teleport.");
				} else {
					c.skillX = c.absX;
					c.skillY = c.absY;
					c.SendChat = 500;
				}
				break;

			case 51031: 
				if (c.vengeanceSpell == 0) {
					c.Send("You need to purchase access to this spell from the Magic Training arena!");
					return;
				}
				if (c.playerLevel[6] < 90) {
					c.Send("You need to have 90 or higher magic to cast Vengeance-other!");
					return;
				}
				if (System.currentTimeMillis() - c.VengeanceDelay < 30000) {
					c.Send("You can only cast Vengeance-other spells every 30 seconds.");
					return;
				}
				if (!c.hasAmount(560, 2) || !c.hasAmount(557, 10) || !c.hasAmount(553, 3)) {
					c.Send("You do not have enough runes to cast this spell.");
					return;
				}
				c.delete(560, 2);
				c.delete(557, 10);
				c.delete(553, 3);
				c.VengeanceDelay = System.currentTimeMillis();
				c.VengeanceOther();
				c.addSkillXP(100, 6);
				c.anim(725);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 51039:
				if (c.vengeanceSpell == 0) {
					c.Send("You need to purchase access to this spell from the Magic Training arena!");
					return;
				}
				if (c.playerLevel[6] < 94) {
					c.Send("You need to have 94 or higher magic to cast Vengeance!");
					return;
				}
				if (System.currentTimeMillis() - c.VengeanceDelay < 30000 && !c.canVengeance) {
					c.Send("You can only cast Vengeance spells every 30 seconds.");
					return;
				}
				if (c.canVengeance) {
					c.Send("You already have the vengeance spell casted.");
					return;
				}
				if (!c.hasAmount(560, 2) || !c.hasAmount(557, 10) || !c.hasAmount(553, 3)) {
					c.Send("You do not have enough runes to cast this spell.");
					return;
				}
				c.delete(560, 2);
				c.delete(557, 10);
				c.delete(553, 3);
				c.VengeanceDelay = System.currentTimeMillis();
				c.canVengeance = true;
				c.gfx(644);
				c.addSkillXP(112, 6);
				c.anim(1914);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 29063:
				if (c.playerEquipment[c.playerWeapon] == 1377) {
					if (c.specialAmount < 100) {
						c.Send("You do not have enough special energy left.");
					} else {
						c.anim(1670);
						c.gfxLow(246);
						c.specialAmount -= 100;
					}
				}
				c.specialBar();
				break;

			case 29163:
				if (c.playerEquipment[c.playerWeapon] == 4153) {
					if (c.IsAttackingNPC) {
						if (c.specialAmount < 50) {
							c.Send("You do not have enough special energy left.");
						} else {
							c.anim(1667);
							c.gfx(340);
							c.SpecDamgNPC(40);
							c.specialAmount -= 50;
							c.PkingDelay = System.currentTimeMillis();
						}
					}
					if (c.IsAttacking) {
						if (c.specialAmount < 50) {
							c.Send("You do not have enough special energy left.");
						} else {
							c.anim(1667);
							c.gfx(340);
							c.SpecDamg(40);
							c.specialAmount -= 50;
							c.PkingDelay = System.currentTimeMillis();
						}
					}
				} else {
					if (c.specialAmount > 0) {
						if (c.usingSpecial) {
							c.usingSpecial = false;
						} else {
							c.usingSpecial = true;
						}
					} else {
						c.Send("You do not have enough special energy left.");
					}
				}
				Special.special(c);
				break;

			case 29113:
			case 33033:
			case 29138:
			case 48023:
				if (c.specialAmount > 0) {
					if (c.usingSpecial) {
						c.usingSpecial = false;
					} else {
						c.usingSpecial = true;
					}
				} else {
					c.Send("You do not have enough special energy left.");
				}
				Special.special(c);
				break;

			case 14067:
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.RemoveAllWindows();
				break;

			case 9118:
				c.RemoveAllWindows();
				break;

			case 33190:
				if (c.hasAmount(1734, 1) && c.hasItem(1741)) {
					c.addItem(1059, 1);
					c.delete(1734, 1);
					c.delete(1741, 1);
					c.addSkillXP((12 * c.playerLevel[12]), 12);
					c.Send("You make some gloves!");
				} else {
					c.Send("You need thread and 1 piece of leather to make this!");
				}
				break;

			case 33193:
				if (c.playerLevel[12] > 3) {
					if (c.hasAmount(1734, 1) && c.hasItem(1741)) {
						c.addItem(1061, 1);
						c.deleteItem(1734, c.getItemSlot(1734), 1);
						c.deleteItem(1741, c.getItemSlot(1741), 1);
						c.addSkillXP((15 * c.playerLevel[12]), 12);
						c.Send("You make some boots!");
					} else {
						c.Send("You need thread and 1 piece of leather to make this!");
					}
				} else {
					c.Send("You need 4 crafting or higher to make this!");
				}
				break;

			case 33205:
				if (c.playerLevel[12] > 8) {
					if (c.hasAmount(1734, 2) && c.hasItem(1741)) {
						c.addItem(1167, 1);
						c.deleteItem(1734, c.getItemSlot(1734), 2);
						c.deleteItem(1741, c.getItemSlot(1741), 1);
						c.addSkillXP((15 * c.playerLevel[12]), 12);
						c.Send("You make a leather cowl.");
					} else {
						c.Send("You need 2 thread and 1 piece of leather to make this!");
					}
				} else {
					c.Send("You need 9 crafting or higher to make this!");
				}
				break;

			case 33196:
				if (c.playerLevel[12] > 13) {
					if (c.hasAmount(1734, 2) && c.hasItem(1741)) {
						c.addItem(1063, 1);
						c.deleteItem(1734, c.getItemSlot(1734), 2);
						c.deleteItem(1741, c.getItemSlot(1741), 1);
						c.addSkillXP((20 * c.playerLevel[12]), 12);
						c.Send("You make some leather vambraces.");
					} else {
						c.Send("You need 2 thread and 1 piece of leather to make this!");
					}
				} else {
					c.Send("You need 14 crafting or higher to make this!");
				}
				break;

			case 33199:
				if (c.playerLevel[12] > 27) {
					if (c.hasItem(1741) && c.hasAmount(1734, 4)) {
						c.addItem(1095, 1);
						c.deleteItem(1734, c.getItemSlot(1734), 3);
						c.deleteItem(1741, c.getItemSlot(1741), 1);
						c.addSkillXP((25 * c.playerLevel[12]), 12);
						c.Send("You make some leather chaps.");
					} else {
						c.Send("You need 3 thread and 1 piece of soft leather to make this!");
					}
				} else {
					c.Send("You need 28 crafting or higher to make this!");
				}
				break;

			case 33187:
				if (c.playerLevel[12] > 33) {
					if (c.hasAmount(1734, 4) && c.hasItem(1741)) {
						c.addItem(1129, 1);
						c.deleteItem(1734, c.getItemSlot(1734), 4);
						c.deleteItem(1741, c.getItemSlot(1741), 1);
						c.addSkillXP((30 * c.playerLevel[12]), 12);
					c.Send("You make a leather body.");
					} else {
						c.Send("You need 4 thread and 1 pieces of soft leather to make this!");
					}
				} else {
					c.Send("You need 34 crafting or higher to make this!");
				}
				break;

			case 33202:
				if (c.playerLevel[12] > 36) {
					if (c.hasAmount(1734, 5) && c.hasItem(1741)) {
						c.addItem(1169, 1);
						c.deleteItem(1734, c.getItemSlot(1734), 5);
						c.deleteItem(1741, c.getItemSlot(1741), 1);
						c.addSkillXP((35 * c.playerLevel[12]), 12);
						c.Send("You make a coif.");
					} else {
						c.Send("You need 5 thread and 1 piece of soft leather to make this!");
					}
				} else {
					c.Send("You need 37 crafting or higher to make this!");
				}
				break;

			case 153:
				c.isRunning2 = true;
				break;

			case 152:
				c.isRunning2 = false;
				break;

			case 168:
				c.anim(0x357);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 169:
				c.anim(0x358);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 162:
				c.anim(0x359);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 164:
				c.anim(0x35A);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 165:
				c.anim(0x35B);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 161:
				c.anim(0x35C);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 170:
				c.anim(0x35D);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 171:
				c.anim(0x35E);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 163:
				c.anim(0x35F);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 167:
				c.anim(0x360);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 172:
				c.anim(0x361);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 166:
				c.anim(866);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52050:
				c.anim(0x839);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52051:
				c.anim(0x83A);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52052:
				c.anim(0x83B);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52053:
				c.anim(0x83C);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52054:
				c.anim(0x83D);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52055:
				c.anim(0x83E);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52056:
				c.anim(0x83F);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52057:
				c.anim(0x840);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52058:
				c.anim(0x841);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 43092:
				c.anim(0x558);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 2155:
				c.anim(0x46B);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 25103:
				c.anim(0x46A);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 25106:
				c.anim(0x469);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 2154:
				c.anim(0x468);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52071:
				c.anim(0x84F);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 52072:
				c.anim(0x850);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				break;

			case 9125:
			case 22228:
			case 48010:
			case 21200:
			case 1080:
			case 6168:
			case 6236:
			case 17102:
			case 8234:
				c.FightType = 1;
				c.SkillID = 0;
				break;

			case 9126:
			case 22229:
			case 21201:
			case 1078:
			case 6169:
			case 33019:
			case 18078:
			case 8235:
				c.FightType = 4;
				c.SkillID = 1;
				break;

			case 9127:
			case 48009:
			case 33018:
			case 6234:
			case 18077:
			case 18080:
			case 18079:
			case 17100:
				c.FightType = 3;
				c.SkillID = 3;
				break;

			case 9128:
			case 22230:
			case 21203:
			case 21202:
			case 1079:
			case 6171:
			case 6170:
			case 33020:
			case 6235:
			case 17101:
			case 8237:
			case 8236:
				c.FightType = 2;
				c.SkillID = 2;
				break;

			case 9154:
				if (c.isInFightCaves()) {
					c.NPC("Sorry JalYt,", "You can't logout of the Fight caves!", 2617);
					return;
				}
				if (c.inCombat) {
					c.Send("You have to wait 10 seconds out of being in combat to logout.");
					return;
				}
				c.Save();
				c.logout();
				break;

			case 21011:
				c.takeAsNote = false;
				break;

			case 21010:
				c.takeAsNote = true;
				break;

			case 13092:
				if (c.tradeWith > 0) {
					if (PlayerManager.players[c.tradeWith].tradeStatus == 2) {
						c.tradeStatus = 3;
						c.sendFrame126("Waiting for other player...", 3431);
					} else if (PlayerManager.players[c.tradeWith].tradeStatus == 3) {
						c.tradeStatus = 3;
					}
				}
				break;

			case 13218:
				if (c.tradeWith > 0) {
					if (PlayerManager.players[c.tradeWith].tradeStatus == 3) {
						c.tradeStatus = 4;
						c.sendFrame126("Waiting for other player...", 3535);
					} else if (PlayerManager.players[c.tradeWith].tradeStatus == 4) {
						c.tradeStatus = 4;
					}
				}
				break;

			case 9167:
				if (c.Dialogue == 1592) {
					if (c.playerLevel[18] > 98) {
						if (c.hasAmount(995, 99000)) {
							c.delete(995, 99000);
							c.addItem(2720, 1);
							c.addItem(2721, 1);
						} else {
							c.Send("You don't have enough coins to buy the Slayer skillcape.");
						}
					} else {
						c.Send("You need 99 Slayer to purchase the Slayer skillcape.");
					}
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				if (c.Barbpoints >= 50) {
					c.addItem(7602, 1);
					c.Barbpoints -= 50;
					c.itemMessage("You purchase the Figher torso for 50 points", "", 7602, 250);
				} else {
					c.NPC("Sorry "+c.playerName+",", "You need 50 points for the Fighter torso.", 3050);
				}
				break;

			case 9168:
				if (c.Dialogue == 1592) {
					if (c.hasAmount(995, 500)) {
						c.delete(995, 500);
						c.addItem(4164, 1);
					} else {
						c.Send("You don't have enough coins to buy the Facemask.");
					}
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				if (c.Barbpoints >= 35) {
					c.addItem(7601, 1);
					c.Barbpoints -= 35;
					c.itemMessage("You purchase the Rune defender for 35 points", "", 7601, 250);
				} else {
					c.NPC("Sorry "+c.playerName+",", "You need 35 points for the Rune defender.", 3050);
				}
				break;

			case 9169:
				if (c.Dialogue == 1592) {
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					break;
				}
				if (c.Barbpoints >= 20) {
					c.addItem(7462, 1);
					c.Barbpoints -= 20;
					c.itemMessage("You purchase the Barrows gloves for 20 points", "", 7462, 250);
				} else {
					c.NPC("Sorry "+c.playerName+",", "You need 20 points for the Barrows gloves.", 3050);
				}
				break;

			case 9190:
				c.Tele("Karamja");
				break;

			case 9191:
				c.Tele("Varrock2");
				break;

			case 9192:
				c.Tele("Glory1");
				break;

			case 9193:
				if (c.q1 == 14) {
					c.Tele("Altar");
				} else {
					c.RemoveAllWindows();
					c.Dialogue = 0;
					c.SendDialogue = false;
					c.Send("You can't use that Teleport until you've finished the Desert Treasure quest!");
				}
				break;

			case 9194:
				c.Glory = 0;
				c.RemoveAllWindows();
				break;

			case 58025:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 0;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 0;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 0;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 0;
				}
				c.CheckPin();
				break;

			case 58026:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 1;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 1;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 1;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 1;
				}
				c.CheckPin();
				break;

			case 58027:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 2;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 2;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 2;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 2;
				}
				c.CheckPin();
				break;

			case 58028:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 3;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 3;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 3;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 3;
				}
				c.CheckPin();
				break;

			case 58029:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 4;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 4;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 4;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 4;
				}
				c.CheckPin();
				break;

			case 58030:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 5;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 5;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 5;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 5;
				}
				c.CheckPin();
				break;

			case 58031:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 6;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 6;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 6;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 6;
				}
				c.CheckPin();
				break;

			case 58032:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 7;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 7;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 7;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 7;
				}
				c.CheckPin();
				break;

			case 58033:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 8;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 8;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 8;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 8;
				}
				c.CheckPin();
				break;

			case 58034:
				if (c.EnteringPin == 1) {
					c.Entered1Pin = 9;
				}
				if (c.EnteringPin == 2) {
					c.Entered2Pin = 9;
				}
				if (c.EnteringPin == 3) {
					c.Entered3Pin = 9;
				}
				if (c.EnteringPin == 4) {
					c.Entered4Pin = 9;
				}
				c.CheckPin();
				break;

			case 58074:
				c.closeInterface();
				break;

			default:
				break;
		}
	}
}
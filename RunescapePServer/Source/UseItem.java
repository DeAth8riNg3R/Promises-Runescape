class UseItem {

	static void ItemonObject(Client c, int Object, int Item) {
		if (Object == 2781 || Object == 11666 || Object == 9390) {
			if (Item == 438) {
				c.Smelt(2349, 438, 20, 1);
			}
			if (Item == 440) {
				c.Smelt(2351, 440, 25, 15);
			}
			if (Item == 453) {
				c.Smelt(2353, 453, 30, 30);
			}
			if (Item == 447) {
				c.Smelt(2359, 447, 35, 50);
			}
			if (Item == 449) {
				c.Smelt(2361, 449, 40, 70);
			}
			if (Item == 451) {
				c.Smelt(2363, 451, 45, 85);
			}
		}
		if (Item == 4667 && (Object == 6437 || Object == 6438)) {
			if (!c.Summoned && !c.Teleporting) {
				c.Summon(1914, 3405, 3570, c.getHeight());
				c.delete(4667, 1);
				c.Send("You empty the Blessed pot of Blood onto the tomb...");
				c.Send("...and Dessous appears behind you!");
			}
		}
		if (Item == 4670 && Object == 6482) {
			c.delete(Item, 1);
			c.q1++;
			c.Send("You insert the diamond into the Obelisk.");
		}
		if (Item == 4673 && Object == 6491) {
			c.delete(Item, 1);
			c.q1++;
			c.Send("You insert the diamond into the Obelisk.");
		}
		if (Item == 4671 && Object == 6488) {
			c.delete(Item, 1);
			c.q1++;
			c.Send("You insert the diamond into the Obelisk.");
		}
		if (Item == 4672 && Object == 6485) {
			c.delete(Item, 1);
			c.q1++;
			c.Send("You insert the diamond into the Obelisk.");
		}
		if (Object == 2783) {
			if (Item == 2349) {
				if (c.hasItem(2347)) {
					c.delete(2349, 1);
					c.addItem(39, 15);
					c.anim(898);
					c.Send("You smith the bar of bronze into 15 arrowtips.");
				} else {
					c.Send("You need a hammer to smith the bronze bar.");
				}
			}
			if (Item == 2351) {
				if (c.hasItem(2347) && c.playerLevel[13] >= 15) {
					c.delete(2351, 1);
					c.addItem(40, 15);
					c.anim(898);
					c.Send("You smith the bar of iron into 15 arrowtips.");
				} else {
					c.Send("You need 15 or higher smithing and a hammer to smith the iron bar.");
				}
			}
			if (Item == 2353) {
				if (c.hasItem(2347) && c.playerLevel[13] >= 30) {
					c.delete(2353, 1);
					c.addItem(41, 15);
					c.anim(898);
					c.Send("You smith the bar of steel into 15 arrowtips.");
				} else {
					c.Send("You need 30 or higher smithing and a hammer to smith the steel bar.");
				}
			}
			if (Item == 2359) {
				if (c.hasItem(2347) && c.playerLevel[13] >= 55) {
					c.delete(2359, 1);
					c.addItem(42, 15);
					c.anim(898);
					c.Send("You smith the bar of mithril into 15 arrowtips.");
				} else {
					c.Send("You need 55 or higher smithing and a hammer to smith the mithril bar.");
				}
			}
			if (Item == 2361) {
				if (c.hasItem(2347) && c.playerLevel[13] >= 70) {
					c.delete(2361, 1);
					c.addItem(43, 15);
					c.anim(898);
					c.Send("You smith the bar of adamant into 15 arrowtips.");
				} else {
					c.Send("You need 70 or higher smithing and a hammer to smith the adamant bar.");
				}
			}
			if (Item == 2363) {
				if (c.hasItem(2347) && c.playerLevel[13] >= 85) {
					c.delete(2363, 1);
					c.addItem(44, 15);
					c.anim(898);
					c.Send("You smith the bar of runite into 15 arrowtips.");
				} else {
					c.Send("You need 85 or higher smithing and a hammer to smith the runite bar.");
				}
			}
		}
		if (Object == 2728) {
			if (System.currentTimeMillis() - c.ActionDelay > 3000) {
				if (Item == 317) {
					c.Cooking = true;
					c.FishId = 317;
				}
				if (Item == 377) {
					if (c.playerLevel[7] >= 40) {
						c.Cooking = true;
						c.FishId = 377;
					} else {
						c.Send("You need 40 or higher Cooking to cook lobster.");
						return;
					}
				}
				if (Item == 389) {
					if (c.playerLevel[7] >= 80) {
						c.Cooking = true;
						c.FishId = 389;
					} else {
						c.Send("You need 80 or higher Cooking to cook manta ray.");
						return;
					}
				}
				if (Item == 7944) {
					if (c.playerLevel[7] >= 63) {
						c.Cooking = true;
						c.FishId = 7944;
					} else {
						c.Send("You need 63 or higher Cooking to cook monkfish.");
						return;
					}
				}
				if (Item == 383) {
					if (c.playerLevel[7] >= 76) {
						c.Cooking = true;
						c.FishId = 383;
					} else {
						c.Send("You need 76 or higher Cooking to cook shark.");
						return;
					}
				}
				if (c.hasItem(c.FishId)) {
					c.Cook();
				}
			}
		}
		if ((Item >= 1704 && Item <= 1710) && Object == 2638) {
			c.anim(827);
			c.ReplaceItems(1712, 1704, 1, 1);
			c.ReplaceItems(1712, 1706, 1, 1);
			c.ReplaceItems(1712, 1708, 1, 1);
			c.ReplaceItems(1712, 1710, 1, 1);
			c.Send("You dip the amulet into the altar.");
		}
		if (Object == 409) {
			if (Item == 536) {
				c.addSkillXP(9238, 5);
			} else if (Item == 6729) {
				c.addSkillXP(16582, 5);
			} else {
				return;
			}
			c.Send("The gods are very pleased with your offering.");
			c.anim(883);
			c.stillgfx(624, c.absY-1, c.absX);
			c.delete(Item, 1);
		}
		if (Item == 229 && Object == 874) {
			c.anim(832);
			c.delete(229, 1);
			c.addItem(227, 1);
			c.Send("You fill the vial with water.");
		}
		if (Object == 8552 || Object == 8553) {
			if (System.currentTimeMillis() - c.ActionDelay > 2500) {
				if (Item == 5341) {
					c.anim(2273);
					c.ActionDelay = System.currentTimeMillis();
					c.addItem(6055, 1);
					c.addSkillXP(425, 19);
					c.sendString("" + c.playerXP[19] + "", 13921);
					c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);
					c.Send("You rake the allotment.");
				}
			}
		}
		if (Object == 7837) {
			if (Item == 6055) {
				c.anim(832);
				c.delete(6055, 1);
				c.addSkillXP(25, 19);
				c.Send("You put the weeds into the Compost bin.");
			}
			if (Item == 225) {
				c.anim(832);
				c.delete(225, 1);
				c.addSkillXP(60, 19);
				c.Send("You put the limpwurt into the Compost bin.");
			}
		}
	}

	static void ItemonItem(Client c, int Item, int Item2) {
		if ((Item == 590 && Item2 == 1511) || (Item2 == 590 && Item == 1511)) {
			c.Fire(250, 0, 1511);
		}
		if ((Item == 590 && Item2 == 1521) || (Item2 == 590 && Item == 1521)) {
			c.Fire(400, 15, 1521);
		}
		if ((Item == 590 && Item2 == 1519) || (Item2 == 590 && Item == 1519)) {
			c.Fire(500, 30, 1519);
		}
		if ((Item == 590 && Item2 == 1517) || (Item2 == 590 && Item == 1517)) {
			c.Fire(600, 45, 1517);
		}
		if ((Item == 590 && Item2 == 1515) || (Item2 == 590 && Item == 1515)) {
			c.Fire(700, 60, 1515);
		}
		if ((Item == 590 && Item2 == 1513) || (Item2 == 590 && Item == 1513)) {
			c.Fire(900, 75, 1513);
		}
		if ((Item == 1755 && Item2 == 6571) || (Item == 6571 && Item2 == 1755)) {
			c.Craft(15000, 6571, 6585, 90);
		}
		if ((Item == 1755 && Item2 == 1631) || (Item == 1631 && Item2 == 1755)) {
			c.Craft(3000, 1631, 1683, 80);
		}
		if ((Item == 1755 && Item2 == 1617) || (Item == 1617 && Item2 == 1755)) {
			c.Craft(2000, 1617, 1681, 70);
		}
		if ((Item == 1755 && Item2 == 1619) || (Item == 1619 && Item2 == 1755)) {
			c.Craft(1500, 1619, 1679, 60);
		}
		if ((Item == 1755 && Item2 == 1621) || (Item == 1621 && Item2 == 1755)) {
			c.Craft(1000, 1621, 1677, 45);
		}
		if ((Item == 1755 && Item2 == 1623) || (Item == 1623 && Item2 == 1755)) {
			c.Craft(500, 1623, 1675, 30);
		}
		if ((Item == 2777 && Item2 == 7622) || (Item == 7622 && Item2 == 2777)) {
			c.delete(2777, 1);
			c.delete(7622, 1);
			c.addItem(2745, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 2775 && Item2 == 1540) || (Item == 1540 && Item2 == 2775)) {
			c.delete(2775, 1);
			c.delete(1540, 1);
			c.addItem(2774, 1);
			c.addSkillXP(20000, 13);
		}
		if ((Item == 2778 && Item2 == 7622) || (Item == 7622 && Item2 == 2778)) {
			c.delete(2778, 1);
			c.delete(7622, 1);
			c.addItem(2746, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 2779 && Item2 == 7622) || (Item == 7622 && Item2 == 2779)) {
			c.delete(2779, 1);
			c.delete(7622, 1);
			c.addItem(2747, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 2780 && Item2 == 7622) || (Item == 7622 && Item2 == 2780)) {
			c.delete(2780, 1);
			c.delete(7622, 1);
			c.addItem(2748, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 314 && Item2 == 52) || (Item == 52 && Item2 == 314)) {
			if (c.hasAmount(314, 15) && c.hasAmount(52, 15)) {
				c.delete(314, 15);
				c.delete(53, 15);
				c.addItem(53, 15);
				c.Send("You attach the feathers to the arrow shafts.");
			} else {
				c.Send("You need 15 feathers and arrow shafts to make headless arrows.");
			}
		}
		if ((Item == 39 && Item2 == 53) || (Item == 53 && Item2 == 39)) {
			if (c.hasAmount(39, 15) && c.hasAmount(53, 15)) {
				c.delete(39, 15);
				c.delete(53, 15);
				c.addItem(882, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 40 && Item2 == 53) || (Item == 53 && Item2 == 40)) {
			if (c.hasAmount(40, 15) && c.hasAmount(53, 15)) {
				c.delete(40, 15);
				c.delete(53, 15);
				c.addItem(884, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 41 && Item2 == 53) || (Item == 53 && Item2 == 41)) {
			if (c.hasAmount(41, 15) && c.hasAmount(41, 15)) {
				c.delete(41, 15);
				c.delete(53, 15);
				c.addItem(886, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 42 && Item2 == 53) || (Item == 53 && Item2 == 42)) {
			if (c.hasAmount(42, 15) && c.hasAmount(53, 15)) {
				c.delete(42, 15);
				c.delete(53, 15);
				c.addItem(888, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 43 && Item2 == 53) || (Item == 53 && Item2 == 43)) {
			if (c.hasAmount(43, 15) && c.hasAmount(53, 15)) {
				c.delete(43, 15);
				c.delete(53, 15);
				c.addItem(890, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 44 && Item2 == 53) || (Item == 53 && Item2 == 44)) {
			if (c.hasAmount(44, 15) && c.hasAmount(53, 15)) {
				c.delete(44, 15);
				c.delete(53, 15);
				c.addItem(892, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 249 && Item2 == 227) || (Item == 227 && Item2 == 249)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(249, 1);
			c.addItem(2428, 1);
			c.addSkillXP(100, 15);
		}
		if ((Item == 251 && Item2 == 227) || (Item == 227 && Item2 == 251)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(251, 1);
			c.addItem(2446, 1);
			c.addSkillXP(150, 15);
		}
		if ((Item == 253 && Item2 == 227) || (Item == 227 && Item2 == 253)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(253, 1);
			c.addItem(113, 1);
			c.addSkillXP(200, 15);
		}
		if ((Item == 257 && Item2 == 227) || (Item == 227 && Item2 == 257)) {
			if (c.playerLevel[15] >= 40) {
				c.anim(363);
				c.delete(227, 1);
				c.delete(257, 1);
				c.addItem(2434, 1);
				c.addSkillXP(700, 15);
			} else {
				c.Send("You need 40 or higher Herblore to make Prayer potions.");
			}
		}
		if ((Item == 259 && Item2 == 227) || (Item == 227 && Item2 == 259)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(259, 1);
			c.addItem(2436, 1);
			c.addSkillXP(500, 15);
		}
		if ((Item == 263 && Item2 == 227) || (Item == 227 && Item2 == 263)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(263, 1);
			c.addItem(2440, 1);
			c.addSkillXP(525, 15);
		}
		if ((Item == 3000 && Item2 == 227) || (Item == 227 && Item2 == 3000)) {
			if (c.playerLevel[15] >= 50) {
				c.anim(363);
				c.delete(227, 1);
				c.delete(3000, 1);
				c.addItem(3024, 1);
				c.addSkillXP(950, 15);
			} else {
				c.Send("You need 50 or higher Herblore to make Super restore potions.");
			}
		}
		if ((Item == 265 && Item2 == 227) || (Item == 227 && Item2 == 265)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(265, 1);
			c.addItem(2442, 1);
			c.addSkillXP(500, 15);
		}
		if ((Item == 2366 && Item2 == 2368) || (Item == 2368 && Item2 == 2366)) {
			c.delete(2366, 1);
			c.delete(2368, 1);
			c.addItem(1187, 1);
			c.addSkillXP(15000, 13);
		}
		if ((Item == 267 && Item2 == 227) || (Item == 227 && Item2 == 267)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(267, 1);
			c.addItem(2444, 1);
			c.addSkillXP(500, 15);
		}
		if ((Item == 2481 && Item2 == 227) || (Item == 227 && Item2 == 2481)) {
			c.anim(363);
			c.delete(227, 1);
			c.delete(2481, 1);
			c.addItem(3040, 1);
			c.addSkillXP(550, 15);
		}
		if ((Item == 2998 && Item2 == 227) || (Item == 227 && Item2 == 2998)) {
			if (c.playerLevel[15] >= 65) {
				c.anim(363);
				c.delete(227, 1);
				c.delete(2998, 1);
				c.addItem(6685, 1);
				c.addSkillXP(1200, 15);
			} else {
				c.Send("You need 65 or higher Herblore to make Saradomin brews.");
			}
		}
		if ((Item == 1759 && Item2 == 1683) || (Item == 1683 && Item2 == 1759)) {
			c.delete(1759, 1);
			c.delete(1683, 1);
			c.addItem(1712, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1681 && Item2 == 1759) || (Item == 1759 && Item2 == 1681)) {
			c.delete(1759, 1);
			c.delete(1681, 1);
			c.addItem(1731, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1759 && Item2 == 1679) || (Item == 1679 && Item2 == 1759)) {
			c.delete(1759, 1);
			c.delete(1679, 1);
			c.addItem(1725, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1677 && Item2 == 1759) || (Item == 1759 && Item2 == 1677)) {
			c.delete(1759, 1);
			c.delete(1677, 1);
			c.addItem(1729, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1759 && Item2 == 1675) || (Item == 1675 && Item2 == 1759)) {
			c.delete(1759, 1);
			c.delete(1675, 1);
			c.addItem(1727, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if (Item == 946 && Item2 == 1511) {
			c.delete(1511, 1);
			c.addItem(52, 15);
			c.addSkillXP(100, 9);
		}
		if (Item == 946 && Item2 == 1521) {
			if (c.playerLevel[9] >= 20) {
				c.delete(1521, 1);
				c.addItem(54, 1);
				c.addSkillXP((60 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 20 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1519) {
			if (c.playerLevel[9] >= 40) {
				c.delete(1519, 1);
				c.addItem(60, 1);
				c.addSkillXP((70 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 40 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1517) {
			if (c.playerLevel[9] >= 50) {
				c.delete(1517, 1);
				c.addItem(64, 1);
				c.addSkillXP((80 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 50 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1515) {
			if (c.playerLevel[9] >= 60) {
				c.delete(1515, 1);
				c.addItem(68, 1);
				c.addSkillXP((90 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 60 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1513) {
			if (c.playerLevel[9] >= 80) {
				c.delete(1513, 1);
				c.addItem(72, 1);
				c.addSkillXP((100 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 80 to make this bow.");
			}
		}
		if (Item == 54 && Item2 == 1777) {
			if (c.playerLevel[9] >= 20) {
				c.delete(54, 1);
				c.delete(1777, 1);
				c.addItem(843, 1);
				c.addSkillXP(25, 9);
			} else {
				c.Send("You need a fletching level of 20 to make this bow.");
			}
		}
		if (Item == 60 && Item2 == 1777) {
			if (c.playerLevel[9] >= 40) {
				c.delete(60, 1);
				c.delete(1777, 1);
				c.addItem(849, 1);
				c.addSkillXP(40, 9);
			} else {
				c.Send("You need a fletching level of 40 to make this bow.");
			}
		}
		if (Item == 64 && Item2 == 1777) {
			if (c.playerLevel[9] >= 50) {
				c.delete(64, 1);
				c.delete(1777, 1);
				c.addItem(853, 1);
				c.addSkillXP(65, 9);
			} else {
				c.Send("You need a fletching level of 50 to make this bow.");
			}
		}
		if (Item == 68 && Item2 == 1777) {
			if (c.playerLevel[9] >= 60) {
				c.delete(68, 1);
				c.delete(1777, 1);
				c.addItem(857, 1);
				c.addSkillXP(90, 9);
			} else {
				c.Send("You need a fletching level of 60 to make this bow.");
			}
		}
		if (Item == 72 && Item2 == 1777) {
			if (c.playerLevel[9] >= 80) {
				c.delete(72, 1);
				c.delete(1777, 1);
				c.addItem(861, 1);
				c.addSkillXP(120, 9);
			} else {
				c.Send("You need a fletching level of 80 to make this bow.");
			}
		}
		if ((Item == 1733 && Item2 == 1741) || (Item == 1741 && Item2 == 1733)) {
			c.showInterface(2311);
		}
	}

	static void OperateItem(Client c, int Item, int Slot) {
		boolean eat = false, bury = false, pot = false;
		int abc = 0, cba = 0, aaa = 0, abc2 = 0, heal = 0, add = 0;

		switch (Item) {

		case 952:
			c.digSpade();
			break;

		case 6887:
			c.itemMessage("You currently have "+c.magearenapoints+" points.", "Progress hat", 6887, 250);
			break;

		case 3492:
			if (c.cluescroll == 0) {
				c.Send("You try reading the clue scroll...");
				c.Send("The words on the clue scroll are too small for you to read!");
				c.Send("Maybe you should try reading the scroll through a telescope.");
				c.delete(3492, 1);
				c.addItem(3490, 1);
				return;
			}
			c.Send("You need to finish the clue scroll you are currently doing before starting another!");
			break;

		case 3490:
			if (c.cluescroll == 0) {
				c.Send("You need to find a telescope to read the clue scroll!");
				return;
			}
			c.Send("You need to finish the clue scroll you are currently doing before starting another!");
			break;

		case 3491:
			c.showInterface(6965);
			break;

		case 3494:
			c.showInterface(17620);
			break;

		case 3495:
			c.showInterface(17634);
			break;

		case 3511:
			if (c.hasItem(1755)) {
				c.anim(898);
				c.cluescroll = 0;
				c.deleteItem(3511, Slot, 1);
				int[] items = { 1015, 1077, 1089, 1125, 1165, 1195, 2591, 2593, 2595, 2597, 2583, 2585, 2587, 2589, 2633, 2635, 2637, 2631, 7394, 7396, 7390, 7392, 7386, 7388 };
				int r = (int) (Math.random() * items.length);
				c.addItem(items[r], 1);
				c.showInterface(6960);
				c.Send("Congratulations, you have completed the Treasure trail.");
				c.sendFrame34(6963, items[r], 1, 1);
			} else {
				c.Send("You need a chisel to break open the casket.");
			}
			break;

		case 3543:
			if (c.hasItem(1755)) {
				c.anim(898);
				c.cluescroll = 0;
				c.deleteItem(3543, Slot, 1);
				int[] items = { 2816, 2817, 2819, 2820, 2821, 2810, 2811, 2813, 2814, 2815, 2822, 2823, 2825, 2826, 2827, 2577, 2579, 1073, 1091, 1123, 1161, 1199, 2607, 2609, 2611, 2613, 2599, 2601, 2603, 2605, 7319, 7321, 7323, 7325, 7327, 2645, 2647, 2649 };
				int r = (int) (Math.random() * items.length);
				c.addItem(items[r], 1);
				c.showInterface(6960);
				c.Send("Congratulations, you have completed the Treasure trail.");
				c.sendFrame34(6963, items[r], 1, 1);
			} else {
				c.Send("You need a chisel to break open the casket.");
			}
			break;

		case 405:
			if (c.hasItem(1755)) {
				c.anim(898);
				c.cluescroll = 0;
				c.deleteItem(405, Slot, 1);
				int[] items = { 3619, 3620, 3621, 3622, 3623, 3624, 3625, 3626, 3627, 3628, 3629, 1127, 2615, 2623, 1201, 2621, 2629, 7336, 7342, 7348, 7354, 7360, 1079, 2617, 2625, 2581, 7637, 3481, 3483, 3485, 3486, 2651, 2639, 2641, 2643, 2669, 2671, 2673, 2675, 2798, 2799, 2653, 2655, 2657, 2659, 2806, 2807, 2661, 2663, 2665, 2667, 2802, 2803 };
				int r = (int) (Math.random() * items.length);
				c.addItem(items[r], 1);
				c.showInterface(6960);
				c.Send("Congratulations, you have completed the Treasure trail.");
				c.sendFrame34(6963, items[r], 1, 1);
			} else {
				c.Send("You need a chisel to break open the casket.");
			}
			break;

		case 161:
			c.anim(829);
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(161, Slot, 1);
			c.addItem(229, 1);
			break;

		case 159:
			c.anim(829);
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(159, Slot, 1);
			c.addItem(161, 1);
			break;

		case 157:
			c.anim(829);
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(157, Slot, 1);
			c.addItem(159, 1);
			break;

		case 2440:
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(2440, Slot, 1);
			c.addItem(157, 1);
			break;

		case 113:
			c.anim(829);
			c.Send("You drink a dose of the strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(113, Slot, 1);
			c.addItem(115, 1);
			c.Send("You have 3 doses of potion left.");
			break;

		case 115:
			c.anim(829);
			c.Send("You drink a dose of the strength potion.");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(115, Slot, 1);
			c.addItem(117, 1);
			c.Send("You have 2 doses of potion left.");
			break;

		case 117:
			c.anim(829);
			c.Send("You drink a dose of the strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(117, Slot, 1);
			c.addItem(119, 1);
			c.Send("You have 1 dose of potion left.");
			break;

		case 119:
			c.Send("You drink a dose of the strength potion.");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.deleteItem(119, Slot, 1);
			c.addItem(229, 1);
			c.Send("You have finished the potion.");
			break;

		case 2446:
			c.anim(829);
			c.Send("You drink a dose of the antipoison.");
			c.deleteItem(2446, Slot, 1);
			c.addItem(175, 1);
			c.Poisoned = false;
			break;

		case 175:
			c.anim(829);
			c.Send("You drink a dose of the antipoison.");
			c.deleteItem(175, Slot, 1);
			c.addItem(177, 1);
			c.Poisoned = false;
			break;

		case 177:
			c.anim(829);
			c.Send("You drink a dose of the antipoison potion.");
			c.deleteItem(177, Slot, 1);
			c.addItem(179, 1);
			c.Poisoned = false;
			break;

		case 179:
			c.anim(829);
			c.Send("You drink a dose of the antipoison potion.");
			c.deleteItem(179, Slot, 1);
			c.Poisoned = false;
			c.Send("You have finished your potion.");
			break;

		case 3030:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.deleteItem(3030, Slot, 1);
			c.addItem(229, 1);
			c.Send("You have finished your potion.");
			break;

		case 3028:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.deleteItem(3028, Slot, 1);
			c.addItem(3030, 1);
			c.Send("You have 1 dose of potion left.");
			break;

		case 3026:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.deleteItem(3026, Slot, 1);
			c.addItem(3028, 1);
			c.Send("You have 2 doses of potion left.");
			break;

		case 3024:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.deleteItem(3024, Slot, 1);
			c.addItem(3026, 1);
			c.Send("You have 3 doses of potion left.");
			break;

		case 143:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
				c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.deleteItem(143, Slot, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have finished your potion.");
			c.addItem(229, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 141:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
				c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.deleteItem(141, Slot, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have 1 dose of potion left.");
			c.addItem(143, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 139:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
				c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.deleteItem(139, Slot, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have 2 doses of potion left.");
			c.addItem(141, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 2434:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
				c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.deleteItem(2434, Slot, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have 3 doses of potion left.");
			c.addItem(139, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 167:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(167, Slot, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the super defence potion");
			break;

		case 165:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(165, Slot, 1);
			c.Send("You drink a dose of the super defence potion");
			c.addItem(167, 1);
			break;

		case 163:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(163, Slot, 1);
			c.Send("You drink a dose of the super defence potion");
			c.addItem(165, 1);
			break;

		case 2442:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(2442, Slot, 1);
			c.addItem(163, 1);
			c.Send("You drink a dose of the super defence potion");
			break;

		case 137:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(137, Slot, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 135:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(135, Slot, 1);
			c.addItem(137, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 133:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(133, Slot, 1);
			c.addItem(135, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 2432:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.deleteItem(2432, Slot, 1);
			c.addItem(133, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 3046:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.deleteItem(3046, Slot, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 3044:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.deleteItem(3044, Slot, 1);
			c.addItem(3046, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 3042:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.deleteItem(3042, Slot, 1);
			c.addItem(3044, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 3040:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.deleteItem(3040, Slot, 1);
			c.addItem(3042, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 173:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[6]+"", 4010);
			c.deleteItem(173, Slot, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the range potion.");
			break;

		case 171:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[4]+"", 4010);
			c.deleteItem(171, Slot, 1);
			c.addItem(173, 1);
			c.Send("You drink a dose of the range potion");
			break;

		case 169:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[4]+"", 4010);
			c.deleteItem(169, Slot, 1);
			c.addItem(171, 1);
			c.Send("You drink a dose of the range potion");
			break;

		case 2444:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[4]+"", 4010);
			c.deleteItem(2444, Slot, 1);
			c.addItem(169, 1);
			c.Send("You drink a dose of the range potion");
			break;

		case 149:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(149, Slot, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 147:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(147, Slot, 1);
			c.addItem(149, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 145:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(145, Slot, 1);
			c.addItem(147, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 2436:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(2436, Slot, 1);
			c.addItem(145, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 125:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(125, Slot, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 123:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(123, Slot, 1);
			c.addItem(125, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 121:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(121, Slot, 1);
			c.addItem(123, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 2428:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
				abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.deleteItem(2428, Slot, 1);
			c.addItem(121, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 315:
			c.Send("You eat the shrimps.");
			eat = true;
			heal = 3;
			break;

		case 379:
			c.Send("You eat the lobster.");
			eat = true;
			heal = 12;
			break;

		case 385:
			c.Send("You eat the shark.");
			eat = true;
			heal = 20;
			break;

		case 7946:
			c.Send("You eat the monkfish.");
			eat = true;
			heal = 16;
			break;

		case 6685:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 6687:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 6689:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 6691:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 391:
			c.Send("You eat the manta ray.");
			eat = true;
			heal = 24;
			break;

		case 534:
			bury = true;
			add = 140;
			break;

		case 536:
			bury = true;
			add = 500;
			break;

		case 6729:
			bury = true;
			add = 600;
			break;

		case 526:
		case 528:
		case 2859:
			bury = true;
			add = 40;
			break;

		case 532:
		case 3125:
			bury = true;
			add = 125;
			break;

		default:
			c.Send("Nothing interesting happens.");
			break;
		}
		if (bury) {
			c.anim(827);
			c.deleteItem(Item, Slot, 1);
			c.addSkillXP(add, 5);
			c.Send("You bury the bones.");
		}
		if (eat) {
			c.anim(829);
			c.deleteItem(Item, Slot, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[c.playerHitpoints])) {
				if (Item == 385 || Item == 391 || Item == 7946 || Item == 379 || Item == 315) {
					c.Send("It heals some health.");
				}
			}
			c.UpdateHP(heal);
			if (Item == 6685) {
				c.Send("You have 3 doses of potion left.");
				c.addItem(6687, 1);
			}
			if (Item == 6687) {
				c.Send("You have 2 doses of potion left.");
				c.addItem(6689, 1);
			}
			if (Item == 6689) {
				c.Send("You have 1 dose of potion left.");
				c.addItem(6691, 1);
			}
			if (Item == 6691) {
				c.Send("You have finished the potion.");
				c.addItem(229, 1);
			}
			c.Fighting = 3000;
			c.PkingDelay = System.currentTimeMillis();
			c.IsDead = false;
		}
		c.FoodDelay = System.currentTimeMillis();
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}
}
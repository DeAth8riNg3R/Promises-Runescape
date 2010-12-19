class Prayer {

	public void resetPrayer(Client c) {
		c.protMage = false;
		c.protRange = false;
		c.protMelee = false;
		c.strLow = false;
		c.strMid = false; 
		c.strHigh = false;
		c.defLow = false;
		c.defMid = false;
		c.defHigh = false;
		c.atkLow = false;
		c.atkMid = false;
		c.atkHigh = false;
		c.rapidRest = false;
		c.rapidHeal = false;
		c.ret = false;
		c.redempt = false;
		c.smite = false;
		c.chiv = false;
		c.piety = false;
		c.headIcon = 0;
		c.AtkPray = 0;
		c.DefPray = 0; 
		c.frame36(83,0);
		c.frame36(84,0);
		c.frame36(85,0);
		c.frame36(86,0);
		c.frame36(87,0);
		c.frame36(88,0);
		c.frame36(89,0);
		c.frame36(90,0);
		c.frame36(91,0);
		c.frame36(92,0);
		c.frame36(93,0);
		c.frame36(94,0);
		c.frame36(95,0);
		c.frame36(96,0);
		c.frame36(97,0);
		c.frame36(98,0);
		c.frame36(99,0);
		c.frame36(100,0);
		c.frame36(101,0);
		c.frame36(102,0);
	}
	public void noPrayer(Client c) {
		if (!c.protMage && !c.protRange && !c.protMelee && !c.strLow && !c.strMid &&  !c.strHigh && !c.defLow && !c.defMid && !c.defHigh && !c.atkLow && !c.atkMid && !c.atkHigh && !c.protItem && !c.rapidRest && !c.rapidHeal && !c.ret && !c.redempt && !c.smite && !c.chiv && !c.piety) {
			c.noPrayer = true;
			c.drainPray = false;
		} else {
			c.noPrayer = false;
		}
	}
	public void newDrain(Client c) {
		c.oldDrain = c.prayerDrain;
		if (c.newDrain > c.oldDrain && !c.noPrayer) {
			c.prayerDrain = c.oldDrain;
		} else if(c.newDrain <= c.oldDrain || c.noPrayer) {
			c.prayerDrain = c.newDrain;
		}
	}
	public void drainPrayer(Client c) {
		c.prayerTimer -= 1;
		if (c.prayerTimer < 0 && c.playerLevel[5] < c.getLevelForXP(c.playerXP[5])){
			c.PrayerTimer = 40;
		}
		if (c.prayerTimer == 0 && c.playerLevel[5] < c.getLevelForXP(c.playerXP[5])){
			c.playerLevel[5]++;
		}
		noPrayer(c);
		if (c.prayerTimer <= 1 && c.playerLevel[5] > 0) {
			c.prayerTimer = c.prayerDrain;
			c.playerLevel[5]--;
			c.updateRequired = true;
		}
		if (c.playerLevel[5] <= 0) {
			c.prayerTimer = 0;
			c.drainPray = false;
			c.protItem = false;
			resetPrayer(c);
			if (!c.Skulled) {
				c.headIcon = 0;
			} else {
				c.headIcon = 64;
			}
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.Send("You have run out of Prayer points; you must recharge at an altar.");
		}
		c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
		c.sendFrame126(""+c.playerLevel[5]+"", 4012);
	}
	public void prayerPot(Client c) {
		c.anim(829);
		if(c.getLevelForXP(c.playerXP[5]) >= 1 && c.getLevelForXP(c.playerXP[5]) <= 3) {
			c.playerLevel[5] += 7;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 4 && c.getLevelForXP(c.playerXP[5]) <= 7) {
			c.playerLevel[5] += 8;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 8 && c.getLevelForXP(c.playerXP[5]) <= 11) {
			c.playerLevel[5] += 9;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 12 && c.getLevelForXP(c.playerXP[5]) <= 15) {
			c.playerLevel[5] += 10;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 16 && c.getLevelForXP(c.playerXP[5]) <= 19) {
			c.playerLevel[5] += 11;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 20 && c.getLevelForXP(c.playerXP[5]) <= 23) {
			c.playerLevel[5] += 12;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 24 && c.getLevelForXP(c.playerXP[5]) <= 27) {
			c.playerLevel[5] += 13;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 28 && c.getLevelForXP(c.playerXP[5]) <= 31) {
			c.playerLevel[5] += 14;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 32 && c.getLevelForXP(c.playerXP[5]) <= 35) {
			c.playerLevel[5] += 15;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 36 && c.getLevelForXP(c.playerXP[5]) <= 39) {
			c.playerLevel[5] += 16;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 40 && c.getLevelForXP(c.playerXP[5]) <= 43) {
			c.playerLevel[5] += 17;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 44 && c.getLevelForXP(c.playerXP[5]) <= 47) {
			c.playerLevel[5] += 18;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 48 && c.getLevelForXP(c.playerXP[5]) <= 51) {
			c.playerLevel[5] += 19;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 52 && c.getLevelForXP(c.playerXP[5]) <= 55) {
			c.playerLevel[5] += 20;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 56 && c.getLevelForXP(c.playerXP[5]) <= 59) {
			c.playerLevel[5] += 21;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 60 && c.getLevelForXP(c.playerXP[5]) <= 63) {
			c.playerLevel[5] += 22;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 64 && c.getLevelForXP(c.playerXP[5]) <= 67) {
			c.playerLevel[5] += 23;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 68 && c.getLevelForXP(c.playerXP[5]) <= 71) {
			c.playerLevel[5] += 24;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 72 && c.getLevelForXP(c.playerXP[5]) <= 75) {
			c.playerLevel[5] += 25;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 76 && c.getLevelForXP(c.playerXP[5]) <= 79) {
			c.playerLevel[5] += 26;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 80 && c.getLevelForXP(c.playerXP[5]) <= 83) {
			c.playerLevel[5] += 27;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 84 && c.getLevelForXP(c.playerXP[5]) <= 87) {
			c.playerLevel[5] += 28;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 88 && c.getLevelForXP(c.playerXP[5]) <= 91) {
			c.playerLevel[5] += 29;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 92 && c.getLevelForXP(c.playerXP[5]) <= 95) {
			c.playerLevel[5] += 30;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 96 && c.getLevelForXP(c.playerXP[5]) <= 99) {
			c.playerLevel[5] += 31;
		}
		if(c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) {
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		}
		c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
		c.sendFrame126(""+c.playerLevel[5]+"", 4012);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}
	public void clickPrayer(Client c) {

		switch(c.actionButtonId) {

			case 21233:
				if(c.getLevelForXP(c.playerXP[5]) >= 1) {
					if(c.playerLevel[5] > 0) {
						if(!c.defLow) {
							if(c.defMid || c.defHigh || c.piety || c.chiv) {
								c.defMid = false;
								c.defHigh = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(86, 0);
								c.frame36(92, 0);
							}
							noPrayer(c);
							c.newDrain = 40;
							c.drainPray = true;
							c.defLow = true;
							c.DefPray = 1; 
							newDrain(c);
							c.frame36(83, 1);
						} else {
							c.defLow = false;
							c.frame36(83,0);
						}
					} else {
						c.Send("You need to recharge your Prayer at an altar.");
						c.frame36(83,0);
					}
				} else {
					c.sendFrame126("You need a @blu@Prayer level of 1 to use Thick Skin.", 357);
					c.sendFrame164(356);
					c.frame36(83,0);
				}
				break;

			case 21234:
				if(c.getLevelForXP(c.playerXP[5]) >= 4) {
					if(c.playerLevel[5] > 0) {
						if(!c.strLow) {
							if(c.strMid || c.strHigh || c.piety || c.chiv) {
								c.strMid = false;
								c.strHigh = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(87, 0);
								c.frame36(93, 0);
								c.frame36(101,0);
								c.frame36(102,0);
							}
							noPrayer(c);
							c.newDrain = 38;
							c.drainPray = true;
							c.strLow = true;
							newDrain(c);
							c.frame36(84, 1);
						} else {
							c.strLow = false;
							c.frame36(84, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer at an altar.");
						c.frame36(84,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 4 to use Burst of Strength.");
					c.frame36(84, 0);
				}
				break;

			case 21235:
				if(c.getLevelForXP(c.playerXP[5]) >= 7) {
					if(c.playerLevel[5] > 0) {
						if(!c.atkLow) {
							if(c.atkMid || c.atkHigh || c.piety || c.chiv) {
								c.atkMid = false;
								c.atkHigh = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(88, 0);
								c.frame36(94, 0);
							}
							noPrayer(c);
							c.newDrain = 36;
							c.drainPray = true;
							c.atkLow = true;
							c.AtkPray = 1;
							newDrain(c);
							c.frame36(85, 1);
						} else {
							c.atkLow = false;
							c.frame36(85, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer at an altar.");
						c.frame36(85,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 7 to use Clarity of Thought.");
					c.frame36(85, 0);
				}
				break;

			case 21236:
				if(c.getLevelForXP(c.playerXP[5]) >= 10) {
					if(c.playerLevel[5] > 0) {
						if(!c.defMid) {
							if(c.defLow || c.defHigh || c.piety || c.chiv) {
								c.defLow = false;
								c.defHigh = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(83, 0);
								c.frame36(92, 0);
							}
							noPrayer(c);
							c.newDrain = 34;
							c.drainPray = true;
							c.defMid = true;
							c.DefPray = 2; 
							newDrain(c);
							c.frame36(86, 1);
						} else {
							c.defMid = false;
							c.frame36(86, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(86,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 10 to use Rock Skin.");
					c.frame36(86, 0);
				}
				break;

			case 21237:
				if(c.getLevelForXP(c.playerXP[5]) >= 13) {
					if(c.playerLevel[5] > 0) {
						if(!c.strMid) {
							if(c.strLow || c.strHigh || c.piety || c.chiv) {
								c.strLow = false;
								c.strHigh = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(84, 0);
								c.frame36(93, 0);
							}
							noPrayer(c);
							c.newDrain = 32;
							c.drainPray = true;
							c.strMid = true;
							newDrain(c);
							c.frame36(87, 1);
						} else {
							c.strMid = false;
							c.frame36(87, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(87,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 13 to use Superhuman Strength.");
					c.frame36(87, 0);
				}
				break;

			case 21238:
				if(c.getLevelForXP(c.playerXP[5]) >= 16) {
					if(c.playerLevel[5] > 0) {
						if(!c.atkMid) {
							if(c.atkLow || c.atkHigh || c.piety || c.chiv) {
								c.atkLow = false;
								c.atkHigh = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(85, 0);
								c.frame36(94, 0);
							}
							noPrayer(c);
							c.newDrain = 30;
							c.drainPray = true;
							c.atkMid = true;
							c.AtkPray = 2;
							newDrain(c);
							c.frame36(88, 1);
						} else {
							c.atkMid = false;
							c.frame36(88, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(88,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 16 to use Improved Reflexes.");
					c.frame36(88, 0);
				}
				break;

			case 21239:
				if(c.getLevelForXP(c.playerXP[5]) >= 19) {
					if(c.playerLevel[5] > 0) {
						if(!c.rapidRest) {
							noPrayer(c);
							c.newDrain = 28;
							c.drainPray = true;
							c.rapidRest = true;
							newDrain(c);
							c.frame36(89, 1);
						} else {
							c.rapidRest = false;
							c.frame36(89, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(89,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 19 to use Rapid Restore.");
					c.frame36(89, 0);
				}
				break;

			case 21240:
				if(c.getLevelForXP(c.playerXP[5]) >= 22) {
					if(c.playerLevel[5] > 0) {
						if(!c.rapidHeal) {
							noPrayer(c);
							c.newDrain = 26;
							c.drainPray = true;
							c.rapidHeal = true;
							newDrain(c);
							c.frame36(90, 1);
						} else {
							c.rapidHeal = false;
							c.frame36(90, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(90,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 22 to use Rapid Heal.");
					c.frame36(90, 0);
				}
				break;

			case 21241:
				if(c.getLevelForXP(c.playerXP[5]) >= 25) {
					if(c.playerLevel[5] > 0) {
						if(!c.protItem) {
							noPrayer(c);
							c.newDrain = 24;
							c.drainPray = true;
							c.protItem = true;
							newDrain(c);
							c.frame36(91, 1);
						} else {
							c.protItem = false;
							c.frame36(91, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(91,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 25 to use Protect Item.");
					c.frame36(91, 0);
				}
				break;

			case 21242:
				if(c.getLevelForXP(c.playerXP[5]) >= 28) {
					if(c.playerLevel[5] > 0) {
						if(!c.defHigh) {
							if(c.defLow || c.defMid || c.piety || c.chiv) {
								c.defLow = false;
								c.defMid = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(83, 0);
								c.frame36(86, 0);
							}
							noPrayer(c);
							c.newDrain = 22;
							c.drainPray = true;
							c.defHigh = true;
							c.DefPray = 4; 
							newDrain(c);
							c.frame36(92, 1);
						} else {
							c.defHigh = false;
							c.frame36(92, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(92,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 28 to use Steel Skin.");
					c.frame36(92, 0);
				}
				break;

			case 21243:
				if(c.getLevelForXP(c.playerXP[5]) >= 31) {
					if(c.playerLevel[5] > 0) {
						if(!c.strHigh) {
							if(c.strLow || c.strMid || c.piety || c.chiv) {
								c.strLow = false;
								c.strMid = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(84, 0);
								c.frame36(87, 0);
							}
							noPrayer(c);
							c.newDrain = 20;
							c.drainPray = true;
							c.strHigh = true;
							newDrain(c);
							c.frame36(93, 1);
						} else {
							c.strHigh = false;
							c.frame36(93, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(93,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 31 to use Ultimate Strength.");
					c.frame36(93, 0);
				}
				break;

			case 21244:
				if(c.getLevelForXP(c.playerXP[5]) >= 34) {
					if(c.playerLevel[5] > 0) {
						if(!c.atkHigh) {
							if(c.atkLow || c.atkMid || c.piety || c.chiv) {
								c.atkLow = false;
								c.atkMid = false;
								c.piety = false;
								c.chiv = false;
								c.frame36(101,0);
								c.frame36(102,0);
								c.frame36(85, 0);
								c.frame36(88, 0);
							}
							noPrayer(c);
							c.newDrain = 18;
							c.drainPray = true;
							c.atkHigh = true;
							c.AtkPray = 4;
							newDrain(c);
							c.frame36(94, 1);
						} else {
							c.atkHigh = false;
							c.frame36(94, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(94,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 34 to use Incredible Reflexes.");
					c.frame36(94, 0);
				}
				break;

			case 21245:
				if(c.getLevelForXP(c.playerXP[5]) >= 37) {
					if(c.playerLevel[5] > 0) {
						if(!c.protMage) {
							if(c.protRange || c.protMelee || c.ret || c.redempt || c.smite) {
								c.protMelee = false;
								c.protRange = false;
								c.ret = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(100, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(c);
							c.newDrain = 16;
							c.drainPray = true;
							c.protMage = true;
							newDrain(c);
							c.frame36(95, 1);
							if (!c.Skulled) {
								c.headIcon = 64;
							} else {
								c.headIcon = 128;
							}
						} else {
							c.protMage = false;
							c.frame36(95, 0);
							if (!c.Skulled) {
								c.headIcon = 0;
							} else {
								c.headIcon = 64;
							}
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(95,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 37 to use Protect from Magic.");
					c.frame36(95, 0);
				}
				break;

			case 21246:
				if(c.getLevelForXP(c.playerXP[5]) >= 40) {
					if(c.playerLevel[5] > 0) {
						if(!c.protRange) {
							if(c.protMage || c.protMelee || c.ret || c.redempt || c.smite) {
								c.protMelee = false;
								c.protMage = false;
								c.ret = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(97, 0);
								c.frame36(100, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(c);
							c.newDrain = 14;
							c.drainPray = true;
							c.protRange = true;
							newDrain(c);
							c.frame36(96, 1);
							if (!c.Skulled) {
								c.headIcon = 32;
							} else {
								c.headIcon = 96;
							}
						} else {
							c.protRange = false;
							c.frame36(96, 0);
							if (!c.Skulled) {
								c.headIcon = 0;
							} else {
								c.headIcon = 64;
							}
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(96,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 40 to use Protect from Missiles.");
					c.frame36(96, 0);
				}
				break;

			case 21247:
				if(c.getLevelForXP(c.playerXP[5]) >= 43) {
					if(c.playerLevel[5] > 0) {
						if(!c.protMelee) {
							if(c.protMage || c.protRange || c.ret || c.redempt || c.smite) {
								c.protRange = false;
								c.protMage = false;
								c.ret = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(100, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(c);
							c.newDrain = 12;
							c.drainPray = true;
							c.protMelee = true;
							newDrain(c);
							c.frame36(97, 1);
							if (!c.Skulled) {
								c.headIcon = 16;
							} else {
								c.headIcon = 80;
							}
						} else {
							c.protMelee = false;
							c.frame36(97, 0);
							if (!c.Skulled) {
								c.headIcon = 0;
							} else {
								c.headIcon = 64;
							}
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(97,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 43 to use Protect from Melee.");
					c.frame36(97, 0);
				}
				break;

			case 2171:
				if(c.getLevelForXP(c.playerXP[5]) >= 46) {
					if(c.playerLevel[5] > 0) {
						if(!c.ret) {
							if(c.protMage || c.protRange || c.protMelee || c.redempt || c.smite) {
								c.protRange = false;
								c.protMage = false;
								c.protMelee = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(100, 0);
								c.frame36(99, 0);
							}
							noPrayer(c);
							c.newDrain = 10;
							c.drainPray = true;
							c.ret = true;
							newDrain(c);
							c.frame36(98, 1);
							if (!c.Skulled) {
								c.headIcon = 8;
							} else {
								c.headIcon = 72;
							}
						} else {
							c.ret = false;
							c.frame36(98, 0);
							if (!c.Skulled) {
								c.headIcon = 0;
							} else {
								c.headIcon = 64;
							}
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(98,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 46 to use Retribution.");
					c.frame36(98, 0);
				}
				break;

			case 2172:
				if(c.getLevelForXP(c.playerXP[5]) >= 49) {
					if(c.playerLevel[5] > 0) {
						if(!c.redempt) {
							if(c.protMage || c.protRange || c.ret || c.protMelee || c.smite) {
								c.protRange = false;
								c.protMage = false;
								c.ret = false;
								c.protMelee = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(98, 0);
								c.frame36(100, 0);
							}
							noPrayer(c);
							c.newDrain = 8;
							c.drainPray = true;
							c.redempt = true;
							newDrain(c);
							c.frame36(99, 1);
							if (!c.Skulled) {
								c.headIcon = 32;
							} else {
								c.headIcon = 96;
							}
						} else {
							c.redempt = false;
							c.frame36(99, 0);
							if (!c.Skulled) {
								c.headIcon = 0;
							} else {
								c.headIcon = 64;
							}
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(99,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 49 to use Redemption.");
					c.frame36(99, 0);
				}
				break;

			case 2173:
				if(c.getLevelForXP(c.playerXP[5]) >= 52) {
					if(c.playerLevel[5] > 0) {
						if(!c.smite) {
							if(c.protMage || c.protRange || c.ret || c.redempt || c.protMelee) {
								c.protRange = false;
								c.protMage = false;
								c.ret = false;
								c.redempt = false;
								c.protMelee = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(c);
							c.newDrain = 6;
							c.drainPray = true;
							c.smite = true;
							newDrain(c);
							c.frame36(100, 1);
							if (!c.Skulled) {
								c.headIcon = 16;
							} else {
								c.headIcon = 80;
							}
						} else {
							c.smite = false;
							c.frame36(100, 0);
							if (!c.Skulled) {
								c.headIcon = 0;
							} else {
								c.headIcon = 64;
							}
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(100,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 52 to use Smite.");
					c.frame36(100, 0);
				}
				break;

			case 74096:
				if(c.getLevelForXP(c.playerXP[5]) >= 60) {
					if(c.playerLevel[5] > 0) {
						if(!c.chiv) {
							if (c.piety || c.defLow || c.defMid || c.defHigh || c.strLow || c.strMid || c.strHigh || c.atkLow || c.atkMid || c.atkHigh) {
								c.frame36(83,0);
								c.frame36(84,0);
								c.frame36(85,0);
								c.frame36(86,0);
								c.frame36(87,0);
								c.frame36(88,0);
								c.frame36(92,0);
								c.frame36(93,0);
								c.frame36(94,0);
								c.frame36(102,0);
								c.piety = false;
								c.strLow = false;
								c.strMid = false; 
								c.strHigh = false;
								c.defLow = false;
								c.defMid = false;
								c.defHigh = false;
								c.atkLow = false;
								c.atkMid = false;
								c.atkHigh = false;
							}
							noPrayer(c);
							c.newDrain = 5;
							c.drainPray = true;
							c.chiv = true;
							c.AtkPray = 7;
							c.DefPray = 7; 
							newDrain(c);
							c.frame36(101, 1);
						} else {
							c.chiv = false;
							c.AtkPray = 0;
							c.DefPray = 0; 
							c.frame36(101, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(101,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 60 to use Chivalry.");
					c.frame36(101, 0);
				}
				break;

			case 74102:
				if(c.getLevelForXP(c.playerXP[5]) >= 70) {
					if(c.playerLevel[5] > 0) {
						if(!c.piety) {
							if (c.chiv || c.defLow || c.defMid || c.defHigh || c.strLow || c.strMid || c.strHigh || c.atkLow || c.atkMid || c.atkHigh) {
								c.frame36(83,0);
								c.frame36(84,0);
								c.frame36(85,0);
								c.frame36(86,0);
								c.frame36(87,0);
								c.frame36(88,0);
								c.frame36(92,0);
								c.frame36(93,0);
								c.frame36(94,0);
								c.frame36(101,0);
								c.chiv = false;
								c.strLow = false;
								c.strMid = false; 
								c.strHigh = false;
								c.defLow = false;
								c.defMid = false;
								c.defHigh = false;
								c.atkLow = false;
								c.atkMid = false;
								c.atkHigh = false;
							}
							noPrayer(c);
							c.newDrain = 4;
							c.drainPray = true;
							c.piety = true;
							c.AtkPray = 10;
							c.DefPray = 10; 
							newDrain(c);
							c.frame36(102, 1);
						} else {
							c.piety = false;
							c.AtkPray = 0;
							c.DefPray = 0; 
							c.frame36(102, 0);
						}
					} else {
						c.Send("You need to recharge your Prayer points at an altar.");
						c.frame36(102,0);
					}
				} else {
					c.sendChat("You need a @blu@Prayer level of 70 to use Piety.");
					c.frame36(102, 0);
				}
				break;
		}
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}
}
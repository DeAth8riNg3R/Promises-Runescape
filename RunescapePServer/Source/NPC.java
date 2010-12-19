class NPC {
	public int npcId;
	public int npcType;
	public int face;

	public int SpawnedBy = -1;
	public int PoisonDelay = -1;
        public int PoisonClear;
	public int Poison;
	public int absX, absY;
	public int heightLevel;
	public int makeX, makeY, moverangeX1, moverangeY1, moverangeX2, moverangeY2, moveX, moveY, direction, walkingType, attacknpc, followPlayer;
	public int spawnX, spawnY;
        public int viewX, viewY;
	public int HP, MaxHP, hitDiff, MaxHit, animNumber, actionTimer, StartKilling, enemyX, enemyY;
	public boolean IsDead, DeadApply, NeedRespawn, IsUnderAttack, IsClose, Respawns, IsUnderAttackNpc, IsAttackingNPC, poisondmg, walkingToPlayer, followingPlayer;
	public int[] Killing = new int[Server.PlayerManager.maxPlayers];
	public boolean Stunned;
	public boolean Barrows;

	public boolean faceUpdateRequired;
	public boolean RandomWalk;
	public boolean dirUpdateRequired;
	public boolean animUpdateRequired;
	public boolean hitUpdateRequired;
	public boolean updateRequired;
	public boolean textUpdateRequired;
	public boolean faceToUpdateRequired;
        public boolean attackable = true;
	public String textUpdate;

	public void faceplayer(int i)
	{
		face = i + 32768;
		faceUpdateRequired = true;
		updateRequired = true;
	}

	public void updateface(Stream stream1)
	{
		stream1.writeWord(face);
	}
	
	public NPC(int _npcId, int _npcType) {
		npcId = _npcId;
		npcType = _npcType;
		direction = -1;
		IsDead = false;
		DeadApply = false;
		actionTimer = 0;
		RandomWalk = true;
		StartKilling = 0;
		IsUnderAttack = false;
		IsClose = false;
		for (int i = 0; i < Killing.length; i++) {
			Killing[i] = 0;
		}
	}

	public int getKiller() {
		int Killer = 0;
		int Count = 0;
		for (int i = 1; i < PlayerManager.maxPlayers; i++) {
			if (false) {
				Killer = i;
				Count = 1;
			} else {
				if (Killing[i] > Killing[Killer]) {
					Killer = i;
					Count = 1;
				} else if (Killing[i] == Killing[Killer]) {
					Count++;
				}
			}
		}
		return Killer;
	}
	
	public void updateNPCMovement(Stream str) {
		if (direction == -1) {
			if (updateRequired) {
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else {
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[direction]);
			if (updateRequired) {
				str.writeBits(1, 1);
			} else {
				str.writeBits(1, 0);
			}
		}
	}

	public void appendNPCUpdateBlock(Stream str) {
		if(!updateRequired) return ;
		int updateMask = 0;
		if(textUpdateRequired) updateMask |= 1;
		if(animUpdateRequired) updateMask |= 0x10;
                //if(hitUpdateRequired) updateMask |= 0x8;
		if(hitUpdateRequired) updateMask |= 0x40;
		if(dirUpdateRequired) updateMask |= 0x20;
boolean faceUp = false;
		if(faceUpdateRequired && updateMask == 0){
		updateMask |= 0x20;
		faceUp = true;
		}
			str.writeByte(updateMask);

		if(textUpdateRequired) {
			str.writeString(textUpdate);
		}
		if (animUpdateRequired) appendAnimUpdate(str);
		if (hitUpdateRequired) appendHitUpdate(str);
		if (dirUpdateRequired) appendDirUpdate(str);
if (faceUpdateRequired && faceUp) updateface(str);
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		textUpdateRequired = false;
		hitUpdateRequired = false;
		animUpdateRequired = false;
		dirUpdateRequired = false;
		textUpdate = null;
		moveX = 0;
		moveY = 0;
		direction = -1;
	}

	public int getNextWalkingDirection() {
		int dir;
		dir = Misc.direction(absX, absY, (absX + moveX), (absY + moveY));
		if(dir == -1) return -1;
		dir >>= 1;
		absX += moveX;
		absY += moveY;
		return dir;
	}

	public void getNextNPCMovement() {
		direction = -1;
		direction = getNextWalkingDirection();
	}

	protected void appendHitUpdate(Stream str) {		
		try {
			HP -= hitDiff;
			if (HP <= 0) {
				IsDead = true;
			}
			str.writeByteC(hitDiff);
			if (hitDiff > 0 && !poisondmg) {
				str.writeByteS(1);
			} else if (hitDiff > 0 && poisondmg) {
				str.writeByteS(2);
			} else {
				str.writeByteS(0);
			}
			str.writeByteS(Misc.getCurrentHP(HP, MaxHP, 100));
			str.writeByteC(100);
                        poisondmg = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void appendAnimUpdate(Stream str) {
		str.writeWordBigEndian(animNumber);
		str.writeByte(1);
	}

	public void appendDirUpdate(Stream str){
		str.writeWord(direction);
	}
        
        public void appendFaceToUpdate(Stream str) {
                str.writeWordBigEndian(viewX);
                str.writeWordBigEndian(viewY);
        }
}

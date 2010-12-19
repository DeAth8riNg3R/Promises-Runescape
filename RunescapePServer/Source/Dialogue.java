class Dialogue {

	static void sendDialogue(Client c, int Dialog) {

		switch (Dialog) {


			case 599:
				c.NPC("Hello!", "Would you like a make-over?", 599);	
				break;

			case 600:
				c.Player("Sure,", "I'd love one!");
				break;

			case 601:
				c.RemoveAllWindows();
				c.showInterface(3559);
				c.apset = true;
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
		
			case 0:
				c.NPC("Hello!", "Welcome to F2P City", 0);	
				break;

			case 1:
				c.Player("Thanks,", "I'ts a pleasure!");
				break;

			case 2:
				c.RemoveAllWindows();
				c.showInterface(3559);
				c.apset = true;
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
		}
	}
}
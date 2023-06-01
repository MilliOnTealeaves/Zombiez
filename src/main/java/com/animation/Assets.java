package com.animation;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Assets
{
	private static boolean initialized = false;
	private static Sprite armor;
	private static Sprite no_armor;

	private static Sprite zombieSprite;
	private static Sprite portraitSprite;
	private static Sprite bulletSprite;
	private static Sprite mapSprite;
	
	public Sprite player;
	public Sprite zombie;
	public Sprite portrait;
	public Sprite bullet;
	public Sprite map;
	
	public Assets ()
	{	
		if(!initialized) initializeSprites();
		player = no_armor;
		zombie = zombieSprite;
		portrait = portraitSprite;
		bullet = bulletSprite;
		map = mapSprite;
	}

	public void setPlayerArmor(boolean a)
	{
		int row = player.spriteRow;
		int col = player.spriteCol;
		if(a)
		{
			player = armor;
		}
		else
		{
			player = no_armor;
		}

		// neccessary because position is only initalized for the first-used armor variant
		player.spriteRow = row;
		player.spriteCol = col;
	}

	public static void initializeSprites()
	{
		zombieSprite = new Sprite();
		portraitSprite = new Sprite();
		armor = new Sprite();
		no_armor = new Sprite();
		bulletSprite = new Sprite();
		mapSprite = new Sprite();

		StringReader stream = new StringReader(THE_ABOMINATION);
		Scanner sc = new Scanner(stream);
		String line;
		while(sc != null && sc.hasNextLine())
		{
			line = sc.nextLine();
			if(line.startsWith("<portrait"))
			{
				portraitSprite.add(Sprite.scanSprite(sc));
			}
			else if(line.startsWith("<zombie"))
			{
				zombieSprite.add(Sprite.scanSprite(sc));
			}
			else if(line.startsWith("<player armor"))
			{
				armor.add(Sprite.scanSprite(sc));
			}
			else if(line.startsWith("<player no_armor"))
			{
				no_armor.add(Sprite.scanSprite(sc));	
			}
			else if(line.startsWith("<map"))
			{
				mapSprite.add(Sprite.scanSprite(sc));
			}
		}
		if(sc != null) { sc.close(); sc = null; }

		ArrayList <String> bRight = new ArrayList<>();
		ArrayList <String> bLeft = new ArrayList<>();
		bRight.add("-=o");
		bLeft.add("o=-");
		bulletSprite.add(bRight);
		bulletSprite.add(bLeft);

		initialized = true;
	}
	
	public static final String THE_ABOMINATION = "<portrait armour>\n     _-\"\"\"\"-_\n    / /####\\ \\\n    | ###### |\n    \\--____--/\n     \"\"-__-\"\"\n     /-____-\\\n  _-\"   __   \"-_\n /\"/-__//\\\\__-\\\"\\\n/   /  \\\\//  \\   \\\n|   |,  \"\"  ,|   |\n<endsprite>\n\n<portrait no_armor>\n     _-\"\"\"\"-_\n    /        \\\n    |  @  @  |\n     \\  __  /\n      \"-__-\"\n      /____\\\n  _-\"\"      \"\"-_\n /\" ,        , \"\\\n | /|        |\\ |\n \\ \\|        |/ /\n<endsprite>\n\n<player no_armor 0>\n\n            _---_\n           / , , \\\n           \\  -  /\n            \"-_-\"\n         _-\"/-__\\\"-_\n        / _       _ \\\n        | |\\     /| |\n        \\ \\|     |/ /\n         \\/\"-___-\"\\/\n         /    \\    \\\n         |\"-\"/ \\\"- |\n         \\  /   \\  /\n          / |   | \\\n          |\"|   |\"| \n          \"'    \"'\n<endsprite>\n\n<player no_armor 1>\n\n            _---_\n           / , , \\\n           \\  -  /\n            \"-_-\"\n         _-\"/-__\\\"-_\n        / _       _ \\\n        | |\\     /| |\n        \\ \\|     |/ /\n         \\/\"-___-\"\\/\n         /    \\    \\\n         |\"-\"/ \\\"- |\n         \\  /   \\  /\n          / |   | \\\n          |\"|   |\"| \n          \"'    \"'\n<endsprite>\n\n<player no_armor 2>\n\n            _---_\n           / , , \\\n           \\  -  /\n            \"-_-\"\n          _\"/-__\\\"-_\n        /\"_       _ \\\n       / / \\      /| |\n      |_/, |      |/ /\n     /\"_-\" /\"-___-\"\\/\n    | |'  /    \\    \\\n    | |'  |\"-\"/ \\\"- |\n  -_|-\"   \\  /   \\  /\n          / /    / |\n        <\"\" \\   <\"\"/\n         \"\"'     \"'\n<endsprite>\n\n<player no_armor 3>\n\n            _---_\n           / , , \\\n           \\  -  /\n            \"-_-\"\n          _\"/-__\\\"-_\n    _-\\ /\"_       _ \\\n|_-\" _-\"|/ \\      /| |\n \\_-\"  \"-, |      |/ /\n         \" /\"-___-\"\\/\n          /    \\    \\\n          |\"-\"/ \\\"- |\n          \\  /   \\  /\n          / /    / |\n        <\"\" \\   <\"\"/\n         \"\"'     \"'\n<endsprite>\n\n<player no_armor 4>\n\n            _---_\n           / , , \\\n           \\  -  /\n/_____      \"-_-\"\n\\_,_,_\\   _\"/-__\\\"-_\n    ;\\ \\ / _      _ \\\n      \\   /\\     /| |\n       \"-\" |     |/ /\n          /\"-___-\"\\/\n         /    \\    \\\n         |\"-\"/ \\\"- |\n         \\  /   \\  /\n         / /    / |\n       <\"\" \\   <\"\"/\n        \"\"'     \"'\n<endsprite>\n\n<player armor 0>\n\n            _----_\n           / #### \\\n           \\  \"\"  /\n            /\"--\"\\\n         _-\"--__--\"-_\n        / \"-__/\\__-\" \\\n       |  /   \\/    / |\n        \\ \\        / /\n         \\/\"-____-\"\\/\n         /    /\\    \\\n         |\"-\"/  \\\"-\"|\n         \\  /    \\  /\n         / /     | \\\n         |\"|     |\"| \n         '\"'     '\"'\n<endsprite>\n\n<player armor 1>\n\n            _----_\n           / #### \\\n           \\  \"\"  /\n            /\"--\"\\\n         _-\"--__--\"-_\n        / \"-__/\\__-\" \\\n       |  /   \\/    / |\n       | |\\        /  /\n     _/_/ /\"-____-\"\\ /\n    /\"_-\"/    /\\    \\\n    | |' |\"-\"/  \\\"-\"|\n    | |' \\  /    \\  /\n  -_|-\"  / /     / |\n       <\"\" \\    <\"\"/\n        \"\"'      \"'\n<endsprite>\n\n<player armor 2>\n\n            _----_\n           / #### \\\n           \\  \"\"  /\n            /\"--\"\\\n         _-\"--__--\"-_\n        / \"-__/\\__-\" \\\n       /  /   \\/    / |\n      / ,/\\        /  /\n    _|_-_ /\"-____-\"\\ /\n   /\"/\"-_/    /\\    \\\n  / /'   |\"-\"/  \\\"-\"|\n\\/_/'    \\  /    \\  /\n         / /     / |\n       <\"\" \\    <\"\"/\n        \"\"'      \"'\n<endsprite>\n\n<player armor 3>\n\n            _----_\n           / #### \\\n           \\  \"\"  /\n            /\"--\"\\\n         _-\"--__--\"-_\n    _-\\ / \"-__/\\__-\" \\\n|_-\" _-\"  /   \\/    / |\n \\_-\"  \"-\"\\        /  /\n          /\"-____-\"\\ /\n         /    /\\    \\\n         |\"-\"/  \\\"-\"|\n         \\  /    \\  /\n         / /     / |\n       <\"\" \\    <\"\"/\n        \"\"'      \"'\n<endsprite>\n\n<player armor 4>\n\n            _----_\n           / #### \\\n           \\  \"\"  /\n/_____      /\"--\"\\\n\\_,_,_\\  _-\"--__--\"-_\n    ;\\ \\/ \"-__/\\__-\" \\\n      \\   /   \\/    / |\n       \"-\"\\        /  /\n          /\"-____-\"\\ /\n         /    /\\    \\\n         |\"-\"/  \\\"-\"|\n         \\  /    \\  /\n         / /     / |\n       <\"\" \\    <\"\"/\n        \"\"'      \"'\n<endsprite>\n\n<zombie 0>\n      _-\"\"\"-_     _\n    ~/ o   o \\~  //\n     |   \"   |  //\n      \\  <  /  //\"_\n       |\"\"\"|   \"\"#/\n      /#####\\   l\n     /######|\\  /\n    /#|#####| \\/\n    /#/ \\###/\n    ||   \\#/\n     \"   | \\\n        /   \\\n      ,|     |,\n     ###     ###\n<endsprite>\n\n<zombie 1>\n      _-\"\"\"-_\n   '~/ o   o \\~'\n     |   \"   |   ________\n      \\  O  /    \\-\\=====\"o\n       |\"\"\"|     //\"\n      /#####\\    l\n     /######|\\  /\n    /#|#####| \\/\n    /#/ \\###/\n    ||   \\#/\n     \"   | \\\n        /   \\\n      ,|     |,\n     ###     ###\n<endsprite>\n\n<zombie 2>\n      _-\"\"\"-_\n   \"~/ O   O \\~\"\n     |   \"   |   ________\n      \\  O  /    \\-OOOOOO\"\n       |\"\"\"|     //\"\n      /#####\\    l\n     /######|\\  /\n    /#|#####| \\/\n    /#/ \\###/\n    ||   \\#/\n     \"   | \\\n        /   \\\n      ,|     |,\n     ###     ###\n<endsprite>\n\n<zombie 3>\n      _-\"\"\"-_\n   '~/ O   O \\~'    __-\"\\\n     |   \"   |   _-\"_-\"\"\n      \\  V  /    \\;/\n       |\"\"\"|     //\n      /#####\\    l\n     /######|\\  /\n    /#|#####| \\/\n    /#/ \\###/\n    ||   \\#/\n     \"   | \\\n        /   \\\n      ,|     |,\n     ###     ###\n<endsprite>\n\n<zombie 4>\n      _-\"\"\"-_     _\n    ~/ o   o \\~  //\n     |   \"   |  //\n      \\  V  /  //\"_\n       |\"\"\"|   \"\"#/\n      /#####\\   l\n     /######|\\  /\n    /#|#####| \\/\n    /#/ \\###/\n    ||   \\#/\n     \"   | \\\n        /   \\\n      ,|     |,\n     ###     ###\n<endsprite>\n\n<map 0>\n________________________________________\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|  |__|  |__|  |__|  |__|  |__|  |__|\n __    __    __    __    __    __    __|\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|  |__|  |__|  |__|  |__|  |__|  |__|\n|__    __    __    __    __    __    __|\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|  |__|  |__|  |__|  |__|  |__|  |__|\n|__    __    __    __    __    __    __|\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|  |__|  |__|  |__|  |__|  |__|  |__|\n|__    __    __    __    __    __    __|\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|  |__|  |__|  |__|  |__|  |__|  |__|\n|__    __    __    __    __    __    __|\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|  |__|  |__|  |__|  |__|  |__|  |__|\n|__    __    __    __    __    __    __\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|__|__|__|__|__|__|__|__|__|__|__|__|__|\n<endsprite>\n\n<map 1>\n___    _________________________________\n|  |  |  |  |   \\/   |  |  |  |   __   |\n|__|  |__|  |________|  |  |  |__|  |__|\n|__    __    __    __   |  |   __    __|\n|  |  |  |  /  \\  |  |  |  |  |  |  |  |\n|__|  |__|  \\__/  |__|  |__|  |__|  |__|\n|__    __    __    __    __    __    __|\n|  |  |  |  |  |  |__|  |  |  |  |  |  |\n|__|  |__|  |__|        |__|  |__|  |__|\n|__    __    __    __    __    __     /|\n|  |  |  |  |  |__|  |  |  |  |  |   / |\n|__|  |__|  |________|  |__|  |__|  |  |\n|__    __    __    __    __    __   |  |\n|  |  |  |__|  |  |  |  /  \\  |  |  |  |\n|__|  |________|  |__|  \\__/  |__|  |__|\n|__    __    __    __    __    __    __|\n|  |  |  |  |  |  |  |  |  |  |  |  |  |\n|  |  |__|  |__|  |__|  |__|  |__|  |__|\n|  |   __    __          __    __    __\n|  |  |  |__|  |   __   |  |  |  |  |  |\n|__|__|_________\\_|__|__|__|__|__|__|__|\n<endsprite>";
}
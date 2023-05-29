package animation;
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

		InputStream stream = null;
		Scanner sc = null;
		try	{ stream = new FileInputStream("Sprites.txt");	}
		catch(FileNotFoundException e) { System.out.println("File Not Found"); }
		if(stream != null) sc = new Scanner(stream);
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
}
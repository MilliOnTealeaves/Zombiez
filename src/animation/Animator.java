package animation;
import entities.*;
import utility.*;
import items.Location;
public class Animator
{
	private int animationSpeed;

	public static Assets a = new Assets();

	private final int top;
	private final int left;
	private final int mapVariant;
	private final int mapSize;
	private final Location[][] mapRef;
	
	public Animator(int top, int left, int mapV, Location[][] map)
	{
		setAnimationSpeed(AnimationSpeed.MID);;
		this.top = top;
		this.left = left;
		
		Utility.top = top;
		Utility.left = left;
		Utility.rows = 16;
		Utility.cols = 72;

		if(mapV <= a.map.numFrames) mapVariant = mapV;
		else mapVariant = 0;

		mapSize = map.length;
		mapRef = map;

		a.zombie.spriteRow = top + 1;
		a.zombie.spriteCol = left + 3;
		
		a.player.spriteRow = top;
		a.player.spriteCol = a.zombie.spriteCol + 40;
	}

	public void setAnimationSpeed(AnimationSpeed spd)
	{
		switch (spd)
		{
			case SLOW:
				animationSpeed = 450;
				break;
			case MID:
				animationSpeed = 180;
				break;
			case FAST:
				animationSpeed = 90;
				break;
		}
	}

	public void testAnimations()
	{
		Zombie z = new Zombie();
		Player p = new Player();
		
		drawHealthBar(z, top+16, left+6);
		drawHealthBar(p, top+16, left+50);

		zombieAttack();
		z.attack(p);
		drawHealthBar(z, top+16, left+6);
		drawHealthBar(p, top+16, left+50);

		Utility.wait(1000);

		a.setPlayerArmor(true);
		zombieAttack();
		Utility.wait(1000);

		p.takeDamage(59);

		drawHealthBar(z, top+16, left+6);
		drawHealthBar(p, top+16, left+50);

		a.setPlayerArmor(false);
		playerAttack();
		Utility.wait(1000);
		
		a.setPlayerArmor(true);
		playerAttack();
	}

	// Animates a player attack on a zombie
	public void playerAttack()
	{
		for(int i = 0; i < 5; i++)
		{
			Utility.clearConsole();
			a.player.drawSprite(i);
			a.zombie.drawSprite(0);

			Utility.wait(animationSpeed/2);
		}

		for(int i = 0; i < 5; i++)
		{
			Utility.clearConsole();
			if(i > 1) a.player.drawSprite(5-i);
			else a.player.drawSprite(4);
			a.zombie.drawSprite(0);
			a.bullet.drawSprite(1, top+5, a.player.spriteCol-2-i*6);

			Utility.wait(animationSpeed/3*2);
		}

		Utility.clearConsole();
		a.zombie.spriteRow -= 1;
		a.zombie.drawSprite(0);
		a.player.drawSprite(0);
		Utility.wait(animationSpeed/3*2);
		
		Utility.clearConsole();
		a.zombie.spriteRow += 1;
		a.zombie.drawSprite(0);
		a.player.drawSprite(0);
	}

	// Animates a player attack on a zombie
	public void zombieAttack()
	{
		for(int i = 0; i < 5; i++)
		{
			Utility.clearConsole();
			a.player.drawSprite(0);
			a.zombie.drawSprite(i);
			if(i > 1) a.bullet.drawSprite(0, top+4, left+i*7+20);
			Utility.wait(animationSpeed);
		}
		Utility.clearConsole();
		a.player.spriteCol += 3;
		a.player.drawSprite(0);
		a.zombie.drawSprite(0);
		Utility.wait(animationSpeed/3*2);

		Utility.clearConsole();
		a.player.spriteCol -= 3;
		a.zombie.drawSprite(0);
		a.player.drawSprite(0);
	}

	public void drawHealthBar(Entity e)
	{
		if(e instanceof Player)
			drawHealthBar(e, top+16, left+50);
		else
			drawHealthBar(e, top + 16, left+6);
	}

	private void drawHealthBar(Entity e, int row, int col)
	{
		Utility.clearConsole(2, 24, row, col);

		int boxWidth = 11;
		int barPercent = e.getHealthPercent();

		char fillChar = '#';
		String health = "";
		if(e instanceof Player p && p.armor)
		{
			fillChar = '/';
			health += "{" + p.getHealth() + "}  " + Utility.getColorCode(Style.BOLD, Style.TEXT, Style.CYAN)+ p.getArmor() + Utility.RESET;
		}
		else health += e.getHealth();

		String hpBar = "[";
		for(int i = 0; i < barPercent/10-2; i++)
		{
			hpBar += fillChar;
		}

		if(barPercent > 10) hpBar += "]";
		else if(barPercent == 0) hpBar = "";

		Utility.drawBox(1, boxWidth, row, col);
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.GREEN);
		Utility.writePos(hpBar, row+1, col+1);
		Utility.clearTextStyle();
		Utility.clearConsole(1, 4, row+1, col+boxWidth+3);
		Utility.writePos(health, row+1, col+boxWidth+3);

		Utility.writePos(e.getName(), row+2, col);
	}

	public void drawMap()
	{
		a.map.spriteRow = top + 1;
		a.map.spriteCol = left + 4;
		a.map.drawSprite(mapVariant);

		for(int i = 0; i < mapSize; i++)
		{
			for(int j = 0; j < mapRef[0].length; j++)
			{
				String print = mapRef[i][j].toString();
				if(j < mapRef[0].length - 1) print += "|";
				Utility.writePos( print, top+i+3, left+j*3+6);
			}
		}
	}
}

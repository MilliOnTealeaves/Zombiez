package animation;
import entities.*;
import utility.*;
import items.*;
import java.util.HashMap;

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
		Utility.cols = 79;

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

	public void drawMap(int pRow, int pCol)
	{
		Utility.clearConsole(20, 74, top-1, left);
		a.map.spriteRow = top - 1;
		a.map.spriteCol = left + 3;
		a.map.drawSprite(mapVariant);

		for(int i = 0; i < mapSize; i++)
		{
			for(int j = 0; j < mapRef[0].length; j++)
			{
				String print = mapRef[i][j].toString();
				
				Utility.writePos( print, top+2+i*3, left+j*6+7);
			}
		}
		Utility.writePos( "<>", top+2+pRow*3, left+pCol*6+7);
	}

	public void drawInventory(Player p)
	{
		Utility.clearConsole(16, 30, top, left+45);
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.WHITE);
		Utility.writePos("Inventory" + Utility.RESET, top, left+45);
		int iter = 0;
		for(Item i : p.getInventory())
		{
			Utility.writePos(String.format("%d: %s", iter+1, i.toString()), top+1+iter, left+45);
			iter++;
		}
		int iter2 = 0;
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.WHITE);
		Utility.writePos("Upgrades" + Utility.RESET, top+iter+2, left+45);
		for(Upgrade u : p.getUpgrades())
		{
			Utility.writePos(String.format("%d: %s", iter2+1, u.toString()), top+3+iter, left+45);
			iter++;
			iter2++;
		}
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.WHITE);

		Utility.writePos("Stats" + Utility.RESET, top+15, left+45);
		HashMap<String, Integer> s = p.getStats();
		int iter3 = 0;
		for (String a : s.keySet())
		{
			Utility.writePos(String.format("%s: %d", a, s.get(a)), top+16+iter3, left+45);
			if(iter3 == 3) System.out.print("%");
			iter3++;
		}
		int portraitFrame = 0;
		if(!p.armor) portraitFrame = 1;
		a.portrait.drawSprite(portraitFrame, top+14, left+60);
	}
}

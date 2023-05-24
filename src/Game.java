import utility.Utility;

public class Game
{
	public static final int SLOW = 450;
	public static final int MID = 180;
	public static final int FAST = 90;
	
	private static int animationSpeed = MID;

	private static Assets a = new Assets();

	private final int top;
	private final int left;

	public Game(int top, int left)
	{
		this.top = top;
		this.left = left;
		
		Utility.top = top;
		Utility.left = left;
		Utility.rows = 16;
		Utility.cols = 72;

		a.zombie.spriteRow = top + 1;
		a.zombie.spriteCol = left + 3;
		
		a.player.spriteRow = top;
		a.player.spriteCol = a.zombie.spriteCol + 40;
	}

	public void setAnimationSpeed(int speed)
	{
		if(speed > 0 && speed < 2000) animationSpeed = speed;
	}

	public void testAnimations()
	{
		Zombie z = new Zombie();
		Player p = new Player();
		p.addArmor(12);
		drawHealthBar(z, top+16, left+7);
		drawHealthBar(p, top+16, left+51);

		zombieAttack();
		Utility.wait(1000);

		a.setPlayerArmor(true);
		zombieAttack();
		Utility.wait(1000);

		z.takeDamage(13);
		p.takeDamage(59);

		drawHealthBar(z, top+16, left+7);
		drawHealthBar(p, top+16, left+51);

		a.setPlayerArmor(false);
		playerAttack();
		Utility.wait(1000);
		
		a.setPlayerArmor(true);
		playerAttack();
	}

	// Animates a player attack on a zombie
	private void playerAttack()
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
	private void zombieAttack()
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

	private void drawHealthBar(Entity e, int row, int col)
	{
		int boxWidth = 11;
		int barPercent = e.getHealthPercent();
		char fillChar = '#';
		if(e instanceof Player p)
		{
			if(p.armor)
			{
				fillChar = '/';
			}
		}
		String hpBar = "[";
		for(int i = 0; i < barPercent/10-2; i++)
		{
			hpBar += fillChar;
		}
		hpBar += "]";
		Utility.drawBox(1, boxWidth, row, col);
		Utility.writePos(hpBar, row+1, col+1);
		Utility.clearConsole(1, 4, row+1, col+boxWidth+3);
		Utility.writePos("" + e.getHealth(), row+1, col+boxWidth+3);
	}
}
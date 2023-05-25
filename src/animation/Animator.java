package animation;
import entities.*;
import utility.*;
public class Animator
{
	private int animationSpeed;

	public static Assets a = new Assets();

	private final int top;
	private final int left;
	
	public Animator(int top, int left)
	{
		setAnimationSpeed(AnimationSpeed.MID);;
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
			health += "{" + p.getHealth() + "}  " + p.getArmor();
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
		Utility.writePos(hpBar, row+1, col+1);
		Utility.clearConsole(1, 4, row+1, col+boxWidth+3);
		Utility.writePos(health, row+1, col+boxWidth+3);

		Utility.writePos("" +e.getName(), row+2, col +2);
	}
}

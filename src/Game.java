import animation.Animator;
import entities.*;
import utility.*;

public class Game
{
	private Location[][] map;
	private Animator anim;
	private Player player;
	

	public Game(int top, int left)
	{
		map = new Location[10][10];
		anim = new Animator(top+2, left);
		player = new Player();
	}

	private void drawMap()
	{
		
	}

	public void test()
	{
		// anim.testAnimations();
		battle(Zombie.getRandomZombie());
	}

	public void battle(Entity enemy)
	{
		int playerTicks = 0;
		int zombieTicks = 0;
		
		anim.drawHealthBar(enemy);
		anim.drawHealthBar(player);

		while(player.alive && enemy.alive)
		{
			//player's turn
			if(playerTicks > 100000)
			{
				anim.playerAttack();
				player.attack(enemy);
				anim.drawHealthBar(enemy);
				playerTicks = 0;
			}
			// zombie's turn
			if(zombieTicks > 100000)
			{
				anim.zombieAttack();
				enemy.attack(player);
				anim.drawHealthBar(player);
				zombieTicks = 0;
			}
			zombieTicks += enemy.speed;
			playerTicks += player.speed;
		}
		anim.drawHealthBar(enemy);
		anim.drawHealthBar(player);
	}
}
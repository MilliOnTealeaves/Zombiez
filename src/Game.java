import animation.Animator;
import entities.*;
import items.*;
import utility.*;
import java.util.Scanner;

public class Game
{
	private Animator anim;
	private Player player;
	private Scanner sc;
	
	private Location[][] map;
	private final int mapVariant;

	public Game(int top, int left)
	{
		map = new Location[6][6];
		for(int i = 0; i < map.length; i++) 
			for(int j = 0; j < map[0].length; j++)
				if(Math.random() > 0.6)
					map[i][j] = new EncPoint();
				else
					map[i][j] = new SupplyPoint();

		mapVariant = (int)Math.random()*2;
		anim = new Animator(top+2, left, mapVariant, map);
		player = new Player();
		sc = new Scanner(System.in);
	}

	public void test()
	{
		// drawMap();
		sc.nextLine();
		battle(Zombie.getRandomZombie());
	}

	private void battle(Entity enemy)
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
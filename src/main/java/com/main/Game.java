package com.main;
import com.animation.Animator;
import com.entities.*;
import com.items.*;
import com.utility.*;
import java.util.Scanner;

public class Game
{
	private Animator anim;
	private Player player;
	private Scanner sc;
	
	private Location[][] map;
	private final int mapVariant;
	private final int top;
	private final int left;

	private int playerRow;
	private int playerCol;

	private String message;

	private boolean win = false;

	public Game(int top, int left)
	{
		map = new Location[6][6];
		for(int i = 0; i < map.length; i++) 
			for(int j = 0; j < map[0].length; j++)
				if(Math.random() > 0.5)
					map[i][j] = new EncPoint();
				else
					map[i][j] = new SupplyPoint();

		map[0][0].clear();

		playerRow = 0;
		playerCol = 0;
		this.top = top;
		this.left = left;
		
		message = "";

		mapVariant = (int)(Math.random()*2);
		anim = new Animator(top+2, left, mapVariant, map);
		player = new Player();
		sc = new Scanner(System.in);
	}

	public boolean run()
	{
		int oldRow; int oldCol;
		while(true)
		{
			oldRow = playerRow;
			oldCol = playerCol;
			message = "What do you want to do?";
			anim.drawMap(playerRow, playerCol);
			anim.drawInventory(player);
			boolean move = false;
			while (!move) move = processInput();
			if(win) return true;
			if(!map[playerRow][playerCol].isCleared())
			{
				if(map[playerRow][playerCol] instanceof SupplyPoint sp)
				{
					map[playerRow][playerCol].clear();
					player.addToInventory(sp.getItem());
				}
				if(map[playerRow][playerCol] instanceof EncPoint ep)
				{
					anim.drawMap(playerRow, playerCol);
					anim.drawInventory(player);
					message = Utility.getColorCode(Style.UNDERLINE, Style.BACKGROUND, Style.RED) + "Encountered " + ep.enemy.getName() + ". Fight? [Y/N]" + Utility.RESET;
					String a = getInput();
					if (a.toLowerCase().equals("y"))
					{
						battle(ep.enemy);
						if(!player.alive) return false;
						map[playerRow][playerCol].clear();
					}
					else
					{
						playerRow = oldRow;
						playerCol = oldCol;
					}
				}
			}
		}
	}

	private void battle(Entity enemy)
	{
		Utility.clearConsole(26, 72, top, left);
		Utility.clearConsole(6, 8, top+20, left+70);
		Utility.writePos("BATTLE!", 26, 9);

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
			else if(zombieTicks > 100000)
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
		if(player.alive) player.heal(35);
		double rand = Math.random();
		Item i = Item.getRandomItem();
		switch (enemy.getName())
		{
			default: break;
			case "Buff Tyler":
				player.addToInventory(i); break;
			case "Don Machiavelli":
				if(rand > 0.4) player.addToInventory(i); break;
			case "Speedy Zombzalez":
				if(rand > 0.7) player.addToInventory(i); break;
		}
		message = "Press ENTER to proceed";
		getInput();
	}
	
	private boolean processInput()
	{
		String a = getInput();
		if(a.startsWith("go"))
		{
			switch(a.substring(3))
			{
				case "east":  case "e":
					if(playerCol < 5) { playerCol ++; return true; }
					else if(playerCol == 5 && playerRow == 5) { win = true; return true; }
					else { message = "Invalid direction"; return false; }

				case "west":  case "w":
					if(playerCol > 0) { playerCol --; return true; }
					else { message = "Invalid direction"; return false; }

				case "south": case "s":
					if(playerRow < 5) { playerRow ++; return true; }
					else { message = "Invalid direction"; return false; }

				case "north": case "n": 
					if(playerRow > 0) { playerRow --; return true; }
					else { message = "Invalid direction"; return false; }
				
				default:
					message = "Invalid direction";
			}
			return false;
		}
		else if(a.startsWith("use"))
		{
			int index = -1	;
			try
			{
				index = Integer.parseInt(a.substring(4)) - 1;
			}
			catch(NumberFormatException n)
			{
				message = a.substring(4) + " is not a valid item number";
				return false;
			}
			if(index < player.getInventory().size() && index >= 0)
			{
				player.useItem(index);
				anim.drawInventory(player);
			}
			else message = a.substring(4) + " is not a valid item number";
			return false;
		}
		else if(a.startsWith("remove"))
		{
			int index = -1;
			try
			{
				index = Integer.parseInt(a.substring(7)) - 1;
				if (index < 3)
				{
					player.removeUpgrade(index);
					anim.drawInventory(player);
				}
				else message = a.substring(7) + " is not a valid item number";
			}
			catch(NumberFormatException n)
			{
				message = a.substring(7) + " is not a valid item number";
				return false;
			}
		}
		else
		{
			message = "Please try again";
		}
		return false;
	}

	private String getInput()
	{
		Utility.clearConsole(3, 45, 26, 9);
		Utility.writePos(message, 26, 9);
		Utility.writePos("> ", 28, 9);
		return sc.nextLine().toLowerCase();
	}
}
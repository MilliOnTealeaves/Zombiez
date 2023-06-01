package com.main;
import com.utility.Style;
import com.utility.Utility;

public class Main
{
	// game window
	private static int top = 2;
	private static int left = 4;
	private static int width = 80;
	private static int height = 27;

	
	public static void main(String[] args)
	{
		Game g;
		while(true)
		{
			Utility.clearConsole(50, 500, 0, 0);
			Utility.writePos("Please adjust your console window until all numbers up to 26", 0, 0);
			Utility.drawBox(height, width, top, left);
			for(int i = 0; i <= 25; i++)
			{
				Utility.writePos("" + i, 1 + top + i, 5);
			}
			Utility.writePos(Utility.getColorCode(Style.UNDERLINE, Style.TEXT, Style.WHITE) + "26" + Utility.RESET, top+27, 5);
			Utility.setWritePos(31, 0);
			String answer = System.console().readLine("Done? [Y/N]: ");
			if(answer.toLowerCase().equals("y"))
			{
				Utility.drawBox(height, width, top, left);
				break;
			}
		}
		Utility.clearConsole(50, 500, 0, 0);
		Utility.drawBox(height, width, top, left);

		Utility.setWritePos(top+2, left+3);
		System.out.print("It is the zombie apocalypse. The city is overrun by zombies.");

		Utility.setWritePos(top+3, left+3);
		System.out.print("Your goal is to ");
		Utility.selectTextStyle(Style.ITALIC, Style.INTENSE_BACKGROUND, Style.RED);
		System.out.print("get out of the city." + Utility.RESET);

		Utility.setWritePos(top+5, left+3);
		System.out.print("To move, type ");
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.BLUE);
		System.out.print("go north, go south, go east, go west" + Utility.RESET);

		Utility.setWritePos(top+7, left+3);
		System.out.print("To use an item, type ");
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.BLUE);
		System.out.print("use [item number]" + Utility.RESET);

		Utility.writePos("Your weapon can have 4 upgrade items attached. ", top+9, left+3);
		Utility.setWritePos(top+10, left+3);
		System.out.print("To remove an upgrade, type ");
		Utility.selectTextStyle(Style.UNDERLINE, Style.TEXT, Style.BLUE);
		System.out.print("remove [upgrade number]" + Utility.RESET);

		Utility.writePos("Start the game? " + Utility.RESET, top+13, left+3);
		String play = System.console().readLine("[Y/N]: ");

		if(play.toLowerCase().equals("y"))
		while(true)
		{
			g = new Game(top + 1, left + 1);
			Utility.clearConsole(50, 500, 0, 0);
			Utility.drawBox(height, width, top, left);

			if( g.run()) win(); else lose();
			
			Utility.writePos("Do you want to play again?", top+15, left+20);
			String again = System.console().readLine("[Y/N]: ");
			
			if(!again.toLowerCase().equals("y"))
			{
				Utility.setWritePos(40, left);
				break;
			}
		}
	}

	private static void win()
	{
		Utility.clearConsole(height-1, width-2, top+1, left+1);
		Utility.selectTextStyle(Style.UNDERLINE, Style.INTENSE, Style.GREEN);
		Utility.writePos("You won!" + Utility.RESET, top+13, left+20);
	}
	private static void lose()
	{
		Utility.clearConsole(height-1, width-2, top+1, left+1);
		Utility.selectTextStyle(Style.UNDERLINE, Style.INTENSE, Style.RED);
		Utility.writePos("You lost :(" + Utility.RESET, top+13, left+20);
	}
}

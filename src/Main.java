// import animation.*;
import utility.Style;
import utility.Utility;

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
			Utility.writePos("Please adjust your console window until you can see the box fully", 0, 0);
			Utility.drawBox(height, width, top, left);
			Utility.setWritePos(31, 0);
			String answer = System.console().readLine("Done? [Y/N]: ");
			if(answer.toLowerCase().equals("y")) break;
		}
		Utility.clearConsole(height-2, width-2, top+1, left+1);
		Utility.selectTextStyle(Style.UNDERLINE, Style.INTENSE, Style.WHITE);
		Utility.writePos("Start the game?" + Utility.RESET, top+13, left+20);
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
		Utility.clearConsole(height-2, width-2, top+1, left+1);
		Utility.selectTextStyle(Style.UNDERLINE, Style.INTENSE, Style.GREEN);
		Utility.writePos("You won!" + Utility.RESET, top+13, left+20);
	}
	private static void lose()
	{
		Utility.clearConsole(height-2, width-2, top+1, left+1);
		Utility.selectTextStyle(Style.UNDERLINE, Style.INTENSE, Style.RED);
		Utility.writePos("You lost :(" + Utility.RESET, top+13, left+20);
	}
}

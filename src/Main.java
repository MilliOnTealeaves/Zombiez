// import animation.*;
import utility.Utility;

public class Main
{
	// game window
	private static int top = 2;
	private static int left = 4;
	private static int width = 80;
	private static int height = 25;

	
	public static void main(String[] args)
	{
		Utility.clearConsole(50, 500, 0, 0);
		Utility.writePos("Please adjust your console window until you can see the box fully", 0, 0);
		Utility.drawBox(height, width, top, left);
		Game g = new Game(top + 1, left + 1);
		// g.drawMap();

		//  Utility.wait(2000);
		g.test();
		
		Utility.setWritePos(40, left);
	}
}

import utility.Utility;

public class Main
{
	// POSITIONS
	private static int top = 3;
	private static int left = 5;
	
	public static void main(String[] args)
	{
		Utility.clearConsole(50, 500, 0, 0);
		Utility.writePos("Please adjust your console window until you can see the box fully", 0, 0);
		Utility.drawBox(20, 76, top-1, left-2);
		Game g = new Game(top, left);
		g.testAnimations();
		g.setAnimationSpeed(Game.FAST);
		g.testAnimations();
		Utility.setWritePos(40, left);
	}
}

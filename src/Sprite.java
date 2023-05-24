import java.util.*;

public class Sprite
{
 	private ArrayList<ArrayList<String>> frames;
	public int spriteRow;
	public int spriteCol;
	 
	public int numFrames;

	// Creates an empty sprite
	public Sprite ()
	{
		frames = new ArrayList<>();
		numFrames = 0;
		spriteRow = 0;
		spriteCol = 0;
	}

	// Adds a frame as an ArrayList of Strings to this sprite
	public void add(ArrayList<String> spriteFrame)
	{
		frames.add(new ArrayList<>(spriteFrame));
		numFrames = frames.size();
	}

	// Draws a sprite given a specified frame at the console location
	//   specified with row, col.
	public void drawSprite(int frame, int row, int col)
	{
		if(frame >= frames.size()) return;
		
		for(String s : frames.get(frame))
		{
			Utility.writePos(s, row++, col);
		}
	}

	public void drawSprite(int frame)
	{
		if(frame >= frames.size()) return;
		int row = spriteRow;
		int col = spriteCol;
		for(String s : frames.get(frame))
		{
			Utility.writePos(s, row++, col);
		}
	}

	// Returns a sprite frame as an ArrayList of Strings, where each string
	//   is one line in the sprite.
	public static ArrayList<String> scanSprite(Scanner sc)
	{
		String line = sc.nextLine();
		ArrayList<String> tempList = new ArrayList<>();
		while(!line.equals("<endsprite>"))
		{
			tempList.add(line);
			line = sc.nextLine();
		}
		return tempList;
	}

	// returns a new Sprite object, cloned from the current sprite
	public Sprite clone()
	{
		Sprite clone = new Sprite();
		clone.frames = this.frames;
		clone.numFrames = this.numFrames;
		clone.spriteCol = this.spriteCol;
		clone.spriteRow = this.spriteRow;
		return clone;
	}

	public void destroyRandLine(int frame)
	{
		int s = frames.get(frame).size();
		frames.get(frame).remove((int)(Math.random()*s));
	}
}

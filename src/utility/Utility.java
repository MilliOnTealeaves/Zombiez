package utility;
public class Utility
{
	public static final String RESET = "\033[0m";

	public static void writePos(String contents, int row, int col)
	{
		setWritePos(row, col);
		System.out.print(contents);
	}

	public static void setWritePos(int row, int col)
	{
		System.out.printf("%c[%d;%df", 0x1B, row, col);
	}

	public static void clearConsole(int rows, int cols, int top, int left)
	{
		String empy = "";
		for(int i = 0; i < cols; i++) empy += " ";
		for(int i = top; i < top+rows; i++)
		{
			writePos(empy, i, left);
		}
	}

	public static String getColorCode(int style, int type, int color)
	{
		return String.format("\033[%d;%d%dm", style, type, color);
	}

	public static void selectTextStyle(int style, int type, int color)
	{
		System.out.print(getColorCode(style, type, color));
	}

	public static void clearTextStyle()
	{
		System.out.print(RESET);
	}

	public static int rows;
	public static int cols;
	public static int top;
	public static int left;

	public static void clearConsole()
	{
		clearTextStyle();
		clearConsole(rows, cols, top, left);
	}

	public static void wait(int time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException z) { }
	}

	public static void drawBox(int height, int width, int top, int left)
	{
		String line = "";
		for(int i = 0; i <= width; i++) line += "_";
		writePos(line, top, left);
		writePos(line.substring(2), top+height, left+1);

		for(int i = top + 1; i <= top + height; i++)
		{
			writePos("|", i, left);
			writePos("|", i, left+width);
		}
	}

	public static int varianceInt(int base, double percentVariance)
	{
		return (int)varianceDbl(base, percentVariance);
	}

	public static double varianceDbl(double base, double percentVariance)
	{
		double variance = base * (percentVariance);
		return base + (Math.random() * variance) - (variance/2);
	}
}

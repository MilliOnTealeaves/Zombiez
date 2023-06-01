package items;
import utility.*;

public abstract class Location
{
	protected boolean cleared;

	protected Location()
	{
		cleared = false;
	}

	public boolean isCleared()
	{
		return cleared;
	}

	public void clear()
	{
		if(!cleared)cleared = true;
	}

	@Override
	public String toString() {
		if(cleared) return "  ";
		else
		{
			return Utility.getColorCode(Style.BOLD, Style.INTENSE, Style.GREEN) + "[]" + Utility.RESET;
		}
	}
}

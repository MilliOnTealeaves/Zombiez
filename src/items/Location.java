package items;
import utility.*;

public abstract class Location
{
	public boolean cleared;

	protected Location()
	{
		cleared = false;
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

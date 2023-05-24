package items;
import utility.Utility;

public class CritUpgrade extends Item
{
	public CritUpgrade()
	{
		super(0, 0, Utility.varianceDbl(0.1, 0.3), 0);
	}
}
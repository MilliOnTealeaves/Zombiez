package items;
import utility.Utility;

public class AtkUpgrade extends Item
{
	public AtkUpgrade()
	{
		super(0, Utility.varianceInt(15, 0.3), 0, 0);
	}
}
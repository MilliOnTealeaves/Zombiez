package items;
import utility.Utility;

public class HealthPack extends Item
{
	public HealthPack ()
	{
		super(Utility.varianceInt(15, 0.8), 0, 0, 0);
	}
}

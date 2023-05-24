package items;
import utility.Utility;

public class Armor extends Item
{
	public Armor()
	{
		super(0, 0, 0, Utility.varianceInt(20, 0.5));
	}
}
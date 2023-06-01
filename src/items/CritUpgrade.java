package items;
import utility.Utility;

public class CritUpgrade extends Item
{
	public CritUpgrade()
	{
		super(0, 0, Utility.varianceDbl(0.1, 0.3), 0);
	}
	@Override
	public String toString()
	{
		String a = "" + critBoost;
		return "Crit Rate Upgrade: " + a.substring(2, 4) + "%";
	}
}
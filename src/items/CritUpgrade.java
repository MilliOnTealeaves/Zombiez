package items;
import utility.Utility;

public class CritUpgrade extends Item
{
	public CritUpgrade()
	{
		super(0, 0, Utility.varianceDbl(0.1, 0.3), 0);
	}
	public CritUpgrade(Upgrade u)
	{
		super(u.healing, u.atkBoost, u.critBoost, u.armorPoints);
	}

	@Override
	public String toString()
	{
		return "Crit Rate Upgrade: " + (int)(critBoost*100) + "%";
	}
}
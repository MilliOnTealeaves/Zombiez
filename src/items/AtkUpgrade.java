package items;
import utility.Utility;

public class AtkUpgrade extends Item
{
	public AtkUpgrade()
	{
		super(0, Utility.varianceInt(15, 0.3), 0, 0);
	}
	public AtkUpgrade(Upgrade u)
	{
		super(u.healing, u.atkBoost, u.critBoost, u.armorPoints);
	}

	@Override
	public String toString()
	{
		return "Attack Upgrade: " + atkBoost;
	}
}
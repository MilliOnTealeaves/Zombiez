import utility.Utility;

public class HealthPack implements Item
{
	private int healAmount;

	public HealthPack ()
	{
		healAmount = 10 + (int)Math.random() * 3;
		healAmount = Utility.varianceInt(15, 0.8);
	}

	public void use(Player e)
	{
		e.heal(healAmount);
	}
}


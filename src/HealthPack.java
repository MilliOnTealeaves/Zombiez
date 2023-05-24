public class HealthPack implements Item
{
	private int healAmount;

	public HealthPack ()
	{
		healAmount = 10 + (int)Math.random() * 3;
	}

	public void use(Player e)
	{
		e.heal(healAmount);
	}
}


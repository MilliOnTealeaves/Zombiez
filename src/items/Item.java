package items;
public abstract class Item
{
	public final int healing;
	public final int atkBoost;
	public final double critBoost;
	public final int armorPoints;

	protected Item(int heal, int atk, double crit, int armor)
	{
		healing = heal;
		atkBoost = atk;
		critBoost = crit;
		armorPoints = armor;
	}

	public static Item getRandomItem()
	{
		int itemType = (int)(Math.random()*6);
		switch (itemType)
		{
			case 0: 
			case 1: 
				return new HealthPack();
			case 2:
			case 3:
				return new Armor();
			case 4:
				return new CritUpgrade();
			case 5:
				return new AtkUpgrade();
			
			default: return new HealthPack();
		}
	}
}
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
}
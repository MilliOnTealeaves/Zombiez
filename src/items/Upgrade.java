package items;
public abstract class Upgrade extends Item
{
	protected Upgrade(int heal, int atk, double crit, int armor)
	{
		super(heal, atk, crit, armor);
	}
}
package com.items;
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
		int itemType = (int)(Math.random()*4);
		switch (itemType)
		{
			case 0: 
				return new HealthPack();
			case 1:
				return new Armor();
			case 2:
				return new CritUpgrade();
			case 3:
				return new AtkUpgrade();
			
			default: return new HealthPack();
		}
	}
}
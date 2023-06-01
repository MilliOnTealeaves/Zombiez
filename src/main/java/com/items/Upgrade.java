package com.items;
public class Upgrade extends Item
{
	public Upgrade(int heal, int atk, double crit, int armor)
	{
		super(heal, atk, crit, armor);
	}
	public Upgrade(CritUpgrade c)
	{
		super(0, 0, c.critBoost, 0);
	}

	public Upgrade(AtkUpgrade c)
	{
		super(0, c.atkBoost, 0, 0);
	}

	public String toString()
	{
		if(critBoost>0) return "+" + (int)(critBoost*100) + "% crit rate";
		else return "+" + atkBoost + " attack";
	}
}
package com.items;
import com.utility.Utility;

public class Armor extends Item
{
	public Armor()
	{
		super(0, 0, 0, Utility.varianceInt(30, 0.5));
	}

	@Override
	public String toString()
	{
		return "Armor: " + armorPoints;
	}
}
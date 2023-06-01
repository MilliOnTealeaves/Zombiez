package com.items;
import com.utility.Utility;

public class HealthPack extends Item
{
	public HealthPack ()
	{
		super(Utility.varianceInt(35, 0.8), 0, 0, 0);
	}

	@Override
	public String toString()
	{
		return "Health Pack: " + healing;
	}
}

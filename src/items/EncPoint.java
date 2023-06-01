package items;

import entities.*;

public class EncPoint extends Location
{
	public final Zombie enemy;

	public EncPoint()
	{
		super();
		enemy = Zombie.getRandomZombie();
	}
}
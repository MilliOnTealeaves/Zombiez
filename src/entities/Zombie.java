package entities;
import utility.Utility;

public class Zombie extends Entity
{
	public Zombie ()
	{
		this(150, 50, 0.2, 100);
		name = "Zombie";
	}
	
	protected Zombie(int hp, int atk, double cr, int spd)
	{
		super(
			// health
			Utility.varianceInt(hp, 0.1) ,
			// attack
			Utility.varianceInt(atk, 0.2) ,
			// crit rate
			Utility.varianceDbl(cr, 0.2) ,
			// speed
			Utility.varianceInt(spd, 0.5)
		);
	}

	public static Zombie getRandomZombie()
	{
		int zombieType = (int)(Math.random()*11);
		switch (zombieType)
		{
			case 1:
			case 2:
				return new SadLump();
			
			case 3: 
			case 4:
				return new KillerZombie();
			
			case 5:
			case 6:
				return new SpeedyZombzales();
			
			case 7:
			case 8:
				return new BigGuy();

			case 9: 
				return new DonMachiavelli();
			
			case 10:
				return new BuffTyler();
			
			default: return new Zombie();
		}
	}
}
class SadLump extends Zombie
{
	protected SadLump() 
	{
		super(60, 70, 0.4, 50);
		name = "Sad Lump";
	}
}
class KillerZombie extends Zombie
{
	protected KillerZombie()
	{
		super(100, 80, 0.3, 125);
		name = "Killer Zombie";
	}
}
class SpeedyZombzales extends Zombie
{
	protected SpeedyZombzales()
	{
		super(60, 40, 0.4, 200);
		name = "Speedy Zombzales";
	}
}
class BigGuy extends Zombie
{
	protected BigGuy()
	{
		super(350, 30, 0.15, 60);
		name = "Big Guy";
	}
}
class DonMachiavelli extends Zombie
{
	protected DonMachiavelli()
	{
		super(300, 50, 0.2, 120);
		name = "Don Machiavelli";
	}
}
class BuffTyler extends Zombie
{
	protected BuffTyler()
	{
		super(450, 60, 0.3, 80);
		name = "Buff Tyler";
	}
}
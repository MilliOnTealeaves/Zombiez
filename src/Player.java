import java.util.ArrayList;

public class Player extends Entity
{
	public boolean armor;
	private int armorPoints;
	public ArrayList<Item> inventory;

	public Player ()
	{
		super(250, 50, 0.25, 100);
		armor = false;
		inventory = new ArrayList<>();
	}

	public void addArmor(int amount)
	{
		armor = true;
		armorPoints += amount;
	}

	public int decrementArmor(int amount)
	{
		if(armorPoints >= amount)
		{
			armorPoints -= amount;
			if(armorPoints == 0) armor = false;
			return 0;
		}
		else
		{
			int overkill = amount - armorPoints;
			armorPoints = 0;
			return overkill;
		}	
	}

	public int getArmor()
	{
		return armorPoints;
	}

	public void takeDamage(int damage)
	{
		// if armored, subtract from armor. if there is still damage undealt, deal it.
		// if unarmored, deal the damage.
		if(armor)
		{
			damage = decrementArmor(damage);
		}
		if (damage != 0)
		{
			if(health - damage > 0)
			{
				health -= damage;
			}
			else
			{
				health = 0;
				alive = false;
			}
		}
	}
}
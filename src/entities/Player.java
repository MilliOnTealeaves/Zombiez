package entities;
import java.util.ArrayList;

import items.*;

public class Player extends Entity
{
	public boolean armor;
	private int armorPoints;
	private ArrayList<Item> inventory;
	private ArrayList<Upgrade> upgrades;

	public Player ()
	{
		super(250, 50, 0.25, 100);
		armor = false;
		inventory = new ArrayList<Item>();
		upgrades = new ArrayList<Upgrade>();
	}

	public void addArmor(int amount)
	{
		armor = true;
		armorPoints += amount;
	}

	private int hitArmor(int amount)
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
			damage = hitArmor(damage);
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

	public boolean useItem(int index)
	{
		if(index >= inventory.size()) return false;
		
		if(inventory.get(index) instanceof Upgrade u && upgrades.size()<4)
		{
			critRate += u.critBoost;
			attack += u.atkBoost;
			upgrades.add(u);
			inventory.remove(index);
			return true;
		}
		if(inventory.get(index) instanceof Armor a)
		{
			addArmor(a.armorPoints);
			inventory.remove(index);
		}
		if(inventory.get(index) instanceof HealthPack h)
		{
			heal(h.healing);
			inventory.remove(index);
		}
		return false;
	}

	public boolean removeUpgrade(int index)
	{
		if(index >= upgrades.size()) return false;
		
		inventory.add(upgrades.remove(index));
		return true;
	}

	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
}
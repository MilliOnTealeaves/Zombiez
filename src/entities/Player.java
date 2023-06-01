package entities;

import java.util.*;

import animation.*;
import items.*;

public class Player extends Entity
{
	public boolean armor;
	private int armorPoints;
	private ArrayList<Item> inventory;
	private ArrayList<Upgrade> upgrades;
	private Assets ass;

	public Player ()
	{
		super(250, 50, 0.25, 100);
		armor = false;
		inventory = new ArrayList<Item>();
		upgrades = new ArrayList<Upgrade>();
		ass = Animator.a;
		name = "You";
		addArmor(40);
		critDamage = 1.75;
	}

	public void addToInventory(Item i)
	{
		if(i!=null) inventory.add(i);
	}

	public void addArmor(int amount)
	{
		armor = true;
		armorPoints += amount;
		ass.setPlayerArmor(true);
	}

	private int hitArmor(int amount)
	{
		int output = 0;
		if(armorPoints >= amount)
		{
			armorPoints -= 0.6 * amount;
			if(armorPoints < 0) armor = false;
		}
		else
		{
			output = amount - armorPoints;
			armorPoints = 0;
			armor = false;
		}	
		ass.setPlayerArmor(armor);
		return output;
	}

	public int getArmor()
	{
		return armorPoints;
	}

	@Override
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
		
		if(inventory.get(index) instanceof CritUpgrade u && upgrades.size() < 4)
		{
			critRate += u.critBoost;
			upgrades.add(new Upgrade(u));
			inventory.remove(index);
			return true;
		}
		else if(inventory.get(index) instanceof AtkUpgrade u && upgrades.size() < 4)
		{
			attack += u.atkBoost;
			upgrades.add(new Upgrade(u));
			inventory.remove(index);
			return true;
		}
		else if(inventory.get(index) instanceof Armor a)
		{
			addArmor(a.armorPoints);
			inventory.remove(index);
			return true;
		}
		else if(inventory.get(index) instanceof HealthPack h)
		{
			heal(h.healing);
			inventory.remove(index);
			return true;
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

	public HashMap<String, Integer> getStats()
	{
		HashMap<String, Integer> stats = new HashMap<String, Integer>();
		stats.put("Health", health);
		stats.put("Armor", armorPoints);
		stats.put("Attack", attack);
		stats.put("Crit Rate", (int)(critRate*100));
		return stats;
	}

	public ArrayList<Upgrade> getUpgrades()
	{
		return upgrades;
	}
}
package com.entities;
public abstract class Entity
{
	protected final int maxHealth;
	protected int health;
	protected int attack;
	protected double critRate;
	protected double critDamage;

	public int speed;
	public boolean alive;

	protected String name;
	
	public Entity (int hp, int atk, double crit, int spd)
	{
		maxHealth = hp;
		attack = atk;
		speed = spd;
		critRate = crit;
		critDamage = 1.5;
		
		health = maxHealth;
		alive = true;

		name = "";
	}
	
	public String getName()
	{
		return name;
	}

	public void heal(int amount)
	{
		health += amount;
		if (health > maxHealth) health = maxHealth;
	}

	public int getHealth() { return health; }

	public int getHealthPercent()
	{
		return (int)( (double)health / maxHealth * 100 );
	}

	public void attack(Entity other)
	{
		int atk = attack;
		if(Math.random() < critRate) atk *= critDamage;
		other.takeDamage(atk);
	}

	public void takeDamage(int damage)
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
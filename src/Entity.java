public class Entity
{
	public int maxHealth;
	public int health;
	public int attack;
	public int speed;
	public boolean alive;
	public double critRate;
	
	public Entity (int hp, int atk, int spd)
	{
		maxHealth = hp;
		health = maxHealth;
		attack = atk;
		speed = spd;
		critRate = 0.2;
		alive = true;
	}

	public int getHealthPercent()
	{
		return (int)( (double)health / maxHealth * 100 );
	}

	public void attack(Entity other)
	{

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
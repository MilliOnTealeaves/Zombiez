public class Zombie extends Entity
{
	public Zombie ()
	{
		super(
			// health
			Utility.varianceInt(150, 0.1) ,
			// attack
			Utility.varianceInt(050, 0.2) ,
			// crit rate
			Utility.varianceDbl(0.2, 0.2) ,
			// speed
			Utility.varianceInt(100, 0.2) );
	}
}
public class Location
{
	
	private boolean cleared;

	protected Location()
	{
		cleared = false;
	}

	@Override
	public String toString() {
		if(cleared) return "  ";
		else
		{
			return "";
		}
	}
}

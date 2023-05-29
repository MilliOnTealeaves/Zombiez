package items;

public class SupplyPoint extends Location
{
	public final Item item;

	public SupplyPoint()
	{
		super();
		item = Item.getRandomItem();
	}
}

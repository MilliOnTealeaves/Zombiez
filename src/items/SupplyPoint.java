package items;

public class SupplyPoint extends Location
{
	private final Item item;

	public SupplyPoint()
	{
		super();
		item = Item.getRandomItem();
	}
	
	public Item getItem()
	{
		return item;
	}
}

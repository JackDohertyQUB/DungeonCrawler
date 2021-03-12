
public class Item {
	private String name;
	private ItemType itmType;
	private DamageType dmgType;
	public Item(String name, ItemType item){
		this.name = name;
		this.itmType = item;
	}
	
	public void useItem() {
		if(this.itmType == ItemType.damage) {
			
		}
	}
}

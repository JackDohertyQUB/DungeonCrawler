
public class Enemy extends Actor {
	private int expDrop;
	public Enemy(String n_name, int n_Health, int n_Armour, DamageType n_dmgType, int level, int expDrop) {
		super(n_name, n_Health, n_Armour, n_dmgType, level);
		this.expDrop = expDrop;
	}
	
	public int getExpDrop() { return expDrop;};
}

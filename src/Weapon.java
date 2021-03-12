
public class Weapon {
	private String name;
	private String Description;
	private int atkBonus;
	private int Damage;
	private static DamageType damageType;
	
	public Weapon(String n_name, int n_Damage, int n_atk, DamageType new_Damage) {
		name = n_name;
		Damage = n_Damage;
		damageType = new_Damage;
		atkBonus = n_atk;
	}
	
	//Setters
	public void setName(String new_name) {name = new_name;}
	public void setDescription(String n_Desc) {Description = n_Desc;}
	public void setDamage(int n_dmg) {Damage = n_dmg;};
	public void setatkBonus(int n_atk) {atkBonus = n_atk;};
	public void setDmgType(DamageType new_type) {damageType = new_type;}
	
	//Getters
	public String getName(){ return name;};
	public int getDamage() {return Damage;};
	public String getDesc() {return Description;};
	public DamageType getDamageType() {return damageType;};
	
	public String getDetails() {
		String details ="Name: " + getName() 
		+ "\nDescription: " + getDesc()
		+ "\nDamage: 1d" + getDamage()
		+ "\nDamage Type: " + getDamageType();
		
		return details;
	}
	
	public int attack() {
		int finalAttack = (int) (Math.random() * 100);
		finalAttack+= atkBonus;
		return finalAttack;
	}
	
	public int damage() {
		int finalAttack = (int) (Math.random() * Damage);
		if (finalAttack == 0) {
			do {
				 finalAttack = (int) (Math.random() * Damage);
			}while (finalAttack == 0);
		}
		return finalAttack;
	}
	
}

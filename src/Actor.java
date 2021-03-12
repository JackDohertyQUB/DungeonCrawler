
public class Actor {
	
	//Actor Attributes
	private String name;
	private int maxHealth;
	private int currentHealth;
	private int Armour;
	private DamageType weakness;
	private Weapon equipped;
	private Weapon storedWeapon[] = new Weapon[2];
	private Item stored[] = new Item[3];
	private boolean Alive = true;
	private boolean guardState = false;
	private int level;
	private int experience = 0;
	//Constructors
	public Actor(String n_name, int n_Health, int n_Armour, DamageType n_dmgType, int level) {
		name = n_name;
		currentHealth = n_Health;
		maxHealth = n_Health;
		Armour = n_Armour;
		weakness = n_dmgType;
		this.setLevel(level);
	}
		
	//Getters
	public int getCurrentHealth(){ return currentHealth; };
	public int getMaxHealth(){ return maxHealth; };
	public int getArmour(){ return Armour; };
	public String getName(){ return name; };
	public DamageType getWeakness(){ return weakness; };
	public Weapon getWeapon() { return equipped; };
	public boolean getAlive() { return Alive; };
	public boolean getGuardState() { return guardState;};
	public int getLevel() { return level;};
	public int getEXP() { return experience;};
	
	//ADD VALIDATION TO SETTERS.
	//Setters
	public void setName(String new_name) {name = new_name;};
	public void setCurrentHealth(int new_Health) {currentHealth = new_Health;};
	public void setMaxHealth(int new_Health) {maxHealth = new_Health;};
	public void setArmour(int new_Armour) {Armour = new_Armour;};
	public void setWeakness(DamageType new_Weakness) {weakness = new_Weakness;};
	public void setWeapon(Weapon new_Weapon) {equipped = new_Weapon;};
	public void setAlive(boolean new_Alive) {Alive = new_Alive;};
	public void setGuard(boolean new_Guard) {guardState = new_Guard;};
	public void setLevel(int new_level) {level = new_level;};
		
	//Methods
	//Combat Methods
	public void attackEntity(Actor target) {
		int targAC = target.armourState(target.getArmour());
		int weaponATK = weaponAttack();
		boolean crit = critCheck(weaponATK);
		DamageType targWeak = target.getWeakness();
		System.out.println(name + " attacks " + target.getName() + " with their " + equipped.getName() + "!");
		if(targAC < weaponATK) {		
			if(targWeak == equipped.getDamageType()) {
				int weaponDMG = weaponDamage() + weaponDamage();
				int targHP = target.getCurrentHealth();
				if(critCheck(weaponATK)) {
					weaponDMG += weaponDamage();
					System.out.println("Critical Hit!");
				}
				if(target.getGuardState() == true) {
					weaponDMG = weaponDMG / 2;
					target.setGuard(false);
				}
				target.setCurrentHealth(targHP-weaponDMG);
				System.out.println("Target took " + weaponDMG + " damage! The target was weak to the attack!");
			} else {
				int weaponDMG = weaponDamage();
				int targHP = target.getCurrentHealth();
				if(critCheck(weaponATK)) {
					weaponDMG += weaponDamage();
					System.out.println("Critical Hit!");
				}
				if(target.getGuardState() == true) {
					weaponDMG = weaponDMG / 2;
					target.setGuard(false);
				}
				target.setCurrentHealth(targHP-weaponDMG);
				System.out.println("Target took " + weaponDMG + " damage!");
			}
			
		} else {
			System.out.println("Attack Missed!");
		}
	}
	
	public void guard() {
		guardState = true;
		System.out.println(name + " guards!");
	}
	
	
	// Misc Methods
	public boolean critCheck(int attack) { // Checks if last attack was a critical hit
		if(attack >= 100) {
			return true;
		} else {
			return false;
		}
	}		
	public boolean lifeCheck(int hp) { // Checks if health is equal to or below 0
		if(hp <= 0) {
			return false;
		} else {
			return true;
		}
	}
	public boolean lifeCheck() { // Checks if health is equal to or below 0
		if(currentHealth <= 0) {
			System.out.println(name + " is defeated!");
			return false;
		} else {
			return true;
		}
	}
	
	void addEXP(int exp) {
		this.experience += exp;
	}
	private void levelUp(int newLevel) {
		int newHealth;
		newHealth = (int) ((newLevel * 2) + (Math.random() * 8));
		System.out.println("Levelled up to Level: " + newLevel);
		System.out.println("Old Health: " + this.maxHealth);
		this.setMaxHealth(newHealth);
		System.out.println("New Health: " + newHealth);
		this.setLevel(newLevel);
		this.setCurrentHealth(newHealth);
	}
	
	public void expCheck() {
		int exp = this.getEXP();
		if(exp <= 100 && level < 1) {
			this.levelUp(1);
		}else if(exp > 100 && exp <= 200 && level < 2) {
			this.levelUp(2);
		}else if(exp > 200 && exp <= 300 && level < 3) {
			this.levelUp(3);
		}else if(exp > 300 && exp <= 400 && level < 4) {
			this.levelUp(4);
		}else if(exp > 400 && exp <= 500 && level < 5) {
			this.levelUp(5);
		}else if(exp > 500 && exp <= 600 && level < 6) {
			this.levelUp(6);
		}else if(exp > 600 && exp <= 700 && level < 7) {
			this.levelUp(7);
		}else if(exp > 700 && exp <= 800 && level < 8) {
			this.levelUp(8);
		}else if(exp > 800 && exp <= 900 && level < 9) {
			this.levelUp(9);
		}else if(exp > 900 && exp <= 1000&& level < 10) {
			this.levelUp(10);
		}
	}
	
	// Weapon/Armour Methods
	public int weaponAttack() {
		return equipped.attack();
	}
	public int weaponDamage() {
		return equipped.damage();
	}
	
	public String weaponName() {
		return equipped.getName();
	}
	
	public DamageType weaponType() {
		return equipped.getDamageType();
	}
	
	public int armourState(int Armour) {
		int finalAC = 40;
		finalAC+=Armour;
		return finalAC;
	}
	
	public String getEquippedDetails() {
		 String details =  equipped.getDetails();
		 return details;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

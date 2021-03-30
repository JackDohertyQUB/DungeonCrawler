import java.util.Random;

public class RandomNameGenerator {
	private String[] firstName = { "Ghaul", "Garth", "Darm", "Dhrum", "Scrull", "Driak", "Larne", "Sharma", "Yiant",
			"Viass", "Quena", "Seeker", "Yggdrasil", "Oryx", "Cringeass", "Lance", "Barney", "Zer0" };
	private String[] bossTitlePartOne = { "Deathbringer", "Godslayer", "Phantom", "Queen", "Regicider", "Freelancer" };
	private String[] bossTitlePartTwo = { "Galaxy", "Universe", "Kingdom", "Realm", "Lands", "City" }; 
	private String[] gruntTitle = { "Thief", "Conman", "Grunt", "Slave" };
	Random rnd = new Random();

	public String getGruntName() {
		String name;
		name = firstName[rnd.nextInt(firstName.length)] + ", " + gruntTitle[rnd.nextInt(gruntTitle.length)];
		return name;
	}

	public String getBossName() {
		String name;
		name = firstName[rnd.nextInt(firstName.length)] + ", " + bossTitlePartOne[rnd.nextInt(bossTitlePartOne.length)]
				+ " of the " + bossTitlePartTwo[rnd.nextInt(bossTitlePartTwo.length)];
		return name;
	}

}

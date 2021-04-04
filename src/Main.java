import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.sql.Time;
import java.time.*;

public class Main {
	public int x = 5;
	public int y = 10;
	public char matrixScreen[][] = new char[x][y];
	public static boolean combat = false;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Actor myPlayer;
		myPlayer = generateStarterPlayer();
		Enemy Player2 = new Enemy("Player 2", 10, 10, DamageType.none, 1, 100);
		GridMap map = new GridMap(myPlayer, 1, 12, 12);
		String[][] mapmap = printMap(map);
		

		map.moveEntity(0, map.getEnemyPos(0), incrementPos(map.getEnemyPos(0), 0, -1));
		mapmap = printMap(map);

		Weapon equippedItem2 = new Weapon("Sword2", 5, 30, DamageType.fire);
		System.out.println((Math.random() * 3));

		Player2.setWeapon(equippedItem2);

		System.out.println(battle(myPlayer, Player2));

	}

	public static int[] incrementPos(int[] toChange, int xChange, int yChange) {
		int[] newPos = new int[toChange.length];
		for (int i = 0; i < newPos.length; i++) {
			newPos[i] = toChange[i];
		}
		newPos[0] += xChange;
		newPos[1] += yChange;

		return newPos;
	}
	
	public static String[][] printMap(GridMap map) {
		String[][] mapmap = map.generateViewMap();
		for (int i = 0; i < mapmap.length; i++) {
			for (int j = 0; j < mapmap[0].length; j++) {
				System.out.print(mapmap[i][j] + " ");
			}
			System.out.println();
		}
		return mapmap;
	}

	public static Actor generateStarterPlayer() {
		Actor myPlayer = new Actor("Player 1", 10, 10, DamageType.none, 1);
		Weapon equippedItem = new Weapon("Sword", 5, 30, DamageType.piercing);
		myPlayer.setWeapon(equippedItem);
		return myPlayer;
	}

	public static boolean battle(Actor playerOne, Enemy playerTwo) {
		boolean playerTurn = true;
		Actor currentTurn = playerOne;
		Enemy opponent = playerTwo;
		int i = 1;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Combat Start!");
			do {
				System.out.println("Turn " + i);
				if (playerTurn) {
					System.out.println("What will you do? " + "\n 1>Attack " + "\n 2>Guard " + "\n 3>Check "
							+ "\n 4>Use Item" + "\n 5>Swap Weapon");
					String answer = input.next();
					if (answer.equalsIgnoreCase("Attack") || answer.equalsIgnoreCase("1")) {
						currentTurn.attackEntity(opponent);
						i++;
						System.out.println();
					} else if (answer.equalsIgnoreCase("Guard") || answer.equalsIgnoreCase("2")) {
						currentTurn.guard();
						i++;
					} else if (answer.equalsIgnoreCase("Check") || answer.equalsIgnoreCase("3")) {
						System.out.println("Which enemy stat will you check? " + "\n 1>Health " + "\n 2>Weakness"
								+ "\n 3>Weapon ");
						String answerCheck = input.next();
						if (answerCheck.equalsIgnoreCase("1") || answer.equalsIgnoreCase("Health")) {
							System.out.println("The opponents current health is: " + opponent.getCurrentHealth());
							i++;
						} else if (answerCheck.equalsIgnoreCase("2") || answer.equalsIgnoreCase("Weakness")) {
							System.out.println("The opponents weakness is: " + opponent.getWeakness());
							i++;
						} else if (answerCheck.equalsIgnoreCase("3") || answer.equalsIgnoreCase("Weapon")) {
							System.out.println("The opponents weapon is: \n" + opponent.getEquippedDetails());
							i++;
						}

					} else if (answer.equalsIgnoreCase("Item") || answer.equalsIgnoreCase("4")) {

					}

				} else if (playerTurn == false) {
					opponent.attackEntity(currentTurn);
					i++;
					System.out.println();
				}

				playerTurn = !playerTurn;
			} while (currentTurn.lifeCheck() == true && opponent.lifeCheck() == true);
			if(playerOne.getAlive() == true) {
				System.out.println("Combat Over! \n");
				playerOne.addEXP(playerTwo.getExpDrop());
				playerOne.expCheck();
				return true;
			} else {
				System.out.println("Combat Over! \n");
				return false;
			}
			
			
		} while (combat);

	}
	
	public static void processOption(String choice, GridMap map) {
		switch(choice) {
		case "left": movePlayerLeft(map); break;
		case "right": movePlayerRight(map); break;
		case "up": movePlayerUp(map); break;
		case "down": movePlayerDown(map); break;
		default: System.out.println("Invalid choice");
		
		
		
		
		
		}
	}

	public static boolean movePlayerLeft(GridMap map) {
		return map.movePlayer(map.getPlayerPos(), incrementPos(map.getPlayerPos(), 0, -1));
	}

	public static boolean movePlayerRight(GridMap map) {
		return map.movePlayer(map.getPlayerPos(), incrementPos(map.getPlayerPos(), 0, 1));
	}

	public static boolean movePlayerDown(GridMap map) {
		return map.movePlayer(map.getPlayerPos(), incrementPos(map.getPlayerPos(), 1, 0));
	}

	public static boolean movePlayerUp(GridMap map) {
		return map.movePlayer(map.getPlayerPos(), incrementPos(map.getPlayerPos(), -1, 0));
	}

}

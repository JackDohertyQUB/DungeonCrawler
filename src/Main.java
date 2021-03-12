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
		
		
		
		Weapon equippedItem2 = new Weapon("Sword2", 5, 30, DamageType.fire);
		System.out.println((Math.random() * 3));
		
		
		Player2.setWeapon(equippedItem2);
		
		battle(myPlayer, Player2);
		
		/*
		  for(int i = 0; i<x ; i++) { for(int j = 0; j<y ; j++) { matrixScreen[i][j] =
		  'x'; System.out.print(matrixScreen[i][j]); } System.out.println(); }
		 */
	}
	
	public static Actor generateStarterPlayer() {
		Actor myPlayer = new Actor("Player 1", 10, 10, DamageType.none, 1);
		Weapon equippedItem = new Weapon("Sword", 5, 30, DamageType.piercing);
		myPlayer.setWeapon(equippedItem);
		return myPlayer;
	}
	
	
	
	public static void battle(Actor playerOne, Enemy playerTwo) {
		boolean playerTurn = true;
		Actor currentTurn = playerOne;
		Enemy opponent = playerTwo;
		int i = 1;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Combat Start!");
			do {
				System.out.println("Turn " + i);
				if(playerTurn) {
					System.out.println("What will you do? "
							+ "\n 1>Attack "
							+ "\n 2>Guard "
							+ "\n 3>Check "
							+ "\n 4>Use Item"
							+ "\n 5>Swap Weapon");
					String answer = input.next();
					if(answer.equalsIgnoreCase("Attack") || answer.equalsIgnoreCase("1")) {
					currentTurn.attackEntity(opponent);						
					i++;
					System.out.println();
				} else if(answer.equalsIgnoreCase("Guard") || answer.equalsIgnoreCase("2")) {
					currentTurn.guard();
					i++;					
				} else if(answer.equalsIgnoreCase("Check") || answer.equalsIgnoreCase("3")) {
					System.out.println("Which enemy stat will you check? "
							+ "\n 1>Health "
							+ "\n 2>Weakness"
							+ "\n 3>Weapon ");
					String answerCheck = input.next();
					if(answerCheck.equalsIgnoreCase("1") || answer.equalsIgnoreCase("Health")) {
						System.out.println("The opponents current health is: " + opponent.getCurrentHealth());
						i++;
					} else if(answerCheck.equalsIgnoreCase("2") || answer.equalsIgnoreCase("Weakness")) {
						System.out.println("The opponents weakness is: " + opponent.getWeakness());
						i++;
					} else if(answerCheck.equalsIgnoreCase("3") || answer.equalsIgnoreCase("Weapon")) {
						System.out.println("The opponents weapon is: \n" + opponent.getEquippedDetails());
						i++;
					}
					
				}  else if(answer.equalsIgnoreCase("Item") || answer.equalsIgnoreCase("4")) {
					
				}
					
					
						
					
				} else if(playerTurn == false) {
					opponent.attackEntity(currentTurn);
					i++;
					System.out.println();
				}
		
				
			playerTurn = !playerTurn;
			}while(currentTurn.lifeCheck() == true && opponent.lifeCheck() == true);
			System.out.println("Combat Over! \n");
			playerOne.addEXP(playerTwo.getExpDrop()); 
			playerOne.expCheck();
		}while(combat);
		
	}

}

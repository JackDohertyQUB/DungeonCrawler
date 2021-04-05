import java.util.Arrays;
import java.util.Random;

public class GridMap {
	private String[][] viewMap;
	private Tile[][] tileMap;

	private int xSize;
	private int ySize;

	// Player Variables
	private Actor player;
	private int[] playerPos;

	// Enemy Variables
	private Enemy[] enemy;
	private int[][] enemyPos;
	private int enemyCount;

	private int level;
	Random rnd = new Random();
	RandomNameGenerator RNG = new RandomNameGenerator();

	public GridMap(Actor Player, int level, int xSize, int ySize) {
		viewMap = new String[xSize][ySize];
		this.xSize = xSize;
		this.ySize = ySize;
		tileMap = generateOuterWalls(generateBlankTileMap(xSize, ySize));
		this.player = Player;
		tileMap = spawnPlayer();

		enemyCount = level + (rnd.nextInt(2));

		enemy = new Enemy[enemyCount];
		enemyPos = new int[enemyCount][2];
		for (int i = 0; i < enemyCount; i++) {
			enemy[i] = generateEnemy(level);
			tileMap = spawnEntityRandom(i);
		}

	}

	// Getter/Setters

	public Actor getPlayer() {
		return this.player;
	}

	public Enemy getEnemy(int id) {
		return enemy[id];
	}

	public Enemy[] getAllEnemy() {
		return enemy;
	}

	public int[] getEnemyPos(int enemyID) {
		return enemyPos[enemyID];
	}

	public int[] getPlayerPos() {
		return playerPos;
	}

	// Map Generation

	private Tile[][] generateBlankTileMap(int xSize, int ySize) {
		Tile[][] newMap = new Tile[xSize][ySize];
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				newMap[i][j] = Tile.FLOOR;
			}
		}
		return newMap;
	}

	public boolean isAdjacentClear(int posOne, int posTwo) {
		if (tileMap[posOne + 1][posTwo] == Tile.ENEMY || tileMap[posOne - 1][posTwo] == Tile.ENEMY
				|| tileMap[posOne][posTwo + 1] == Tile.ENEMY || tileMap[posOne][posTwo - 1] == Tile.ENEMY
				|| tileMap[posOne + 1][posTwo + 1] == Tile.ENEMY || tileMap[posOne + 1][posTwo - 1] == Tile.ENEMY
				|| tileMap[posOne - 1][posTwo + 1] == Tile.ENEMY || tileMap[posOne - 1][posTwo - 1] == Tile.ENEMY
				|| tileMap[posOne + 1][posTwo] == Tile.PLAYER || tileMap[posOne - 1][posTwo] == Tile.PLAYER
				|| tileMap[posOne][posTwo + 1] == Tile.PLAYER || tileMap[posOne][posTwo - 1] == Tile.PLAYER
				|| tileMap[posOne + 1][posTwo + 1] == Tile.PLAYER || tileMap[posOne + 1][posTwo - 1] == Tile.PLAYER
				|| tileMap[posOne - 1][posTwo + 1] == Tile.PLAYER || tileMap[posOne - 1][posTwo - 1] == Tile.PLAYER) {
			return false;
		} else {
			return true;
		}

	}

	public boolean isAdjacentDirect(int posOne, int posTwo) {
		if (tileMap[posOne + 1][posTwo] == Tile.ENEMY || tileMap[posOne - 1][posTwo] == Tile.ENEMY
				|| tileMap[posOne][posTwo + 1] == Tile.ENEMY || tileMap[posOne][posTwo - 1] == Tile.ENEMY) {
			return false;
		} else {
			return true;
		}
	}

	private Tile[][] generateOuterWalls(Tile[][] tileMap) {
		if (tileMap.length <= 2 || tileMap[0].length <= 2) {
			return tileMap;
		} else {
			Tile[][] wallMap = tileMap;

			for (int j = 0; j < wallMap.length; j++) {
				for (int k = 0; k < wallMap[0].length; k++) {
					if (k == 0 || k == wallMap[0].length - 1) {
						wallMap[j][k] = Tile.WALL;
					} else if (j == 0 || j == wallMap.length - 1) {
						wallMap[j][k] = Tile.WALL;
					}
				}
			}

			return wallMap;
		}
	}

	public String[][] generateViewMap() {
		String[][] viewMap = new String[tileMap.length][tileMap[0].length];
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[0].length; j++) {
				viewMap[i][j] = tileMap[i][j].toString();
			}
		}
		this.viewMap = viewMap;
		return viewMap;
	}

	// Entity Manipulation

	public boolean moveEntity(int enemyID, int[] pos, int[] newPos) {
		Tile[][] newMap = tileMap;
		System.out.println("Old position: " + Arrays.toString(enemyPos[enemyID]));
		if (enemyID > -1 && enemyID < enemy.length) {

			if (newMap[newPos[0]][newPos[1]] == Tile.FLOOR) {
				newMap[newPos[0]][newPos[1]] = Tile.ENEMY;
				newMap[pos[0]][pos[1]] = Tile.FLOOR;
				enemyPos[enemyID] = newPos;
				System.out.println("New position: " + Arrays.toString(enemyPos[enemyID]));
				tileMap = newMap;
				return true;
			} else {
				return false;
			}

		} else {
			System.out.println("This aint working chief");
			return false;
		}

	}

	public boolean movePlayer(int[] pos, int[] newPos) {
		Tile[][] newMap = tileMap;
		System.out.println("Old Position: " + Arrays.toString(playerPos));
		if (newMap[newPos[0]][newPos[1]] == Tile.FLOOR) {
			newMap[newPos[0]][newPos[1]] = Tile.PLAYER;
			newMap[pos[0]][pos[1]] = Tile.FLOOR;
			playerPos = newPos;
			System.out.println("New position: " + Arrays.toString(playerPos));
			tileMap = newMap;
			return true;
		} else {
			return false;
		}

	}

	private Tile[][] spawnPlayer() {
		Tile[][] newMap = tileMap;
		boolean goodSpawn = false;
		do {
			int posOne, posTwo;
			posOne = rnd.nextInt(xSize);
			posTwo = rnd.nextInt(ySize);
			if (newMap[posOne][posTwo] == Tile.FLOOR && isAdjacentClear(posOne, posTwo)) {
				int[] newPosition = { posOne, posTwo };
				playerPos = newPosition;
				newMap[posOne][posTwo] = Tile.PLAYER;
				goodSpawn = true;
			}

		} while (!goodSpawn);

		return newMap;
	}

	private Tile[][] spawnEntityRandom(int enemyID) {
		Tile[][] newMap = tileMap;
		boolean goodSpawn = false;
		do {
			int posOne, posTwo;
			posOne = rnd.nextInt(xSize);
			posTwo = rnd.nextInt(ySize);
			if (newMap[posOne][posTwo] == Tile.FLOOR && isAdjacentClear(posOne, posTwo)) {
				int[] newPosition = { posOne, posTwo };
				enemyPos[enemyID] = newPosition;
				newMap[posOne][posTwo] = Tile.ENEMY;
				goodSpawn = true;
			}
		} while (!goodSpawn);

		return newMap;

	}

	private Tile[][] spawnEntityPos(int enemyID, int posOne, int posTwo) {
		Tile[][] newMap = tileMap;
		if (newMap[posOne][posTwo] == Tile.FLOOR && isAdjacentClear(posOne, posTwo)) {
			int[] newPosition = { posOne, posTwo };
			enemyCount++;
			enemyPos[enemyID] = newPosition;
			newMap[posOne][posTwo] = Tile.ENEMY;
		}
		return newMap;

	}

	private Enemy generateEnemy(int level) {
		Enemy tempEnemy = new Enemy(RNG.getGruntName(), rnd.nextInt(level * 10), 10 + rnd.nextInt(level),
				DamageType.none, this.level, level * 10 + rnd.nextInt(5));
		return tempEnemy;

	}

	public Enemy enemyAdjacent() {
		if (isAdjacentDirect(playerPos[0], playerPos[1])) {
			if (tileMap[playerPos[0] + 1][playerPos[1]] == Tile.ENEMY) {
				int[] enemyCheck = { playerPos[0] + 1, playerPos[1] };
				int enemyID;
				for (int i = 0; i < enemy.length; i++) {
					if (enemyPos[i] == enemyCheck) {
						enemyID = i;
						return enemy[i];

					}
				}

			} else if (tileMap[playerPos[0] - 1][playerPos[1]] == Tile.ENEMY) {
				int[] enemyCheck = { playerPos[0] - 1, playerPos[1] };
				int enemyID;
				for (int i = 0; i < enemy.length; i++) {
					if (enemyPos[i] == enemyCheck) {
						enemyID = i;
						return enemy[i];

					}
				}

			} else if (tileMap[playerPos[0]][playerPos[1] + 1] == Tile.ENEMY) {
				int[] enemyCheck = { playerPos[0] , playerPos[1] + 1 };
				int enemyID;
				for (int i = 0; i < enemy.length; i++) {
					if (enemyPos[i] == enemyCheck) {
						enemyID = i;
						return enemy[i];

					}
				}

			}  else if (tileMap[playerPos[0]][playerPos[1] - 1] == Tile.ENEMY) {
				int[] enemyCheck = { playerPos[0] , playerPos[1] - 1 };
				int enemyID;
				for (int i = 0; i < enemy.length; i++) {
					if (enemyPos[i] == enemyCheck) {
						enemyID = i;
						return enemy[i];

					}
				}

			}
			
		}
		return null;
	}
}

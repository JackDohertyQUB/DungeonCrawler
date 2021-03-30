import java.util.Random;

public class GridMap {
	private String[][] viewMap;
	private Tile[][] tileMap;

	// Player Variables
	private Actor player;
	private int[] playerPos;

	// Enemy Variables
	private Enemy[] enemy;
	private int[][] enemyPos;

	private int level;

	public GridMap(Actor Player, int level, int xSize, int ySize) {
		viewMap = new String[xSize][ySize];
		tileMap = generateBlankTileMap(xSize, ySize);
		this.player = Player;
		Random rnd = new Random();
		int enemyCount = rnd.nextInt(level + (rnd.nextInt(2)));
		for(int i = 0; i < enemyCount; i++) {
			enemy[i] = generateEnemy(level);
		}
	}
	

	private Tile[][] generateBlankTileMap(int xSize, int ySize) {
		Tile[][] newMap = new Tile[xSize][ySize];
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				newMap[i][j] = Tile.ENEMY; 
			}
		}
		return newMap;
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
	
	private Enemy generateEnemy(int level) {
		
		
		return null;
	}
}


public enum Tile {
	PLAYER("O"), ENEMY("X"), FLOOR(" "), WALL("|");

	private final String label;

	private Tile(String label) {
		this.label = label;
	}

	public String toString() {
		return label;
	}
}

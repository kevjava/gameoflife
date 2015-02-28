package gameoflife;

public enum Compass {

	NW(Directions.UP, Directions.LEFT), N(Directions.UP), NE(Directions.UP, Directions.RIGHT), W(Directions.LEFT), E(
			Directions.RIGHT), SW(Directions.DOWN, Directions.LEFT), S(Directions.DOWN), SE(Directions.DOWN,
			Directions.RIGHT);

	private Directions[] _directions;

	private Compass(Directions... directions) {
		_directions = directions;
	}

	public Directions[] getDirections() {
		return _directions;
	}
}

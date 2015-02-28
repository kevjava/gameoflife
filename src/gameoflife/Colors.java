package gameoflife;

import org.newdawn.slick.Color;

public class Colors {

	public static Color COLOR_DEAD = Color.black;
	public static Color COLOR_ALIVE = Color.green;
	public static Color COLOR_OVERCROWDED = Color.red;
	public static Color COLOR_LONELY = Color.blue;

	public static Color getColor(CellState state, float tickPercentage) {
		switch (state) {
		case DEAD:
			return COLOR_DEAD;
		case ALIVE:
			return COLOR_ALIVE;
		case SPAWNING:
			return COLOR_ALIVE.darker(1.0f - tickPercentage);
		case OVERCROWDED:
			return COLOR_OVERCROWDED.darker(tickPercentage);
		case LONELY:
			return COLOR_LONELY.darker(tickPercentage);
		default:
			throw new RuntimeException("Unknown cell state."); // Should not happen.
		}
	}
}

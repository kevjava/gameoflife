package gameoflife;

import java.util.Collection;
import java.util.logging.Logger;

public class Cell {

	private CellState _newState;
	private CellState _state;
	private Collection<Cell> _neighbors;
	private int _x;
	private int _y;
	private static final Logger logger = Logger.getLogger(Cell.class.getSimpleName());

	public Cell(int x, int y) {
		_x = x;
		_y = y;
		_state = CellState.DEAD;
		_newState = CellState.DEAD;
	}

	public void setNeighbors(Collection<Cell> neighbors) {
		_neighbors = neighbors;
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public void setState(CellState state) {
		_state = state;
	}

	public boolean isAlive() {
		return (CellState.ALIVE.equals(_state) || CellState.SPAWNING.equals(_state));
	}

	public void update() {
		_state = _newState;
	}

	public void preUpdate() {
		switch (_state) {
		case SPAWNING:
		case ALIVE:
			_newState = CellState.ALIVE;
			checkForDeath();
			break;
		case OVERCROWDED:
		case LONELY:
		case DEAD:
			_newState = CellState.DEAD;
			checkForSpawn();
			break;
		}
		// logger.log(Level.INFO, String.format("Cell %s has %s living neighbors.  It was state %s, and is now %s.",
		// toString(), getLivingNeighbors(), _state, _newState));
	}

	@Override
	public String toString() {
		return String.format("[%d, %d] (%s)", _x, _y, _state);
	}

	private void checkForDeath() {
		if (getLivingNeighbors() > 3) {
			_newState = CellState.OVERCROWDED;
		} else if (getLivingNeighbors() < 2) {
			_newState = CellState.LONELY;
		}
	}

	private void checkForSpawn() {
		if (getLivingNeighbors() == 3) {
			_newState = CellState.SPAWNING;
		}
	}

	private int getLivingNeighbors() {
		int livingNeighbors = 0;
		for (Cell cell : _neighbors) {
			if (cell.isAlive()) {
				livingNeighbors++;
			}
		}
		return livingNeighbors;
	}

	public Collection<Cell> getNeighbors() {
		return _neighbors;
	}

	public CellState getState() {
		return _state;
	}

}

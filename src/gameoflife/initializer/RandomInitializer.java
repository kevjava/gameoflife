package gameoflife.initializer;

import gameoflife.Cell;
import gameoflife.CellGrid;
import gameoflife.CellState;

public class RandomInitializer implements GameOfLifeInitializer {

	CellGrid _cellGrid;
	private static final int INITIAL_LIVE_CELLS = 300;

	@Override
	public void setGrid(CellGrid cellGrid) {
		_cellGrid = cellGrid;
	}

	public void init() {
		for (int i = 0; i < INITIAL_LIVE_CELLS; i++) {
			Cell cell = getRandomEmptyCell();
			cell.setState(CellState.SPAWNING);
		}
	}

	private Cell getRandomEmptyCell() {
		Cell c = getRandomCell();
		while (c.isAlive()) {
			c = getRandomCell();
		}
		return c;
	}

	private Cell getRandomCell() {
		int x = (int) (Math.random() * _cellGrid.getWidth());
		int y = (int) (Math.random() * _cellGrid.getHeight());
		return _cellGrid.getCell(x, y);
	}
}

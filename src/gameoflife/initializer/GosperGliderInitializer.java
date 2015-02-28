package gameoflife.initializer;

import gameoflife.CellGrid;

public class GosperGliderInitializer implements GameOfLifeInitializer {

	private CellGrid _grid;

	public void setGrid(CellGrid grid) {
		_grid = grid;
	}

	@Override
	public void init() {
		_grid.clearGrid();

		// _O_
		// __O
		// OOO
		_grid.setCellAlive(1, 0);
		_grid.setCellAlive(2, 1);
		_grid.setCellAlive(0, 2);
		_grid.setCellAlive(1, 2);
		_grid.setCellAlive(2, 2);
	}

}

package gameoflife.initializer;

import gameoflife.CellGrid;

public interface GameOfLifeInitializer {

	public void setGrid(CellGrid grid);

	public void init();

}

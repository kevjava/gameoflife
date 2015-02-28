package gameoflife;

import gameoflife.initializer.GameOfLifeInitializer;
import gameoflife.initializer.RandomInitializer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameOfLifeLogic {

	private CellGrid _cellGrid;
	private Logger logger = Logger.getLogger(GameOfLifeLogic.class.getSimpleName());
	private int _cellsWide;
	private int _cellsHigh;
	private int _pixelWidth;
	private int _pixelHeight;
	private GameOfLifeInitializer _initializer;
	private int _livingCellCount;

	private static final int CELL_WIDTH = 10;
	private static final int CELL_HEIGHT = 10;

	public GameOfLifeLogic(int pixelWidth, int pixelHeight) {
		_pixelWidth = pixelWidth;
		_pixelHeight = pixelHeight;
	}

	public void init() {
		logger.log(Level.INFO, "Starting initialization.");
		initCells();
		initGrid();
		logger.log(Level.INFO, "Initialization complete.");
	}

	private void initGrid() {
		if (_initializer == null) {
			_initializer = new RandomInitializer();
			// _initializer = new GosperGliderInitializer();
		}
		_initializer.setGrid(_cellGrid);
		_initializer.init();
		populateCellNeighbors();
	}

	private void populateCellNeighbors() {
		for (Cell cell : _cellGrid.getCellCollection()) {
			cell.setNeighbors(_cellGrid.getNeighbors(cell));
		}
	}

	private void initCells() {
		_cellsWide = _pixelWidth / (CELL_WIDTH + 1);
		_cellsHigh = _pixelHeight / (CELL_HEIGHT + 1);
		_cellGrid = new CellGrid(_cellsWide, _cellsHigh);
	}

	public void tick() {
		_livingCellCount = 0;
		for (Cell cell : _cellGrid.getCellCollection()) {
			cell.preUpdate();
		}
		for (Cell cell : _cellGrid.getCellCollection()) {
			cell.update();
			if (cell.isAlive()) {
				_livingCellCount++;
			}
		}
	}

	public int getCellsWide() {
		return _cellsWide;
	}

	public int getCellsHigh() {
		return _cellsHigh;
	}

	public int getCellWidth() {
		return CELL_WIDTH;
	}

	public int getCellHeight() {
		return CELL_HEIGHT;
	}

	public CellGrid getCellGrid() {
		return _cellGrid;
	}

	public void setInitializer(GameOfLifeInitializer initializer) {
		_initializer = initializer;
	}

	public int getLivingCellCount() {
		return _livingCellCount;
	}
}

package gameoflife;

import java.util.ArrayList;
import java.util.Collection;

public class CellGrid {

	private class Coordinate {
		int x;
		int y;

		Coordinate(Cell cell) {
			x = cell.getX();
			y = cell.getY();
		}

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void go(Compass compass) {
			Directions[] directions = compass.getDirections();
			for (Directions direction : directions) {
				switch (direction) {
				case UP:
					y -= 1;
					break;
				case DOWN:
					y += 1;
					break;
				case LEFT:
					x -= 1;
					break;
				case RIGHT:
					x += 1;
					break;
				}
			}
		}

		boolean isValid() {
			return (x >= 0 && x < _width && y >= 0 && y < _height);
		}
	}

	private Cell[][] _cells;
	private int _width;
	private int _height;
	private Collection<Cell> _cellCollection;

	public CellGrid(int width, int height) {
		_width = width;
		_height = height;
		_cellCollection = new ArrayList<Cell>();
		initGrid();
	}

	private void initGrid() {
		_cells = new Cell[_width][_height];
		_cellCollection = new ArrayList<Cell>();
		for (int x = 0; x < _width; x++) {
			for (int y = 0; y < _height; y++) {
				Cell c = new Cell(x, y);
				_cells[x][y] = c;
				_cellCollection.add(c);
			}
		}
	}

	public Collection<Cell> getCellCollection() {
		return _cellCollection;
	}

	public Collection<Cell> getNeighbors(Cell cell) {
		Collection<Cell> neighbors = new ArrayList<Cell>();

		for (Compass compass : Compass.values()) {
			Cell neighbor = getNeighbor(cell, compass);
			if (null != neighbor) {
				neighbors.add(neighbor);
			}
		}

		return neighbors;
	}

	private Cell getNeighbor(Cell cell, Compass compass) {
		Coordinate coordinate = new Coordinate(cell);
		coordinate.go(compass);
		return getCell(coordinate);
	}

	private Cell getCell(Coordinate coordinate) {
		if (coordinate.isValid()) {
			return _cells[coordinate.x][coordinate.y];
		}
		return null;
	}

	public Cell getCell(int x, int y) {
		return _cells[x][y];
	}

	public int getHeight() {
		return _height;
	}

	public int getWidth() {
		return _width;
	}

	public void setCellAlive(int x, int y) {
		Cell cell = getCell(new Coordinate(x, y));
		if (cell != null) {
			cell.setState(CellState.SPAWNING);
		}
	}

	public void clearGrid() {
		initGrid();
	}
}

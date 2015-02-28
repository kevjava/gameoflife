package gameoflife;

import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class GridView {

	private int _cellsWide;
	private int _cellsHigh;
	private int _cellWidth;
	private int _cellHeight;

	public GridView(int cellsWide, int cellsHigh, int cellWidth, int cellHeight, int gridWidth, int gridHeight) {
		_cellsWide = cellsWide;
		_cellsHigh = cellsHigh;
		_cellWidth = cellWidth;
		_cellHeight = cellHeight;
	}

	public void draw(Graphics graphics, CellGrid grid, float tickPercentage, Map<String, Object> statistics) {
		// drawGridLines(graphics, grid); // BUG: The grid lines don't work right at the moment.
		drawCells(graphics, grid, tickPercentage);
		drawStatistics(graphics, statistics);
	}

	private void drawStatistics(Graphics graphics, Map<String, Object> statistics) {
		int y = 8;
		for (String name : statistics.keySet()) {
			String message = String.format("%s: %s", name, statistics.get(name).toString());
			graphics.setColor(Color.gray);
			graphics.drawString(message, 8, y);
			y += 20;
		}
	}

	private void drawCells(Graphics graphics, CellGrid grid, float tickPercentage) {
		for (Cell cell : grid.getCellCollection()) {
			int x1 = 1 + (_cellWidth + 1) * cell.getX();
			int y1 = 1 + (_cellHeight + 1) * cell.getY();
			graphics.setColor(Colors.getColor(cell.getState(), tickPercentage));
			graphics.fill(new Rectangle(x1, y1, _cellWidth, _cellHeight));
		}
	}

	private void drawGridLines(Graphics graphics, CellGrid grid) {
		graphics.setColor(new Color(0.1f, 0.1f, 0.1f));
		for (int x = 0; x <= _cellsWide; x++) {
			graphics.drawLine(x * (_cellWidth + 1), 0, x * (_cellWidth + 1), grid.getHeight());
		}

		for (int y = 0; y <= _cellsHigh; y++) {
			graphics.drawLine(0, y * (_cellHeight + 1), grid.getWidth(), y * (_cellHeight + 1));
		}
	}
}

package GameEngine;

import java.util.Set;

public class WireWorld extends Automaton2Dim {

	public WireWorld(CellStateFactory cellStateFactory,	CellNeighborhood cellNeighborhood, int width, int height) {
		super(cellStateFactory, cellNeighborhood, width, height);
	}
	public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
		return new WireWorld(cellStateFactory, cellNeighborhood, height, height);
	}
	@Override
	public CellState nextCellState(Cell currentCell, Set<Cell> neighborState){
		return null;
	}
}

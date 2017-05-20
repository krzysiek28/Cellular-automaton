package GameEngine;

import java.util.Set;

public class LangtonAnt extends Automaton2Dim {
	
	public LangtonAnt(CellStateFactory cellStateFactory,CellNeighborhood cellNeighborhood, int width, int height) {
		super(cellStateFactory, cellNeighborhood, width, height);
	}
	public Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
		return new LangtonAnt(cellStateFactory, cellNeighborhood, height, height);
	}
	@Override
	public CellState nextCellState(Cell currentCell, Set<Cell> neighborState){
		return null;
	}
}

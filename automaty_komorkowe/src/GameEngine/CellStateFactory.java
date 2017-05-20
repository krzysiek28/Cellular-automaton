package GameEngine;

public interface CellStateFactory {
	public CellState initialState(CellCoordinates cellCoordinates);
	
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object obj);
}

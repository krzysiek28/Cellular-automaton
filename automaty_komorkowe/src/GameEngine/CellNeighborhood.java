package GameEngine;
import java.util.Set;


public interface CellNeighborhood {
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cellCoordinates);
	
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object obj);
}

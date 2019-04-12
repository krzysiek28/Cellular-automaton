package neighbours;
import cell.coordinates.CellCoordinates;

import java.util.Set;


public interface CellNeighborhood {

	Set<CellCoordinates> cellNeighbors(CellCoordinates cellCoordinates);
	
	@Override
	int hashCode();

	@Override
	boolean equals(Object obj);
}

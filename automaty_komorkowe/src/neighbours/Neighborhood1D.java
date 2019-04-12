package neighbours;

import cell.coordinates.CellCoordinates;
import cell.coordinates.Coords1D;
import neighbours.CellNeighborhood;

import java.util.HashSet;
import java.util.Set;

public class Neighborhood1D implements CellNeighborhood {

	private int size;
	
	public Neighborhood1D(int size){
		this.size = size;
	}
	
	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cellCoordinates) {
		Set<CellCoordinates> cellNeighs = new HashSet<>();
		Coords1D coord = (Coords1D) cellCoordinates;

		if(coord.getX()-1>=0 && coord.getX()-1<size){
			for(int i = coord.getX()-1; i <= coord.getX()+1; i++)
				cellNeighs.add(new Coords1D(i));
		} else if(coord.getX()-1<0){
			cellNeighs.add(new Coords1D(size-1));
			cellNeighs.add(new Coords1D(coord.getX()));
			cellNeighs.add(new Coords1D(coord.getX()+1));
		} else {
			cellNeighs.add(new Coords1D(coord.getX()-1));
			cellNeighs.add(new Coords1D(coord.getX()));
			cellNeighs.add(new Coords1D(0));
		}
		return cellNeighs;
	}
}

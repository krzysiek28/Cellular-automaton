package GameEngine;

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
		
		//gdy komorka bedzie na skraju mapy to nastepuje zawijanie
		if(coord.x-1>=0 && coord.x-1<size){
			for(int i = coord.x-1; i <= coord.x+1; i++)
				cellNeighs.add(new Coords1D(i));
		}else if(coord.x-1<0){
			cellNeighs.add(new Coords1D(size-1));
			cellNeighs.add(new Coords1D(coord.x));
			cellNeighs.add(new Coords1D(coord.x+1));
		}else {
			cellNeighs.add(new Coords1D(coord.x-1));
			cellNeighs.add(new Coords1D(coord.x));
			cellNeighs.add(new Coords1D(0));
		}
		
		return cellNeighs;
	}

	

}

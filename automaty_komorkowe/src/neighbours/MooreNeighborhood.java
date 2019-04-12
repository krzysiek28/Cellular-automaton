package neighbours;
import cell.coordinates.CellCoordinates;
import cell.coordinates.Coords2D;

import java.util.HashSet;
import java.util.Set;


public class MooreNeighborhood implements CellNeighborhood {
	
	private int radius;
	private boolean mapWrap;
	private int width;
	private int height;
	
	public MooreNeighborhood(int radius, boolean mapWrap, int width, int height){
		this.radius = radius;
		this.mapWrap = mapWrap;
		this.width = width;
		this.height = height;
	}
		
	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cellCoordinates) {
		Set<CellCoordinates> cellNeighs = new HashSet<>();
		Coords2D coords = (Coords2D) cellCoordinates;
		
		int k,l;
		
		for(int j = coords.getY()-radius; j <= coords.getY()+radius; j++){
			for(int i = coords.getX()-radius; i <= coords.getX()+radius; i++){
				if(mapWrap == false){
					if(i>=0 && i<width && j>=0 && j<height){
						if(i != coords.getX() || j != coords.getY()){
							cellNeighs.add(new Coords2D(i,j));
						}
					}
				} else{
					k = i;
					l = j;
					if(i<0)
						k = i+width;
					if(i>=width)
						k = i-width;
					if(j<0)
						l = j+height;
					if(j>=height)
						l = j-height;
					if(k != coords.getX() || l != coords.getY())
						cellNeighs.add(new Coords2D(k,l));					
				}		
			}
		}
		return cellNeighs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + (mapWrap ? 1231 : 1237);
		result = prime * result + radius;
		result = prime * result + width;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MooreNeighborhood other = (MooreNeighborhood) obj;
		if (height != other.height)
			return false;
		if (mapWrap != other.mapWrap)
			return false;
		if (radius != other.radius)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	
}

package GameEngine;
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
		
		for(int j = coords.y-radius; j <= coords.y+radius; j++){
			for(int i = coords.x-radius; i <= coords.x+radius; i++){
				if(mapWrap == false){
					if(i>=0 && i<width && j>=0 && j<height){
						if(i != coords.x || j != coords.y){
							cellNeighs.add(new Coords2D(i,j));
						}
					}
				}
				// z zawijaniem mapy
				else{
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
					if(k != coords.x || l != coords.y)
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

/*
//bez zawijania mapy
else{
	int min_x = coords.x - radius;
	int min_y = coords.y - radius;
	
	//zapewniamy uciecie wartosci przed i nad tablica
	while(min_x < 0){
		min_x++;
	}
	while(min_y < 0){
		min_y++;
	}
	
	//zapewniamy granice sasiadow
	int max_x = coords.x + radius;
	int max_y = coords.y + radius;
	
	while(max_x >= width){
		max_x--;
	}
	while(max_y >= height){
		max_y--;
	}
	
	for(int j = min_y; j <= max_y; j++){
		for(int i = min_x; i <= max_x; i++){
			if(i != coords.x && j != coords.y)
				cellNeighs.add(new Coords2D(i,j));
		}
	}			
} */

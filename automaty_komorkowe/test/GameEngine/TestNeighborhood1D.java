package GameEngine;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestNeighborhood1D {

	@Test
	public void Neighborhood1Dtest() {
		int size = 10;
		Neighborhood1D proNeighs = new Neighborhood1D(size); 
		Set<CellCoordinates> retNeighs = proNeighs.cellNeighbors(new Coords1D(3));
		
		//tworze zbiór s¹siadów
		Set<CellCoordinates> cNeighs = new HashSet<>();
		cNeighs.add(new Coords1D(2));
		cNeighs.add(new Coords1D(3));
		cNeighs.add(new Coords1D(4));
		
		Assert.assertTrue(retNeighs.equals(cNeighs));
	}
	
	@Test
	public void Neighborhood1Dtest2() {
		int size = 10;
		Neighborhood1D proNeighs = new Neighborhood1D(size); 
		Set<CellCoordinates> retNeighs = proNeighs.cellNeighbors(new Coords1D(0));
		
		//tworze zbiór s¹siadów
		Set<CellCoordinates> cNeighs = new HashSet<>();
		cNeighs.add(new Coords1D(9));
		cNeighs.add(new Coords1D(0));
		cNeighs.add(new Coords1D(1));
		
		Assert.assertTrue(retNeighs.equals(cNeighs));
	}

}

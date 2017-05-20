package GameEngine;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestMooreNeighboor {

	@Test
	public void mooreNeighboorTest() {
		int width = 6, height = 5;
		MooreNeighborhood proNeighs = new MooreNeighborhood(1, false, width, height); 
		Set<CellCoordinates> retNeighs = proNeighs.cellNeighbors(new Coords2D(2,0));
		
		//tworze zbiór s¹siadów
		Set<CellCoordinates> cNeighs = new HashSet<>();
		cNeighs.add(new Coords2D(1,0));
		cNeighs.add(new Coords2D(3,0));
		cNeighs.add(new Coords2D(1,1));
		cNeighs.add(new Coords2D(2,1));
		cNeighs.add(new Coords2D(3,1));
		
		/*   ___________
		 * 	|_|_|x|_|_|_|
		 * 	|_|_|_|_|_|_|	
		 *  |_|_|_|_|_|_|
		 *  |_|_|_|_|_|_|
		 *  |_|_|_|_|_|_|
		 */
		Assert.assertTrue(retNeighs.equals(cNeighs));
	}

}

package GameEngine;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestVonNeumanNeighboor {

	@Test
	public void VonNeumanNeighboorTest() {
		int width = 6, height = 5;
		VonNeumanNeighborhood proNeighs = new VonNeumanNeighborhood(1, false, width, height); 
		Set<CellCoordinates> retNeighs = proNeighs.cellNeighbors(new Coords2D(2,0));
		
		
		//tworze zbiór s¹siadów
		Set<CellCoordinates> cNeighs = new HashSet<>();
		cNeighs.add(new Coords2D(1,0));
		cNeighs.add(new Coords2D(3,0));
		cNeighs.add(new Coords2D(2,1));
		
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

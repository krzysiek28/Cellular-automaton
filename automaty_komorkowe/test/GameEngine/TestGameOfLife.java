package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import automaton.Automaton;
import cell.coordinates.CellCoordinates;
import cell.coordinates.Coords2D;
import cell.states.BinaryState;
import cell.states.CellState;
import cell.states.UniformStateFactory;
import games.GameOfLife;
import neighbours.MooreNeighborhood;
import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestGameOfLife {

    @Test
    public void automatonTest() {
        int height = 20, width = 20;
        List<Integer> aliveRules = new ArrayList<Integer>();
        List<Integer> deadRules = new ArrayList<Integer>();
        aliveRules.add(2);
        aliveRules.add(3);
        deadRules.add(3);
        Map<CellCoordinates, CellState> map = new HashMap<>();
            map.put(new Coords2D(1, 10), BinaryState.ALIVE);
            map.put(new Coords2D(2, 10), BinaryState.ALIVE);
            map.put(new Coords2D(3, 10), BinaryState.ALIVE);
 
        Automaton automaton = new GameOfLife(
        		new UniformStateFactory(BinaryState.DEAD),
                new MooreNeighborhood(1, false, width, height),
                width,
                height, aliveRules, deadRules
            );
        automaton.insertStructure(map);      
        
        Automaton automaton2 = new GameOfLife(
        		new UniformStateFactory(BinaryState.DEAD),
                new MooreNeighborhood(1, false, width, height),                
                width,
                height, aliveRules, deadRules
        );
        automaton2.insertStructure(map);
        Assert.assertTrue(automaton.equals(automaton2));
    }
    
    @Test
    public void nextStateTest() {
    	int width = 5, height = 5;
    	 List<Integer> aliveRules = new ArrayList<Integer>();
         List<Integer> deadRules = new ArrayList<Integer>();
         aliveRules.add(2);
         aliveRules.add(3);
         deadRules.add(3);
    	 Map<CellCoordinates, CellState> map = new HashMap<>();
         map.put(new Coords2D(0, 0), BinaryState.ALIVE);
         map.put(new Coords2D(3, 0), BinaryState.ALIVE);
         map.put(new Coords2D(1, 1), BinaryState.ALIVE);
         map.put(new Coords2D(2, 1), BinaryState.ALIVE);
         map.put(new Coords2D(3, 2), BinaryState.ALIVE);
         map.put(new Coords2D(1, 3), BinaryState.ALIVE);
         map.put(new Coords2D(3, 3), BinaryState.ALIVE);
         map.put(new Coords2D(4, 4), BinaryState.ALIVE);
    	
         /*  _________
     	 * 	|x|_|_|x|_|
     	 * 	|_|x|x|_|_|	
     	 *  |_|_|_|x|_|
     	 *  |_|x|_|x|_|
     	 *  |_|_|_|_|x|
     	 */
         
    	Automaton automaton = new GameOfLife(
        		new UniformStateFactory(BinaryState.DEAD),
                new MooreNeighborhood(1, false, width, height),
                width,
                height, aliveRules, deadRules
            );
    	automaton.insertStructure(map);
    	automaton = automaton.nextState();
    	
    	 /*  _________
     	 * 	|_|x|x|_|_|
     	 * 	|_|x|x|x|_|	
     	 *  |_|x|_|x|_|
     	 *  |_|_|x|x|x|
     	 *  |_|_|_|_|_|
     	 */
    	
    	Map<CellCoordinates, CellState> map2 = new HashMap<>();
        map2.put(new Coords2D(1, 0), BinaryState.ALIVE);
        map2.put(new Coords2D(2, 0), BinaryState.ALIVE);
        map2.put(new Coords2D(1, 1), BinaryState.ALIVE);
        map2.put(new Coords2D(2, 1), BinaryState.ALIVE);
        map2.put(new Coords2D(3, 1), BinaryState.ALIVE);
        map2.put(new Coords2D(1, 2), BinaryState.ALIVE);
        map2.put(new Coords2D(3, 2), BinaryState.ALIVE);
        map2.put(new Coords2D(2, 3), BinaryState.ALIVE);
        map2.put(new Coords2D(3, 3), BinaryState.ALIVE);
        map2.put(new Coords2D(4, 3), BinaryState.ALIVE);
        
        Automaton automaton2 = new GameOfLife(
        		new UniformStateFactory(BinaryState.DEAD),
                new MooreNeighborhood(1, false, width, height),
                width,
                height, aliveRules, deadRules
            );
    	automaton2.insertStructure(map2);
    	
    //	Assert.assertTrue(automaton.cells.equals(automaton2.cells));
    	Assert.assertTrue(mapEquality(automaton.cells,automaton2.cells));
    	
    }
    boolean mapEquality(Map<CellCoordinates, CellState> map1, Map<CellCoordinates, CellState> map2){
    			for (Map.Entry<CellCoordinates, CellState> entry : map1.entrySet()){
    			    if (!map2.get(entry.getKey()).equals(entry.getValue()))
    			    	    return false;
    			}
				return map1.size() == map2.size();
    }
}


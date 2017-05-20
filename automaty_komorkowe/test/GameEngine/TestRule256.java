package GameEngine;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestRule256 {


    @Test
    public void nextStateTest() {
    	int size = 10;
    	int rule = 30;

    	Map<CellCoordinates, CellState> map = new HashMap<>();
        map.put(new Coords1D(5), BinaryState.ALIVE);
    	      
    	Automaton automaton = new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(size),
                size,
                rule
            );
    	automaton.insertStructure(map);
    	automaton = automaton.nextState();
    	

    	Map<CellCoordinates, CellState> map2 = new HashMap<>();
        map2.put(new Coords1D(4), BinaryState.ALIVE);
        map2.put(new Coords1D(5), BinaryState.ALIVE);
        map2.put(new Coords1D(6), BinaryState.ALIVE);

        Automaton automaton2 = new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(size),
                size,
                rule
            );
    	automaton2.insertStructure(map2);
    	
    //	Assert.assertTrue(automaton.cells.equals(automaton2.cells));
    	Assert.assertTrue(mapEquality(automaton.cells,automaton2.cells));  	
    }
    
    @Test
    public void nextStateTest2() {
    	int size = 9;
    	int rule = 50;

    	Map<CellCoordinates, CellState> map = new HashMap<>();
        map.put(new Coords1D(5), BinaryState.ALIVE);
        map.put(new Coords1D(7), BinaryState.ALIVE);

    	      
    	Automaton automaton = new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(size),
                size,
                rule
            );
    	automaton.insertStructure(map);
    	automaton = automaton.nextState();
    	

    	Map<CellCoordinates, CellState> map2 = new HashMap<>();
        map2.put(new Coords1D(4), BinaryState.ALIVE);
        map2.put(new Coords1D(6), BinaryState.ALIVE);
        map2.put(new Coords1D(8), BinaryState.ALIVE);


        Automaton automaton2 = new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(size),
                size,
                rule
            );
    	automaton2.insertStructure(map2);
    	
    //	Assert.assertTrue(automaton.cells.equals(automaton2.cells));
    	Assert.assertTrue(mapEquality(automaton.cells,automaton2.cells));  	
    }
    
    @Test
    public void nextStateTest3() {
    	int size = 10;
    	int rule = 30;

    	Map<CellCoordinates, CellState> map = new HashMap<>();
        map.put(new Coords1D(3), BinaryState.ALIVE);
        map.put(new Coords1D(4), BinaryState.ALIVE);
        map.put(new Coords1D(5), BinaryState.ALIVE);
    	      
    	Automaton automaton = new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(size),
                size,
                rule
            );
    	automaton.insertStructure(map);
    	automaton = automaton.nextState();
    	

    	Map<CellCoordinates, CellState> map2 = new HashMap<>();
        map2.put(new Coords1D(2), BinaryState.ALIVE);
        map2.put(new Coords1D(3), BinaryState.ALIVE);
        map2.put(new Coords1D(6), BinaryState.ALIVE);

        Automaton automaton2 = new Rule256(
        		new UniformStateFactory(BinaryState.DEAD),
                new Neighborhood1D(size),
                size,
                rule
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

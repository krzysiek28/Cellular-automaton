package games;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeRules {
	
	public List<Integer> convertStringToListAlive(String isAlive) {
		List<Integer> aliveRules = new ArrayList<Integer>();
		String[] parts = isAlive.split(",");

		for(int i=0; i<parts.length; i++){
			aliveRules.add(Integer.parseInt(parts[i]));
		}		
		return aliveRules;
	}

	public List<Integer> convertStringToListDead(String isDead) {
		List<Integer> deadRules = new ArrayList<Integer>();
		String[] parts = isDead.split(",");

		for(int i=0; i<parts.length; i++){
			deadRules.add(Integer.parseInt(parts[i]));
		}		
		return deadRules;
	}
}

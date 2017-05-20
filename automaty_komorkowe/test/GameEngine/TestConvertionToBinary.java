package GameEngine;

import org.junit.Test;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class TestConvertionToBinary {

	@Test
	public void test() {
		
		int[] numberOfRule = new int[8];
		for(int i=0; i<8; i++)
			numberOfRule[i] = 0;
		int number = 50;
		//konwersja typu np 30 = 00011110
		int i = 7;
		while(number != 0){
			numberOfRule[i--] = number % 2;
			number /= 2;
		}
		
		int[] check = {0,0,1,1,0,0,1,0};
		
		Assert.assertTrue(mapEquality(numberOfRule,check));  
	}

	private boolean mapEquality(int[] numberOfRule, int[] check) {

		for(int i = 0; i<8; i++)
			if(numberOfRule[i]!=check[i])
				return false;
		return true;
	}

}

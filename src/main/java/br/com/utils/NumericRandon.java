package br.com.utils;

public class NumericRandon {
	
public static class Numeric{
		
		
		private static final String NUMERI_STRING = "0123456789";
		public static String randomNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*NUMERI_STRING.length());
		builder.append(NUMERI_STRING.charAt(character));
		}
		return builder.toString();
		}	
		
	}

}

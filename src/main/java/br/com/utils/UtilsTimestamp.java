package br.com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsTimestamp {
	
	public static long geraTimestamp() {
		Date date = new Date();
		return date.getTime() / 1000;

	}
	

	
	public static long timestamoComMillisegundos() {
		Date date = new Date();
		return date.getTime();

	}
	
	public static void main(String []args) {
	String padrao = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(padrao);

	String date = simpleDateFormat.format(new Date());
	System.out.println(date);
	}
}

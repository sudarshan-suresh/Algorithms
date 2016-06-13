package util;

import java.io.BufferedInputStream;
import java.io.IOException;

import javafx.animation.FillTransitionBuilder;

public final class BinaryStdIn {

	private static BufferedInputStream in = new BufferedInputStream(System.in);
	private static final int EOF = -1;
	
	private static int buffer;  //1 character buffer
	private static int n; //no.of bits left in buffer
	
	static {
		fillBuffer();
	}
	
	private  BinaryStdIn(){};
	
	private static void fillBuffer(){
		try{
			buffer = in.read();
			n= 8;
		}catch (IOException e){
			System.out.println("EOF");
			buffer = EOF;
			n = -1;
		}
	}
	
	public static void close(){
		try{
			in.close();
		} catch(IOException e){
			throw new RuntimeException("couldn't close BinaryStdIn");
		}
	}
	
	public static boolean isEmpty(){
		return buffer == EOF;
	}
	
	public static boolean readBoolean(){
		if(isEmpty()){
			throw new RuntimeException("Reading from empyt inputStream");
		}
		n--;
		boolean bit = ((buffer >> n) & 1) == 1;
		if(n== 0){
			fillBuffer();
		}
		return bit;
	}
	
	
	public static char readChar(){
		if (isEmpty()){
			throw new RuntimeException();			
		}
		//special case when aligned byte
		if( n == 8){
			int x = buffer;
			fillBuffer();
			return (char) (x & 0xff);
		}
		//combine last n bits of new Buffer with first n bits of new Buffer.
		int x = buffer;
		x <<= (8-n);
		int oldN = n;
		fillBuffer();
		if(isEmpty()) throw new RuntimeException("Reading from input Stream");
		n = oldN;
		x |= (buffer >>> n);
		return (char) (x & 0xff);
	}
}

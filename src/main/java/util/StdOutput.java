package util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public final class StdOutput {

	private static final String CHARSET_NAME = "UTF-8";

	// assume language = English, country = US for consistency with StdIn
	private static final Locale LOCALE = Locale.US;

	private static PrintWriter out = null;

	static {
		try {
			out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

	private StdOutput() {

	}

	public static void close() {
		out.close();
	}

	public static void println() {
		out.println();
	}

	public static void prinltn(Object x) {
		out.println(x);
	}

	public static void println(char x) {
		out.println(x);
	}

	public static void println(int x) {
		out.println(x);
	}

	public static void println(double x) {
		out.println(x);
	}

	public static void println(long x) {
		out.println(x);
	}

	public static void println(short x) {
		out.println(x);
	}

	public static void println(byte x) {
		out.println(x);
	}

	public static void println(float x) {
		out.println(x);
	}

	public static void println(boolean x) {
		out.println(x);
	}

	public static void print() {
		out.flush();
	}

	public static void print(Object x) {
		out.println(x);
		out.flush();
	}

	public static void print(char x) {
		out.println(x);
		out.flush();
	}

	public static void print(int x) {
		out.println(x);
		out.flush();
	}

	public static void print(double x) {
		out.println(x);
		out.flush();
	}

	public static void print(long x) {
		out.println(x);
		out.flush();
	}

	public static void print(short x) {
		out.println(x);
		out.flush();
	}

	public static void print(byte x) {
		out.println(x);
		out.flush();
	}

	public static void print(float x) {
		out.println(x);
		out.flush();
	}

	public static void print(boolean x) {
		out.println(x);
		out.flush();
	}

	public static void printf(Locale locale, String format, Object... args) {
		out.printf(locale, format, args);
		out.flush();
	}

	public static void printf(String format, Object... args) {
		out.printf(LOCALE, format, args);
		out.flush();
	}

	public static void main(String[] args) {
		System.out.println("Hi");
		System.out.println(25);
		System.out.println(true);
		System.out.printf("%.5f\n", 1.0 / 7.0);

	}

}

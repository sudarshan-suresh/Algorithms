package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Locale;

public class Out {

	private static final String CHARSET_NAME = "UTF-8";
	private static final Locale LOCALE = Locale.US;
	private PrintWriter out;

	public Out(OutputStream os) {
		OutputStreamWriter osw;
		try {
			osw = new OutputStreamWriter(os, CHARSET_NAME);
			out = new PrintWriter(osw, true);
		} catch (UnsupportedEncodingException e) {
			StdOutput.prinltn(e);
		}

	}

	public Out() {
		this(System.out);
	}

	public Out(Socket socket) {
		try {
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
			out = new PrintWriter(osw, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Out(String fileName) {
		try {
			OutputStream os = new FileOutputStream(fileName);
			OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
			out = new PrintWriter(osw, true);
		} catch (Exception e) {
			out.println(e);
		}
	}

	public void close() {
		out.close();
	}

	public void println() {
		out.println();
	}

	public void println(Object x) {
		out.println(x);
	}

	public void println(boolean x) {
		out.println(x);
	}

	public void println(char x) {
		out.println(x);
	}

	public void println(double x) {
		out.println(x);
	}

	public void println(float x) {
		out.println(x);
	}

	public void println(int x) {
		out.println(x);
	}

	public void println(long x) {
		out.println(x);
	}

	public void println(byte x) {
		out.println(x);
	}

	public void print() {
		out.flush();
	}

	public void print(boolean x) {
		out.print(x);
		out.flush();
	}

	public void print(char x) {
		out.print(x);
		out.flush();
	}

	public void print(double x) {
		out.print(x);
		out.flush();
	}

	public void print(float x) {
		out.print(x);
		out.flush();
	}

	public void print(long x) {
		out.print(x);
		out.flush();
	}

	public void print(byte x) {
		out.print(x);
		out.flush();
	}

	public void printf(String format, Object... args) {
		out.printf(LOCALE, format, args);
		out.flush();
	}

	public void printf(Locale locale, String format, Object... args) {
		out.printf(locale, format, args);
		out.flush();
	}

	public static void main(String[] args) {
		Out out = null;
		out = new Out();
		out.println("Hello how are you");
		out.close();

		out = new Out("test.txt");
		out.println("Hey I'm gone write inside a file");
		out.close();
	}
}

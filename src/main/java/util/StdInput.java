/**
 * compile :- javac StdInput.java
 * execution:- java StdInput
 * Dependencies:- None
 * 
 */
package util;

import java.io.BufferedInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class is written as a wrapper class for reading input from STDIN. This
 * code has the same logic used in "introcs.cs.princeton.edu" And this code is
 * written for educational purpose.
 * 
 * @author sudarshans
 *
 */
public final class StdInput {

	private static final String CHARSET_NAME = "UTF-8";
	private static Scanner scanner = null;
	private static final Locale LOCALE = Locale.US;
	private static final Pattern WHITE_SPACE = Pattern.compile("\\p{javaWhitespace}+");
	private static final Pattern EMPTY_PATTERN = Pattern.compile("");
	private static final Pattern READ_EVERYTHING = Pattern.compile("\\A");

	// doesn't make sense to instantiate the class.
	private StdInput() {

	}

	static {
		resync();
	}

	private static void resync() {
		setScanner(new Scanner(new BufferedInputStream(System.in), CHARSET_NAME));

	}

	private static void setScanner(Scanner scanner) {
		StdInput.scanner = scanner;
		StdInput.scanner.useLocale(LOCALE);
	}

	public static boolean isEmpty() {
		return !scanner.hasNext();
	}

	public static boolean hasNextLine() {
		return scanner.hasNextLine();
	}

	public static boolean hasNextChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		boolean result = scanner.hasNext();
		scanner.useDelimiter(WHITE_SPACE);
		return result;
	}

	public static String readLine() {
		String line;
		try {
			line = scanner.nextLine();
		} catch (NoSuchElementException e) {
			line = null;
		}
		return line;
	}

	public static char readChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		String ch = scanner.next();
		assert ch.length() == 1 : "Internal (Std) In.readchar error!";
		scanner.useDelimiter(WHITE_SPACE);
		return ch.charAt(0);
	}

	public static String readAll() {
		if (!scanner.hasNextLine()) {
			return "";
		}
		String result = scanner.useDelimiter(READ_EVERYTHING).next();
		scanner.useDelimiter(WHITE_SPACE);
		return result;
	}

	public static String readString() {
		return scanner.next();
	}

	public static int readInt() {
		return scanner.nextInt();
	}

	public static double readDouble() {
		return scanner.nextDouble();
	}

	public static float readFloat() {
		return scanner.nextFloat();
	}

	public static long readLong() {
		return scanner.nextLong();
	}

	public static short readShort() {
		return scanner.nextShort();
	}

	public static byte readByte() {
		return scanner.nextByte();
	}

	public static boolean readBoolean() {
		String s = readString();
		if (s.equalsIgnoreCase("true")) {
			return true;
		}
		if (s.equalsIgnoreCase("false")) {
			return false;
		}
		if (s.equals("1")) {
			return true;
		}
		if (s.equals("0")) {
			return false;
		}
		throw new InputMismatchException();
	}

	public static String[] readAllStrings() {
		String[] tokens = WHITE_SPACE.split(readAll());
		if (tokens.length == 0 | tokens[0].length() > 0) {
			return tokens;
		}
		String[] decapTokens = new String[tokens.length - 1];
		for (int i = 0; i < tokens.length - 1; i++) {
			decapTokens[i] = tokens[i + 1];

		}
		return decapTokens;
	}

	public static String[] readAllLines() {
		ArrayList<String> lines = new ArrayList<String>();
		while (hasNextLine()) {
			lines.add(readLine());
		}
		return lines.toArray(new String[0]);
	}

	public static int[] readAllInts() {
		String[] fields = readAllStrings();
		int[] vals = new int[fields.length];
		for (int i = 0; i < fields.length; i++) {
			vals[i] = Integer.parseInt(fields[i]);
		}
		return vals;
	}

	public static double[] readAllDoubles() {
		String[] fields = readAllStrings();
		double[] vals = new double[fields.length];
		for (int i = 0; i < fields.length; i++) {
			vals[i] = Double.parseDouble(fields[i]);
		}
		return vals;
	}

	public static int[] readInts() {
		return readAllInts();
	}

	public static double[] readDoubles() {
		return readAllDoubles();
	}

	public static String[] readStrings() {
		return readAllStrings();
	}

	public static void main(String[] args) {
		System.out.println("Enter a string");
		String s = StdInput.readString();
		System.out.println(s);
	}
}

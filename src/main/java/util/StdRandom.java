package util;

import java.util.Random;

public final class StdRandom {

	private static Random random = null;
	private static long seed = 0l;

	static {
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	private StdRandom() {

	}

	public static void setSeed(long l) {
		seed = l;
		random = new Random(seed);

	}

	public static long getSeed() {
		return seed;
	}

	public static double uniform() {
		return random.nextDouble();
	}

	public static int uniform(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("N must be +ve number");
		}
		return random.nextInt(n);
	}

	public static double random() {
		return uniform();
	}

	public static int random(int a, int b) {
		if (b <= a) {
			throw new IllegalArgumentException("invalid Range");
		}
		if ((long) b - a >= Integer.MAX_VALUE) {
			throw new IllegalArgumentException("invalid Range");
		}
		return a + uniform(b - a);
	}

	public static double uniform(double a, double b) {
		if (!(a < b)) {
			throw new IllegalArgumentException("invalid Range");
		}
		return a + random() * (b - a);
	}

	public static boolean bernoulli(double p) {
		if (!(p >= 0.0 && p <= 1.0)) {
			throw new IllegalArgumentException("invalid range");
		}
		return uniform() < p;
	}

	public static boolean bernoulli() {
		return bernoulli(0.5);
	}

	public static double gaussian() {
		double x, y, z;

		do {
			x = uniform(-1.0, 1.0);
			y = uniform(-1.0, 1.0);
			z = x * x + y * y;
		} while (z >= 1 || z == 0);

		return x * Math.sqrt(-2 * Math.log(z) / z);
	}

	public static double gaussian(double mu, double sigma) {
		return mu + sigma * gaussian();
	}

	public static int geometric(double p) {
		if (!(p >= 0.0 && p <= 1.0)) {
			throw new IllegalArgumentException("probability must be b/w 0.0 and 1.0");
		}

		return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p));
	}

	public static int poisson(double lambda) {
		if (!(lambda > 0.0)) {
			throw new IllegalArgumentException("lambda must be +ve");
		}
		if (Double.isInfinite(lambda)) {
			throw new IllegalArgumentException("lambda must not be infinite");
		}
		int k = 0;
		double p = 1.0;
		double l = Math.exp(-lambda);
		do {
			k++;
			p *= uniform();
		} while (p >= l);

		return k - 1;
	}

	public static double pareto() {
		return pareto(1.0);
	}

	public static double pareto(double alpha) {
		if (!(alpha > 0.0)) {
			throw new IllegalArgumentException();
		}
		return Math.pow(1 - uniform(), -1.0 / alpha) - 1.0;
	}

	public static double cauchy() {
		return Math.tan(Math.PI * (uniform() - 0.5));
	}

	public static int discrete(double[] probabilities) {
		if (probabilities == null) {
			throw new NullPointerException("argument array is null");
		}
		double EPSILON = 1E-14;
		double sum = 0.0;
		for (int i = 0; i < probabilities.length; i++) {
			if (!((probabilities[i] >= 0.0))) {
				throw new IllegalArgumentException("array entry must be non negative");
			}
			sum += probabilities[i];
		}
		if (sum > 1.0 + EPSILON || sum < 1.0 - EPSILON) {
			throw new IllegalArgumentException("sum of array entries doesn't equal to 1.0");
		}

		while (true) {
			double r = uniform();
			sum = 0.0;
			for (int i = 0; i < probabilities.length; i++) {
				sum += probabilities[i];
				if (sum > r) {
					return i;
				}
			}
		}
	}

	public static int discrete(int[] frequencies) {
		if (frequencies == null) {
			throw new NullPointerException("array can't be null");
		}
		long sum = 0;
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] < 0) {
				throw new IllegalArgumentException("array field values can't be negative");
			}
			sum += frequencies[i];
		}

		if (sum == 0) {
			throw new IllegalArgumentException("sum has to be greaqter than 0");
		}
		if (sum >= Integer.MAX_VALUE) {
			throw new IllegalArgumentException("sum overflows an int");
		}

		double r = uniform((int) sum);
		sum = 0;
		for (int i = 0; i < frequencies.length; i++) {
			sum += frequencies[i];
			if (sum > r) {
				return i;
			}
		}
		// can't reach here.
		assert false;
		return -1;
	}

	public static double exp(double lambda) {
		if (!(lambda > 0.0)) {
			throw new IllegalArgumentException("lambda must be +ve");
		}
		return -Math.log(1 - uniform()) / lambda;
	}

	public static void shuffle(double[] a) {
		if (a == null) {
			throw new NullPointerException("array can't be null");
		}
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int r = i + uniform(n - i);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void shuffle(Object[] a) {
		if (a == null) {
			throw new NullPointerException("array can't be null");
		}
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int r = i + uniform(n - i);
			Object temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void shufle(int[] a) {
		if (a == null) {
			throw new NullPointerException("array can't be null");
		}
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int r = i + uniform(n - i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void shuffle(Object[] a, int lo, int hi) {
		if (a == null) {
			throw new NullPointerException("array can't be null");
		}
		if (lo < 0 || lo > hi || hi >= a.length) {
			throw new IndexOutOfBoundsException("Illegal SubArray range");
		}
		for (int i = lo; i <= hi; i++) {
			int r = i + uniform(hi - i + 1);
			Object temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void shuffle(double[] a, int lo, int hi) {
		if (a == null) {
			throw new NullPointerException("array can't be null");
		}
		if (lo < 0 || lo > hi || hi >= a.length) {
			throw new IndexOutOfBoundsException("Illegal size for subarray");
		}
		for (int i = lo; i <= hi; i++) {
			int r = i + uniform(hi - i + 1);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void shuffle(int[] a, int lo, int hi) {
		if (a == null) {
			throw new NullPointerException("array can't be null");
		}
		if (lo < 0 || lo > hi || hi >= a.length) {
			throw new IndexOutOfBoundsException("Illegal Subarray access");
		}
		for (int i = lo; i <= hi; i++) {
			int r = i + uniform(hi - i + 1);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		if (args.length == 2) {
			StdRandom.setSeed(Long.valueOf(args[1]));
		}
		double[] probabilities = { 0.5, 0.3, 0.1, 0.1 };
		int[] frequencies = { 5, 3, 1, 1 };
		String[] a = "A B C D E F G".split(" ");
		StdOutput.prinltn("seed =" + StdRandom.getSeed());

		for (int i = 0; i < n; i++) {
			StdOutput.printf("%2d", uniform(100));
			System.out.print(" ");
			StdOutput.printf("%8.5f", uniform(10.0, 99.0));
			System.out.print(" ");
			StdOutput.printf("%5b ", bernoulli(0.5));
			System.out.print(" ");
			StdOutput.printf("%7.5f", gaussian(9.0, 0.2));
			System.out.print(" ");
			StdOutput.printf("%1d", discrete(probabilities));
			System.out.print(" ");
			StdOutput.printf("%1d", discrete(frequencies));
			StdRandom.shuffle(a);
			for (String s : a) {
				StdOutput.print(s);
			}
			StdOutput.println();
		}
	}
}

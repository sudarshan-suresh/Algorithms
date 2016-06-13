package util;

public class StopWatch {

	private static long start = 0l;

	public StopWatch() {
		start = System.currentTimeMillis();
	}

	public double elappsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		StopWatch stop1 = new StopWatch();
		double sum = 0.0;
		for (int i = 0; i < n; i++) {
			sum += Math.sqrt(i);
		}
		
		double time1 = stop1.elappsedTime();

		StdOutput.printf("%e (%.2f) seconds \n", sum, time1);
	}

}

package Sorting;

public class InsertionSort {

	private static int[] num = { 8, 2, 4, 9, 3, 6 };
	private static int length;

	public static void main(String[] args) {
		printArray(num);
		insertSort(num, true);
		printArray(num);
		insertSort(num, false);
		printArray(num);
	}

	static void printArray(int[] arr) {
		length = arr.length;
		for (int i = 0; i < length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void insertSort(int[] arr, boolean asc) {

		for (int j = 1; j < length; j++) {
			int key = arr[j];
			int i = j - 1;
			// ascending
			if (asc) {
				while (i >= 0 && arr[i] > key) {
					arr[i + 1] = arr[i];
					i--;
				}
			} else {
				while (i >= 0 && arr[i] < key) {
					arr[i + 1] = arr[i];
					i--;
				}
			}
			arr[i + 1] = key;

		}
	}
}

package operator;

public class ShiftOperator {

	public static void main(String[] args){
		int a  = 4;
		System.out.println(a);
		a >>= 1; // divides by 2  "n" time here n is one
		System.out.println(a);
		a <<= 1; //multiplies by 2 "n" time here n is 1
		System.out.println(a);
		a >>>= 2;
		System.out.println(a);
		
		 int bitmask = 0x000F;
	        int val = 0x2222;
	        System.out.println(val);
	        System.out.println(bitmask);
	        // prints "2"
	        System.out.println(val & bitmask);
	}
}

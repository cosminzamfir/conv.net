package test;

public class Factor {

	public static void main(String[] args) {
		int n = 3674275;
		for (int i = 200; i < 500; i++) {
			for (int j = 200; j < 500; j++) {
				for (int k = 200; k < 500; i++) {
					if (i*j*k - n == 0) {
						System.out.println(i + "," + j + "," + k);
					}
				}
			}

		}
	}
}

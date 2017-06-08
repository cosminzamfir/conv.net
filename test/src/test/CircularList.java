package test;

import java.util.ArrayList;
import java.util.List;

public class CircularList {

	public static void main(String[] args) {
		int n = 20;
		List<Integer> l = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			l.add(i+1);
		}
		System.out.println(l);
		int i1 = 3;
		int delta = 125;
		int newPosition = move(l, i1, delta);
		System.out.println(newPosition);
	}

	/**
	 * 
	 * @param l a circular list 
	 * @param i1 the initial position
	 * @param delta the numver of positions to move. negative values of the left direction
	 * @return the new position
	 */
	public static int move(List<Integer> l, int i1, int delta) {
		int n = l.size();
		int res = i1 + delta;
		return res >= 0 ? 0 + res % n : (n-1) - ((-res - 1) % n); 
	}
}

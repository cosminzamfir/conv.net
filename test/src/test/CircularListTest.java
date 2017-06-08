package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CircularListTest {

	private static final int N = 10;
	private static List<Integer> l; 

	static {
		l = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			l.add(i);
		}
	}
	
	private void assertInternal(List<Integer> l, int initialPosition, int delta, int finalPosition) {
		int f = CircularList.move(l, initialPosition, delta);
		Assert.assertEquals("Unexpected final position", finalPosition, f);
	}
	
	@Test
	public void test1() throws Exception {
		assertInternal(l, 0, 0, 0);
		assertInternal(l, 0, 1, 1);
		assertInternal(l, 0, 2, 2);
		assertInternal(l, 0, 10, 0);
		assertInternal(l, 0, 15, 5);
		assertInternal(l, 0, 20, 0);
		
		assertInternal(l, 0, -1, 9);
		assertInternal(l, 0, -2, 8);
		assertInternal(l, 0, -11, 9);
	}
}

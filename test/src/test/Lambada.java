package test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 */

public class Lambada {

	public static void main(String[] args) {

		List<String> data = Arrays.asList("A", "C", null, "B", "", null);
		System.out.println("Source : \n" + data);

		//sort non-null element in a separate list
		List<String> sorted = data.stream().filter(x -> x != null && !x.isEmpty()).collect(Collectors.toList()); 
		sorted.sort((c1,c2) -> c1.compareTo(c2));
		
		//merge the sorted list with the original
		Iterator<String> iter = sorted.iterator();
		data = data.stream().map(e -> e == null || e.isEmpty() ? e : iter.next()).collect(Collectors.toList());
		System.out.println("Sort non-null and non-empty elements; keep null elements in their place: \n" + data);
	}
	
}

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class OthersTest {
	@Test
	public void testArrayListInitialCapacity() {
		int initCap = 5;
		ArrayList<Double> arrayList = new ArrayList<Double>(initCap);
		System.out.println(arrayList.size());
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}
	}
}

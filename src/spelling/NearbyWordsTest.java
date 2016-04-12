package spelling;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NearbyWordsTest {
	
	NearbyWords n;

	@Before
	public void setUp() throws Exception {
		n = new NearbyWords(new DictionaryBST());
		DictionaryLoader.loadDictionary(n.dict, "data/dict.txt");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNearbyWords() {
		fail("Not yet implemented");
	}

	@Test
	public void testDistanceOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubsitution() {
		System.out.println("Off by one: (words only)\n"+n.distanceOne("Hello", true));
		System.out.println("Off by one: (anything goes)\n"+n.distanceOne("Hello", false));
	}

	@Test
	public void testInsertions() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletions() {
		fail("Not yet implemented");
	}

	@Test
	public void testSuggestions() {
		fail("Not yet implemented");
	}

}

/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			emptyList.remove(0);
			fail("Empty list allowed removal");
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.remove(-1);
			fail("Empty list allowed removal");
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.remove(2);
			fail("Empty list allowed removal");
		} catch (IndexOutOfBoundsException e) {}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        shortList.add("C");
		assertEquals("Added at end.", shortList.get(shortList.size()-1), "C");
		emptyList.add(100);
		assertEquals("Integer added to empty.", (Integer)100, emptyList.get(emptyList.size()-1));
        assertTrue("Wrong return type", longerList.add(2000));
        try {
        	longerList.add(null);
        	fail("Added null to end of the list");
        } catch (NullPointerException e) {}

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals(shortList.size(), 2);
		shortList.remove(shortList.get(shortList.size()-1));
		assertEquals(shortList.size(), 1);
		assertEquals(emptyList.size(), 0);
		assertEquals(longerList.size(), LONG_LIST_LENGTH);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        shortList.add(1, "C");
        assertEquals("Preceding node incorrect", shortList.get(0), "A");
        assertEquals("Following node incorrect", shortList.get(2), "B");
        assertEquals("Inserted node incorrect", shortList.get(1), "C");
		
        emptyList.add(0, 100);
        assertEquals("Failed added to index 0 of empty list", (Integer)100, emptyList.get(0));
        
        try {
        	longerList.add(-1, 100);
        	fail("Didn't detect OOB < 0");
        } catch (IndexOutOfBoundsException e) {}
        
        try {
        	longerList.add(longerList.size()+1, 1000);
        	fail("Didn't detect OOB > size");
        } catch (IndexOutOfBoundsException e) {}
        longerList.add(longerList.size(), 10);
        assertEquals("Incorrectly added to end", longerList.get(10), (Integer) 10);
        longerList.add(5, 500);
        assertEquals("Incorrectly added in middle", longerList.get(5), (Integer)500);
        assertEquals("Didn't slide down", longerList.get(6),(Integer)5);
        try {
        	longerList.add(0, null);
        	fail("Didn't detect adding null to middle");
        } catch (NullPointerException e) {}

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    String replaced = shortList.set(1, "C");
	    assertEquals("Wrong returned value", "B", replaced);
	    try {
	    	shortList.set(-1, "C");
	    	fail("Allowed to set values OOB < 0");
	    } catch (IndexOutOfBoundsException e) {}
	    try {
	    	shortList.set(2, "C");
	    	fail("Allowed to set values OOB >= size");
	    } catch (IndexOutOfBoundsException e) {}
	    try {
	    	emptyList.set(0, 1000);
	    	fail("Allowed to set values on empty list");
	    } catch (IndexOutOfBoundsException e) {}
        try {
        	shortList.set(1, null);
        	fail("Did not catch setting element to null");
        } catch (NullPointerException e) {}
	}
	
	
	@Test
	public void testAddRemoveAdd() {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(0, 1);
		list.remove(0);
		list.add(0, 1);
		assertEquals("Wrong number elements", 1, list.size());
	}
	
}

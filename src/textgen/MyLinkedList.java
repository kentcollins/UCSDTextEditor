package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		tail.prev = head;
		head.next = tail;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		if (element == null) throw new NullPointerException("Added null to the end.");
		new LLNode<E>(element, tail.prev, tail);
		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The index " + index + " is not within bounds.");
		}
		LLNode<E> curr = null;
		if (index < size / 2) {
			// closer to the head
			int i = 0;
			curr = head.next;
			while (i < index) {
				i++;
				curr = curr.next;
			}
		} else {
			int i = size - 1;
			curr = tail.prev;
			while (i > index) {
				i--;
				curr = curr.prev;
			}
		}
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element)
	{
		if (element == null)
			throw new NullPointerException("Can't add a null element");
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("The index " + index + " cannot be used to add an element");
		} else if (index == size) {
			add(element);
			return;
		}
		LLNode<E> target = nodeAtIndex(index);
		new LLNode<E>(element, target.prev, target);
		size++;
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The item at index " + index + " cannot be removed.");
		}
		LLNode<E> target = nodeAtIndex(index);
		target.prev.next = target.next;
		target.next.prev = target.prev;
		size--;
		return target.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (element == null)
			throw new NullPointerException("Can't set to null");
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The item at index " + index + " cannot be set.");
		}
		LLNode<E> target = nodeAtIndex(index);
		E data = get(index);
		new LLNode<E>(element, target.prev, target.next);
		return data;
	}

	private LLNode<E> nodeAtIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The index " + index + " does not point to a valid node.");
		}
		int i = 0;
		LLNode<E> curr = head.next;
		while (i < index) {
			curr = curr.next;
			i++;
		}
		return curr;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode<E> before, LLNode<E> after) {
		this(e);
		prev = before;
		next = after;
		before.next = this;
		after.prev = this;
	}

}

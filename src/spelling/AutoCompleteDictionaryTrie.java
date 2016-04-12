package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		TrieNode node = root;
		String lower = word.toLowerCase();
		if (isWord(lower)) return false;
		char[] letters = lower.toCharArray();
		for (int i = 0; i<letters.length; i++) {
			if (node.getChild(letters[i])==null){
				node.insert(letters[i]);
			}
			node = node.getChild(letters[i]);
		}
	    node.setEndsWord(true);
	    size++;
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		if (s.equals("")) return false;
		String lower = s.toLowerCase();
	    TrieNode node = root;
	    for (char c:lower.toCharArray()){
	    	if (node.getValidNextCharacters().contains(c)){
		    	node = node.getChild(c);
	    	} else break;
	    }
	    if (node!= null && node.getText().equals(lower)&& node.endsWord()) return true;
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode curr = root;
    	 String stem = prefix.toLowerCase();
    	 char[] letters = stem.toCharArray();
    	 for (int i = 0; i<letters.length; i++) {
    		 if (curr.getText().equals(stem)) {//stem found
    			 break;
    		 }
    		 if (curr.getValidNextCharacters().contains(letters[i])){
    			 curr = curr.getChild(letters[i]);
    			 continue;
    		 }
    		 // stem not found
    		 return new ArrayList<String>();
    	 }
    	 List<String> completions = new LinkedList<String>();
    	 List<TrieNode> nodes = new LinkedList<TrieNode>();
    	 nodes.add(curr);
    	 int numFound = 0;
    	 while (nodes.size()>0 && numFound<numCompletions) {
    		 curr = nodes.remove(0);
    		 if (curr.endsWord()) {
    			 completions.add(curr.getText());
    			 numFound++;
    		 }
    		 for (char c:curr.getValidNextCharacters()) {
    			 nodes.add(curr.getChild(c));
    		 }
    	 }
    	 System.out.println(completions.size());
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
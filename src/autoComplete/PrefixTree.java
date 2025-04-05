package autoComplete;

import java.util.ArrayList;
import java.util.Map;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up to 26, one per letter).
 * Each child node represents a letter. A path from a root's child node down to a node where isWord is true represents the sequence
 * of characters in a word.
 */
public class PrefixTree {
    private TreeNode root; 

    // Number of words contained in the tree
    private int size;

    public PrefixTree(){
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node
     * If the word, is already in the tree, then this has no effect.
     * @param word
     */
    public void add(String word){
        TreeNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (!currNode.children.containsKey(word.charAt(i))) {
                TreeNode newChar = new TreeNode();
                newChar.letter = word.charAt(i);
                currNode.children.put(word.charAt(i), newChar);
            }
            currNode = currNode.children.get(word.charAt(i));
        }
        if(!currNode.isWord){
            size++;
        }
        currNode.isWord = true;
    }

    /**
     * Checks whether the word has been added to the tree
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word){
        /*This should return true if the word is contained in the tree. 
        Starting at the root, you can iterate through the word's characters 
        searching down the path of nodes. If each character is found in the 
        correct order and the last is marked as a word, then the word is 
        contained in the tree. Once you complete this, 
        the testContains method should pass. */
        TreeNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if(currNode.children.containsKey(word.charAt(i))){
                currNode = currNode.children.get(word.charAt(i));
            }
        }
        if(currNode.isWord){
            return true;
        }
        return false;
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself).
     * The order of the list can be arbitrary.
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix){
        //TODO: complete me preorder traversal and helper
        /*This should return a list of words contained in the tree 
        that start with the prefix characters, including the prefix 
        if it is a word itself. These words can be found by 
        traversing through the letters of the prefix and then 
        traversing any child decendents of the last prefix node. 
        There are multiple ways to implement this, but probably 
        the easiest is to write a separate helper, recursive method 
        to preorder-traverse the child decendents. Look in the BST 
        activity for an example of a recursive pre-order traversal; 
        however, note that this tree is not binary. */


        return null;
    }

    public void prefixHelper(ArrayList<String> list){
        
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}

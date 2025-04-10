package autoComplete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up
 * to 26, one per letter). Each child node represents a letter. A path from a root's child node down
 * to a node where isWord is true represents the sequence of characters in a word.
 */
public class PrefixTree {
    private TreeNode root;

    // Number of words contained in the tree
    private int size;

    public PrefixTree() {
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node If the word, is
     * already in the tree, then this has no effect.
     * 
     * @param word
     */
    public void add(String word) {
        TreeNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (!currNode.children.containsKey(word.charAt(i))) {
                TreeNode newChar = new TreeNode();
                newChar.letter = word.charAt(i);
                currNode.children.put(word.charAt(i), newChar);
            }
            currNode = currNode.children.get(word.charAt(i));
        }
        if (!currNode.isWord) {
            size++;
        }
        currNode.isWord = true;
    }

    /**
     * Checks whether the word has been added to the tree
     * 
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word) {
        /*
         * This should return true if the word is contained in the tree. Starting at the root, you can
         * iterate through the word's characters searching down the path of nodes. If each character is
         * found in the correct order and the last is marked as a word, then the word is contained in the
         * tree. Once you complete this, the testContains method should pass.
         */
        TreeNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (currNode.children.containsKey(word.charAt(i))) {
                currNode = currNode.children.get(word.charAt(i));
            }
        }
        if (currNode.isWord) {
            return true;
        }
        return false;
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself). The
     * order of the list can be arbitrary.
     * 
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix) {
        ArrayList<String> list = new ArrayList<>();
        TreeNode currNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (currNode.children.containsKey(prefix.charAt(i))) {
                currNode = currNode.children.get(prefix.charAt(i));
            } else {
                return list;
            }
        }
        if (currNode.isWord) {
            list.add(prefix);
        }
        for (Entry<Character, TreeNode> key : currNode.children.entrySet()) {
            prefixHelper(key.getValue(), prefix, list);
        }
        return list;
    }

    /**
     * Pre-order traverses through children (including their children if they have them) 
     * of last prefix node
     * @param node
     * @param prefix
     * @param list
     * 
     */
    public void prefixHelper(TreeNode node, String prefix, ArrayList<String> list) {
        prefix = prefix + node.letter;
        if (node.isWord) {
            list.add(prefix);
        }
        for (Entry<Character, TreeNode> key : node.children.entrySet()) {
            prefixHelper(key.getValue(), prefix, list);
        }
    }

    /**
     * @return the number of words in the tree
     */
    public int size() {
        return size;
    }

}

package trie;

/* 211. Add and Search Word - Data structure design
Description:
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or '.'.
A '.' means it can represent any one letter.

Example:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.
 */

public class AddAndSearchWordDataStructureDesign {
    class WordDictionary {
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }

        TrieNode root;

        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            TrieNode node = root;
            return searchHelper(node, word, 0);
        }

        private boolean searchHelper(TrieNode node, String word, int index) {
            if (index == word.length()) {
                return node.isEnd;
            }
            char c = word.charAt(index);
            if (c == '.') {
                for (int i = 0; i < 26; ++i) {
                    if (node.children[i] != null) {
                        if (searchHelper(node.children[i], word, index + 1)) {
                            return true;
                        }
                    }
                }
            } else {
                if (node.children[c - 'a'] == null) {
                    return false;
                } else {
                    return searchHelper(node.children[c - 'a'], word, index + 1);
                }
            }
            return false;
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}

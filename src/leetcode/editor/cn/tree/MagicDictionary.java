package leetcode.editor.cn.tree;

public class MagicDictionary {
    class TrieNode{
        private final int SIZE = 26;
        TrieNode[] child;
        private boolean isWord;

        TrieNode(){
            child = new TrieNode[SIZE];
            isWord = false;
        }
    }

    private TrieNode root;

    public MagicDictionary(){
        root = new TrieNode();
    }

    public void buildTrieNode(String[] dict){
        for (String word : dict) {
            char[] chars = word.toCharArray();
            TrieNode tmp = root;
            for (char c : chars) {
                if (tmp.child[c - 'a'] == null) {
                    tmp.child[c - 'a'] = new TrieNode();
                }
                tmp = tmp.child[c - 'a'];
            }
            tmp.isWord = true;
        }
    }
}

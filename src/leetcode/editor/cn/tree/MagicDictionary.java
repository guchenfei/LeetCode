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


    public boolean searchReplaceWord(TrieDemo.TrieNode root, String word){

        TrieDemo.TrieNode tmp = root;
        char[] chars = word.toCharArray();
        for (int i = 0;i< chars.length;i++){
            char c = chars[i];
            //如果我们发现该字符在字典树不存在那么继续向下查找但是必须保证后续都能查到,isWord为true
            if (tmp.child[c - 'a'] == null){
                pastSearchWord(tmp );
            }
        }
    }
}

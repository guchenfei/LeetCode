package leetcode.editor.cn.tree;

/**
 * 该类是有关字典树的操作
 * 1.字典树构建
 * 2.找到没有单词是否属于该字典树
 * 3.将某个单词插入到字典树
 * 上诉操作需要一种数据结构
 */
public class TrieDemo {
    /**
     * 字典树每个节点的数据结构
     * 它可以存在多种形式,我们暂且以该字典树只存小写26个字母为例
     */
    class TrieNode{
        private final int SIZE = 26;
        TrieNode[] child;
        /**
         * 标记该节点是否是单词末尾节点(也就是说该节点以上可以构成一个单词)
         */
        boolean isWord;

        public TrieNode(){
            isWord = false;
            child = new TrieNode[SIZE];
        }
    }

    /**
     * 该方法是字典树的前序遍历
     * 我们通过前序遍历的特点以及每个节点子节点肯定都是1-26顺序排列
     * 这样我们很容易实现前序遍历
     * @param root 给定的字典树
     */
    public void preOrder(TrieNode root){
        for (int i = 0;i < 26;i++){
            if (root.child[i]!=null){
                System.out.println(i + 'a');
                if (root.child[i].isWord){
                    System.out.println("(end)");
                }
                preOrder(root.child[i]);
            }
        }
    }

    /**
     * 该方法是将一个单词插入到字典树中
     * @param root 待插入字典树
     * @param word 待插入单词
     */
    public void insertWord(TrieNode root,String word){
        char[] chars = word.toCharArray();
        TrieNode tmp = root;
       for (int i = 0;i<word.length();i++){
           char c = chars[i];
           if (tmp.child[c - 'a'] == null){
               tmp.child[c - 'a'] = new TrieNode();
           }
           tmp = tmp.child[c - 'a'];
       }
       tmp.isWord = true;
    }
}

package leetcode.editor.cn;
//设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单
//词存在于你构建的字典中。 
//
// 实现 MagicDictionary 类： 
//
// 
// MagicDictionary() 初始化对象 
// void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字
//符串互不相同 
// bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得
//所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入
//["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
//输出
//[null, null, false, true, false, false]
//
//解释
//MagicDictionary magicDictionary = new MagicDictionary();
//magicDictionary.buildDict(["hello", "leetcode"]);
//magicDictionary.search("hello"); // 返回 False
//magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
//magicDictionary.search("hell"); // 返回 False
//magicDictionary.search("leetcoded"); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= dictionary.length <= 100 
// 1 <= dictionary[i].length <= 100 
// dictionary[i] 仅由小写英文字母组成 
// dictionary 中的所有字符串 互不相同 
// 1 <= searchWord.length <= 100 
// searchWord 仅由小写英文字母组成 
// buildDict 仅在 search 之前调用一次 
// 最多调用 100 次 search 
// 
// 
// 
// 
// Related Topics 设计 字典树 哈希表 字符串 👍 119 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImplementMagicDictionary {
    public static void main(String[] args) {
        MagicDictionary solution = new ImplementMagicDictionary().new MagicDictionary();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MagicDictionary {
        class TrieNode {
            private final int SIZE = 26;
            TrieNode[] child;
            private boolean isWord;

            TrieNode() {
                child = new TrieNode[SIZE];
                isWord = false;
            }
        }

        private TrieNode root;

        public MagicDictionary() {
            root = new TrieNode();
        }

        public void buildDict(String[] dictionary) {
            for(String word : dictionary){
                TrieNode tmp = root;
                char[] chars = word.toCharArray();
                for (char c : chars){
                    if (tmp.child[c - 'a'] == null){
                        tmp.child[c - 'a'] = new TrieNode();
                    }
                    tmp = tmp.child[c -'a'];
                }
                tmp.isWord = true;
            }
        }

        /**
         * 该方法的功能是在给定的字典树中查找,是否存在一个单词可以通过给定的单词
         * 并且只是替换给定单词中一个字母达到替换后的单词存在字典树中
         * 满足上诉条件返回true
         * @param searchWord 待替换的单词
         * @return 给定的单词是否满足条件
         */
        public boolean search(String searchWord) {
            //解题思路:从字典树每层查找不同于给定字符串相对位置的字符
            //如果该层中发现某个节点不为空并且与对应的字符串字符不同,那么按照正常字典树查找,待查字符串剩余字符满足剩余单词检索则为满足条件(true)
            //如果该层所有节点都没有和相对位置字符不同的节点,必定该节点前的字符都相同,直到找到不同节点为止,否则没有满足条件的字符串存在
            TrieNode tmp = root;
            char[] chars = searchWord.toCharArray();
            for (int i = 0;i<chars.length;i++){
                char c = chars[i];
                for (int j = 0;j<26;j++){
                    if ((char)(j + 'a') == c||tmp.child[j] == null){
                        continue;
                    }
                    //检索该节点后的节点是否和待查单词剩余字符都相同(注意最后一个节点isWord属性)
                    if (pastSearchTrieNode(tmp.child[j],searchWord,i+1)) {
                        return  true;
                    }
                }
                //执行到这里必定此节点和以上字符都匹配相同,继续查找下边是否存在不同节点
                if (tmp.child[c - 'a'] == null){
                   return false;
                }
                tmp = tmp.child[c - 'a'];
            }
            //此刻是该字典树存在与待查字符串一样的单词,显然此场景不满足题意
            return false;
        }

        /**
         * 该方法是从当前给定节点开始查找,是否存在待查单词在给定索引的位置后字符都存在该节点位置后并且注意末尾单词节点isWord为true
         * @param trieNode 待查字典树节点
         * @param searchWord 待查单词
         * @param beginIndex 开始匹配待查单词字符index
         * @return 是否存在index后的单词
         */
        private boolean pastSearchTrieNode(TrieNode trieNode, String searchWord, int beginIndex) {
            TrieNode tmp = trieNode;
            char[] chars = searchWord.toCharArray();
            for (int i = beginIndex;i<searchWord.length();i++){
                char c =  chars[i];
                if (tmp.child[c - 'a']==null){
                    return false;
                }
                tmp = tmp.child[c - 'a'];
            }
            return tmp.isWord;
        }

//        //key为单词长度,value为单词长度为相同的单词集
//        private Map<Integer, ArrayList<String>> map;
//
//        public MagicDictionary() {
//            map = new HashMap<>();
//        }
//
//        public void buildDict(String[] dictionary) {
//            for(String word : dictionary){
//               map.computeIfAbsent(word.length(),x->new ArrayList<String>()).add(word);
//            }
//        }
//
//        /**
//         * 法一:由于满足题意的检索不能发挥出字典树查询效率
//         * 换一种数据结构,通过待查找的字符串长度巧妙过滤掉不满足条件的字符串
//         * 采用分桶思维将相同长度字符串放到一起,只比较长度一样的字符串字符不同个数达到判定是否存在这样替换
//         * @param searchWord 待查找替换单词
//         * @return 是否存在满足题意待替换单词存在
//         */
//        public boolean search(String searchWord) {
//            int len = searchWord.length();
//            if (!map.containsKey(len)) {
//                return  false;
//            }
//            //依次比较相同长度桶内的字符串,diff为1则立即返回true
//            for (String word : map.get(len)){
//                int diff = computeDiff(word,searchWord);
//                if (diff == 1){
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        private int computeDiff(String word, String searchWord) {
//            int diff = 0;
//            for (int i = 0;i< word.length();i++){
//                if (word.charAt(i) != searchWord.charAt(i)){
//                    diff++;
//                }
//                if (diff>1){
//                    return diff;
//                }
//            }
//            return diff;
//        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
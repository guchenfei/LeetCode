//给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。 
//
// 
//
// 示例 1： 
//
// 
//输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], 
//k = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 1 <= words.length <= 500 
// 1 <= words[i] <= 10 
// words[i] 由小写英文字母组成。 
// k 的取值范围是 [1, 不同 words[i] 的数量] 
// 
//
// 
//
// 进阶：尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// Related Topics 字典树 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 👍 510 👎 0

package leetcode.editor.cn;

import java.util.*;

public class TopKFrequentWords {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentWords().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> wordsFre = new HashMap<>();

            for (String word : words) {
                wordsFre.put(word, wordsFre.getOrDefault(word, 0) + 1);
            }
            //法一
//            List<String> result = new ArrayList<>(wordsFre.keySet());
//            Collections.sort(result, (word1, word2) ->
//                    wordsFre.get(word1).equals(wordsFre.get(word2)) ? word1.compareTo(word2) : wordsFre.get(word2) - wordsFre.get(word1));
//            return result.subList(0, k);

            //法二:利用最小堆来实现
//            PriorityQueue<String> priorityQueue = new PriorityQueue<>((word1, word2) ->
//                    wordsFre.get(word1).equals(wordsFre.get(word2)) ? word2.compareTo(word1) : wordsFre.get(word1) - wordsFre.get(word2));
//
//            for (String word : wordsFre.keySet()) {
//                priorityQueue.add(word);
//                if (priorityQueue.size() > k) {
//                    priorityQueue.poll();
//                }
//            }
//
//            List<String> result = new ArrayList<>();
//            while (!priorityQueue.isEmpty()){
//                result.add(priorityQueue.poll());
//            }
//            Collections.reverse(result);
//            return result;

            //法三 桶+Trie树高效计算字典序
            Trie[] tries = new Trie[words.length];
            for (Map.Entry<String, Integer> entry : wordsFre.entrySet()) {
                Integer fre = entry.getValue();
                String word = entry.getKey();
                if (tries[fre] == null){
                    tries[fre] = new Trie();
                }
                tries[fre].addWord(word);
            }

            List<String> result = new ArrayList<>();
            for(int i = tries.length -1;i>=0;i--){
                List<String> tmp = new ArrayList<>();
                if (tries[i] != null){
                    tries[i].getWords(tries[i].root,tmp);
                }
                if (tmp.size() <= k){
                    result.addAll(tmp);
                    k = k - tmp.size();
                } else {
                    for (int j = 0;j< k;j++){
                        result.add(tmp.get(j));
                    }
                    break;
                }
            }
            return result;
        }

        public class Trie {
            public TrieNode root = new TrieNode();

            class TrieNode{
                TrieNode[] children;
                String word;

                TrieNode(){
                    this.children = new TrieNode[26];
                    this.word = null;
                }
            }


            public void addWord(String word){
                TrieNode cur  = root;
                for (char c : word.toCharArray()) {
                    if (cur.children[c -'a'] == null){
                        cur.children[c -'a'] = new TrieNode();
                    }
                    cur = cur.children[c -'a'];
                }
                cur.word = word;
            }

            //该方法是将某个TrieNode节点中的单词按照字典序放到指定传入的集合中
            public void getWords(TrieNode root, List<String> result){
                if(root == null) {return;}
                if (root.word != null){
                    result.add(root.word);
                }

                for (int i = 0;i<26;i++){
                    if (root.children[i] != null){
                        getWords(root.children[i],result);
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}


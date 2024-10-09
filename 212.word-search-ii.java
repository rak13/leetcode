/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 *
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (36.54%)
 * Likes:    9531
 * Dislikes: 470
 * Total Accepted:    707K
 * Total Submissions: 1.9M
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n' +
  '["oath","pea","eat","rain"]'
 *
 * Given an m x n board of characters and a list of strings words, return all
 * words on the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once in a word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board =
 * [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 * 
 * 
 */

// @lc code=start
class Solution {

    char[][] board;
    TrieNode head;
    int M, N;
    List<String> res;

    class TrieNode {
        Map<Character, TrieNode> charMap = new HashMap<>();
        String word = null;
    }

    private void dfsBT(int row, int col, TrieNode root) {
        char letter = board[row][col];
        TrieNode nextNode = root.charMap.get(letter);
        if(nextNode.word != null) {
            res.add(nextNode.word);
            nextNode.word = null;
        }

        board[row][col] = '#';

        int[] rowOffset = { -1, 0, 1, 0 };
        int[] colOffset = { 0, 1, 0, -1 };

        
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (
                newRow < 0 ||
                newRow >= M ||
                newCol < 0 ||
                newCol >= N
                || board[newRow][newCol] == '#'
            ) {
                continue;
            }
            char newLetter = board[newRow][newCol];
            if (nextNode.charMap.containsKey(newLetter)) {
                dfsBT(newRow, newCol, nextNode);
            }
        }
        board[row][col] = letter;
        if(nextNode.charMap.isEmpty()) {
            root.charMap.remove(letter);
        }
    }
    
    private void createTrie(TrieNode head, String[] words) {
        TrieNode it;
        for(var word: words) {
            it = head;
            int len = word.length();
            for(int i = 0; i < len; i++) {
                char c = word.charAt(i);
                
                if(!it.charMap.containsKey(c)) {
                    it.charMap.put(c, new TrieNode());
                }

                it = it.charMap.get(c);
            }
            it.word = word;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        
        TrieNode head = new TrieNode(); 

        this.board = board;
        this.head = head;
        this.res = new ArrayList<>();

        createTrie(head, words);
        
        M = board.length;
        N = board[0].length;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j<N; j++) {
                char boardChar = board[i][j];
                if(head.charMap.containsKey(boardChar)){
                    dfsBT(i, j, head);
                }
            }
        }
        return this.res;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (35.31%)
 * Likes:    40044
 * Dislikes: 1919
 * Total Accepted:    6.2M
 * Total Submissions: 17.6M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not
 * a substring.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) return 0;
        Set<Character> set = new HashSet<>();
        int maxLen = Integer.MIN_VALUE;

        int bg = 0;
        for(int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            while(set.contains(curChar)) {
                char charAtBg = s.charAt(bg++);
                set.remove(charAtBg);
            }
            set.add(curChar);
            maxLen = Math.max(maxLen, set.size());
        }
        return maxLen;
    }
}
// @lc code=end


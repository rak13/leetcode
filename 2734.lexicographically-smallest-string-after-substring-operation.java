/*
 * @lc app=leetcode id=2734 lang=java
 *
 * [2734] Lexicographically Smallest String After Substring Operation
 *
 * https://leetcode.com/problems/lexicographically-smallest-string-after-substring-operation/description/
 *
 * algorithms
 * Medium (31.16%)
 * Likes:    249
 * Dislikes: 187
 * Total Accepted:    28.4K
 * Total Submissions: 89.1K
 * Testcase Example:  '"cbabc"'
 *
 * Given a string s consisting of lowercase English letters. Perform the
 * following operation:
 * 
 * 
 * Select any non-empty substring then replace every letter of the substring
 * with the preceding letter of the English alphabet. For example, 'b' is
 * converted to 'a', and 'a' is converted to 'z'.
 * 
 * 
 * Return the lexicographically smallest string after performing the
 * operation.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "cbabc"
 * 
 * Output: "baabc"
 * 
 * Explanation:
 * 
 * Perform the operation on the substring starting at index 0, and ending at
 * index 1 inclusive.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aa"
 * 
 * Output: "az"
 * 
 * Explanation:
 * 
 * Perform the operation on the last letter.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "acbbc"
 * 
 * Output: "abaab"
 * 
 * Explanation:
 * 
 * Perform the operation on the substring starting at index 1, and ending at
 * index 4 inclusive.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "leetcode"
 * 
 * Output: "kddsbncd"
 * 
 * Explanation:
 * 
 * Perform the operation on the entire string.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 3 * 10^5
 * s consists of lowercase English letters
 * 
 * 
 */

// @lc code=start
class Solution {
    public String smallestString(String s) {
        int n = s.length();
        StringBuilder str = new StringBuilder(s);

        int i = 0;

        //ignore all 'a' in the beginning
        while(i < n && str.charAt(i) == 'a'){
            i++;
        } 

        //reduce all chars until next 'a'
        for(int j = i; j < n; j++) {
            char c = s.charAt(j);
            if(c == 'a') {
                break;
            } else {
                str.setCharAt(j, (char)(c - 1));
            }
        }

        //all a's - then change last char only
        if(i == n) {
            str.setCharAt(n - 1, 'z');
        }

        return str.toString();
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=1249 lang=java
 *
 * [1249] Minimum Remove to Make Valid Parentheses
 *
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
 *
 * algorithms
 * Medium (69.21%)
 * Likes:    6910
 * Dislikes: 146
 * Total Accepted:    772.2K
 * Total Submissions: 1.1M
 * Testcase Example:  '"lee(t(c)o)de)"'
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 * 
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in
 * any positions ) so that the resulting parentheses string is valid and return
 * any valid string.
 * 
 * Formally, a parentheses string is valid if and only if:
 * 
 * 
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid
 * strings, or
 * It can be written as (A), where A is a valid string.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is either '(' , ')', or lowercase English letter.
 * 
 * 
 */

// @lc code=start
class Solution {
    
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stk = new Stack<Integer>();
        HashSet<Integer> iToRemove = new HashSet<Integer>();

        for(int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if(curChar == '(') {
                stk.add(i);
            }
            else if(curChar == ')') {
                if(stk.isEmpty()) {
                    iToRemove.add(i);
                }
                else stk.pop();
            }
        }

        
        while(!stk.isEmpty()) {
            iToRemove.add(stk.pop());
        }

        StringBuilder str = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(iToRemove.contains(i)) {
                continue;
            }
            str.append(s.charAt(i));
        }
        return str.toString();
    }
}
// @lc code=end


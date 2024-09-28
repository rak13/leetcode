/*
 * @lc app=leetcode id=68 lang=java
 *
 * [68] Text Justification
 *
 * https://leetcode.com/problems/text-justification/description/
 *
 * algorithms
 * Hard (44.81%)
 * Likes:    3799
 * Dislikes: 4833
 * Total Accepted:    459.3K
 * Total Submissions: 1M
 * Testcase Example:  '["This", "is", "an", "example", "of", "text", "justification."]\n16'
 *
 * Given an array of strings words and a width maxWidth, format the text such
 * that each line has exactly maxWidth characters and is fully (left and right)
 * justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly maxWidth characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line does not divide evenly between words, the
 * empty slots on the left will be assigned more spaces than the slots on the
 * right.
 * 
 * For the last line of text, it should be left-justified, and no extra space
 * is inserted between words.
 * 
 * Note:
 * 
 * 
 * A word is defined as a character sequence consisting of non-space characters
 * only.
 * Each word's length is guaranteed to be greater than 0 and not exceed
 * maxWidth.
 * The input array words contains at least one word.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["This", "is", "an", "example", "of", "text",
 * "justification."], maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth
 * = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall
 * be", because the last line must be left-justified instead of
 * fully-justified.
 * Note that the second line is also left-justified because it contains only
 * one word.
 * 
 * Example 3:
 * 
 * 
 * Input: words =
 * ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"],
 * maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * ⁠ "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 * 
 * 
 */

// @lc code=start
class Solution {

    String justify(List<String> words, int maxWidth, boolean isLast) {
        int minSpacesReq = words.size() - 1;
        int charInWords = 0;
        for(var word: words) {
            charInWords += word.length();
        }

        int spaceLeft = maxWidth - charInWords;
        int spaceBetweenWords = minSpacesReq <= 0 ? 0 : spaceLeft / minSpacesReq;
        spaceBetweenWords = isLast ? 1 :  spaceBetweenWords;
        int extraSpaces = minSpacesReq <= 0 ? 0 : spaceLeft % minSpacesReq;
        extraSpaces = isLast ? 0 :  extraSpaces;
        
        StringBuilder res = new StringBuilder("");

        for(int j = 0; j < words.size(); j++) {
            String word = words.get(j);
            res.append(word);
            if(j < words.size() - 1) {
                res.append(" ".repeat(spaceBetweenWords));
            }
            if(extraSpaces > 0) {
                res.append(" ");
                extraSpaces--;
            }
        }
        

        if(words.size() == 1 || isLast){
            res.append(" ".repeat(maxWidth - res.length()));
        }

        // System.out.println("|" + res + "|");

        return res.toString();
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        List<String> curWords = new ArrayList<>();
        int curMinCharLength = 0;
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            int prevWordSpace =  curMinCharLength == 0 ? 0 : 1; // space between words
            if(curMinCharLength + prevWordSpace + word.length() <= maxWidth) {
                curWords.add(word);
                curMinCharLength += prevWordSpace + word.length();
            } else{ 
                res.add(justify(curWords, maxWidth, false));
                curWords = new ArrayList<>();
                curMinCharLength = word.length();
                curWords.add(word);
            }
        }
        if(!curWords.isEmpty()) {
            res.add(justify(curWords, maxWidth, true));
        }
        return res;
    }
}
// @lc code=end


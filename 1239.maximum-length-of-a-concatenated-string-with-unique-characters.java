/*
 * @lc app=leetcode id=1239 lang=java
 *
 * [1239] Maximum Length of a Concatenated String with Unique Characters
 *
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/description/
 *
 * algorithms
 * Medium (54.12%)
 * Likes:    4429
 * Dislikes: 331
 * Total Accepted:    293.1K
 * Total Submissions: 541.6K
 * Testcase Example:  '["un","iq","ue"]'
 *
 * You are given an array of strings arr. A string s is formed by the
 * concatenation of a subsequence of arr that has unique characters.
 * 
 * Return the maximum possible length of s.
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All the valid concatenations are:
 * - ""
 * - "un"
 * - "iq"
 * - "ue"
 * - "uniq" ("un" + "iq")
 * - "ique" ("iq" + "ue")
 * Maximum length is 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible longest valid concatenations are "chaers" ("cha" +
 * "ers") and "acters" ("act" + "ers").
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * Explanation: The only string in arr has all 26 characters.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {

    public int maxLength(List<String> arr) {
        return maxLenBitMask(arr, 0, 0);
    }

    public static long setBit(long num, int pos) {
        return num | (1 << pos);
    }

    public static boolean checkBit(long num, int pos) {
        return (num & (1 << pos)) != 0;
    }

    public static long unsetBit(long num, int pos) {
        return num & ~(1 << pos);
    }

    public static int countSetBits(long num) {
        int count = 0;
        while (num > 0) {
            count += (num & 1);
            num >>= 1;
        }
        return count;
    }

    private long getMask(String curStr, long mask) {
        boolean keepsUnique = true;
        for(int i = 0; i<curStr.length(); i++) {
            int charPos =  curStr.charAt(i) - 'a';
            if(!checkBit(mask, charPos)) {
                mask = setBit(mask, charPos);
            } else {
                keepsUnique = false;
                break;
            }
        }
        return keepsUnique == false ? - 1 : mask;
    }

    public int maxLenBitMask(List<String> arr, int pos, long mask) {
        if(pos == arr.size()) {
            return countSetBits(mask);
        }
        int way1 = maxLenBitMask(arr, pos+1, mask);
        int way2 = 0;

        String curStr = arr.get(pos);

        long curMask = getMask(curStr, mask);
        if(curMask >= 0) {
            way2 = maxLenBitMask(arr, pos+1, curMask);
        }

        // System.out.println(curStr + ", keepsUnique " + keepsUnique + ", way1 " + way1 + ", way2 "  + way2 + ", set " + chars);
        return Math.max(way1, way2);
    }

    public int maxLenHelper(List<String> arr, int pos, Set<Character> chars) {
        if(pos == arr.size()) {
            return chars.size();
        }
        int way1 = maxLenHelper(arr, pos+1, chars);
        int way2 = 0;

        String curStr = arr.get(pos);

        Set<Character> curCars = new HashSet<>();
        boolean keepsUnique = true;
        for(int i = 0; i<curStr.length(); i++) {
            char c = curStr.charAt(i);
            if(!curCars.contains(c) && !chars.contains(c)) {
                curCars.add(c);
            } else {
                keepsUnique = false;
                break;
            }
        }
        if(keepsUnique) {
            chars.addAll(curCars);
            way2 = maxLenHelper(arr, pos+1, chars);
            for(int i = 0; i<curStr.length(); i++) {
                char c = curStr.charAt(i);
                chars.remove(c);
            }
        }

        return Math.max(way1, way2);
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=2979 lang=java
 *
 * [2979] Most Expensive Item That Can Not Be Bought
 *
 * https://leetcode.com/problems/most-expensive-item-that-can-not-be-bought/description/
 *
 * algorithms
 * Medium (77.53%)
 * Likes:    18
 * Dislikes: 18
 * Total Accepted:    2.9K
 * Total Submissions: 3.7K
 * Testcase Example:  '2\n5'
 *
 * You are given two distinct prime numbers primeOne and primeTwo.
 * 
 * Alice and Bob are visiting a market. The market has an infinite number of
 * items, for any positive integer x there exists an item whose price is x.
 * Alice wants to buy some items from the market to gift to Bob. She has an
 * infinite number of coins in the denomination primeOne and primeTwo. She
 * wants to know the most expensive item she can not buy to gift to Bob.
 * 
 * Return the price of the most expensive item which Alice can not gift to
 * Bob.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: primeOne = 2, primeTwo = 5
 * Output: 3
 * Explanation: The prices of items which cannot be bought are [1,3]. It can be
 * shown that all items with a price greater than 3 can be bought using a
 * combination of coins of denominations 2 and 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: primeOne = 5, primeTwo = 7
 * Output: 23
 * Explanation: The prices of items which cannot be bought are
 * [1,2,3,4,6,8,9,11,13,16,18,23]. It can be shown that all items with a price
 * greater than 23 can be bought.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 < primeOne, primeTwo < 10^4
 * primeOne, primeTwo are prime numbers.
 * primeOne * primeTwo < 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        // Number theory!!  
        // return primeOne * primeTwo - primeOne - primeTwo;

        
        // solution 1
        // int prod = primeOne * primeTwo;
        // boolean[] mark = new boolean[prod+1];
        // for(int i = 0; i <= Math.max(primeOne, primeTwo); i++) {
        //     for(int j = 0; j <= Math.max(primeOne, primeTwo); j++) {
        //         int val = primeOne * i + primeTwo * j;
        //         if(val > prod) {
        //             break;
        //         }
        //         else {
        //             mark[val] = true;
        //         }
        //     }
        // }

        //solution 2 - mark true if previous one can be formed
        // int prod = primeOne * primeTwo;
        // boolean[] mark = new boolean[prod+1];

        // mark[primeOne] = true;
        // mark[primeTwo] = true;
        // for(int i = 0; i <= prod; i++) {
        //     mark[i] = mark[i];
        //     if(!mark[i] && (i - primeOne) >= 0) {
        //         mark[i] = mark[i - primeOne];
        //     }

        //     else if(!mark[i] && (i - primeTwo) >= 0) {
        //         mark[i] = mark[i - primeTwo];
        //     }
        // }

        // for(int i = prod; i >= 0; i--) {
        //     if(mark[i] == false) {
        //         return i;
        //     }
        // }

        //Solution 3 - if the current one can be formed - mark the next one as true 
        int n = primeOne * primeTwo;
        boolean[] items = new boolean[n];
        items[0] = true;

        for(int i = 0; i < n; i++) {
            if(items[i]) { //start from items[0] our base DP case
                if(i + primeOne < n) {
                    items[i + primeOne] = true;
                }

                if(i + primeTwo < n) {
                    items[i + primeTwo] = true;
                }
            }
        }
        // System.out.println(Arrays.toString(items));
        for(int i = n - 1; i >= 0; i--) {
            if(!items[i])
                return i;
        }

        

        return -1;
    }
}
// @lc code=end


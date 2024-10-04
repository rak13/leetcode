/*
 * @lc app=leetcode id=1605 lang=java
 *
 * [1605] Find Valid Matrix Given Row and Column Sums
 *
 * https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/description/
 *
 * algorithms
 * Medium (83.13%)
 * Likes:    2129
 * Dislikes: 90
 * Total Accepted:    148.9K
 * Total Submissions: 179.2K
 * Testcase Example:  '[3,8]\n[4,7]'
 *
 * You are given two arrays rowSum and colSum of non-negative integers where
 * rowSum[i] is the sum of the elements in the i^th row and colSum[j] is the
 * sum of the elements of the j^th column of a 2D matrix. In other words, you
 * do not know the elements of the matrix, but you do know the sums of each row
 * and column.
 * 
 * Find any matrix of non-negative integers of size rowSum.length x
 * colSum.length that satisfies the rowSum and colSum requirements.
 * 
 * Return a 2D array representing any matrix that fulfills the requirements.
 * It's guaranteed that at least one matrix that fulfills the requirements
 * exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: rowSum = [3,8], colSum = [4,7]
 * Output: [[3,0],
 * ⁠        [1,7]]
 * Explanation: 
 * 0^th row: 3 + 0 = 3 == rowSum[0]
 * 1^st row: 1 + 7 = 8 == rowSum[1]
 * 0^th column: 3 + 1 = 4 == colSum[0]
 * 1^st column: 0 + 7 = 7 == colSum[1]
 * The row and column sums match, and all matrix elements are non-negative.
 * Another possible matrix is: [[1,2],
 * ⁠                            [3,5]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: rowSum = [5,7,10], colSum = [8,6,8]
 * Output: [[0,5,0],
 * ⁠        [6,1,0],
 * ⁠        [2,0,8]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= rowSum.length, colSum.length <= 500
 * 0 <= rowSum[i], colSum[i] <= 10^8
 * sum(rowSum) == sum(colSum)
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] res = new int[m][n];
        int minRowCol = 0;
        
        // //solution 1
        // for(int i = 0; i < m; i++) {
        //     for(int j = 0; j < n; j++) {
        //         minRowCol = Math.min(rowSum[i], colSum[j]);
        //         res[i][j] = minRowCol;
        //         rowSum[i] -= minRowCol;
        //         colSum[j] -= minRowCol;
        //     }
        // }

        //Solution 2 - optimized !!
        int i = 0, j = 0;
        while(i < m && j < n) {
            minRowCol = Math.min(rowSum[i], colSum[j]);
            res[i][j] = minRowCol;
            rowSum[i] -= minRowCol;
            colSum[j] -= minRowCol;

            //if row sum is 0 - move to next row!
            if(rowSum[i] == 0) {
                i++;
            } else {
                j++;
            }
        }

        return res;
    }
}
// @lc code=end


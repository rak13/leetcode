/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 *
 * https://leetcode.com/problems/maximal-square/description/
 *
 * algorithms
 * Medium (47.17%)
 * Likes:    10238
 * Dislikes: 233
 * Total Accepted:    729.4K
 * Total Submissions: 1.5M
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest
 * square containing only 1's and return its area.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix =
 * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: matrix = [["0"]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximalSquare(char[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        
        int[][] dp = new int[M][N];
        int res = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(matrix[i][j] - '0' != 1) continue;

                if(i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }

    public int inititalSolution(char[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] rowSum = new int[M][N];
        int[][] colSum = new int[M][N];
        
        //rowSum
        for(int i = 0; i < M; i++) {
            int sum = 0;
            for(int j = 0; j < N; j++) {
                sum += matrix[i][j] - '0';
                rowSum[i][j] = sum;
            }
        }
        //colSum
        for(int j = 0; j < N; j++) {
            int sum = 0;
            for(int i = 0; i < M; i++) {
                sum += matrix[i][j] - '0';
                colSum[i][j] = sum;
            }
        }

        int res = 0;

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                int row = i;
                int col = j;
                int prev = 0;
                boolean isOne = matrix[i][j] - '0' == 1;
                while(isOne && (row < M && col < N)) {
                    if(row == i || col == j) {
                        prev = matrix[row][col]  - '0';
                    }
                    else {
                        int rowWindowSum = rowSum[row][col] - (j == 0 ? 0 : rowSum[row][j - 1]);
                        int colWindowSum = colSum[row][col] - (i == 0 ? 0 : colSum[i-1][col]);
                        prev = prev + rowWindowSum + colWindowSum - (matrix[row][col] - '0');
                    }
                    int curSize = (row - i + 1) * (col - j + 1);
                    if(prev == curSize) {
                        res = Math.max(res, prev);
                    } else {
                        break;
                    }
                    row++;
                    col++;
                }
            }
        }
        return res;
    }
}
// @lc code=end


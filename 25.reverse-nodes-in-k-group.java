/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (60.12%)
 * Likes:    13913
 * Dislikes: 724
 * Total Accepted:    1M
 * Total Submissions: 1.7M
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given the head of a linked list, reverse the nodes of the list k at a time,
 * and return the modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the
 * linked list. If the number of nodes is not a multiple of k then left-out
 * nodes, in the end, should remain as it is.
 * 
 * You may not alter the values in the list's nodes, only nodes themselves may
 * be changed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * 
 * 
 * 
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * iter = 0
 * bg = 0
 * 
 * 
 * cur = 2
 * nodeCount = 2
 * 
 * 
 * [0, 1,2,3,4,5]
 */
class Solution {
    void printNodes(ListNode head) {
        while(head != null) {
            System.out.printf(head.val + "->" );
            head = head.next;
        }
        System.out.println();
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tmpHead = new ListNode();
        tmpHead.next = head;

        ListNode iter = tmpHead;
        ListNode bg = tmpHead;
        int nodeCount = 0;
        while(iter.next != null) {
            ListNode cur = iter.next;
            nodeCount++;
            if(nodeCount == k) {
                ListNode bgNext = bg.next;
                reverseGroup(bg, k);
                bg = bgNext;
                iter = bg;
                nodeCount = 0;
                // printNodes(tmpHead.next);
            } else {
                iter = cur;
            }
        }
        
        return tmpHead.next;
    }

    void reverseGroup(ListNode bg, int k) {
        int count = 0;
        ListNode start = bg;
        ListNode startNext = start.next;

        ListNode prev = bg;
        ListNode cur = bg.next;
        ListNode next;
        while(count != k) {
            next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;

            count++;
        }

        start.next = prev;
        startNext.next = cur;
        
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (53.90%)
 * Likes:    19672
 * Dislikes: 726
 * Total Accepted:    2.2M
 * Total Submissions: 4M
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted
 * in ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * ⁠ 1->4->5,
 * ⁠ 1->3->4,
 * ⁠ 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: lists = []
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: lists = [[]]
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10^4.
 * 
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
 */
class Solution {
    void merge(ListNode sHead, ListNode lHead) {
        if(lHead == null) return;
        ListNode nextS = sHead.next;
        ListNode nextL = lHead.next;
        if(nextS == null || lHead.val < nextS.val) {
            sHead.next = lHead;
            lHead.next = nextS;
            merge(lHead, nextL);
        } else {
            merge(nextS, lHead);
        }
    }

    void mergeIterative(ListNode sHead, ListNode lHead) {
        if(sHead.next == null) {
            sHead.next = lHead;
            return;
        }

        ListNode nextS;
        ListNode nextL;
        while(lHead != null) {
            nextS = sHead.next;
            nextL = lHead.next;
            if(nextS == null || lHead.val < nextS.val) {
                sHead.next = lHead;
                lHead.next = nextS;
                sHead = lHead;
                lHead = nextL;
            } else {
                sHead = nextS;
            }
        }
    }

    public ListNode mergeKListsPQ(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode pos = head;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        ListNode curHead;
        for(int i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        while(!pq.isEmpty()) {
            curHead = pq.poll();
            pos.next = curHead;
            pos = pos.next;
            if(curHead.next != null) {
                pq.add(curHead.next);
            }
        }


        return head.next;
    }



    public ListNode mergeKLists(ListNode[] lists) {
        // ListNode sHead = new ListNode();
        return mergeKListsPQ(lists);
        
        // for(int i = 0; i < lists.length; i++) {
        //     mergeIterative(sHead, lists[i]);
        // }
        // return sHead.next;
    }
}
// @lc code=end


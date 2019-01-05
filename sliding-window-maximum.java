package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        Deque <Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int indx = 0;
        for(int i = 0; i<nums.length; i++) {
            //remove elements from front that are not in window
            while(!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            //remove elements within this window (from last) that are smaller than current element
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            //add current element
            deque.offer(i);
            if(i >= k - 1) {
                res[indx++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
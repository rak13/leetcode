/**

@lc app=leetcode id=716 lang=java

[716] Max Stack [Premium]

https://leetcode.com/problems/max-stack

Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

MaxStack() Initializes the stack object.
void push(int x) Pushes element x onto the stack.
int pop() Removes the element on top of the stack and returns it.
int top() Gets the element on the top of the stack without removing it.
int peekMax() Retrieves the maximum element in the stack without removing it.
int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

 

Example 1:

Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]

Explanation
MaxStack stk = new MaxStack();
stk.push(5);   // [5] the top of the stack and the maximum number is 5.
stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
stk.top();     // return 5, [5, 1, 5] the stack did not change.
stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
stk.top();     // return 1, [5, 1] the stack did not change.
stk.peekMax(); // return 5, [5, 1] the stack did not change.
stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
stk.top();     // return 5, [5] the stack did not change.
 

Constraints:

-107 <= x <= 107
At most 105 calls will be made to push, pop, top, peekMax, and popMax.
There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
 * 
 *
 */

// @lc code=start

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

class MaxStack {

    class Node {
        int val;
        int counter;
        Node next;
        Node prev;

        Node(int val, int counter) {
            this.val = val;
            this.counter = counter;
            next = null;
            prev = null;
        }
    }

    Node head;
    // Map<Integer, List<Node>> nodeMap;
    PriorityQueue<Node> maxHeap;

    int counter = 0;

    public MaxStack() {
        head = new Node(0, 0);
        maxHeap = new PriorityQueue<>((a, b) -> {
            if(b.val - a.val == 0) {
                return b.counter - a.counter;
            } 
            return b.val - a.val;   
        });
    }

    void printNodes() {
        Node tmpHead = head.next;
        while(tmpHead != null) {
            System.out.print(" => (" + tmpHead.val + ", " + tmpHead.counter + ") ");
            tmpHead = tmpHead.next;
        }
        System.out.println();
    }
    
    public void push(int x) {
        counter++;
        Node node = new Node(x, counter);
        Node nextToHead = head.next;

        head.next = node;
        node.prev = head;
        node.next = nextToHead;

        if(nextToHead != null) {
            nextToHead.prev = node;
        }

        maxHeap.add(node);

        // printNodes();
    }
    
    public int pop() {
        Node topElement = head.next;
        if(topElement != null) {
            Node nextTop = topElement.next;
            head.next = nextTop;
            if(nextTop != null) {
                nextTop.prev = head;
            }
            maxHeap.remove(topElement);
        }
        // printNodes();
        return topElement != null ? topElement.val : -1;
    }
    
    public int top() {
        return head.next != null ? head.next.val : -1;
    }
    
    public int peekMax() {
        return maxHeap.peek().val;
    }

    public void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        if(next != null) {
            next.prev = prev;
        }
    }
    
    public int popMax() {
        Node maxNode = maxHeap.poll();
        deleteNode(maxNode);

        // printNodes();

        return maxNode.val;
    }
}

// @lc code=end
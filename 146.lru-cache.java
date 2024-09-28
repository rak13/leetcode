/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Medium (43.10%)
 * Likes:    20905
 * Dislikes: 1059
 * Total Accepted:    1.8M
 * Total Submissions: 4.1M
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * Design a data structure that follows the constraints of a Least Recently
 * Used (LRU) cache.
 * 
 * Implement the LRUCache class:
 * 
 * 
 * LRUCache(int capacity) Initialize the LRU cache with positive size
 * capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise
 * return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache. If the number of keys
 * exceeds the capacity from this operation, evict the least recently used
 * key.
 * 
 * 
 * The functions get and put must each run in O(1) average time complexity.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= capacity <= 3000
 * 0 <= key <= 10^4
 * 0 <= value <= 10^5
 * At most 2 * 10^5 calls will be made to get and put.
 * 
 * 
 */

// @lc code=start
class LRUCache {

    class Node {
        int key;
        int val;

        Node next;
        Node prev;

        Node() {}

        Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    Map<Integer, Node> map = new HashMap<>();
    Node start = new Node();
    Node end = new Node();

    int MAX_CAP;

    public LRUCache(int capacity) {
        MAX_CAP = capacity;
        start.next = end;
        end.prev = start;
    }

    public void addNodeToFront(Node node) {
        Node curFront = start.next;
        
        start.next = node;
        node.prev = start;

        node.next = curFront;
        curFront.prev = node;
    }

    public Node deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;

        return node;
    }

    public void bringToFront(Node node) {
        deleteNode(node);
        addNodeToFront(node);
    }


    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            bringToFront(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            bringToFront(node);
        } else {
            if(MAX_CAP == 0) return;

            Node node = new Node(key, value);
            addNodeToFront(node);
            map.put(key, node);

            if(map.size() > MAX_CAP) {
                Node toDelete = end.prev;
                map.remove(toDelete.key);
                deleteNode(toDelete);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end


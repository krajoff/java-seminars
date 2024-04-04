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
class Solution23 {
    public int maxDepth(String s) {
        int max, temp;
        max = temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                temp++;
                if (temp > max)
                    max = temp;
            } else if (s.charAt(i) == ')') {
                temp--;
            }
        }
        return max;
    }
}


public class MergekSorterdList_Task23 {
    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        Solution23 solution = new Solution23();
        System.out.println(solution.maxDepth(s));
    }
}

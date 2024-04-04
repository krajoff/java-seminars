import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res;
        List<Integer> temp = new ArrayList<>();
        if (lists.length == 0)
            return new ListNode();
        for (int i = 0; i < lists.length; i++) {
            do {
                temp.add(lists[i].val);
                lists[i] = lists[i].next;
            }
            while (lists[i] != null);
        }
        temp.sort(Comparator.reverseOrder());
        //System.out.println(temp);
        if (temp.isEmpty())
            return new ListNode();
        res = new ListNode(temp.get(0));
        for (int i = 1; i < temp.size(); i++) {
            res = new ListNode(temp.get(i), res);
        }
        return res;
    }
}


public class MergekSorterdList_Task23 {
    public static void main(String[] args) {
        ListNode L1 = new ListNode(1,
                new ListNode(4,
                        new ListNode(5)));
        ListNode L2 = new ListNode(1,
                new ListNode(3,
                        new ListNode(4)));
        ListNode L3 = new ListNode(2,
                new ListNode(6));
        ListNode[] L = new ListNode[]{L1, L2, L3};
        Solution23 solution = new Solution23();
        System.out.println(solution.mergeKLists(L));
        ListNode[] L4 = new ListNode[]{};
        System.out.println(solution.mergeKLists(L4));
    }
}

import java.util.*;

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
        System.out.println(lists.length);
        if (lists.length == 0)
            return null;
        for (int i = 0; i < lists.length; i++) {
            System.out.println(lists[i] == null);
            try {
                do {

                    temp.add(lists[i].val);
                    lists[i] = lists[i].next;
                }
                while (lists[i] != null);
            } catch (Exception e) {
                continue;
            }
        }
        temp.sort(Comparator.reverseOrder());
        //System.out.println(temp);
        if (temp.isEmpty())
            return null;
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

        //ListNode{val=1, next=ListNode{val=1, next=ListNode{val=2,
        // next=ListNode{val=3, next=ListNode{val=4,
        // next=ListNode{val=4, next=ListNode{val=5,
        // next=ListNode{val=6, next=null}}}}}}}}
        System.out.println(solution.mergeKLists(L));

        //null
        ListNode[] L4 = new ListNode[]{};
        System.out.println(solution.mergeKLists(L4));

        //null
        ListNode L5 = new ListNode();
        ListNode[] L6 = new ListNode[]{L5};
        System.out.println(solution.mergeKLists(L6));

        //ListNode{val=0, next=null}
        ListNode L7 = new ListNode(0);
        ListNode[] L8 = new ListNode[]{L7};
        System.out.println(solution.mergeKLists(L8));


    }
}

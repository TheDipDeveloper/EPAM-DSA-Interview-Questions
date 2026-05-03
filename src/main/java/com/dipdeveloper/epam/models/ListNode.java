package com.dipdeveloper.epam.models;

/**
 * Standard Singly Linked List Node.
 * Used across Q2, Q3-style questions.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /** Helper: build a linked list from an int array */
    public static ListNode of(int... values) {
        if (values == null || values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    /** Helper: convert linked list to readable string */
    public static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(" -> ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

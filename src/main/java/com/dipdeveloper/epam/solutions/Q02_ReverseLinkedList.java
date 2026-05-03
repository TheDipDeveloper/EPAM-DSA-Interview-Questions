package com.dipdeveloper.epam.solutions;

import com.dipdeveloper.epam.models.ListNode;

/**
 * ============================================================
 * Q2. REVERSE A LINKED LIST
 * ============================================================
 * PROBLEM:
 *   Given the head of a singly linked list, reverse the list
 *   and return the reversed list's head.
 *
 * EXAMPLE:
 *   Input:  1 -> 2 -> 3 -> 4 -> 5
 *   Output: 5 -> 4 -> 3 -> 2 -> 1
 *
 * EPAM INTERVIEW TIP:
 *   EPAM loves this as a warm-up. Know both iterative and recursive.
 *   Interviewer often follows up: "Can you do it without extra space?"
 *   → Iterative does it in O(1) space.
 * ============================================================
 */
public class Q02_ReverseLinkedList {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Iterative (Three-Pointer Technique) ← PREFERRED
    // Time  Complexity: O(n)
    // Space Complexity: O(1)  ← no extra stack, in-place reversal
    // ─────────────────────────────────────────────────────────
    public ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;  // 1. save next
            curr.next = prev;               // 2. reverse the pointer
            prev = curr;                    // 3. move prev forward
            curr = nextTemp;                // 4. move curr forward
        }
        return prev;  // prev is now the new head
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Recursive
    // Time  Complexity: O(n)
    // Space Complexity: O(n)  ← call stack depth = n
    //
    // LOGIC:
    //   Recursively reverse from the end.
    //   After recursion unwinds, wire head.next.next = head and head.next = null
    // ─────────────────────────────────────────────────────────
    public ListNode reverseRecursive(ListNode head) {
        // base case: empty or single node
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseRecursive(head.next);  // reverse the rest

        head.next.next = head;   // make the next node point back to current
        head.next = null;        // break the original forward link

        return newHead;
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q02_ReverseLinkedList solution = new Q02_ReverseLinkedList();

        ListNode list1 = ListNode.of(1, 2, 3, 4, 5);
        ListNode list2 = ListNode.of(1, 2, 3, 4, 5);

        System.out.println("=== Q2: Reverse Linked List ===");
        System.out.println("Original   : " + ListNode.toString(ListNode.of(1,2,3,4,5)));
        System.out.println("Iterative  : " + ListNode.toString(solution.reverseIterative(list1)));
        System.out.println("Recursive  : " + ListNode.toString(solution.reverseRecursive(list2)));
    }
}

package com.dipdeveloper.epam.solutions;

import com.dipdeveloper.epam.models.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * ============================================================
 * Q9. DETECT CYCLE IN A LINKED LIST
 * ============================================================
 * PROBLEM:
 *   Given the head of a linked list, determine if the linked list
 *   has a cycle in it.
 *
 *   Follow-up: Return the node where the cycle begins (not just true/false).
 *
 * EXAMPLE:
 *   3 → 2 → 0 → -4
 *           ↑_______↓    (tail connects back to node at index 1)
 *   Output: true, cycle starts at node with value 2
 *
 * EPAM INTERVIEW TIP:
 *   HashSet approach is the obvious answer. Floyd's Cycle Detection
 *   (fast/slow pointers) is what separates average from strong candidates.
 *   EPAM DEFINITELY asks the follow-up: "Find the start of the cycle."
 *   Know the mathematical proof for Floyd's — very impressive to explain.
 * ============================================================
 */
public class Q09_DetectCycle {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: HashSet (Store visited nodes)
    // Time  Complexity: O(n)
    // Space Complexity: O(n)  ← storing node references
    // ─────────────────────────────────────────────────────────
    public boolean hasCycleHashSet(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;

        while (curr != null) {
            if (!visited.add(curr)) {  // add() returns false if already present
                return true;           // revisited a node → cycle exists
            }
            curr = curr.next;
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Floyd's Cycle Detection (Fast & Slow Pointers) ← OPTIMAL
    // Time  Complexity: O(n)
    // Space Complexity: O(1)  ← only two pointers
    //
    // LOGIC:
    //   Slow moves 1 step at a time. Fast moves 2 steps at a time.
    //   If there's a cycle, they WILL meet inside the cycle.
    //   If no cycle, fast will reach null first.
    // ─────────────────────────────────────────────────────────
    public boolean hasCycleFloyd(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // move 1 step
            fast = fast.next.next;  // move 2 steps

            if (slow == fast) return true;  // they met → cycle detected
        }
        return false;  // fast hit null → no cycle
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 3: Floyd's — Find Cycle START Node ← FOLLOW-UP
    // Time  Complexity: O(n)
    // Space Complexity: O(1)
    //
    // MATHEMATICAL PROOF:
    //   Let: distance from head to cycle start = a
    //        distance from cycle start to meeting point = b
    //        remaining cycle length = c
    //
    //   When slow and fast meet:
    //     slow traveled: a + b
    //     fast traveled: a + b + c + b = a + 2b + c
    //     fast = 2 × slow  →  a + 2b + c = 2(a + b)  →  c = a
    //
    //   CONCLUSION: distance from head to cycle start (a)
    //               = distance from meeting point to cycle start (c)
    //
    //   So: reset one pointer to head, keep other at meeting point,
    //       move both 1 step at a time → they meet at cycle start!
    // ─────────────────────────────────────────────────────────
    public ListNode detectCycleStart(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if cycle exists and find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;  // meeting point found
        }

        // No cycle detected
        if (fast == null || fast.next == null) return null;

        // Phase 2: Find cycle start
        // Reset slow to head, keep fast at meeting point
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;  // cycle start node
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q09_DetectCycle solution = new Q09_DetectCycle();

        // Build: 3 → 2 → 0 → -4 → (back to node "2")
        ListNode head = new ListNode(3);
        ListNode cycleStart = new ListNode(2);
        head.next = cycleStart;
        cycleStart.next = new ListNode(0);
        cycleStart.next.next = new ListNode(-4);
        cycleStart.next.next.next = cycleStart; // creates cycle

        System.out.println("=== Q9: Detect Cycle in Linked List ===");
        System.out.println("HashSet (has cycle)     : " + solution.hasCycleHashSet(head));
        System.out.println("Floyd   (has cycle)     : " + solution.hasCycleFloyd(head));
        ListNode start = solution.detectCycleStart(head);
        System.out.println("Cycle start node value  : " + (start != null ? start.val : "null")); // 2

        // No cycle test
        ListNode noCycle = ListNode.of(1, 2, 3);
        System.out.println("No cycle list           : " + solution.hasCycleFloyd(noCycle)); // false
    }
}

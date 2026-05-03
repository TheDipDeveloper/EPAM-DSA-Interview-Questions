package com.dipdeveloper.epam;

import com.dipdeveloper.epam.models.ListNode;
import com.dipdeveloper.epam.models.TreeNode;
import com.dipdeveloper.epam.solutions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ============================================================
 * EPAM DSA Interview — All 10 Questions Test Suite
 * YouTube: Dip Developer
 * ============================================================
 */
@DisplayName("Dip Developer — EPAM DSA Interview Test Suite")
class EpamDsaInterviewTest {

    // ─────────────────────────────────────────────────────────
    // Q1 — Two Sum
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q1 — Two Sum")
    class TwoSumTest {
        private final Q01_TwoSum solution = new Q01_TwoSum();

        @Test void bruteForce_standard()         { assertArrayEquals(new int[]{0, 1}, solution.twoSumBruteForce(new int[]{2,7,11,15}, 9)); }
        @Test void hashMap_standard()            { assertArrayEquals(new int[]{0, 1}, solution.twoSumHashMap(new int[]{2,7,11,15}, 9)); }
        @Test void hashMap_middleElements()      { assertArrayEquals(new int[]{1, 2}, solution.twoSumHashMap(new int[]{3,2,4}, 6)); }
        @Test void twoPointers_found()           { assertTrue(solution.twoSumTwoPointers(new int[]{2,7,11,15}, 9)); }
        @Test void twoPointers_notFound()        { assertFalse(solution.twoSumTwoPointers(new int[]{1,2,3}, 10)); }
    }

    // ─────────────────────────────────────────────────────────
    // Q2 — Reverse Linked List
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q2 — Reverse Linked List")
    class ReverseLinkedListTest {
        private final Q02_ReverseLinkedList solution = new Q02_ReverseLinkedList();

        @Test
        @DisplayName("Iterative: 1->2->3->4->5 becomes 5->4->3->2->1")
        void iterative() {
            ListNode reversed = solution.reverseIterative(ListNode.of(1, 2, 3, 4, 5));
            assertEquals("[5 -> 4 -> 3 -> 2 -> 1]", ListNode.toString(reversed));
        }

        @Test
        @DisplayName("Recursive: same result")
        void recursive() {
            ListNode reversed = solution.reverseRecursive(ListNode.of(1, 2, 3, 4, 5));
            assertEquals("[5 -> 4 -> 3 -> 2 -> 1]", ListNode.toString(reversed));
        }

        @Test void singleNode()  { assertEquals("[42]",  ListNode.toString(solution.reverseIterative(ListNode.of(42)))); }
        @Test void emptyList()   { assertNull(solution.reverseIterative(null)); }
    }

    // ─────────────────────────────────────────────────────────
    // Q3 — Valid Parentheses
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q3 — Valid Parentheses")
    class ValidParenthesesTest {
        private final Q03_ValidParentheses solution = new Q03_ValidParentheses();

        @Test void allTypesValid()   { assertTrue(solution.isValidHashMap("()[]{}"));  }
        @Test void nestedValid()     { assertTrue(solution.isValidHashMap("{[]}"));    }
        @Test void wrongOrder()      { assertFalse(solution.isValidHashMap("([)]"));   }
        @Test void unclosed()        { assertFalse(solution.isValidHashMap("("));      }
        @Test void closedFirst()     { assertFalse(solution.isValidHashMap("]"));      }
        @Test void ifElseValid()     { assertTrue(solution.isValidIfElse("()[]{}"));  }
        @Test void emptyString()     { assertTrue(solution.isValidHashMap(""));        }
    }

    // ─────────────────────────────────────────────────────────
    // Q4 — Binary Search
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q4 — Binary Search")
    class BinarySearchTest {
        private final Q04_BinarySearch solution = new Q04_BinarySearch();
        private final int[] nums = {-1, 0, 3, 5, 9, 12};

        @Test void iterative_found()      { assertEquals(4, solution.searchIterative(nums, 9));  }
        @Test void iterative_notFound()   { assertEquals(-1, solution.searchIterative(nums, 2)); }
        @Test void recursive_found()      { assertEquals(4, solution.searchRecursive(nums, 9));  }
        @Test void recursive_notFound()   { assertEquals(-1, solution.searchRecursive(nums, 2)); }
        @Test void firstOccurrence()      { assertEquals(1, solution.findFirstOccurrence(new int[]{1,2,2,2,3}, 2)); }
    }

    // ─────────────────────────────────────────────────────────
    // Q5 — Longest Substring Without Repeating Characters
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q5 — Longest Substring No Repeat")
    class LongestSubstringTest {
        private final Q05_LongestSubstringNoRepeat solution = new Q05_LongestSubstringNoRepeat();

        @Test void standard()       { assertEquals(3, solution.lengthOfLongestHashMap("abcabcbb")); }
        @Test void allSame()        { assertEquals(1, solution.lengthOfLongestHashMap("bbbbb"));    }
        @Test void pwwkew()         { assertEquals(3, solution.lengthOfLongestHashMap("pwwkew"));   }
        @Test void empty()          { assertEquals(0, solution.lengthOfLongestHashMap(""));         }
        @Test void singleChar()     { assertEquals(1, solution.lengthOfLongestHashMap("z"));        }
        @Test void hashSetMatch()   { assertEquals(3, solution.lengthOfLongestHashSet("abcabcbb")); }
    }

    // ─────────────────────────────────────────────────────────
    // Q6 — Merge Two Sorted Arrays
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q6 — Merge Sorted Arrays")
    class MergeSortedArraysTest {
        private final Q06_MergeSortedArrays solution = new Q06_MergeSortedArrays();

        @Test
        void mergeFromEnd_standard() {
            int[] nums1 = {1, 2, 3, 0, 0, 0};
            solution.mergeFromEnd(nums1, 3, new int[]{2, 5, 6}, 3);
            assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
        }

        @Test
        void mergeFromEnd_allNums2Smaller() {
            int[] nums1 = {4, 5, 6, 0, 0, 0};
            solution.mergeFromEnd(nums1, 3, new int[]{1, 2, 3}, 3);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, nums1);
        }

        @Test
        void mergeFromEnd_nums2Empty() {
            int[] nums1 = {1, 2, 3};
            solution.mergeFromEnd(nums1, 3, new int[]{}, 0);
            assertArrayEquals(new int[]{1, 2, 3}, nums1);
        }
    }

    // ─────────────────────────────────────────────────────────
    // Q7 — Find All Duplicates
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q7 — Find All Duplicates")
    class FindDuplicatesTest {
        private final Q07_FindDuplicates solution = new Q07_FindDuplicates();

        @Test
        void hashSet_multipleDuplicates() {
            List<Integer> result = solution.findDuplicatesHashSet(new int[]{4,3,2,7,8,2,3,1});
            assertEquals(2, result.size());
            assertTrue(result.containsAll(List.of(2, 3)));
        }

        @Test
        void indexNegation_multipleDuplicates() {
            List<Integer> result = solution.findDuplicatesIndexNegation(new int[]{4,3,2,7,8,2,3,1});
            assertEquals(2, result.size());
            assertTrue(result.containsAll(List.of(2, 3)));
        }

        @Test
        void noDuplicates() {
            assertTrue(solution.findDuplicatesHashSet(new int[]{1,2,3,4}).isEmpty());
        }
    }

    // ─────────────────────────────────────────────────────────
    // Q8 — Level Order Traversal
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q8 — Level Order Traversal")
    class LevelOrderTest {
        private final Q08_LevelOrderTraversal solution = new Q08_LevelOrderTraversal();

        private TreeNode buildTree() {
            return new TreeNode(3,
                    new TreeNode(9),
                    new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        }

        @Test
        void bfs_standard() {
            List<List<Integer>> result = solution.levelOrderBFS(buildTree());
            assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), result);
        }

        @Test
        void dfs_standard() {
            List<List<Integer>> result = solution.levelOrderDFS(buildTree());
            assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), result);
        }

        @Test void nullRoot() { assertTrue(solution.levelOrderBFS(null).isEmpty()); }
    }

    // ─────────────────────────────────────────────────────────
    // Q9 — Detect Cycle
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q9 — Detect Cycle in Linked List")
    class DetectCycleTest {
        private final Q09_DetectCycle solution = new Q09_DetectCycle();

        private ListNode buildCyclicList() {
            ListNode head = new ListNode(3);
            ListNode cycleStart = new ListNode(2);
            head.next = cycleStart;
            cycleStart.next = new ListNode(0);
            cycleStart.next.next = new ListNode(-4);
            cycleStart.next.next.next = cycleStart;
            return head;
        }

        @Test void hashSet_hasCycle()       { assertTrue(solution.hasCycleHashSet(buildCyclicList())); }
        @Test void floyd_hasCycle()         { assertTrue(solution.hasCycleFloyd(buildCyclicList()));   }
        @Test void floyd_noCycle()          { assertFalse(solution.hasCycleFloyd(ListNode.of(1,2,3))); }
        @Test void cycleStart_value()       { assertEquals(2, solution.detectCycleStart(buildCyclicList()).val); }
        @Test void cycleStart_noCycle()     { assertNull(solution.detectCycleStart(ListNode.of(1,2,3)));  }
    }

    // ─────────────────────────────────────────────────────────
    // Q10 — Kth Largest Element
    // ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("Q10 — Kth Largest Element")
    class KthLargestTest {
        private final Q10_KthLargestElement solution = new Q10_KthLargestElement();

        @Test void sort_k2()          { assertEquals(5, solution.findKthLargestSort(new int[]{3,2,1,5,6,4}, 2)); }
        @Test void minHeap_k2()       { assertEquals(5, solution.findKthLargestMinHeap(new int[]{3,2,1,5,6,4}, 2)); }
        @Test void quickSelect_k2()   { assertEquals(5, solution.findKthLargestQuickSelect(new int[]{3,2,1,5,6,4}, 2)); }
        @Test void minHeap_k4()       { assertEquals(4, solution.findKthLargestMinHeap(new int[]{3,2,3,1,2,4,5,5,6}, 4)); }
        @Test void quickSelect_k1()   { assertEquals(6, solution.findKthLargestQuickSelect(new int[]{3,2,1,5,6,4}, 1)); }
    }
}

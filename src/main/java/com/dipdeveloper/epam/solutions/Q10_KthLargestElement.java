package com.dipdeveloper.epam.solutions;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * ============================================================
 * Q10. KTH LARGEST ELEMENT IN AN ARRAY
 * ============================================================
 * PROBLEM:
 *   Given an integer array nums and an integer k, return the
 *   kth largest element in the array.
 *
 *   Note: kth largest means kth largest IN SORTED ORDER,
 *         not kth distinct element.
 *
 * EXAMPLE:
 *   Input:  nums = [3, 2, 1, 5, 6, 4], k = 2
 *   Output: 5
 *
 *   Input:  nums = [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4
 *   Output: 4
 *
 * EPAM INTERVIEW TIP:
 *   3-level escalation expected:
 *     Level 1 (Junior): Sorting approach
 *     Level 2 (Mid):    Min-Heap of size k
 *     Level 3 (Senior): QuickSelect (O(n) average, like QuickSort partitioning)
 *   EPAM senior rounds specifically ask for QuickSelect.
 * ============================================================
 */
public class Q10_KthLargestElement {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Sort (Junior Level)
    // Time  Complexity: O(n log n)
    // Space Complexity: O(1)  — in-place sort
    // ─────────────────────────────────────────────────────────
    public int findKthLargestSort(int[] nums, int k) {
        Arrays.sort(nums);                  // sorts ascending
        return nums[nums.length - k];       // kth from end = kth largest
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Min-Heap of Size K (Mid Level) ← MOST COMMON ANSWER
    // Time  Complexity: O(n log k)  ← insert n elements into heap of size k
    // Space Complexity: O(k)
    //
    // KEY INSIGHT:
    //   Maintain a min-heap of the k largest elements seen so far.
    //   The root (minimum of the heap) is always the kth largest overall.
    //   If a new element > heap root, evict the root and insert new element.
    // ─────────────────────────────────────────────────────────
    public int findKthLargestMinHeap(int[] nums, int k) {
        // PriorityQueue is a min-heap by default in Java
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            // Keep heap size at exactly k
            if (minHeap.size() > k) {
                minHeap.poll();  // remove the smallest element
            }
        }

        return minHeap.peek();  // root = kth largest element
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 3: QuickSelect (Senior Level) ← OPTIMAL AVERAGE CASE
    // Time  Complexity: O(n) average, O(n²) worst case
    // Space Complexity: O(1) iterative / O(log n) recursive call stack
    //
    // LOGIC (Hoare's Partition):
    //   Like QuickSort but only recurse into the RELEVANT partition.
    //   We want index (n-k) in sorted order = kth largest.
    //   After partitioning around pivot, if pivot is at target index → done.
    //   Otherwise, recurse only into the half that contains target index.
    // ─────────────────────────────────────────────────────────
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int targetIndex = nums.length - k;   // kth largest = (n-k)th smallest
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) return nums[left];

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == targetIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            return quickSelect(nums, pivotIndex + 1, right, targetIndex);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, targetIndex);
        }
    }

    private int partition(int[] nums, int left, int right) {
        // Random pivot to avoid O(n²) worst case on sorted input
        int randomIndex = left + new Random().nextInt(right - left + 1);
        swap(nums, randomIndex, right);   // move pivot to end

        int pivot = nums[right];
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);  // place pivot in its final position
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q10_KthLargestElement solution = new Q10_KthLargestElement();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        System.out.println("=== Q10: Kth Largest Element ===");
        System.out.println("Sort        (k=2): " + solution.findKthLargestSort(nums.clone(), k));        // 5
        System.out.println("Min-Heap    (k=2): " + solution.findKthLargestMinHeap(nums.clone(), k));     // 5
        System.out.println("QuickSelect (k=2): " + solution.findKthLargestQuickSelect(nums.clone(), k)); // 5

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("Min-Heap    (k=4): " + solution.findKthLargestMinHeap(nums2.clone(), 4));    // 4
    }
}

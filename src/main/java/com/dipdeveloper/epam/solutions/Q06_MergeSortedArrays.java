package com.dipdeveloper.epam.solutions;

import java.util.Arrays;

/**
 * ============================================================
 * Q6. MERGE TWO SORTED ARRAYS
 * ============================================================
 * PROBLEM:
 *   You are given two integer arrays nums1 and nums2, sorted in
 *   non-decreasing order, and two integers m and n, representing
 *   the number of elements in nums1 and nums2 respectively.
 *
 *   Merge nums2 into nums1 as one sorted array IN-PLACE.
 *   nums1 has length m + n (extra zeros at the end as placeholder).
 *
 * EXAMPLE:
 *   Input:  nums1 = [1,2,3,0,0,0], m=3 | nums2 = [2,5,6], n=3
 *   Output: [1,2,2,3,5,6]
 *
 * EPAM INTERVIEW TIP:
 *   The "merge from the end" trick is the key insight here.
 *   If you fill from the front, you have to shift elements.
 *   Filling from the END avoids shifting → O(m+n) and O(1) space.
 * ============================================================
 */
public class Q06_MergeSortedArrays {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Extra Array (Straightforward)
    // Time  Complexity: O((m+n) log(m+n))  ← due to Arrays.sort
    // Space Complexity: O(m+n)
    // ─────────────────────────────────────────────────────────
    public void mergeWithExtraSpace(int[] nums1, int m, int[] nums2, int n) {
        // Copy nums2 into the tail of nums1
        System.arraycopy(nums2, 0, nums1, m, n);
        // Sort the combined array
        Arrays.sort(nums1);
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Two Pointers from Front (Requires temp copy)
    // Time  Complexity: O(m+n)
    // Space Complexity: O(m)  ← copy of nums1's first m elements
    // ─────────────────────────────────────────────────────────
    public void mergeTwoPointersFront(int[] nums1, int m, int[] nums2, int n) {
        int[] copy = Arrays.copyOf(nums1, m);  // save original nums1 values
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (copy[i] <= nums2[j]) nums1[k++] = copy[i++];
            else                     nums1[k++] = nums2[j++];
        }
        while (i < m) nums1[k++] = copy[i++];
        while (j < n) nums1[k++] = nums2[j++];
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 3: Two Pointers from END ← OPTIMAL
    // Time  Complexity: O(m+n)
    // Space Complexity: O(1)  ← truly in-place, no extra array
    //
    // KEY INSIGHT:
    //   Compare from the LARGEST elements and place at the END.
    //   This way we never overwrite elements we still need to process.
    // ─────────────────────────────────────────────────────────
    public void mergeFromEnd(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;         // pointer for nums1 (last valid element)
        int j = n - 1;         // pointer for nums2 (last element)
        int k = m + n - 1;     // pointer for fill position (end of nums1)

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If nums2 still has remaining elements, copy them
        // (nums1 leftover elements are already in place)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q06_MergeSortedArrays solution = new Q06_MergeSortedArrays();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        solution.mergeFromEnd(nums1, 3, nums2, 3);

        System.out.println("=== Q6: Merge Sorted Arrays ===");
        System.out.println("Merged: " + Arrays.toString(nums1)); // [1,2,2,3,5,6]
    }
}

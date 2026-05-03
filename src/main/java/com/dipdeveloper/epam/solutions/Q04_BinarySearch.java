package com.dipdeveloper.epam.solutions;

/**
 * ============================================================
 * Q4. BINARY SEARCH
 * ============================================================
 * PROBLEM:
 *   Given a sorted array of distinct integers and a target,
 *   return the index of target, or -1 if not found.
 *
 * EXAMPLE:
 *   Input:  nums = [-1, 0, 3, 5, 9, 12], target = 9
 *   Output: 4
 *
 * EPAM INTERVIEW TIP:
 *   The classic "off-by-one" trap. EPAM frequently asks:
 *   "Why left <= right and not left < right?"
 *   → Because when left == right, there is still ONE element to check.
 *   Also show the safe mid-point formula to avoid integer overflow.
 * ============================================================
 */
public class Q04_BinarySearch {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Iterative ← PREFERRED (O(1) space)
    // Time  Complexity: O(log n)
    // Space Complexity: O(1)
    // ─────────────────────────────────────────────────────────
    public int searchIterative(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // SAFE mid: avoids (left + right) overflow for large integers
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;   // target is in RIGHT half
            } else {
                right = mid - 1;  // target is in LEFT half
            }
        }
        return -1;  // not found
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Recursive
    // Time  Complexity: O(log n)
    // Space Complexity: O(log n)  ← recursive call stack
    // ─────────────────────────────────────────────────────────
    public int searchRecursive(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    private int binarySearchHelper(int[] nums, int target, int left, int right) {
        if (left > right) return -1;  // base case: not found

        int mid = left + (right - left) / 2;

        if (nums[mid] == target)      return mid;
        else if (nums[mid] < target)  return binarySearchHelper(nums, target, mid + 1, right);
        else                          return binarySearchHelper(nums, target, left, mid - 1);
    }

    // ─────────────────────────────────────────────────────────
    // BONUS: Find First and Last Position (Binary Search variant)
    // EPAM follow-up: "What if duplicates exist? Find first occurrence."
    // Time  Complexity: O(log n)
    // Space Complexity: O(1)
    // ─────────────────────────────────────────────────────────
    public int findFirstOccurrence(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;        // record and keep searching LEFT
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q04_BinarySearch solution = new Q04_BinarySearch();
        int[] nums = {-1, 0, 3, 5, 9, 12};

        System.out.println("=== Q4: Binary Search ===");
        System.out.println("Iterative  (target=9) : " + solution.searchIterative(nums, 9));
        System.out.println("Recursive  (target=9) : " + solution.searchRecursive(nums, 9));
        System.out.println("Iterative  (target=2) : " + solution.searchIterative(nums, 2));

        int[] withDuplicates = {1, 2, 2, 2, 3, 4};
        System.out.println("First occurrence of 2 : " + solution.findFirstOccurrence(withDuplicates, 2));
    }
}

package com.dipdeveloper.epam.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * Q1. TWO SUM
 * ============================================================
 * PROBLEM:
 *   Given an integer array nums and a target integer,
 *   return indices of the two numbers that add up to target.
 *   Each input has exactly one solution. Cannot use same element twice.
 *
 * EXAMPLE:
 *   Input:  nums = [2, 7, 11, 15], target = 9
 *   Output: [0, 1]  (because nums[0] + nums[1] = 2 + 7 = 9)
 *
 * EPAM INTERVIEW TIP:
 *   Always start with brute force to show you understand the problem,
 *   then optimize to HashMap. Interviewer wants to see your thought process.
 * ============================================================
 */
public class Q01_TwoSum {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Brute Force (Nested Loops)
    // Time  Complexity: O(n²)
    // Space Complexity: O(1)
    // ─────────────────────────────────────────────────────────
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};  // no solution found
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: HashMap (One Pass) ← OPTIMAL — use this in interview
    // Time  Complexity: O(n)
    // Space Complexity: O(n)
    //
    // LOGIC:
    //   For each num, check if (target - num) already exists in the map.
    //   If yes → found the pair. If no → store num with its index.
    // ─────────────────────────────────────────────────────────
    public int[] twoSumHashMap(int[] nums, int target) {
        // key = number value, value = its index
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{};
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 3: Two Pointers on Sorted Array
    // Time  Complexity: O(n log n)  ← due to sorting
    // Space Complexity: O(n)        ← to store original indices
    //
    // NOTE: Only works if you DON'T need original indices,
    //       or if the array is already sorted.
    // ─────────────────────────────────────────────────────────
    public boolean twoSumTwoPointers(int[] nums, int target) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int left = 0;
        int right = sorted.length - 1;

        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target)  return true;
            else if (sum < target) left++;
            else                   right--;
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q01_TwoSum solution = new Q01_TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("=== Q1: Two Sum ===");
        System.out.println("Brute Force  : " + Arrays.toString(solution.twoSumBruteForce(nums, target)));
        System.out.println("HashMap      : " + Arrays.toString(solution.twoSumHashMap(nums, target)));
        System.out.println("Two Pointers : " + solution.twoSumTwoPointers(nums, target));
    }
}

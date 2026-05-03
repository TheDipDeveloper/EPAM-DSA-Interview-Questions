package com.dipdeveloper.epam.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ============================================================
 * Q7. FIND ALL DUPLICATES IN AN ARRAY
 * ============================================================
 * PROBLEM:
 *   Given an integer array nums of length n where all integers
 *   are in range [1, n] and each integer appears once or twice,
 *   return an array of all the integers that appear twice.
 *
 *   Follow-up: Can you solve it in O(n) time and O(1) extra space?
 *
 * EXAMPLE:
 *   Input:  [4, 3, 2, 7, 8, 2, 3, 1]
 *   Output: [2, 3]
 *
 * EPAM INTERVIEW TIP:
 *   The HashSet approach is the expected answer. But if the interviewer
 *   says "O(1) space", immediately switch to the negative marking trick.
 *   That trick is only valid when nums[i] is in range [1, n] — be sure
 *   to mention this constraint to the interviewer.
 * ============================================================
 */
public class Q07_FindDuplicates {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Brute Force (Nested Loops)
    // Time  Complexity: O(n²)
    // Space Complexity: O(1)  (ignoring output list)
    // ─────────────────────────────────────────────────────────
    public List<Integer> findDuplicatesBruteForce(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && !result.contains(nums[i])) {
                    result.add(nums[i]);
                }
            }
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: HashSet ← STANDARD ANSWER
    // Time  Complexity: O(n)
    // Space Complexity: O(n)
    // ─────────────────────────────────────────────────────────
    public List<Integer> findDuplicatesHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            if (!seen.add(num)) {   // add() returns false if already present
                result.add(num);
            }
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 3: Index Negation (In-Place Marking) ← FOLLOW-UP ANSWER
    // Time  Complexity: O(n)
    // Space Complexity: O(1)  ← modifies input array as the "marker"
    //
    // KEY INSIGHT:
    //   Since values are in range [1, n], we can use the value as an index.
    //   When we visit value v, flip nums[v-1] to negative.
    //   If we try to flip it again and it's already negative → DUPLICATE.
    //
    // NOTE: This mutates the input array. Mention this to the interviewer.
    // ─────────────────────────────────────────────────────────
    public List<Integer> findDuplicatesIndexNegation(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            int index = Math.abs(num) - 1;  // convert value to 0-based index

            if (nums[index] < 0) {
                result.add(Math.abs(num));  // already flipped → duplicate
            } else {
                nums[index] = -nums[index]; // flip to mark as visited
            }
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q07_FindDuplicates solution = new Q07_FindDuplicates();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};

        System.out.println("=== Q7: Find All Duplicates ===");
        System.out.println("HashSet         : " + solution.findDuplicatesHashSet(nums.clone()));
        System.out.println("Index Negation  : " + solution.findDuplicatesIndexNegation(nums.clone()));
    }
}

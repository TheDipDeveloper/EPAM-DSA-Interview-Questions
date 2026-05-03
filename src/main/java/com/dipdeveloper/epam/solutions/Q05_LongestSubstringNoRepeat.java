package com.dipdeveloper.epam.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ============================================================
 * Q5. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
 * ============================================================
 * PROBLEM:
 *   Given a string s, find the length of the longest substring
 *   that contains no repeating characters.
 *
 * EXAMPLE:
 *   Input:  "abcabcbb"  → Output: 3  ("abc")
 *   Input:  "bbbbb"     → Output: 1  ("b")
 *   Input:  "pwwkew"    → Output: 3  ("wke")
 *
 * EPAM INTERVIEW TIP:
 *   This is the #1 Sliding Window question in EPAM rounds.
 *   Start with the HashSet approach (easy to explain),
 *   then optimize to HashMap to jump the left pointer directly.
 *   This shows interviewer you can optimize iteratively.
 * ============================================================
 */
public class Q05_LongestSubstringNoRepeat {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Brute Force
    // Time  Complexity: O(n³)  ← O(n²) windows × O(n) uniqueness check
    // Space Complexity: O(min(n, m))  where m = charset size
    // ─────────────────────────────────────────────────────────
    public int lengthOfLongestBruteForce(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }
        return maxLen;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> chars = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (!chars.add(s.charAt(i))) return false;
        }
        return true;
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Sliding Window with HashSet
    // Time  Complexity: O(2n) = O(n)  ← left and right each traverse once
    // Space Complexity: O(min(n, m))
    // ─────────────────────────────────────────────────────────
    public int lengthOfLongestHashSet(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            // Shrink window from left until no duplicate
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 3: Sliding Window with HashMap ← OPTIMAL
    // Time  Complexity: O(n)  ← left pointer jumps directly, no while loop
    // Space Complexity: O(min(n, m))
    //
    // KEY INSIGHT:
    //   Store the LAST SEEN INDEX of each character.
    //   When a duplicate is found, jump left pointer directly to
    //   (last seen index + 1) instead of shrinking one step at a time.
    // ─────────────────────────────────────────────────────────
    public int lengthOfLongestHashMap(String s) {
        // key = character, value = last seen index
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character was seen AND is inside current window → jump left
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }

            lastSeen.put(c, right);  // update last seen index
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q05_LongestSubstringNoRepeat solution = new Q05_LongestSubstringNoRepeat();

        System.out.println("=== Q5: Longest Substring Without Repeating Chars ===");
        System.out.println("'abcabcbb' → " + solution.lengthOfLongestHashMap("abcabcbb")); // 3
        System.out.println("'bbbbb'    → " + solution.lengthOfLongestHashMap("bbbbb"));    // 1
        System.out.println("'pwwkew'   → " + solution.lengthOfLongestHashMap("pwwkew"));   // 3
    }
}

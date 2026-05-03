package com.dipdeveloper.epam.solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * ============================================================
 * Q3. VALID PARENTHESES
 * ============================================================
 * PROBLEM:
 *   Given a string s containing only '(', ')', '{', '}', '[', ']',
 *   determine if the input string is valid.
 *
 *   Valid rules:
 *   1. Open brackets must be closed by the same type of bracket.
 *   2. Open brackets must be closed in the correct order.
 *   3. Every close bracket has a corresponding open bracket.
 *
 * EXAMPLE:
 *   Input:  "()[]{}"  → true
 *   Input:  "([)]"    → false
 *   Input:  "{[]}"    → true
 *
 * EPAM INTERVIEW TIP:
 *   Classic Stack problem. Always use Deque over Stack in Java
 *   (Stack is legacy; Deque is the preferred interface).
 *   Show the HashMap trick for clean bracket matching.
 * ============================================================
 */
public class Q03_ValidParentheses {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: Stack with HashMap (Clean & Scalable) ← OPTIMAL
    // Time  Complexity: O(n)
    // Space Complexity: O(n)  ← worst case all opening brackets pushed
    // ─────────────────────────────────────────────────────────
    public boolean isValidHashMap(String s) {
        // Maps closing bracket → its matching opening bracket
        Map<Character, Character> matchMap = Map.of(
                ')', '(',
                '}', '{',
                ']', '['
        );

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (matchMap.containsKey(c)) {
                // It's a closing bracket
                if (stack.isEmpty() || stack.peek() != matchMap.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                // It's an opening bracket → push onto stack
                stack.push(c);
            }
        }

        return stack.isEmpty();  // valid only if all brackets matched
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: Stack with if-else (Explicit, easy to explain verbally)
    // Time  Complexity: O(n)
    // Space Complexity: O(n)
    // ─────────────────────────────────────────────────────────
    public boolean isValidIfElse(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            // Push corresponding closing bracket when we see an opener
            if      (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                // c is a closing bracket
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q03_ValidParentheses solution = new Q03_ValidParentheses();

        System.out.println("=== Q3: Valid Parentheses ===");
        System.out.println("'()[]{}'  → " + solution.isValidHashMap("()[]{}"));
        System.out.println("'([)]'    → " + solution.isValidHashMap("([)]"));
        System.out.println("'{[]}'    → " + solution.isValidHashMap("{[]}"));
        System.out.println("'('       → " + solution.isValidHashMap("("));
    }
}

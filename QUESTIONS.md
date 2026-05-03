# 🎯 EPAM Systems — Top 10 DSA Interview Questions
### Java / Software Engineer | 0–6 Years Experience
> **YouTube: Dip Developer** | All approaches, time/space complexity, and EPAM-specific tips in one place.

---

## ⚡ Complexity Quick Reference Table

| # | Problem | Best Approach | Time | Space |
|---|---------|--------------|------|-------|
| Q1 | Two Sum | HashMap (One Pass) | O(n) | O(n) |
| Q2 | Reverse Linked List | Iterative (3-pointer) | O(n) | **O(1)** |
| Q3 | Valid Parentheses | Stack + HashMap | O(n) | O(n) |
| Q4 | Binary Search | Iterative | O(log n) | **O(1)** |
| Q5 | Longest Substring No Repeat | Sliding Window + HashMap | O(n) | O(m) |
| Q6 | Merge Sorted Arrays | Two Pointers from End | O(m+n) | **O(1)** |
| Q7 | Find All Duplicates | Index Negation | O(n) | **O(1)** |
| Q8 | Level Order Traversal | BFS with Queue | O(n) | O(n) |
| Q9 | Detect Cycle | Floyd's Algorithm | O(n) | **O(1)** |
| Q10 | Kth Largest Element | Min-Heap / QuickSelect | O(n log k) / O(n) avg | O(k) / O(1) |

> **Bold O(1)** = interviewer brownie points for mentioning constant space!

---

## Q1. Two Sum

**Problem:** Find indices of two numbers that add up to `target`.

### Approach 1 — Brute Force
```
Time: O(n²) | Space: O(1)
```
- Nested loops: check every pair `(i, j)` where `i < j`.
- Return when `nums[i] + nums[j] == target`.

### Approach 2 — HashMap One Pass ✅ OPTIMAL
```
Time: O(n) | Space: O(n)
```
- For each `nums[i]`, compute `complement = target - nums[i]`.
- If complement is in the map → return pair of indices.
- Else → store `nums[i]` with its index in the map.

### Approach 3 — Two Pointers (sorted array only)
```
Time: O(n log n) | Space: O(n)
```
- Only valid if indices don't matter. Sort → left + right pointers.

**EPAM Tip:** Start with brute force verbally, then say "We can optimize this to O(n) using a HashMap." This structured thinking impresses interviewers.

---

## Q2. Reverse a Linked List

**Problem:** Reverse a singly linked list in-place.

### Approach 1 — Iterative (3-Pointer) ✅ PREFERRED
```
Time: O(n) | Space: O(1)
```
```
prev = null | curr = head
LOOP: save next → flip pointer → advance prev → advance curr
Return: prev (new head)
```

### Approach 2 — Recursive
```
Time: O(n) | Space: O(n)  ← call stack
```
- Recurse to end. On unwind: `head.next.next = head`, `head.next = null`.

**EPAM Tip:** "Can you do this without extra memory?" → they want iterative O(1). Always mention call stack cost of recursion.

---

## Q3. Valid Parentheses

**Problem:** Check if bracket sequence is valid using `(){}[]`.

### Approach 1 — Stack + HashMap ✅ OPTIMAL
```
Time: O(n) | Space: O(n)
```
- Map: `')' → '('`, `'}' → '{'`, `']' → '['`.
- For closing bracket: stack must have matching opener on top.
- For opening bracket: push onto stack.
- Valid only if stack is empty at end.

### Approach 2 — Stack + if-else (easier to verbalize)
```
Time: O(n) | Space: O(n)
```
- On opening `(`, push `)` (the expected closer) onto stack.
- On closing: pop and compare.

**EPAM Tip:** Always use `Deque<Character>` not `Stack<>`. Stack is legacy in Java. Say "I prefer Deque since Stack is a legacy class that inherits from Vector."

---

## Q4. Binary Search

**Problem:** Find index of target in sorted array, or -1.

### Approach 1 — Iterative ✅ PREFERRED
```
Time: O(log n) | Space: O(1)
```
```
while (left <= right):
    mid = left + (right - left) / 2   ← safe overflow formula!
    if nums[mid] == target → return mid
    if nums[mid] < target  → left  = mid + 1
    else                   → right = mid - 1
```

### Approach 2 — Recursive
```
Time: O(log n) | Space: O(log n)  ← call stack
```

### Bonus — Find First Occurrence (Duplicates follow-up)
```
Time: O(log n) | Space: O(1)
```
- When `nums[mid] == target`, record result but keep `right = mid - 1` to search left.

**EPAM Tip:** The classic trap: `left <= right` not `left < right`. When `left == right`, one element still unchecked. Also mention `left + (right - left) / 2` to avoid int overflow.

---

## Q5. Longest Substring Without Repeating Characters

**Problem:** Find length of longest substring with all unique chars.

### Approach 1 — Brute Force
```
Time: O(n³) | Space: O(min(n,m))
```
- All O(n²) substrings × O(n) uniqueness check.

### Approach 2 — Sliding Window + HashSet
```
Time: O(2n) = O(n) | Space: O(min(n,m))
```
- Expand right. Shrink left (one step at a time) until no duplicate.

### Approach 3 — Sliding Window + HashMap ✅ OPTIMAL
```
Time: O(n) | Space: O(min(n,m))
```
- Store `char → last seen index`.
- When duplicate found: jump `left = lastSeen[char] + 1` directly.
- No while loop needed — single pass.

**EPAM Tip:** This is the #1 sliding window question in EPAM. Clearly explain the "jump left pointer" optimization over HashSet approach.

> `m` = size of character set (e.g., 128 for ASCII, 26 for lowercase only)

---

## Q6. Merge Two Sorted Arrays

**Problem:** Merge `nums2` into `nums1` in-place (nums1 has extra zeros).

### Approach 1 — Sort-based
```
Time: O((m+n) log(m+n)) | Space: O(1)
```
- Copy nums2 into tail of nums1, then `Arrays.sort(nums1)`.

### Approach 2 — Two Pointers from Front
```
Time: O(m+n) | Space: O(m)  ← needs copy of nums1
```
- Compare front of both arrays, fill nums1 from index 0.

### Approach 3 — Two Pointers from END ✅ OPTIMAL
```
Time: O(m+n) | Space: O(1)
```
```
i = m-1 (nums1 last valid)
j = n-1 (nums2 last)
k = m+n-1 (fill from end)

while i >= 0 AND j >= 0:
    place max(nums1[i], nums2[j]) at nums1[k]
```

**EPAM Tip:** "Why fill from the end?" → Filling from front would overwrite nums1 values we still need. Filling from end avoids shifts entirely.

---

## Q7. Find All Duplicates in an Array

**Problem:** Array of length n, values in [1,n], each appears 1 or 2 times. Return duplicates.

### Approach 1 — Brute Force
```
Time: O(n²) | Space: O(1)
```

### Approach 2 — HashSet ✅ STANDARD ANSWER
```
Time: O(n) | Space: O(n)
```
- `set.add()` returns `false` if already present → duplicate found.

### Approach 3 — Index Negation ✅ O(1) SPACE FOLLOW-UP
```
Time: O(n) | Space: O(1)
```
- For each `num`, use `abs(num) - 1` as index.
- Negate `nums[index]`. If already negative → it's a duplicate.

**EPAM Tip:** Index negation only works because values are in [1,n]. Always state this constraint to the interviewer. Also mention: "This mutates the input array."

---

## Q8. Binary Tree Level Order Traversal

**Problem:** Return node values level by level (BFS).

### Approach 1 — BFS with Queue ✅ STANDARD
```
Time: O(n) | Space: O(n)  ← O(n/2) nodes at leaf level
```
```
Enqueue root.
While queue not empty:
    levelSize = queue.size()   ← snapshot = nodes at THIS level
    Process exactly levelSize nodes, enqueue their children.
    Add level list to result.
```

### Approach 2 — DFS with Level Tracking
```
Time: O(n) | Space: O(h)  ← h = tree height
```
- Pass `level` in recursion. If `level == result.size()` → new level, add new list.

**EPAM Tip:** BFS with Queue is always the go-to. DFS approach shows deeper understanding if interviewer asks for alternatives.

---

## Q9. Detect Cycle in Linked List

**Problem:** Does the linked list have a cycle? Bonus: Find the cycle start node.

### Approach 1 — HashSet
```
Time: O(n) | Space: O(n)
```
- Store visited nodes. If you see the same node again → cycle.

### Approach 2 — Floyd's Cycle Detection ✅ OPTIMAL
```
Time: O(n) | Space: O(1)
```
- Slow = 1 step/iter. Fast = 2 steps/iter.
- If they meet → cycle exists. If fast reaches null → no cycle.

### Approach 3 — Find Cycle Start (Floyd's Phase 2)
```
Time: O(n) | Space: O(1)
```
**Mathematical Proof:**
```
Let: a = head → cycle start
     b = cycle start → meeting point
     c = meeting point → cycle start (remaining)

When they meet:
  fast = 2 × slow → a + 2b + c = 2(a+b) → c = a

CONCLUSION: Reset one pointer to head.
            Move both 1 step at a time.
            They meet at the cycle start.
```

**EPAM Tip:** This proof gets you BIG points in senior EPAM rounds. Memorize it. Draw it on the whiteboard.

---

## Q10. Kth Largest Element

**Problem:** Find the Kth largest element in an unsorted array.

### Approach 1 — Sort (Junior Level)
```
Time: O(n log n) | Space: O(1)
```
- Sort ascending. Return `nums[n-k]`.

### Approach 2 — Min-Heap ✅ MID-LEVEL EXPECTED ANSWER
```
Time: O(n log k) | Space: O(k)
```
- Maintain a min-heap of size k.
- If heap grows beyond k: `poll()` (removes the smallest).
- After processing all elements: `peek()` = kth largest.

```
Why min-heap, not max-heap?
  Min-heap root = smallest of the top-k = kth largest overall.
  We evict anything smaller than our running top-k.
```

### Approach 3 — QuickSelect ✅ SENIOR LEVEL
```
Time: O(n) average, O(n²) worst | Space: O(1) iterative
```
- Like QuickSort's partition but only recurse into the partition containing `targetIndex = n - k`.
- Use random pivot to avoid O(n²) worst case.
- Once pivot lands at `targetIndex` → that's the answer.

**EPAM Tip:**
- Junior → mentions sorting.
- Mid → min-heap. Explain "Why min-heap not max-heap?" clearly.
- Senior → QuickSelect. Mention random pivot + worst vs average case distinction.

---

## 📋 EPAM Interview Strategy Tips

### General
- **Always think aloud.** EPAM values problem-solving process over just the answer.
- **State constraints first:** null input? empty array? single element?
- **Start brute force, optimize step-by-step.** Never jump to optimal without explaining why brute force fails.
- **Time/Space complexity is mandatory.** State it after every approach.

### Java-Specific Signals EPAM Looks For
| ❌ Don't use | ✅ Use instead | Why |
|---|---|---|
| `Stack<>` | `Deque<>` / `ArrayDeque<>` | Stack is legacy, extends Vector |
| `(left + right) / 2` | `left + (right - left) / 2` | Prevents integer overflow |
| `HashMap` for graph visited | `HashSet` | Semantically clearer |
| Recursive DFS on large input | Iterative with explicit stack | Stack overflow risk |

### Data Structures → When to Use
| Scenario | Use |
|---|---|
| "O(1) lookup / uniqueness" | `HashMap` / `HashSet` |
| "Process in order" | `Queue` / `ArrayDeque` |
| "LIFO / matching" | `Deque` as stack |
| "Smallest/Largest of K" | `PriorityQueue` (min/max heap) |
| "Sorted + search" | Binary Search / TreeMap |
| "Two-pointer sliding" | Sorted array / String problems |

---

*Made with ❤️ for the Dip Developer YouTube community.*
*Subscribe for more Java backend, Spring Boot, Microservices & Interview Prep content!*

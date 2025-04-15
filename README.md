# 🚀 Good Triplets Counter using BIT (Binary Indexed Tree)

This Java project provides an efficient solution to count the number of **good triplets** in two permutations of `[0, 1, ..., n-1]`.

---

## 📌 Problem Statement

Given two arrays `nums1` and `nums2`, both permutations of `[0, 1, ..., n - 1]`, a **good triplet** `(x, y, z)` satisfies:

- The values are distinct.
- The order of positions of `(x, y, z)` is strictly increasing in both arrays.

🔍 Your task is to count the number of such good triplets.

---

## 🧠 Approach

The solution uses:

- **Mapping** of elements in `nums2` for quick position lookup.
- **Transformation** of `nums1` based on those positions.
- **Binary Indexed Trees (Fenwick Trees)** to:
  - Count smaller elements to the left.
  - Count larger elements to the right.

Finally, we multiply the left and right counts for each element to compute how many triplets it can form as a middle value.

⏱ Time Complexity: **O(n log n)**  
📦 Space Complexity: **O(n)**

---

## 📂 Structure

- `ImplementGoodTripletsCounterUsingBIT.java` — main class with logic and test case
- `BIT` — helper class implementing the Binary Indexed Tree

---

## 🧪 Sample Test Case

```java
nums1 = [4, 0, 1, 3, 2];
nums2 = [4, 1, 0, 2, 3];

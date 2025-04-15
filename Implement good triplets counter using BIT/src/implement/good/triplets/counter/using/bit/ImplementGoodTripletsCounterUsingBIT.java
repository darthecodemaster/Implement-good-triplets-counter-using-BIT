/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package implement.good.triplets.counter.using.bit;

/**
 *
 * @author ASUS
 */
public class ImplementGoodTripletsCounterUsingBIT {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        int[] nums1 = {4, 0, 1, 3, 2};
        int[] nums2 = {4, 1, 0, 2, 3};

        long result = countGoodTriplets(nums1, nums2);
        System.out.println("Total good triplets: " + result);
    }

    public static long countGoodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Step 1: Map value to index in nums2
        int[] posInNums2 = new int[n];
        for (int i = 0; i < n; i++) {
            posInNums2[nums2[i]] = i;
        }

        // Step 2: Transform nums1 based on nums2 positions
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = posInNums2[nums1[i]];
        }

        // Step 3: Count smaller to the left and greater to the right
        BIT bitLeft = new BIT(n);
        int[] leftSmaller = new int[n];

        for (int i = 0; i < n; i++) {
            leftSmaller[i] = bitLeft.query(arr[i] - 1);
            bitLeft.update(arr[i], 1);
        }

        BIT bitRight = new BIT(n);
        int[] rightLarger = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            rightLarger[i] = bitRight.query(n - 1) - bitRight.query(arr[i]);
            bitRight.update(arr[i], 1);
        }

        // Step 4: Calculate the total good triplets
        long count = 0;
        for (int i = 0; i < n; i++) {
            count += (long) leftSmaller[i] * rightLarger[i];
        }

        return count;
    }

    // Binary Indexed Tree (Fenwick Tree) class
    static class BIT {
        int[] tree;
        int n;

        public BIT(int size) {
            n = size;
            tree = new int[n + 1];
        }

        public void update(int index, int delta) {
            index++; // convert to 1-based index
            while (index <= n) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        public int query(int index) {
            index++; // convert to 1-based index
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
}

//**🔧 How We Solve It:
//Map positions
//First, we build a map of where each number appears in nums2.
//Rebuild nums1
//Then, we create a new array where each element is replaced by its position in nums2. Now the problem becomes finding triplets that increase in this new array.
//Count increasing triplets
//We go through this array and count:
//How many smaller elements are to the left? (using a BIT)
//How many larger elements are to the right? (another BIT)
//Multiply those two counts for each element → gives number of triplets with that element in the middle.
//Sum it up
//Add all those products together and that’s your final count of good triplets.

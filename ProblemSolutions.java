
/******************************************************************
 *
 *   Arthur Heredia / COMP 272 002 F25
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     *
     * -----------------------------------------------------------------------------------------------------------------
     *
     * Pseudocode:
     *
     * 1. Create a max priority queue (reverse order)
     * 2. Insert all boulders into the queue
     * 3. While there are at least two boulders:
     *    a. Remove the two largest (x and y)
     *    b. If x != y, insert the result (y - x) back into the queue
     * 4. If the queue is empty, return 0; otherwise, return the last element
     *
     */

  public static int lastBoulder(int[] boulders) {

      // 1. Create a max priority queue so that largest boulders come first
      java.util.PriorityQueue<Integer> pq =
              new java.util.PriorityQueue<>(java.util.Collections.reverseOrder());

      // 2. Add all boulders to the priority queue
      for (int boulder : boulders) {
          pq.add(boulder);
      }

      // 3. Keep smashing until one or zero boulders remain
      while (pq.size() > 1) {
          // 3a. Remove two heaviest boulders
          int y = pq.poll();  // largest
          int x = pq.poll();  // second largest

          // 3b. If they are not equal, add the remaining difference
          if (y != x) {
              pq.add(y - x);
          }
          // If equal, both are destroyed and nothing is added back
      }

      // 4. Return the remaining boulder weight, or 0 if none remain
      return pq.isEmpty() ? 0 : pq.peek();
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     *
     *------------------------------------------------------------------------------------------------------------------
     *
     * Pseudocode:
     *
     * 1. Create two HashSets: one called seen and one called dupes
     * 2. For each word in the input list:
     *      a. Try to add the word to seen
     *      b. If adding fails (already in seen), put the word into dupes
     * 3. Convert dupes into an ArrayList called result
     * 4. Sort result in ascending order using Collections.sort
     * 5. Return result
     *
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {
        // 1. Make two sets: one for first time seen, one for duplicates
        java.util.HashSet<String> seen = new java.util.HashSet<>();
        java.util.HashSet<String> dups  = new java.util.HashSet<>();

        // 2. Loop through each word and detect duplicates
        for (String word : input) {
            // 2a/2b. add() returns false if already present -> duplicate
            if (!seen.add(word)) {
                dups.add(word);
            }
        }

        // 3. move the duplicates into a list
        ArrayList<String> result = new ArrayList<>(dups);

        // 4. sort ascending
        java.util.Collections.sort(result);

        // 5. return the sorted duplicates
        return result;
    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     *
     * -----------------------------------------------------------------------------------------------------------------
     *
     * Pseudocode:
     *
     * 1. Create a HashSet<Integer> called seen to track numbers already processed
     * 2. Create a HashSet<String> called pairs to store unique string pairs
     * 3. For each number in input array:
     *      a. Compute target = k - num
     *      b. If target is in seen, we have a valid pair
     *           i.  Sort the pair so smaller number comes first
     *           ii. Build the string "(smaller, larger)"
     *           iii.Add this string to pairs
     *      c. Add num to seen for future checks
     * 4. Convert pairs into an ArrayList<String> called result
     * 5. Sort result in ascending order with Collections.sort()
     * 6. Return result
     *
     */

    public static ArrayList<String> pair(int[] input, int k) {

        // 1. Track numbers we've seen so far
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();

        // 2. Track unique pairs found
        java.util.HashSet<String> pairs = new java.util.HashSet<>();

        // 3. Loop through each number in the array
        for (int num : input) {
            int target = k - num;

            // 3b. If target is already seen, form a valid pair
            if (seen.contains(target)) {
                int small = Math.min(num, target);
                int large = Math.max(num, target);

                // Build pair string and add it to the set
                String pairStr = "(" + small + ", " + large + ")";
                pairs.add(pairStr);
            }

            // 3c. Add num to seen for future lookups
            seen.add(num);
        }

        // 4. Convert set of pairs into a list
        ArrayList<String> result = new ArrayList<>(pairs);

        // 5. Sort the list alphabetically
        java.util.Collections.sort(result);

        // 6. Return the sorted list of pairs
        return result;

    }
}
package kr.co.simol;

import java.util.*;

public class Solution {
    public int solution(int[] numbers, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int n : numbers) {
            queue.offer(n);
        }

        for (int i=1; i<k; i++) {
            int poll1 = queue.poll();
            int poll2 = queue.poll();
            queue.offer(poll1);
            queue.offer(poll2);
        }

        return queue.peek();
    }

}

package kr.co.simol.programers.sort.m42584;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {1, 2, 3, 2, 3};
        solution.solution(prices);
    }
    public int[] solution(int[] prices) {
        Checker checker = new Checker();
        checker.addAll(prices);
        checker.check(prices);
        return checker.getAnswer();
    }
}



class Checker {
    private Queue<Integer> queue;
    private int[] answer;

    public Checker() {
        this.queue = new LinkedList<>();
    }

    public void addAll(int[] prices) {
        for (int i=0; i<prices.length; i++) {
            int price = prices[i];
            this.queue.offer(price);
        }

        this.answer = new int[prices.length];
    }

    public void check(int[] prices) {
        for (int i=0; i<this.answer.length; i++) {
            int targetNumber = this.queue.poll();
            int count = 0;
            for (int j=i+1; j<prices.length; j++) {
                int compareNumber = prices[j];
                if (targetNumber > compareNumber) {
                    count++;
                    break;
                }
                if (targetNumber <= compareNumber) {
                    count++;
                }
            }
            this.answer[i] = count;
        }
    }

    public int[] getAnswer() {
        return this.answer;
    }
}
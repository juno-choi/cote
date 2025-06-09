package kr.co.simol.programers.skilcheck.lv1.m2;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] food = {1,3,4,6};
        Solution solution = new Solution();
        solution.solution(food);
    }
}

class Solution {
    public String solution(int[] food) {
        Stack<Integer> stack = new Stack<>();
        int foodNumber = 1;
        for (int i=1; i<food.length; i++) {
            int f = food[i];
            for (int j=0; j<f; j++) {
                stack.add(foodNumber);
            }
            foodNumber++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("0");
        while (! stack.isEmpty()) {
            if (stack.size() <= 1) {
                break;
            }
            int pop = stack.pop();
            int peek = stack.peek();
            if (pop == peek) {
                int pop2 = stack.pop();
                sb.insert(0, pop);
                sb.append(pop2);
            }
        }

        return sb.toString();
    }
}
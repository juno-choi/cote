package kr.co.simol.study.datastructure.maxsequence;

import java.util.*;

/**
 * set에 순서대로 정렬해두려면 treeSet!
 */
public class Main {
    public static void main(String[] args) {
//        8, 1, 9, 3, 10, 2, 4, 0, 2, 3
//        -3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1
        int[] nums = {-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7};
        Solution solution = new Solution();
        int sol = solution.solution(nums);
        System.out.println(sol);
    }
}

class Solution {
    public int solution(int[] nums) {
        // 순서대로 정렬
        Calculator calculator = new Calculator();
        calculator.calculator(nums);
        return calculator.getMax();
    }
}

class Calculator {
    Stack<Integer> stack;
    int max;
    int before;

    public Calculator() {
        this.stack = new Stack<>();
        this.max = -1;
        this.before = Integer.MIN_VALUE;
    }

    public void calculator(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int i : nums) {
            set.add(i);
        }

        for (int i : set) {
            // 이전 수보다 2이상 크다면 before 변경하고 clear후 push
            if (Math.abs(i - this.before) > 1) {
                int size = this.stack.size();
                if (this.max < size) {
                    this.max = size;
                }
                this.stack.clear();
            }
            // 이전 수보다 1이 크다면 push
            stack.push(i);
            this.before = i;
        }
    }

    public int getMax() {
        return this.max == -1 ? this.stack.size() : this.max;
    }

}
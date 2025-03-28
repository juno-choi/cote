package kr.co.simol.study.greedy.sprinkler;

import java.util.*;
import java.util.stream.*;

/**
 * while while 문에 대해 이해하고 기억하고 있자!
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}, 8));
        System.out.println(solution.solution(new int[]{1, 2, 2, 0, 0}, 4));
        System.out.println(solution.solution(new int[]{2, 0, 0, 0, 0, 2}, 5));
        System.out.println(solution.solution(new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}, 11));
    }
}

class Solution {
    public int solution(int[] nums, int n) {
        Checker checker = new Checker(nums, n);
        checker.sprinklerSort();
        return checker.calculator();
    }
}

class Sprinkler {
    int left;
    int right;

    public Sprinkler(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

class Checker {
    List<Sprinkler> checkList;
    int n;

    public Checker(int[] nums, int n) {
        this.checkList = new ArrayList<>();

        for (int i=0; i<nums.length; i++) {
            int power = nums[i];
            int leftPower = i-power;
            int rightPower = i+power;

            int left = leftPower < 0 ? 0 : leftPower;
            int right = rightPower > n ? n : rightPower;

            this.checkList.add(new Sprinkler(left, right));
        }
        this.n = n;
    }

    public void sprinklerSort() {
        this.checkList = this.checkList.stream().sorted((o1, o2) -> {
            return o1.left - o2.left;
        }).collect(Collectors.toList());
    }

    public int calculator() {
        int answer = 0;
        int start = 0;
        int end = 0;
        // 끊기면 -1로 리턴~!
        int i = 0;
        while(i < this.checkList.size()) {
            while(i < this.checkList.size() && this.checkList.get(i).left <= start) {
                if (this.checkList.get(i).right > end) {
                    end = this.checkList.get(i).right;
                }
                i++;
            }
            answer++;
            if (end == this.n) {
                return answer;
            }
            if (start == end) {
                return -1;
            }
            start = end;
        }

        return answer;
    }
}
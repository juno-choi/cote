package kr.co.simol.study.hash.minusnumberline;

import java.util.*;

/**
 * 핵심은
 * 왼쪽에서 더해온 수를 오른쪽에 더해진 수에서 빼보고 존재한다면 그 count만큼 answer에 더해주는 것!
 * sum-m
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1};
        int m = 0;
        Solution solution = new Solution();
        int sol = solution.solution(nums, m);
        System.out.println("answer = " + sol);
    }

}

class Solution {
    public int solution(int[] nums, int m) {
        Calculator calculator = new Calculator();
        calculator.calculator(nums, m);
        return calculator.answer;
    }
}

class Calculator {
    int answer;
    Map<Integer, Integer> map;

    public Calculator() {
        this.answer = 0;
        this.map = new HashMap<>();
    }

    public void calculator(int[] nums, int m) {
        // 0은 세팅되지 않으니 꼭 1로 초기화 해준다.
        this.map.put(0, 1);
        // 왼쪽에서 더해온다
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            int number = nums[i];
            sum += number;

            // 왼쪽에서 오른쪽을 빼본다
            int checkNumber = sum - m;
            int checkNumberCount = this.map.getOrDefault(checkNumber, 0);

            if (checkNumberCount > 0) {
                // m이 된다면 catch
                answer += checkNumberCount;
            }

            // 체크 한 뒤에 put 해주자!
            int count = this.map.getOrDefault(sum, 0) + 1;
            this.map.put(sum, count);
        }
    }
}
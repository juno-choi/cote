package kr.co.simol.study.greedy.titanic;

import java.util.*;

/**
 * 2인, 1인이 나오는 문제
 * 여러명이거나 여러개여도 결국 제한으로 인해 문제를 살펴보면 2명까지 2개까지인 제한이 많다.
 * 이럴땐 two pointer 알고리즘으로 풀어내면 된다.
 * 문제 중 2명,2개 예시)
 * 유람선에는 N명의 승객이 타고 있습니다. 구명보트를 타고 탈출해야 하는데 타이타닉에 있는 구명보트는 2명 이하로만 탈 수 있으며
 */

public class Main {
    public static void main(String[] args) {
        int[] nums = {86, 95, 107, 67, 38, 49, 116, 22, 78, 53};
        int m = 150;
        Solution solution = new Solution();
        int sol = solution.solution(nums, m);
        System.out.println(sol);
    }
}

class Solution {
    public int solution(int[] nums, int m) {
        Check check = new Check(nums);
        check.sort();
        return check.calculator(m);
    }
}

class Check {
    int[] nums;
    int light;
    int heavy;

    public Check (int[] nums){
        this.nums = nums;
        this.light = 0;
        this.heavy = nums.length -1;
    }

    public void sort() {
        Arrays.sort(this.nums);
    }

    public int calculator(int m) {
        int answer = 0;
        while(heavy > light) {
            int lightPerson = this.nums[light];
            int heavyPerson = this.nums[heavy];
            int sum = lightPerson + heavyPerson;
            if(sum > m) {
                // 무거운 1명은 혼자 타
                answer++;
                // 그 다음 무거운 사람
                this.heavy--;
                continue;
            }
            // 2명이서 타
            answer++;
            // 그다음 사람들
            this.light++;
            this.heavy--;
        }
        return answer;
    }
}

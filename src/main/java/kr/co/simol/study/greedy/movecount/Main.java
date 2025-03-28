package kr.co.simol.study.greedy.movecount;

import java.util.*;

/**
 * titanic 동일한 문제
 * 2인, 1인이 나오는 문제
 * 여러명이거나 여러개여도 결국 제한으로 인해 문제를 살펴보면 2명까지 2개까지인 제한이 많다.
 * 이럴땐 two pointer 알고리즘으로 풀어내면 된다.
 * 문제 중 2명,2개 예시)
 * 유람선에는 N명의 승객이 타고 있습니다. 구명보트를 타고 탈출해야 하는데 타이타닉에 있는 구명보트는 2명 이하로만 탈 수 있으며
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 4, 2, 3};
        Solution solution = new Solution();
        int answer = solution.solution(nums);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int[] nums) {
        Person person = new Person(nums);
        person.sort();
        return person.calculator();
    }
}

class Person {
    int limit;
    int[] nums;

    int light;
    int heavy;

    public Person(int[] nums) {
        this.limit = 5;
        this.nums = nums;
        this.light = 0;
        this.heavy = nums.length -1;
    }

    public void sort() {
        Arrays.sort(this.nums);
    }

    public int calculator() {
        int answer = 0;

        while(this.heavy > this.light) {
            int lightThing = this.nums[this.light];
            int heavyThing = this.nums[this.heavy];
            int sum = lightThing + heavyThing;
            if (sum > this.limit) {
                // 한개만 들어
                answer++;
                // 다음 무거운거
                this.heavy--;
                continue;
            }
            // 둘다 들어
            answer++;
            this.light++;
            this.heavy--;
        }
        return answer;
    }
}
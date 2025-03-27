package kr.co.simol.study.greedy.movecount;

import java.util.*;

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
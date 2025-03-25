package kr.co.simol.study.hash.nonsamefrequency;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "aaabbbcccdddeeeeeff";
        Solution solution = new Solution();
        int sol = solution.solution(s);
        System.out.println(sol);
    }
}

class Solution {
    public int solution(String s) {
        Calculator calculator = new Calculator(s);
        calculator.setUpMap();
        calculator.calculator();
        return calculator.answer;
    }
}

class Calculator {
    char[] chars;
    int[] visited;
    Map<Character, Integer> map;

    int answer;

    public Calculator(String s) {
        char[] chars = s.toCharArray();
        this.chars = chars;
        this.visited = new int[chars.length+1];
        this.map = new HashMap<>();
        this.answer = 0;
    }

    public void setUpMap() {
        for (int i=0; i<this.chars.length; i++) {
            char c = chars[i];
            int count = this.map.getOrDefault(c, 0) + 1;
            this.map.put(c, count);
        }
    }

    public void calculator() {
        Set<Character> keys = this.map.keySet();
        for (char c : keys) {
            int index = this.map.get(c);
            while(true) {
                if (index == 0) {
                    break;
                }
                if (visited[index] == 0) {
                    visited[index] = 1;
                    break;
                }
                this.answer++;
                index--;
            }
        }
    }
}
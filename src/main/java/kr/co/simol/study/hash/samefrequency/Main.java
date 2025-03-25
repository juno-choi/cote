package kr.co.simol.study.hash.samefrequency;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "abbccddee";
        Solution solution = new Solution();
        int[] sol = solution.solution(s);
        System.out.println(sol[0]+","+sol[1]+","+sol[2]+","+sol[3]+","+sol[4]+"");
    }

}

class Solution {
    public int[] solution(String s) {
        Calculator calculator = new Calculator(s);
        calculator.setUp();
        // 최대값에서 반대로 수를 역산해서 반환하기
        return calculator.getAnswer();
    }
}

class Calculator {
    char[] compare;  //a,b,c,d,e
    char[] chars;
    int max;
    Map<Character, Integer> map;

    public Calculator(String s) {
        char[] compare = {'a', 'b', 'c', 'd', 'e'};
        this.compare = compare;
        this.chars = s.toCharArray();
        this.max = 0;
        this.map = new HashMap<>();
    }

    public void setUp() {
        // char로 만들어서 hash에 모두 넣기
        for (char c : chars) {
            int count = this.map.getOrDefault(c, 0)+1;
            this.map.put(c, count);
            // 최대값 구하기
            if (this.max < count) {
                this.max = count;
            }
        }
    }

    public int[] getAnswer() {
        int compareLength = this.compare.length;
        int[] answer = new int[compareLength];
        for (int i=0; i<compareLength; i++) {
            char c = this.compare[i];
            answer[i] = max - this.map.getOrDefault(c, 0);
        }
        return answer;
    }
}

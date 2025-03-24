package kr.co.simol.study.hash.firstcharacter;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "abcdeabcdfg";
        Solution solution = new Solution();
        int index = solution.solution(s);
        System.out.println(index);
    }
}

class Solution {
    public int solution(String s) {
        StringCalculator stringCalculator = new StringCalculator(s);
        char c = stringCalculator.getFirst();
        return stringCalculator.getAnswer(c);
    }
}

class StringCalculator {
    private Map<Character, Integer> map;
    private char[] chars;

    public StringCalculator(String s) {
        this.map = new HashMap<>();
        char[] chars = s.toCharArray();
        this.chars = chars;
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
    }

    public char getFirst() {
        for (char c : chars) {
            int count = map.get(c);
            if (count > 1) {
                continue;
            }
            return c;
        }
        return ' ';
    }

    public int getAnswer(char c) {
        if (c == ' ') {
            return -1;
        }

        for (int i=0; i<chars.length; i++) {
            if (chars[i] == c) {
                return i+1;
            }
        }
        return 0;
    }
}
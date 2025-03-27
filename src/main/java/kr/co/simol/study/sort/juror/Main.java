package kr.co.simol.study.sort.juror;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] scores = {88, 99, 91, 89, 90, 72, 75, 94, 95, 100};
        int k = 5;
        Solution solution = new Solution();
        int answer = solution.solution(scores, k);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int[] scores, int k) {
        Arrays.sort(scores);
        Checker checker = new Checker(scores);
        checker.findIndex(k);
        int answer = 0;
        System.out.println("index = " + checker.index);
        for (int i=checker.index; i<checker.index+k; i++) {
            System.out.println("score = " + scores[i]);
            answer += scores[i];
        }
        return answer / k;
    }
}

class Checker {
    int[] scores;
    int index;

    public Checker(int[] scores) {
        this.scores = scores;
        this.index = 0;
    }

    public void findIndex(int k) {
        for (int i=0; i<scores.length; i++) {
            if (isOverTen(i, k)) {
                continue;
            }
            this.index = i;
            return ;
        }
    }

    private boolean isOverTen(int index, int k) {
        return scores[index+(k-1)] > scores[index] + 10;
    }
}
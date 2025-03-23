package kr.co.simol.study.simulation.ladder;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 5;
        int[][] ladder = {{1,3}, {2,4}, {1,4}};
        n = 12;
        ladder = new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}};
        String[] solution1 = solution.solution(n, ladder);
        System.out.println("test");
    }


}

class Solution {
    public String[] solution(int n, int[][] ladder) {
        // 초기값 세팅
        String[] answer = new String[n];

        for (int i=0; i<n; i++) {
            answer[i] = Character.toString(('a'+i)).toUpperCase();
        }

        int ladderXLength = ladder.length;

        for (int i=0; i<ladderXLength; i++) {
            int ladderYLength = ladder[i].length;

            for (int j=0; j<ladderYLength; j++) {
                int target = ladder[i][j];

                String targetA = answer[target-1];
                String targetB = answer[target];
                answer[target-1] = targetB;
                answer[target] = targetA;
            }
        }

        return answer;
    }
}

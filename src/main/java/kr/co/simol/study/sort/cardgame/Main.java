package kr.co.simol.study.sort.cardgame;

import java.util.*;
import java.util.stream.*;

/**
 * reverse 할때는 stream으로 변환하자
 * 우선권이 없을때 일반적인 게임 점수를 먼저 구하고
 * 우선권으로 가장 큰 점수를 가져올 수 있는 경우에만 쓴다고 가정하에 카드 점수 차를 구하여
 * 우선권 개수만큼 더하면 됨
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {14, 15, 20, 11, 10, 20, 20, 12, 9, 22, 27,
                25, 30, 19};
        int k = 3;
        Solution solution = new Solution();
        int sol = solution.solution(nums, k);
        System.out.println(sol);
    }
}

class Solution {
    public int solution(int[] nums, int k) {
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        numList.sort((n1, n2) -> n2 - n1);

        int answer = 0;
        int[] diff = new int[numList.size()/2];
        for (int i=0; i<numList.size(); i+=2) {
            int num1 = numList.get(i);
            int num2 = numList.get(i+1);
            answer+=num2;
            diff[i/2] = num1 - num2;
        }

        List<Integer> diffList = Arrays.stream(diff).boxed().collect(Collectors.toList());
        diffList.sort((d1, d2) -> d2-d1);

        for(int i=0; i<k; i++) {
            answer += diffList.get(i);
        }

        return answer;
    }
}

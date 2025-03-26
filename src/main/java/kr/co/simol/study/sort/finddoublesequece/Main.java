package kr.co.simol.study.sort.finddoublesequece;

import java.util.*;

/**
 * 이미 만들어진 수열에 2배를 한거라 2배를 찾아서 제거해주었다.
 * 최소수는 무조건 존재하는 수이기 때문에 정렬해서 시작
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 1, 6, 2, 2, 7, 3, 14};
        Solution solution = new Solution();
        int[] sol = solution.solution(nums);
        for(int s : sol) {
            System.out.println(s);
        }
    }
}

class Solution {
    public int[] solution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        // 정렬
        Arrays.sort(nums);

        for (int num : nums) {
            int count = map.getOrDefault(num, 0)+1;
            map.put(num, count);
        }

        List<Integer> answerList = new ArrayList<>();
        for (int key : nums) {
            int count = map.get(key);
            if (count == 0) {
                continue;
            }
            int minus = map.get(key) - 1;
            map.put(key, minus);
            int doubleNumber = key * 2;
            int minus2 = map.get(doubleNumber) - 1;
            map.put(doubleNumber, minus2);
            answerList.add(key);
        }

        int[] answer = new int[answerList.size()];
        for (int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}

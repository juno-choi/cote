package kr.co.simol.study.sort.binarynumber;

import java.util.*;

/**
 * Integer의
 * 2진수,8진수,16진수 = toBinaryString, toOctalString, toHexString
 * 다시 10진수 = parseInt(n, 2), parseInt(n, 8), parseInt(n, 16)
 * 2진수 비트 카운트 = bitCount(n)
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {12, 5, 7, 23, 45, 21, 17};
        Solution solution = new Solution();
        int[] sol = solution.solution(nums);
        for (int s : sol) {
            System.out.println(s);
        }
    }
}

class Solution {
    public int[] solution(int[] nums) {
        int[] answer = new int[nums.length];
        List<Binary> binaryList = new ArrayList<>();
        for (int num : nums) {
            binaryList.add(new Binary(num));
        }

        binaryList.sort((o1, o2) -> {
            if(o1.binary == o2.binary) {
                return o1.origin - o2.origin;
            }
            return o1.binary - o2.binary;
        });
        for (int i=0; i<binaryList.size(); i++) {
            answer[i] = binaryList.get(i).origin;
        }
        return answer;
    }
}

// 2진수 계산
class Binary {
    int origin;
    int binary;

    public Binary(int num) {
        this.origin = num;
        this.binary = Integer.bitCount(num);
    }
}

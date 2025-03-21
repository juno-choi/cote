package kr.co.simol.dfs.m43165;

/**
 * dfs를 통해 문제를 풀이해야 함.
 * 예전에는 다 이해했는데 이젠 이해가 안된다... 다른 문제를 풀면서 더 이해해보자.
 */
public class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        //dfs 탐색을 통하여 문제를 풀이해야함...
        dfs(0,0,numbers,target);
        return answer;
    }
    public void dfs(int level, int sum, int[] numbers, int target){
        if(level == numbers.length){
            if(sum == target) answer++;
        }else{
            dfs(level+1, sum+numbers[level], numbers, target);
            dfs(level+1, sum-numbers[level], numbers, target);
        }
    }
}
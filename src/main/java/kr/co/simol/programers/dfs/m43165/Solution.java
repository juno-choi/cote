package kr.co.simol.programers.dfs.m43165;

/**
 * dfs를 통해 문제를 풀이해야 함.
 * 예전에는 다 이해했는데 이젠 이해가 안된다... 다른 문제를 풀면서 더 이해해보자.
 */
public class Solution {
    public int solution(int[] numbers, int target) {

        Checker checker = new Checker(numbers);
        checker.dfs(0, 0, target);

        return checker.getAnswer();
    }
}

class Checker {
    private int[] numbers;
    private int answer;

    public Checker (int[] numbers){
        this.numbers = numbers;
    }

    public void dfs(int level, int sum, int target) {
        int numbersLength = this.numbers.length;

        if (level == numbersLength) {
            if (sum == target) {
                answer++;
            }
            return ;
        }

        dfs(level+1, sum+this.numbers[level], target);
        dfs(level+1, sum-this.numbers[level], target);
    }

    public int getAnswer() {
        return this.answer;
    }
}

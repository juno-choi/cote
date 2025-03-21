package kr.co.simol.dfs.m43165;

/**
 * dfs를 통해 문제를 풀이해야 함.
 * 예전에는 다 이해했는데 이젠 이해가 안된다... 다른 문제를 풀면서 더 이해해보자.
 */
public class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        Calculator calculator = new Calculator();
        calculator.updateData(numbers, target);
        calculator.dfs(0, 0);
        return calculator.getAnswer();
    }

}

class Calculator{
    private int answer;
    private int[] numbers;
    private int target;

    public Calculator() {
        this.answer = 0;
    }

    public void updateData(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
    }

    public void dfs(int level, int sum) {

        if (level == this.numbers.length) {
            if (sum == target) {
                this.answer++;
            }
            return ;
        }
        dfs(level+1, sum + this.numbers[level]);
        dfs(level+1, sum - this.numbers[level]);
    }

    public int getAnswer() {
        return this.answer;
    }
}
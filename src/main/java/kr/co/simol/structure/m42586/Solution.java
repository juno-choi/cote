package kr.co.simol.structure.m42586;

import java.util.*;

public class Solution {
    /**
     * queue를 사용하여 앞에서부터 하나씩 처리하기
     * 문제를 풀때 너무 복잡해지면 복잡한걸 쪼개서 간단하게 만들어보자.
     */
    public int[] solution(int[] progresses, int[] speeds) {
        Calculator calculator = Calculator.create();
        calculator.updateProgresses(progresses);
        calculator.updateSpeeds(speeds);
        calculator.work();

        return calculator.getAnswer();
    }
}

class Calculator {
    private Queue<Integer> progresses;
    private Queue<Integer> speeds;
    private List<Integer> results;

    protected Calculator() {
        this.progresses = new LinkedList<>();
        this.speeds = new LinkedList<>();
        this.results = new ArrayList<>();
    }

    public static Calculator create() {
        return new Calculator();
    }

    public void updateProgresses(int[] progresses) {
        for (int i : progresses) {
            this.progresses.add(i);
        }
    }

    public void updateSpeeds(int[] speeds) {
        for (int i : speeds) {
            this.speeds.add(i);
        }
    }

    public void work() {
        while(! this.progresses.isEmpty()) {
            int progressesSize = this.progresses.size();
            int result = 0;

            for(int i=0; i<progressesSize; i++) {
                int progress = this.progresses.poll();
                int speed = this.speeds.poll();
                int sum = progress + speed;
                this.progresses.add(sum);
                this.speeds.add(speed);
            }

            if (this.progresses.peek() < 100) {
                continue;
            }

            while(! this.progresses.isEmpty()){
                int progress = this.progresses.peek();

                if (progress < 100) {
                    break;
                }

                this.progresses.poll();
                this.speeds.poll();
                result++;
            }

            if (result > 0) {
                this.results.add(result);
            }
        }
    }

    public int[] getAnswer() {
        int resultsSize = this.results.size();
        int[] answer = new int[resultsSize];

        for(int i = 0; i<resultsSize; i++) {
            answer[i] = results.get(i);
        }

        return answer;
    }

}
package kr.co.simol.programers.structure.m12906;

import java.util.*;

public class Solution {
    /**
     * stack을 활용하여 중복수를 제거하는 방식 사용
     */
    public int[] solution(int []arr) {

        Checker checker = Checker.create();
        checker.addAll(arr);

        return checker.getAnswer();
    }
}

class Checker {
    Stack<Integer> stack;

    protected Checker() {
        this.stack = new Stack();
    }

    public static Checker create() {
        return new Checker();
    }

    public void addAll(int[] arr) {
        for (int i : arr) {
            if (this.stack.size() == 0) {
                this.stack.push(i);
                continue;
            }

            int peek = this.stack.peek();
            if (peek != i) {
                this.stack.push(i);
            }
        }
    }

    public int[] getAnswer() {
        int stackSize = this.stack.size();
        int[] answer = new int[stackSize];
        for (int i=stackSize-1; i>=0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}
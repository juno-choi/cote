package kr.co.simol.programers.sort.m42748;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stream을 쓸때는 java.util.stream.* 을 import 해주자.
 * toList()를 쓰면 좋은데 프로그래머스는 안되니까 Collectors.toList() 꼭 기억하자.
 *
 */
public class Solution {
    public int[] solution(int[] array, int[][] commands) {
        // array를 subList()
        // asc 정렬
        // 정렬에서 n번째수

        ArrayCalculator arrayCalculator = ArrayCalculator.create();
        arrayCalculator.updateArray(array);
        arrayCalculator.updateCommands(commands);
        arrayCalculator.work();

        return arrayCalculator.getAnswer();
    }
}

class ArrayCalculator {
    private List<Integer> array;
    private List<Commands> commands;
    private List<Integer> results;

    protected ArrayCalculator() {
        this.array = new ArrayList<>();
        this.commands = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public static ArrayCalculator create() {
        return new ArrayCalculator();
    }

    public void updateArray(int[] array) {
        for (int a : array) {
            this.array.add(a);
        }
    }

    public void updateCommands(int[][] commandList) {
        for (int[]  c : commandList) {
            Commands commands = Commands.from(c);
            this.commands.add(commands);
        }
    }

    public void work() {

        for (Commands c : this.commands) {
            int start = c.get(0) - 1;
            int end = c.get(1);
            int index = c.get(2) - 1;

            List<Integer> subList = this.array.subList(start, end);
            subList = subList.stream().sorted().collect(Collectors.toList());

            results.add(subList.get(index));
        }
    }

    public int[] getAnswer() {
        int[] answer = new int[results.size()];

        for (int i=0; i<this.results.size(); i++) {
            answer[i] = results.get(i);
        }
        return answer;
    }
}

class Commands{
    private List<Integer> commands;

    protected Commands(List<Integer> commands) {
        this.commands = commands;
    }

    public static Commands from(int[] commands) {
        List<Integer> list = new ArrayList<>();
        for (int c : commands) {
            list.add(c);
        }
        return new Commands(list);
    }

    public int get(int i) {
        return commands.get(i);
    }
}
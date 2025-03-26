package kr.co.simol.study.datastructure.doorinout;

import java.util.*;

/**
 * 시작값을 초기 값으로 세팅해주어야 함
 * queue에서 꺼내다가 시간이 떨어져있을 경우 최소 시간으로 변경하여 진행하는 로직 중요
 */
public class Main {
    public static void main(String[] args) {
        int[] arrival = {2, 2, 2, 3, 4, 8, 8, 9, 10, 10};
        int[] state = {1, 0, 0, 0, 1, 1, 0, 1, 1, 0};

        Solution solution = new Solution();
        int[] answer = solution.solution(arrival, state);
        for (int i=0; i<answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}

class Solution {
    public int[] solution(int[] arrival, int[] state) {
        Line line = new Line(state);
        line.setUp(arrival, state);
        line.path(arrival);
        return line.answer;
    }
}

class Line {
    Queue<Person> in;
    Queue<Person> out;
    boolean state;

    int[] answer;

    public Line(int[] state) {
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
        this.state = state[0] == 1;
        this.answer = new int[state.length];
    }

    public void setUp(int[] arrival, int[] state) {
        for (int i=0; i<arrival.length; i++) {
            int arrive = arrival[i];
            int s = state[i];

            if (s == 1) {
                out.add(new Person(i, arrive));
                continue;
            }
            in.add(new Person(i, arrive));
        }
    }

    public void path(int[] arrival) {
        int time = arrival[0];

        while(! in.isEmpty() || ! out.isEmpty()) {
            Person person;
            if (this.state) {
                if (out.isEmpty()) {
                    state = !state;
                    continue;
                }
                person = out.peek();
            } else {
                if (in.isEmpty()) {
                    state = !state;
                    continue;
                }
                person = in.peek();
            }
            if (person.time > time) {
                state = !state;

                if (out.peek().time > time && in.peek().time > time) {
                    if (out.peek().time > in.peek().time) {
                        time = in.peek().time;
                        state = false;
                        continue;
                    }
                    time = out.peek().time;
                    state = true;
                }
                continue;
            }
            if (this.state) {
                person = out.poll();
            } else {
                person = in.poll();
            }
            answer[person.id] = time;
            time++;
        }
    }
}

class Person {
    int id;
    int time;

    public Person(int id, int time) {
        this.id = id;
        this.time = time;
    }
}
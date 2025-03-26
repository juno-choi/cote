package kr.co.simol.study.datastructure.dermatology;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] laser = {30, 20, 25, 15};
        String[] enter = {"10:20 1", "10:40 1", "11:00 1", "11:20 1",
                "11:40 1"};

        Solution solution = new Solution();
        int sol = solution.solution(laser, enter);
        System.out.println(sol);
    }
}

class Solution {
    public int solution(int[] laser, String[] enter) {

        // 룸 만들기
        Room room = new Room();

        // 사람 만들기
        // 분으로 바꾸기
        Queue<Person> personQueue = new LinkedList<>();
        for(String e : enter) {
            personQueue.add(new Person(e, laser));
        }
        // 시간은 09:00 부터 20:00까지
        room.start(personQueue);
        return room.answer;
    }
}

class Room {
    Queue<Person> waiting;
    int answer;
    public Room() {
        this.waiting = new LinkedList<>();
        this.answer = 0;
    }

    public void start(Queue<Person> personQueue) {
        Person firstPerson = personQueue.poll();
        int startTime = firstPerson.inTime;
        int endTime = 20*60;
        Person inPerson = firstPerson;

        for (int i=startTime; i<endTime; i++) {
            if (personQueue.isEmpty() && this.waiting.isEmpty()) {
                int size = this.waiting.size();
                if (size > this.answer) {
                    this.answer = size;
                }
                break;
            }

            if (! personQueue.isEmpty()) {
                Person comePerson = personQueue.peek();

                if (comePerson.inTime <= i) {
                    this.waiting.add(personQueue.poll());
                }
            }

            if (inPerson.outTime == i) {
                if (this.waiting.isEmpty()) {
                    Person person = personQueue.peek();
                    i = person.inTime - 1;
                    continue;
                }
                if (this.waiting.peek().inTime == i) {
                    int size = this.waiting.size();
                    if (size > this.answer) {
                        this.answer = size;
                    }
                }
                inPerson = this.waiting.poll();
            }
        }
    }
}

class Person {
    int inTime;
    int outTime;

    public Person(String info, int[] laser) {
        String[] personInfo = info.split(" ");
        String[] timeInfo = personInfo[0].split(":");
        int hour = Integer.valueOf(timeInfo[0]) * 60;
        int minute = Integer.valueOf(timeInfo[1]);
        this.inTime = hour + minute;

        int machine = Integer.valueOf(personInfo[1]);
        this.outTime = inTime + laser[machine];
    }
}
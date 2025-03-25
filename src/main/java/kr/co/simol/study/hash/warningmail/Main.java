package kr.co.simol.study.hash.warningmail;

import java.time.*;
import java.util.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

/**
 * ChronoUnit 사용법 알아두자!
 */
public class Main {
    public static void main(String[] args) {
        String[] reports = {"chato 09:15 in", "emilly 10:00 in", "chato 10:15 out", "luis 10:57 in", "daniel 12:00 in", "emilly 12:20 out", "luis 11:20 out", "daniel 15:05 out"};
        int time = 60;
        Solution solution = new Solution();
        String[] sol = solution.solution(reports, time);
        for (String s : sol) {
            System.out.println(s);
        }
    }
}

class Solution {
    public String[] solution(String[] reports, int time) {
        Checker chekcer = new Checker();
        // name time status
        // Person 객체로 만들어서 담아두기
        chekcer.setUp(reports);
        chekcer.check(time);
        // 알파벳 사전순으로 출력
        return chekcer.getAnswer();
    }
}

class Checker {
    Map<String, Person> personMap;
    PriorityQueue<String> targetQueue;
    Set<String> targetSet;

    public Checker() {
        this.personMap = new HashMap<>();
        this.targetQueue = new PriorityQueue<>();
        this.targetSet = new HashSet<>();
    }

    void setUp(String[] reports) {
        for(String r : reports) {
            String[] personInfo = r.split(" ");
            String name = personInfo[0];
            String time = personInfo[1];
            String status = personInfo[2];

            if ("out".equals(status)) {
                this.personMap.get(name).out(time);
                continue;
            }

            Person person = this.personMap.getOrDefault(name, new Person(name, time));
            person.in(time);
            this.personMap.put(name, person);
        }
    }

    void check(int limit) {
        Set<String> keys = personMap.keySet();
        for (String key : keys) {
            Person person = personMap.get(key);
            if (person.totalTime > limit) {
                targetSet.add(person.name);
            }
        }
    }

    String[] getAnswer() {
        for (String t : targetSet) {
            this.targetQueue.add(t);
        }

        String[] answer = new String[this.targetQueue.size()];
        int i = 0;
        while(! this.targetQueue.isEmpty()) {
            answer[i] = this.targetQueue.poll();
            i++;
        }
        return answer;
    }
}

class Person {
    String name;
    LocalTime inTime;
    int totalTime;

    DateTimeFormatter formatter;

    public Person(String name, String inTimeAsString) {
        this.name = name;
        this.formatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    public void out(String outTimeAsString) {
        LocalTime outTime = LocalTime.parse(outTimeAsString, this.formatter);
        long diff = ChronoUnit.MINUTES.between(this.inTime, outTime);
        this.totalTime += diff;
    }

    public void in(String inTimeAsString) {
        LocalTime inTime = LocalTime.parse(inTimeAsString, this.formatter);
        this.inTime = inTime;
    }
}

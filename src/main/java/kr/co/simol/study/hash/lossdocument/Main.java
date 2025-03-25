package kr.co.simol.study.hash.lossdocument;

import java.util.*;
import java.time.*;
import java.time.format.*;

public class Main {
    public static void main(String[] args) {
        String[] reports = {"cody 14:20", "luis 10:12", "alice 15:40", "tom 15:20", "daniel 14:50"};
        String times = "14:20 15:20";

        Solution solution = new Solution();
        String[] sol = solution.solution(reports, times);
        for (String s : sol) {
            System.out.println(s);
        }

    }
}

class Solution {
    public String[] solution(String[] reports, String times) {
        Checker checker = new Checker();
        checker.setUp(reports, times);
        checker.checkPerson();
        return checker.getAnswer();
    }
}

class Checker {

    Map<String, LocalTime> personMap;
    PriorityQueue<Target> targetQueue;
    LocalTime startTime;
    LocalTime endTime;

    public Checker() {
        this.personMap = new HashMap<>();
        this.targetQueue = new PriorityQueue<>((o1, o2) -> o1.time.isBefore(o2.time) ? -1 : 1);
    }

    public void setUp(String[] reports, String times) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (String r : reports) {
            String[] personInfo = r.split(" ");
            String name = personInfo[0];
            String time = personInfo[1];
            this.personMap.put(name, LocalTime.parse(time, formatter));
        }

        String[] timeInfo = times.split(" ");
        String startTime = timeInfo[0];
        String endTime = timeInfo[1];
        this.startTime = LocalTime.parse(startTime, formatter);
        this.endTime = LocalTime.parse(endTime, formatter);

    }

    public void checkPerson() {
        Set<String> keys = this.personMap.keySet();
        for (String key: keys) {
            LocalTime inTime = this.personMap.get(key);
            if (inTime.isBefore(this.startTime)) {
                continue;
            }
            if (inTime.isAfter(this.endTime)) {
                continue;
            }

            this.targetQueue.offer(new Target(key, inTime));
        }
    }

    public String[] getAnswer() {
        String[] answer = new String[targetQueue.size()];
        int i=0;
        while(! targetQueue.isEmpty()) {
            answer[i] = targetQueue.poll().name;
            i++;
        }

        return answer;
    }
}

class Target {
    String name;
    LocalTime time;

    public Target(String name, LocalTime time) {
        this.name = name;
        this.time = time;
    }
}
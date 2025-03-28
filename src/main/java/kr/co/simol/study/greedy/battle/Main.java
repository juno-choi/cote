package kr.co.simol.study.greedy.battle;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] solution1 = solution.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"});
        int[] solution2 = solution.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"});
        int[] solution3 = solution.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"});
        int[] solution4 = solution.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"});
        System.out.println("테스트 종료");
    }
}

class Solution {
    public int[] solution(String[] students) {

        StudentList studentList = new StudentList();
        for (int i=0; i<students.length; i++) {
            String[] studentInfo = students[i].split(" ");
            String team = studentInfo[0];
            int attack = Integer.valueOf(studentInfo[1]);
            studentList.add(new Student(i, team, attack));
        }

        studentList.sort();
        studentList.battle();

        int[] answer = new int[studentList.list.size()];
        for (Student s : studentList.list) {
            answer[s.id] = s.score;
        }

        return answer;
    }
}

class Student {
    int id;
    String team;
    int attack;

    int score;

    public Student(int id, String team, int attack) {
        this.id = id;
        this.team = team;
        this.attack = attack;
        this.score = 0;
    }

    public void addScore(int score) {
        this.score += score;
    }
}

class StudentList {
    List<Student> list;

    public StudentList() {
        this.list = new ArrayList<>();
    }

    public void add(Student student) {
        this.list.add(student);
    }

    public void sort() {
        this.list = this.list.stream().sorted((s1, s2) -> {
            return s2.attack - s1.attack;
        }).collect(Collectors.toList());
    }

    public void battle() {
        for(int i=0; i<this.list.size(); i++) {
            Student attackStudent = this.list.get(i);
            String attackStudentTeam = attackStudent.team;
            for (int j=i+1; j<this.list.size(); j++) {
                Student targetStudent = this.list.get(j);
                if (attackStudentTeam.equals(targetStudent.team)) {
                    continue;
                }
                if (targetStudent.attack >= attackStudent.attack) {
                    continue;
                }
                attackStudent.addScore(targetStudent.attack);
            }
        }
    }
}
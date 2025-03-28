package kr.co.simol.softeer.lv2.gptnumber;

import java.io.*;
import java.util.*;
import java.util.stream.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = Integer.valueOf(sc.nextLine());

        String[] inputData = new String[input];
        for (int i=0; i<input; i++) {
            inputData[i] = sc.nextLine();
        }

        Solution solution = new Solution();
        String[] answer = solution.solution(inputData);

        for (String d : answer) {
            System.out.println(d);
        }

    }
}

class Solution{
    public String[] solution(String[] inputData) {
        GptList gptList = new GptList();
        for (String s : inputData) {
            gptList.add(new Gpt(s));
        }
        gptList.sort();
        return gptList.getAnswer();
    }
}

class Gpt {
    String front;
    String back;

    public Gpt(String input) {
        if(input.indexOf(".") < 0) {
            this.front = input;
            this.back = "-1";
            return ;
        }
        String[] inputInfo = input.split("\\.");
        this.front = inputInfo[0];
        this.back = inputInfo[1];
    }
}

class GptList {
    List<Gpt> list;

    public GptList() {
        this.list = new ArrayList<>();
    }

    public void add(Gpt gpt) {
        this.list.add(gpt);
    }

    public void sort() {
        this.list = this.list.stream().sorted((g1, g2) -> {
            if (Integer.valueOf(g1.front) == Integer.valueOf(g2.front)) {
                return Integer.valueOf(g1.back) - Integer.valueOf(g2.back);
            }
            return Integer.valueOf(g1.front) - Integer.valueOf(g2.front);
        }).collect(Collectors.toList());
    }

    public String[] getAnswer() {
        String[] answer = new String[this.list.size()];

        for (int i=0; i<this.list.size(); i++) {
            Gpt gpt = this.list.get(i);
            if (gpt.back.equals("-1")) {
                answer[i] = gpt.front;
                continue;
            }
            answer[i] = gpt.front+"."+gpt.back;
        }
        return answer;
    }
}
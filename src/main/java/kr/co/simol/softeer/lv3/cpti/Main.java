package kr.co.simol.softeer.lv3.cpti;

import java.io.*;
import java.util.*;

/**
 * 비트 연산을 해야되는 문제인데 비트연산을 실무에서 쓰지 않아 잘 모르겟음
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mn = br.readLine();
            String[] input = mn.split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            String[] inputData = new String[n];
            for (int i=0; i<n; i++) {
                inputData[i] = br.readLine();
            }

            Solution solution = new Solution();
            int sol = solution.solution(n, m, inputData);
            System.out.println(sol);
        } catch (Exception e) {
            System.out.println("입력 오류");
        }
    }
}

class Solution {
    public int solution(int n, int m, String[] inputData) {
        CptiList cptiList = new CptiList();

        for (int i=0; i<inputData.length; i++) {
            String input = inputData[i];
            Cpti cpti = new Cpti();
            cpti.setUp(input);
            cptiList.add(cpti);
        }

        return cptiList.calculator(m);
    }
}

class Cpti {
    Queue<Character> queue;

    public Cpti() {
        this.queue = new LinkedList<>();
    }

    public void setUp(String input) {
        for(char c : input.toCharArray()) {
            this.queue.add(c);
        }
    }
}

class CptiList {
    List<Cpti> list;

    public CptiList() {
        this.list = new ArrayList<>();
    }

    public void add(Cpti cpti) {
        this.list.add(cpti);
    }

    public int calculator(int m) {
        int answer = 0;
        for (int i=0; i<this.list.size(); i++) {
            Cpti cpti = this.list.get(i);
            for (int j=i+1; j<this.list.size(); j++) {
                Cpti targetCpti = this.list.get(j);
                int count = 0;

                for(int h=0; h<m; h++) {
                    char c1 = cpti.queue.poll();
                    char c2 = targetCpti.queue.poll();
                    // 다르면
                    if(c1 != c2) {
                        count++;
                    }
                    // count 2보다 크면
                    if (count > 2) {
                        continue;
                    }
                    cpti.queue.add(c1);
                    targetCpti.queue.add(c2);
                }
                if(count <= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
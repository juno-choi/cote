package kr.co.simol.study.sort.gatherplace;

import java.util.*;

/**
 * 표에서 이동한 거리 최소를 구하는건 각 학생들의 중앙 지점을 구해서 더해주면 된다.
 * 중앙 지점은 전체 학생들의 위치를 정렬하여 중앙에서 자른 값이 모두의 중앙 값이다.
 * 해당 index의 값을 전부 빼주고 절대값으로 더해주면 최소 이동 경로가 된다.
 */
public class Main {
    public static void main(String[] args) {
        int[][] baord ={{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}};

        Solution solution = new Solution();
        int answer = solution.solution(baord);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int[][] board) {
        // 1번 지점을 찾자
        List<Person> personList = new ArrayList<>();

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                int boardPoint = board[i][j];
                if (boardPoint == 1) {
                    personList.add(new Person(i, j));
                }
            }
        }
        DistanceCalculator distanceCalculator = new DistanceCalculator(personList.size());
        distanceCalculator.setUp(personList);
        distanceCalculator.sort();

        return distanceCalculator.getAnswer();
    }
}

class Person {
    int x;
    int y;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class DistanceCalculator {
    int[] xList;
    int[] yList;

    public DistanceCalculator(int length) {
        this.xList = new int[length];
        this.yList = new int[length];
    }

    public void setUp(List<Person> personList) {
        for (int i=0; i<personList.size(); i++) {
            Person person = personList.get(i);
            this.xList[i] = person.x;
            this.yList[i] = person.y;
        }
    }

    public void sort() {
        Arrays.sort(this.xList);
        Arrays.sort(this.yList);
    }

    public int getAnswer() {
        int xAnswer = 0;
        int yAnswer = 0;

        // 가운데 수 찾기
        int index = xList.length / 2;
        int indexXNumber = xList[index];
        int indexYNumber = yList[index];

        System.out.println("index = "+index);
        // 가운데 수로 빼주기
        for (int i=0; i<xList.length; i++) {
            System.out.println("x = "+ xList[i] + " y = "+ yList[i]);
            xAnswer += Math.abs(xList[i] - indexXNumber);
            yAnswer += Math.abs(yList[i] - indexYNumber);
            System.out.println("xAnswer = "+ xAnswer + " yAnswer = "+ yAnswer);
        }

        return xAnswer + yAnswer;
    }
}




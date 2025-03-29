package kr.co.simol.softeer.lv2.treeatack;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String nm = br.readLine();
            String[] nmArray = nm.split(" ");
            int row = Integer.parseInt(nmArray[0]);
            int col = Integer.parseInt(nmArray[1]);

            int[][] persons = new int[row][col];
            for(int i=0; i<row; i++) {
                String input = br.readLine();
                String[] cols = input.split(" ");
                for(int j=0; j<col; j++) {
                    persons[i][j] = Integer.parseInt(cols[j]);
                }
            }

            int[][] attacks = new int[2][2];

            for (int i=0; i<2; i++) {
                String input = br.readLine();
                String[] attackRange = input.split(" ");
                for(int j=0; j<2; j++) {
                    attacks[i][j] = Integer.parseInt(attackRange[j]);
                }
            }

            Solution solution = new Solution();
            int sol = solution.solution(persons, attacks);

            System.out.println(sol);

        } catch (Exception e) {

        }
    }
}

class Solution {
    public int solution(int[][] persons, int[][] attacks) {
        AttackCalculator attackCalculator = new AttackCalculator(persons);
        attackCalculator.setPersonList(persons);
        attackCalculator.setAttackList(attacks);
        return attackCalculator.calculator();
    }
}

class AttackCalculator {
    int[] attackList;
    int[] personList;

    public AttackCalculator(int[][] persons) {
        this.attackList = new int[persons.length];
        this.personList = new int[persons.length];
    }

    public void setPersonList(int[][] persons) {
        for(int i=0; i<persons.length; i++) {
            int totalPerson = 0;
            for(int j=0; j<persons[i].length; j++) {
                totalPerson += persons[i][j];
            }
            this.personList[i] = totalPerson;
        }
    }

    public void setAttackList(int[][] attacks) {
        for (int i=0; i<attacks.length; i++) {
            for(int j=attacks[i][0]-1; j<attacks[i][1]; j++) {
                this.attackList[j] += 1;
            }
        }
    }

    public int calculator() {
        for(int i=0; i<this.attackList.length; i++) {
            int attack = this.attackList[i];
            int person = this.personList[i];
            this.personList[i] = person - attack <= 0 ? 0 :person - attack;
        }
        return Arrays.stream(this.personList).sum();
    }
}

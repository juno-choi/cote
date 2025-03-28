package kr.co.simol.study.greedy.plant;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1,3,2}, new int[]{2,3,2}));
        System.out.println(solution.solution(new int[]{2, 1, 4, 3}, new int[]{2, 5, 3, 1}));
        System.out.println(solution.solution(new int[]{1, 1, 1}, new int[]{7, 3, 2}));
        System.out.println(solution.solution(new int[]{5, 7, 10, 15, 7, 3, 5}, new int[]{6, 7, 2, 10, 15, 6, 7}));
        System.out.println(solution.solution(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{7, 5, 4, 3, 2, 1, 6}));
    }
}

class Solution {
    public int solution(int[] plantTime, int[] growTime) {

        PlantList plantList = new PlantList();
        for(int i=0; i<plantTime.length; i++) {
            int plant = plantTime[i];
            int grow = growTime[i];
            plantList.add(new Plant(plant, grow));
        }

        plantList.sort();

        return plantList.calculator();
    }
}

class Plant {
    int plant;
    int grow;

    public Plant(int plant, int grow) {
        this.plant = plant;
        this.grow = grow;
    }
}

class PlantList {
    List<Plant> list;

    public PlantList() {
        this.list = new ArrayList<>();
    }

    public void add(Plant plant) {
        this.list.add(plant);
    }

    public void sort() {
        this.list = this.list.stream().sorted((p1, p2) -> {
                    if(p2.grow == p1.grow) {
                        return p2.plant - p1.plant;
                    }
                    return p2.grow - p1.grow;
                })
                .collect(Collectors.toList());
    }

    public int calculator() {
        int start = 0;
        int end = 0;
        int i = 0;

        while(this.list.size() > i) {
            start += this.list.get(i).plant;
            int grow = start + this.list.get(i).grow;
            if (grow > end) {
                end = grow;
            }
            i++;
        }

        return end;
    }
}
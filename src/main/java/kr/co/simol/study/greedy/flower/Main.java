package kr.co.simol.study.greedy.flower;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] plantTime = {1, 2, 3, 4, 5, 6, 7};
        int[] growTime = {7, 5, 4, 3, 2, 1, 6};

        Solution solution = new Solution();
        System.out.println(solution.solution(plantTime, growTime));
    }
}

class Solution {
    public int solution(int[] plantTime, int[] growTime) {
        // 개화가 오래 걸리는걸 앞에 해주자.
        // 개화 시기가 같다면 심는 기간이 오래걸리는걸 앞에 해주자.
        FlowerCalculator flowerCalculator = new FlowerCalculator();
        for (int i=0; i<plantTime.length; i++) {
            int plant = plantTime[i];
            int grow = growTime[i];

            Flower flower = new Flower(plant, grow);
            flowerCalculator.add(flower);
        }
        flowerCalculator.sort();
        return flowerCalculator.calculator();
    }
}

class Flower {
    int plant;
    int grow;

    public Flower(int plant, int grow) {
        this.plant = plant;
        this.grow = grow;
    }
}

class FlowerCalculator {
    List<Flower> list;

    public FlowerCalculator() {
        this.list = new ArrayList<>();
    }

    public void add(Flower flower) {
        this.list.add(flower);
    }

    public int calculator() {
        int start = 0;
        int end = 0;
        // 종료날은 plant 기준 + grow;
        for(Flower f : this.list) {
            start += f.plant;
            end = Math.max(end, start+f.grow);
        }
        return end;
    }

    public void sort() {
        this.list.sort((f1, f2) -> {
            if (f1.grow == f2.grow) {
                return f2.plant - f1.plant;
            }
            return f2.grow - f1.grow;
        });
    }
}
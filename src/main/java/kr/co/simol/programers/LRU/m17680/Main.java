package kr.co.simol.programers.LRU.m17680;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // int result = solution.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        // int result = solution.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
        // int result = solution.solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
        int result = solution.solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
        System.out.println(result);
    }
}

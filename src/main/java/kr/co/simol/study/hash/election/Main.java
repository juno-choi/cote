package kr.co.simol.study.hash.election;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        String[] votes = {"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"};
        int k = 3;
        Solution solution = new Solution();
        String sol = solution.solution(votes, k);
        System.out.println(sol);
    }
}

class Solution {
    public String solution(String[] votes, int k) {
        VoteChecker voteChecker = new VoteChecker();
        // 선거 map을 만들어 누가 누구에게 투표했는지 확인
        voteChecker.votes(votes);
        // 투표받은 map을 만들어 2번 투표받았는지 확인
        voteChecker.checkElected(k);
        // 선물 줘야 하는 사람 분류
        voteChecker.checkGift();
        // 선물 받는 사람중 가장 많이 받는 사람 리스트 만들기
        voteChecker.checkGreaterThanVoter();

        return voteChecker.getAnswer();
    }
}

class VoteChecker {
    Map<String, Integer> voteMap;
    Map<String, List<String>> electionMap;
    PriorityQueue<String> voteList;

    int max = 0;

    public VoteChecker() {
        this.voteMap = new HashMap<>();
        this.electionMap = new HashMap<>();
        this.voteList = new PriorityQueue<>();
    }

    public void votes(String[] votes) {
        for (String v : votes) {
            String[] voteInfo = v.split(" ");
            String voter = voteInfo[0];
            String elected = voteInfo[1];

            List<String> voteList = this.electionMap.getOrDefault(elected, new ArrayList<>());
            voteList.add(voter);
            this.electionMap.put(elected, voteList);
        }
    }

    public void checkElected(int k) {
        // 일정 득표수 이상만 통과
        Set<String> keys = this.electionMap.keySet();
        Map<String, List<String>> newElectionMap = new HashMap<>();
        for (String key : keys) {
            if (this.electionMap.get(key).size() >= k) {
                newElectionMap.put(key, this.electionMap.get(key));
            }
        }
        this.electionMap = newElectionMap;
    }

    public void checkGift() {
        Set<String> keys = this.electionMap.keySet();
        for (String key : keys) {
            List<String> voters = this.electionMap.get(key);
            for (String v : voters) {
                int count = this.voteMap.getOrDefault(v, 0) + 1;
                this.voteMap.put(v, count);
                if (this.max < count) {
                    this.max = count;
                }
            }
        }
    }

    public void checkGreaterThanVoter() {
        // 최고 많이 받은 사람만 뽑기
        Set<String> keys = this.voteMap.keySet();
        for (String key : keys) {
            int count = this.voteMap.get(key);
            if (count == max) {
                this.voteList.add(key);
            }
        }
    }

    public String getAnswer() {
        return this.voteList.poll();
    }
}

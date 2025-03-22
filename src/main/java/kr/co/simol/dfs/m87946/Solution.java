package kr.co.simol.dfs.m87946;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 80;
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};
        solution.solution(k, dungeons);
    }
    public int solution(int k, int[][] dungeons) {
        Checker checker = new Checker(dungeons);
        checker.dfs(k, 0);
        return checker.getAnswer();
    }
}


class Checker {
    private int[][] dungeons;
    private boolean[] visited;
    private int answer;

    public Checker(int[][] dungeons) {
        this.dungeons = dungeons;
        this.visited = new boolean[dungeons.length];
        this.answer = 0;
    }

    public void dfs(int k, int count) {
        if (count > answer) {
            answer = count;
        }

        for (int i=0; i<dungeons.length; i++) {
            // 방문한 곳은 다시 방문하지 않는다.
            if (visited[i]) {
                continue ;
            }
            int minK = dungeons[i][0];
            int useK = dungeons[i][1];

            // 피로도 조건이 충족하면 입장
            if (k >= minK) {
                visited[i] = true;
                dfs(k - useK, count+1);
                // 방문한 곳을 다시 오픈
                visited[i] = false;
            }
        }
    }

    public int getAnswer() {
        return this.answer;
    }

}
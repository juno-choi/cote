import kr.co.simol.dfs.m43162.Solution;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.solution(n, computers));
    }
}

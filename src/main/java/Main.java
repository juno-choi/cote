import kr.co.simol.structure.m42586.Solution;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        solution.solution(progresses, speeds);
    }
}

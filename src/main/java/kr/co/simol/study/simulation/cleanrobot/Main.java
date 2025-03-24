package kr.co.simol.study.simulation.cleanrobot;

public class Main {
    public static void main(String[] args) {
        int k = 10;
        // int[][] board = {
        //     {0, 0, 0, 0, 0}, 
        //     {0, 1, 1, 0, 0}, 
        //     {0, 0, 0, 0, 0}, 
        //     {1, 0, 1, 0, 1}, 
        //     {0, 0, 0, 0, 0}};
        k = 20;
        int[][] board = {{0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0}};
        Solution solution = new Solution();
        int[] result = solution.solution(k, board);
        System.out.println("x = "+result[0] + "y = "+result[1]);
    }
}

class Solution {
    public int[] solution(int k, int[][] board) {
        Robot robot = new Robot(board.length, board[0].length);

        while(k > 0) {
            robot.move(board);
            k--;
        }
        
        return robot.getAnswer();
    }
}

class Robot {
    private int x;
    private int y;
    private int direction;
    private int[] driectionX;
    private int[] driectionY;
    public Robot(int xLength, int yLength) {
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        int[] driectionX = {0,1,0,-1};
        int[] driectionY = {1,0,-1,0};
        this.driectionX = driectionX;
        this.driectionY = driectionY;
    }

    public int[] getAnswer() {
        int[] answer = new int[2];
        answer[0] = this.x;
        answer[1] = this.y;
        return answer;
    }

    public void move(int[][] board) {
        int peekMoveX = this.x + this.driectionX[direction];
        int peekMoveY = this.y + this.driectionY[direction];
        int xLength = board.length;
        int yLength = board[0].length;
        if (peekMoveX < 0 || peekMoveX >= xLength) {
            rotation();
            return ;
        }
        if (peekMoveY < 0 || peekMoveY >= yLength) {
            rotation();
            return ;
        }
        if (board[peekMoveX][peekMoveY] == 1) {
            rotation();
            return ;
        }

        move(peekMoveX, peekMoveY);

    }

    private void rotation() {
        this.direction = (this.direction + 1) % 4;
    }

    private void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
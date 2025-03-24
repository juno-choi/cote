package kr.co.simol.study.simulation.seatnumber;

public class Main {
    public static void main(String[] args) {
        int c = 6;
        int r = 5;
        int k = 30;

        Solution solution = new Solution();
        int[] sol = solution.solution(c, r, k);
        System.out.println(sol[0]+ ", "+ sol[1]);
    }
}
class Solution {
    public int[] solution(int c, int r, int k) {
        // 1,1 에서 시작이기 때문에 +1
        int[][] board = new int[c+1][r+1];
        Seat seat = new Seat();
        int limit = c*r;

        if (limit < k) {
            int[] aswner = new int[2];
            return aswner;
        }

        // 초기값으로 세팅
        board[1][1] = 1;
        k--;
        while (k > 0) {
            seat.reservation(board);
            
            k--;
            limit--;
        }

        return seat.getAnswer();
    }
} 

class Seat {
    private int x;
    private int y;
    private int direction;
    private int[] directionX;
    private int[] directionY;

    public Seat() {
        this.x = 1;
        this.y = 1;
        this.direction = 0;
        int[] directionX = {0, 1, 0, -1};
        int[] directionY = {1, 0, -1, 0};
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public void reservation(int[][] board) {
        int moveX = this.x + this.directionX[this.direction];
        int moveY = this.y + this.directionY[this.direction];

        if (moveX < 1 || moveX >= board.length) {
            rotation();
            reservation(board);
            return ;
        }

        if (moveY < 1 || moveY >= board[0].length) {
            rotation();
            reservation(board);
            return ;
        }
        
        if (board[moveX][moveY] == 1) {
            rotation();
            reservation(board);
            return ;
        }

        this.x = moveX;
        this.y = moveY;
        board[moveX][moveY] = 1;
    }

    public int[] getAnswer() {
        int[] answer = new int[2];
        answer[0] = this.x;
        answer[1] = this.y;
        return answer;
    }

    private void rotation() {
        this.direction = (this.direction+1) % 4;
    }
}

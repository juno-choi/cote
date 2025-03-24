package kr.co.simol.study.simulation.lostdog;

/**
 * 빈칸 - 0
 * 나무 - 1
 * 현수 - 2
 * 강아지 - 3
 */
public class Main {
    public static void main(String[] args) {
        int[][] input = {
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 0, 0, 0, 2, 0, 0}, 
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 3, 0, 0, 0, 1}, 
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, 
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}
        };

        int[][] board = input;

        Solution solution = new Solution();
        int sol = solution.solution(board);
        System.out.println(sol);
    }
}

class Solution {
    public int solution(int[][] board) {
        // 현수, 강아지 찾기
        int boardX = board.length;
        int boardY = board[0].length;
        Person huynsu = null;
        Dog dog = null;
        for (int i=0; i<boardX; i++) {
            if (huynsu != null && dog != null) {
                break;
            }
            for (int j=0; j<boardY; j++) {
                if (huynsu != null && dog != null) {
                    break;
                }
                int position = board[i][j];
                if (position == 2) {
                    huynsu = new Person(i,j);
                }
                if (position == 3) {
                    dog = new Dog(i, j);
                }
            }
        }
        // 이동
        int timer = 0;
        // 10,000 분까지만 찾아봄.
        while(timer < 10001) {
            huynsu.move(board);
            dog.move(board);
            timer++;
            if (huynsu.samePosition(dog)) {
                return timer;
            }
        }
        return 0;
    }
}

class DefaultObject {
    private int x;
    private int y;
    private int direction;
    private int[] directionX;
    private int[] directionY;

    public DefaultObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 0;
        int[] directionX = {-1, 0, 1, 0};
        int[] directionY = {0, 1, 0, -1};
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public void move(int[][] board) {
        int moveX = this.x + this.directionX[direction];
        int moveY = this.y + this.directionY[direction];

        if (moveX < 0 || moveX >= board.length) {
            rotation();
            return ;
        }
        if (moveY < 0 || moveY >= board[0].length) {
            rotation();
            return ;
        }
        if (board[moveX][moveY] == 1) {
            rotation();
            return ;
        }

        this.x = moveX;
        this.y = moveY;
    }

    public void rotation() {
        this.direction = (this.direction+1) % 4;
    }

    public boolean samePosition(DefaultObject object) {
        return (this.x == object.getX()) && (this.y == object.getY());
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}

class Person extends DefaultObject{
    public Person(int x, int y) {
        super(x, y);
    }
}

class Dog extends DefaultObject{
    public Dog(int x, int y) {
        super(x, y);
    }
}
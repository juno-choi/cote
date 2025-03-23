package kr.co.simol.bfs.m1844;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] maps = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };
        int solution1 = solution.solution(maps);
        System.out.println(solution1);
    }
    public int solution(int[][] maps) {
        int mapRow = maps.length;
        int mapCol = maps[0].length;
        int mapXLimit = mapRow-1;
        int mapYLimit = mapCol-1;
        int[][] visited = new int[mapRow][mapCol];
        int[] directX = {1, 0, -1, 0};
        int[] directY = {0, 1, 0, -1};

        Queue<Player> queue = new LinkedList<>();
        queue.add(new Player(0,0, 1));
        visited[0][0] = 1;

        while(! queue.isEmpty()) {
            Player player = queue.poll();
            for (int j = 0; j<directX.length; j++) {
                int moveX = directX[j];
                int moveY = directY[j];
                int peekX = player.peekX(moveX);
                int peekY = player.peekY(moveY);
                if (peekX < 0 || peekX > mapXLimit) {
                    continue;
                }

                if (peekY < 0|| peekY > mapYLimit) {
                    continue;
                }

                if (visited[peekX][peekY] == 1) {
                    continue;
                }

                if (maps[peekX][peekY] == 0) {
                    continue;
                }

                if (peekX == mapXLimit && peekY == mapYLimit) {
                    return player.getDistance() + 1;
                }

                visited[peekX][peekY] = 1;
                queue.add(new Player(peekX, peekY, player.getDistance() + 1));
            }
        }
        return -1;
    }
}

class Player {
    private int x;
    private int y;
    private int distance;
    public Player(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int peekX(int x) {
        return this.x + x;
    }

    public int peekY(int y) {
        return this.y + y;
    }

    public int getDistance() {
        return this.distance;
    }
}

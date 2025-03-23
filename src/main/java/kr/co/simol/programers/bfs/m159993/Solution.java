package kr.co.simol.programers.bfs.m159993;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        Solution solution = new Solution();
        int solution1 = solution.solution(maps);
        System.out.println(solution1);
    }

    public int solution(String[] maps) {
        // map으로 만들어야 함.
        // start 찾아야 함
        int mapX = maps.length;
        int mapY = maps[0].length();
        String[][] map = new String[mapX][mapY];
        int startPointX = 0;
        int startPointY = 0;
        int[][] visited = new int[mapX][mapY];
        int[] directX = {1, 0, -1, 0};
        int[] directY = {0, 1, 0, -1};
        boolean isCatchL = false;

        for (int i=0; i<maps.length; i++) {
            String mapAsString = maps[i];
            char[] chars = mapAsString.toCharArray();
            for (int j=0; j<chars.length; j++) {
                String point = ""+ chars[j];
                map[i][j] = point;
                if ("S".equals(point)) {
                    startPointX = i;
                    startPointY = j;
                }
            }
        }

        // bfs로 돌리기
        Queue<Player> queue = new LinkedList<>();
        queue.offer(new Player(startPointX, startPointY, 1, false));
        visited[startPointX][startPointY] = 1;


        while (! queue.isEmpty()) {
            Player player = queue.poll();
            boolean isL = player.getL();

            for (int i = 0; i < 4; i++) {
                int moveX = directX[i];
                int moveY = directY[i];

                int peekX = player.peekX(moveX);
                int peekY = player.peekY(moveY);

                if (peekX < 0 || peekX > mapX -1) {
                    continue;
                }

                if (peekY < 0 || peekY > mapY -1) {
                    continue;
                }

                String mapInfo = map[peekX][peekY];
                if ("X".equals(mapInfo)) {
                    continue;
                }

                if (isL) {

                    if (visited[peekX][peekY] == 2) {
                        continue;
                    }
                    if ("E".equals(mapInfo)) {
                        return player.getSecond();
                    }

                    visited[peekX][peekY] = 2;
                } else{
                    if (isCatchL) {
                        continue;
                    }
                    if (visited[peekX][peekY] == 1) {
                        continue;
                    }
                    if ("L".equals(mapInfo)) {
                        player.catchL();
                        isCatchL = true;
                    }
                    visited[peekX][peekY] = 1;
                }
                queue.offer(new Player(peekX, peekY, player.getSecond() + 1, player.getL()));

            }
        }

        return -1;
    }
}

class Player {
    private int x;
    private int y;
    private int second;
    private boolean isL;

    public Player(int x, int y, int second, boolean isL) {
        this.x = x;
        this.y = y;
        this.second = second;
        this.isL = isL;
    }

    public int peekX(int x) {
        return this.x + x;
    }

    public int peekY(int y) {
        return this.y + y;
    }

    public void catchL() {
        this.isL = true;
    }

    public boolean getL () {
        return this.isL;
    }

    public int getSecond() {
        return this.second;
    }
}

package kr.co.simol.bfs.study;

import java.util.LinkedList;
import java.util.Queue;

public class TreeSearch {
    public static void main(String[] args){
        Checker checker = new Checker();
        System.out.println(+checker.bfs(13, 35));
    }

}

class Checker {
    private boolean[] visited;
    private int[] direct;
    public Checker() {
        this.visited = new boolean[10001];
        int[] direct = {1, -1, 5};
        this.direct = direct;
    }

    public int bfs(int startPoint, int targetPoint) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint);
        visited[startPoint] = true;
        int level = 0;
        while(! queue.isEmpty()) {

            int size = queue.size();
            for (int i=0; i<size; i++) {

                int currentPoint = queue.poll();

                if (currentPoint == targetPoint) {
                    return level;
                }

                for (int d : direct) {
                    int movePoint = currentPoint + d;
                    if (visited[movePoint]) {
                        continue;
                    }
                    if (movePoint < 0) {
                        continue;
                    }
                    if (movePoint > 10000) {
                        continue;
                    }
                    queue.offer(movePoint);
                    visited[movePoint] = true;
                }
            }
            level++;

        }
        return 0;
    }
}

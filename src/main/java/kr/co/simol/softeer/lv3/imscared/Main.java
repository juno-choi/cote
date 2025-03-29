package kr.co.simol.softeer.lv3.imscared;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            String[][] board = new String[n][m];
            for(int i=0; i<n; i++) {
                String[] strs = br.readLine().split("");
                for(int j=0; j<m; j++) {
                    board[i][j] = strs[j];
                }
            }

            System.out.println(new Solution().solution(n, m, board));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    public String solution(int n, int m, String[][] board){

        Miro miro = new Miro(board);

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j].equals("N")) {
                    Person person = new Person(i, j, 0, "N");
                    miro.setPerson(person);
                }
                if (board[i][j].equals("G")) {
                    Ghost ghost = new Ghost(i, j, 0, "G");
                    miro.addGhost(ghost);
                }
            }
        }

        int personMoveCount = miro.personCalculator();
        int ghostMoveCount = miro.ghostCalculator();

        return personMoveCount >= ghostMoveCount ? "No" : "Yes";
    }
}

class Person extends MiroObject {

    public Person(int x, int y, int move, String type) {
        super(x, y, move, type);
    }
}

class Ghost extends MiroObject{
    public Ghost(int x, int y, int move, String type) {
        super(x, y, move, type);
    }
}

class MiroObject {
    int x;
    int y;

    int move;
    String type;

    public MiroObject(int x, int y, int move, String type) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.type = type;
    }
}

class Miro {
    String[][] board;
    Person person;
    List<Ghost> ghostList;

    public Miro(String[][] board) {
        this.board = board;
        this.person = null;
        this.ghostList = new ArrayList<>();
    }

    public void addGhost(Ghost ghost) {
        ghostList.add(ghost);
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int personCalculator() {

        return bfs(this.person);
    }

    public int ghostCalculator() {
        int min = Integer.MAX_VALUE;
        if (this.ghostList.size() == 0) {
            return min;
        }
        for (Ghost g : this.ghostList) {
            min = Math.min(bfs(g), min);
        }

        return min;
    }

    public int bfs(MiroObject miroObject) {
        Queue<MiroObject> queue = new LinkedList<>();
        int[] directionX = {1,0,-1,0};
        int[] directionY = {0,1,0,-1};
        queue.add(miroObject);
        while(! queue.isEmpty()) {
            MiroObject pollObject = queue.poll();
            for (int i=0; i<4; i++) {
                int moveX = pollObject.x + directionX[i];
                int moveY = pollObject.y + directionY[i];

                if (moveX < 0 || moveX >= this.board.length) {
                    continue;
                }

                if (moveY < 0 || moveY >= this.board[0].length) {
                    continue;
                }

                if (pollObject.move > (this.board.length *this.board[0].length)) {
                    return Integer.MAX_VALUE;
                }

                if (pollObject.type.equals("N") && board[moveX][moveY].equals("#")) {
                    continue;
                }

                if (pollObject.type.equals("N") && board[moveX][moveY].equals("G")) {
                    continue;
                }
                if (board[moveX][moveY].equals("D")) {
                    return pollObject.move +1;
                }
                queue.add(new MiroObject(moveX, moveY, pollObject.move +1, pollObject.type));
            }
        }
        return 0;
    }
}
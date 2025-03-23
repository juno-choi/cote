package kr.co.simol.programers.bfs.study;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node lt1 = new Node(2);
        Node rt2 = new Node(3);
        Node lt11 = new Node(4);
        Node rt12 = new Node(5);
        Node lt21 = new Node(6);
        Node rt22 = new Node(7);
        root.updateLt(lt1);
        root.updateRt(rt2);

        lt1.updateLt(lt11);
        lt1.updateRt(rt12);

        rt2.updateLt(lt21);
        rt2.updateRt(rt22);

        Solution solution = new Solution();
        solution.solution(root);
    }

}

class Node {
    private int data;
    private Node lt;
    private Node rt;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public Node getLt() {
        return this.lt;
    }

    public Node getRt() {
        return this.rt;
    }

    public void updateLt(Node lt) {
        this.lt = lt;
    }

    public void updateRt(Node rt) {
        this.rt = rt;
    }
}

class Solution {
    public void solution(Node node) {
        bfs(node);
    }

    public void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int level = 0;

        while (! queue.isEmpty()) {
            int length = queue.size();
            System.out.println("level = "+ level);

            for (int i=0; i<length; i++) {
                Node currentNode = queue.poll();
                System.out.println("current node data = " + currentNode.getData());
                if (currentNode.getLt() != null) {
                    queue.add(currentNode.getLt());
                }
                if (currentNode.getRt() != null) {
                    queue.add(currentNode.getRt());
                }
            }
            level++;
        }
    }
}

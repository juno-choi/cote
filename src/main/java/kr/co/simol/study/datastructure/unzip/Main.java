package kr.co.simol.study.datastructure.unzip;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "2(ab3((cd)))";
        Solution solution = new Solution();
        String sol = solution.solution(s);
        System.out.println(sol);
    }
}

class Solution {
    public String solution(String s) {
        AlZip alZip = new AlZip();
        return alZip.unzip(s);
    }
}

class AlZip {
    Stack<String> stack;
    public AlZip() {
        this.stack = new Stack<>();
    }

    public String unzip(String s) {
        char[] chars = s.toCharArray();
        StringBuffer bf = new StringBuffer();
        for (int i=0; i<chars.length; i++) {
            char c = chars[i];
            if (')' == c) {
                while (! this.stack.isEmpty()) {
                    String data = this.stack.pop();
                    if ("(".equals(data)) {
                        String num = this.stack.pop();
                        if (Character.isDigit(num.charAt(0))) {
                            int number = Integer.valueOf(num);
                            StringBuffer bf2 = new StringBuffer();
                            for (int j=0; j<number; j++) {
                                bf2.insert(0, bf.toString());
                                this.stack.push(bf2.toString());
                                bf.setLength(0);
                            }
                        }
                        break;
                    }
                    bf.insert(0, data);
                }
                continue;
            }
            this.stack.push(c+"");
        }

        StringBuffer answerBuffer = new StringBuffer();
        while(! this.stack.isEmpty()) {
            answerBuffer.insert(0, this.stack.pop());
        }
        return answerBuffer.toString();
    }

}
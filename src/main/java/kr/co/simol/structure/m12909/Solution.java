package kr.co.simol.structure.m12909;
import java.util.*;

/**
 * stack을 사용하며 stack의 크기를 체크해야되는 문제
 */
public class Solution {
    boolean solution(String s) {
        Checker checker = Checker.from(s);
        checker.check();
        return checker.getAnswer();
    }

}

class Checker {
    char[] characters;
    boolean result;

    protected Checker(String s) {
        this.characters = s.toCharArray();
        result = false;
    }

    public static Checker from(String s) {
        return new Checker(s);
    }

    public void check() {
        Stack<Character> stack = new Stack<>();
        for (char c : this.characters) {
            if (c == '(') {
                stack.add(c);
            }

            if (stack.isEmpty()) {
                return ;
            }
            if (c == ')') {
                stack.pop();
            }
        }
        this.result = stack.size() == 0;
    }

    public boolean getAnswer() {
        return this.result;
    }
}
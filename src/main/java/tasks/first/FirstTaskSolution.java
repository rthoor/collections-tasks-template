package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        String answer = "";
        int m = adjacencyMatrix.length;
        ArrayList<Integer> status = new ArrayList<>();
        ArrayDeque<Integer> graph = new ArrayDeque<Integer>();
        graph.add(startIndex);
        while(status.size() < m) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (adjacencyMatrix[i][graph.peekFirst()]){
                    temp.add(i);
                }
            }
            Collections.sort(temp);
            graph.addAll(temp);

            if(!status.contains(graph.peekFirst())) {
                status.add(graph.peekFirst());
                answer = answer + graph.pop() + ",";
            }
            else{
                graph.removeFirst();
            }
        }
        return answer.substring(0, answer.length()-1);
    }

    @Override
    public Boolean validateBrackets(String s) {
        ArrayDeque<Character> symbols = new ArrayDeque<>();
        for(int j = 0; j < s.length(); j++){
            if((s.charAt(j) == '(') || (s.charAt(j) == '{') || (s.charAt(j) == '[')){
                symbols.addFirst(s.charAt(j));
            }
            else if (s.charAt(j) == ')') {
                if(symbols.peekFirst()=='('){
                    symbols.removeFirst();
                }
                else{
                    return false;
                }
            }
            else if (s.charAt(j) == '}') {
                if(symbols.peekFirst()=='{'){
                    symbols.removeFirst();
                }
                else{
                    return false;
                }
            }
            else if (s.charAt(j) == ']') {
                if(symbols.peekFirst()=='['){
                    symbols.removeFirst();
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Long> nums = new ArrayDeque<>();
        String temp = "";
        for (int j = 0; j < s.length(); j++) {
            if ((s.charAt(j) - 48 >= 0) && (s.charAt(j) - 48 <= 9)) {
                temp = temp + s.charAt(j);
            }
            else if ((s.charAt(j) == ' ')){
                if((s.charAt(j-1) - 48 >= 0) && (s.charAt(j-1) - 48 <= 9)) {
                    addNumber(temp, nums);
                    temp = "";
                }
            } else {
                calc(s.charAt(j), nums);
                temp = "";
            }
        }
        return nums.pop();
    }

    public void addNumber(String temp, ArrayDeque<Long> nums){
        long number = 0;
        long k = 1;
        for(int i = temp.length()-1; i >= 0; i--){
            number = number + (temp.charAt(i)-48)*k;
            k*=10;
        }
        nums.addFirst(number);
    }

    public void calc(char op, ArrayDeque<Long> nums){
        long a = nums.pop();
        long b = nums.pop();
        switch(op){
            case('+'):{
                nums.addFirst(a+b);
                break;
            }
            case('-'):{
                nums.addFirst(a-b);
                break;
            }
            case('*'):{
                nums.addFirst(a*b);
                break;
            }
            case('/'):{
                nums.addFirst(a/b);
                break;
            }
        }
    }
}

class Solution {
    public int maxDepth(String s) {
        int max, temp;
        max = temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                temp++;
                if (temp > max)
                    max = temp;
            } else if (s.charAt(i) == ')') {
                temp--;
            }
        }
        return max;
    }
}


public class Main {
    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        Solution solution = new Solution();
        System.out.println(solution.maxDepth(s));
    }
}
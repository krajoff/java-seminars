class Solution9 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String str = String.valueOf(x);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length()-1 - i)) {
                return false;
            }
        }
        return true;
    }
}

public class PalindromeNumber_Task9 {
    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        if (solution9.isPalindrome(121)) {
            System.out.println("1.Good");
        } else {
            System.out.println("1.Bad");
        }
        if (!solution9.isPalindrome(-121)) {
            System.out.println("2.Good");
        } else {
            System.out.println("2.Bad");
        }
        if (!solution9.isPalindrome(10)) {
            System.out.println("3.Good");
        } else {
            System.out.println("3.Bad");
        }
    }
}

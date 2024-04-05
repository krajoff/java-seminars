class Solution6 {
    public String convert(String s, int numRows) {
        int numColoumns = s.length();
        int count = 0;
        int shift;
        if (numRows == 1 || s.length() == 1) return s;
        String[][] output = new String[numRows][numColoumns];

        outer:
        for (int j = 0; j < numColoumns; j++) {
            for (int i = 0; i < numRows; i++) {
                if (count >= s.length()) break outer;
                if (j % (numRows - 1) == 0) {
                    output[i][j] = String.valueOf(s.charAt(count));
                    count++;
                } else {
                    shift = j % (numRows - 1);
                    output[numRows - shift - 1][j] = String.valueOf(s.charAt(count));
                    count++;
                    break;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColoumns; j++) {
                if (output[i][j] != null) {
                    res.append(output[i][j]);
                }
            }
        }
        return res.toString();
    }
}

public class ZigzagConversion_Task6 {
    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        if (solution6.convert("PAYPALISHIRING", 3)
                .equals("PAHNAPLSIIGYIR"))
            System.out.println("1.Good");
        if (solution6.convert("PAYPALISHIRING", 4)
                .equals("PINALSIGYAHRPI"))
            System.out.println("2.Good");
        if (solution6.convert("A", 1)
                .equals("A"))
            System.out.println("3.Good");
        if (solution6.convert("AB", 2)
                .equals("AB"))
            System.out.println("4.Good");
        if (solution6.convert("ABC", 2)
                .equals("ACB"))
            System.out.println("5.Good");
        if (solution6.convert("ABCD", 3)
                .equals("ABDC"))
            System.out.println("6.Good");


    }
}

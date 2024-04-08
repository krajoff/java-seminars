import java.util.*;

class Solution1700 {
    public int countStudents(int[] students, int[] sandwiches) {
        int total = 0;
        int count = 0;
        List<Integer> l_st = new ArrayList<>();
        List<Integer> l_sa = new ArrayList<>();
        for (int student : students) l_st.add(student);
        for (int sandwich : sandwiches) l_sa.add(sandwich);

        do {
            if (l_st.getFirst().equals(l_sa.getFirst())) {
                total++;
                l_sa.removeFirst();
                l_st.removeFirst();
                count = 0;
            } else {
                l_st.addLast(l_st.getFirst());
                l_st.removeFirst();
                count++;
            }
        } while (count != l_st.size());
        return students.length - total;
    }
}

public class NumberofStudentsUnabletoEatLunch_Task1700 {
    public static void main(String[] args) {
        Solution1700 solution1700 = new Solution1700();
        if (solution1700
                .countStudents(new int[]{1, 1, 0, 0},
                        new int[]{0, 1, 0, 1}) == 0) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution1700
                    .countStudents(new int[]{1, 1, 0, 0},
                            new int[]{0, 1, 0, 1}));
        }
        if (solution1700
                .countStudents(new int[]{1, 1, 1, 0, 0, 1},
                        new int[]{1, 0, 0, 0, 1, 1}) == 3) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution1700
                    .countStudents(new int[]{1, 1, 0, 0},
                            new int[]{0, 1, 0, 1}));
        }
    }
}

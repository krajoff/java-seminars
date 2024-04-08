class Solution11 {
    public int maxArea(int[] height) {
        int max = 0;
        int temp;
        for (int i = 0; i < height.length * 2; i++) {
            for (int j = height.length - 1; j > i; j--) {
                if (height[i] > height[j]) {
                    temp = height[j] * (j - i);
                    if (temp > max)
                        max = temp;
                } else {
                    temp = height[i] * (j - i);
                    if (temp > max)
                        max = temp;
                    break;
                }
            }
        }
        return max;
    }
}

public class ContainerWithMostWater_Task11 {
    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        if (solution11.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) == 49) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution11.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        }
        if (solution11.maxArea(new int[]{1, 1}) == 1) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution11.maxArea(new int[]{1, 1}));
        }
    }

}

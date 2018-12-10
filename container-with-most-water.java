/*

Container formed by 1st and  last line have maximum width. Its water level is the smaller of the heights of first and last line.

All other containers are less wide and thus would need a higher water level in order to hold more water.

The smaller one of first and last line can't support a higher water level and can thus be safely removed from further consideration.

*/


class Solution {
    public int maxArea(int[] height) {
        int bg = 0;
        int ed = height.length - 1;
        int  maxArea = 0;
        while(bg < ed) {
            int applicableHeight = Math.min(height[ed],  height[bg]);
            int curArea = (ed - bg)*applicableHeight;
            maxArea = Math.max(maxArea, curArea);
            // System.out.println("bg = " + bg + ", ed = " + ed + ", applicableHeight = " + applicableHeight + ", curArea = " + curArea);
            if(height[bg] < height[ed]) {
                bg++;
            }
            else {
                ed--;
            }
        }
        return maxArea;
    }
}

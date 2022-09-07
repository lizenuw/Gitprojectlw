public class getMaxList {
    public static int getMaxList(int[] list){
        int max=-999999;
        for(int i=0;i<list.length;i++){
            int sum = 0;
            for(int j=i;j<list.length;j++){
                sum+=list[j];
                if(sum>max){
                    max=sum;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] list = new int[]{1, -2, 3,-2, 5, 1};
        int max = getMaxList(list);
        System.out.println("最大子数组之和为："+max);
    }
}

public class OutPutNum {

    public static void main(String[] args) {
        int x,y;
        int count=0;
        for(x=2;x<=20000;x++) {
            for(y=2;y<x;y++) {
                if(x%y==0) break;
            }
            if(y*y>x) {
                count++;
                System.out.print(y + "\t");
                if (count % 5 == 0)
                    System.out.println();
            }
        }
    }
}
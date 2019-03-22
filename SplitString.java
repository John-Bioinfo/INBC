import java.util.ArrayList; 
import java.lang.Math; 

public class SplitString {  
    public static void main(String[] args) {  
        String s1 =  "A:2142	C:0	T:0	G:12	N:0  ";         
        int[] allNums = basesN(s1);  
        
        ArrayList<int[]> list = new ArrayList<int[]>();  
        String s2 = "A:4116	C:3	T:30	G:2	N:60  "; 
        int[] secNums = basesN(s2);
        
        list.add(allNums);
        list.add(secNums);
        
        for (int[] n : list) {  
            System.out.println("element : " + n[0]+":"+n[1]+":"+n[2]+":"+n[3]+":"+n[4]);
            
            int A = sum(n);         
            double times = Math.ceil((double)A/1000);  
        
            int c = (int)Math.round(A/times);
        
            System.out.println(c); 
            System.out.println(times);
        }          
    }
    public static int[] basesN(String in){
        in = in.trim();
        int[] out = new int[5];
        String[] a = in.split("\\\t");
        for(int i=0;i<out.length;i++){
            out[i] = Integer.parseInt(a[i].substring(2));
        }
        return out;
    }
    static int sum(int[] nums){
        int sumN = 0;
        for(int n=0;n<nums.length;n++){
            sumN += nums[n];
        }
        return sumN;
    }
    
}  





import java.io.*;
import java.util.*;

public class Solution
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int t = 1;
      t = sc.nextInt();
      while (t-- > 0)
      {
          solve();
      }
      out.close();
    }

    public static void solve()
    {
       int n = sc.nextInt();
       int[] arr = new int[n];
       List<Integer> neg = new ArrayList<>();
       List<Integer> pos = new ArrayList<>();

       for(int i = 0; i < n; i++){
         int val = sc.nextInt();
         if(val < 0){
           neg.add(val);
         }else{
           pos.add(val);
         } 
       }

       long sum = 1L;
       if(n == 5){
        for(int i = 0; i < neg.size(); i++){
          sum *= neg.get(i);
        }
        for(int i = 0; i < pos.size(); i++){
          sum *= pos.get(i);
        }
        out.println(sum);
        return;
       }


     

       Collections.sort(neg);
       Collections.sort(pos, Collections.reverseOrder());

        // if all all -ve
       if(pos.size() == 0){
         long sum1 = 1L;
         int index = neg.size() - 1;
         int k = 5;
         while(k-- > 0){
           sum1 *= neg.get(index);
           index--;
         }
         out.println(sum1);
         return;
      }

       // 1. 4 -ve, 1 +ve
       long max = Long.MIN_VALUE;
       if(neg.size() >= 4 && pos.size() >= 1){
         long sum1 = 1L;
         for(int i = 0; i <= 3; i++){
           sum1 *= neg.get(i);
         }

         sum1 *= pos.get(0);
        //  out.println(" sum " + sum1);
         max = Math.max(sum1, max);
       }

       // 2. 2 -ve, 3+ve
       if(neg.size() >= 2 && pos.size() >= 3){
         long sum1 = 1L;
         for(int i = 0; i<= 1; i++){
           sum1 *= neg.get(i);
         }

         for(int i = 0; i <= 2; i++){
           sum1 *= pos.get(i);
         }

         max = Math.max(max, sum1);
       }

       // 5 pos
       if(pos.size() >= 5){
        long sum1 = 1L; 
        for(int i = 0; i<= 4; i++){
            sum1 *= pos.get(i);
         }
         max = Math.max(sum1, max);
       }
       
       out.println(max);
    }

    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    public static int lower_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0, e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) <= k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        return Math.abs(s) - 1;
    }

    public static int upper_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0;
        int e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) < k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        if (s == ar.size())
        {
            return -1;
        }
        return s;
    }

    static class Kioken
    {
        // FileInputStream br = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next()
        {
            while (!st.hasMoreTokens())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt()
        {
            return Integer.parseInt(next());
        }

        public long nextLong()
        {
            return Long.parseLong(next());
        }

        public double nextDouble()
        {
            return Double.parseDouble(next());
        }

        public String nextLine()
        {
            try
            {
                return br.readLine();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext()
        {
            String next = null;
            try
            {
                next = br.readLine();
            }
            catch (Exception e)
            {
            }
            if (next == null || next.length() == 0)
            {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }
    }
}
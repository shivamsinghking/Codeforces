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

    /**
     * Observation => 
     * S = S(odd index term)  + S (even index term)
     * at least one of oddd or even S(odd) or S(even) <= S/2
     * for at even or odd indices we will convert it to 1
     */
    
    public static void solve()
    {
      int n = sc.nextInt();
      int[] arr = new int[n];
      long sum1 = 0L;
      for(int i = 0; i < n; i++){
        arr[i] = sc.nextInt();
        sum1  += arr[i]; 
      }

      int max_no = (int)(1e9) + 7;
      int[] ans = new int[n];
      
      long even = 0L, odd = 0L;
      for(int i = 0; i < n; i++){
          if(i%2 != 0){
              odd += arr[i];
          }else{
              even += arr[i];
          }
      }
      

      // check
      boolean flag = true;
     if(2*odd <= sum1){
         flag = false;
       for(int i = 0 ; i <n; i++){
           if(i%2 != 0){
               arr[i] = 1;
           }
       }
     }

     if(2*even <= sum1 && flag){
        for(int i = 0 ; i <n; i++){
            if(i%2 == 0){
                arr[i] = 1;
            }
        }
     }

      for(int i : arr){
        out.print(i + " ");
      }
      out.println();
      
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
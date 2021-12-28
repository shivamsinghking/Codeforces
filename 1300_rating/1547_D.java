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
       for(int i = 0; i < n; i++){
         arr[i] = sc.nextInt();
       }

       if(n == 1){
         out.println(0);
         return;
       }

       int[] ans = new int[n];
       ans[0] = 0;

       int[] grow = new int[n];
       grow[0] = arr[0];
       for(int i = 1;i < n; i++){
        int val = grow[i-1];
        int xor = arr[i]^val;
        
        // out.println(" val " + val + " " + xor);
        for(int j = 0; j < 31; j++){
          int v = (xor >> j)&1;
          // out.println(" ==vv " + v + " ");
          if(v == 1){
            // out.println(" ==val " + v);
            if(((val >> j)&1) == 0){
              // out.println(" == >" + (1 << j));
              val += (1 << j);
            }
          }
        }
        // out.println(" val1 " + val);
        ans[i] = val^arr[i];
        grow[i] = val;
       }

       for(int i: ans){
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
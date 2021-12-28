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

    static int solve(int p, int m){
      int min = Math.min(p, m);
      int max = Math.max(p, m);

      if(min == 0) return 0;
      if(min == 1){
        if(max >= 3) return 1;
        else return 0;
      }

      if(min == 2 && max == 2){
         return 1;
      }
      int q = max/3;
      if(q >= min){
          return min;
      }else{
        int r = max%3;
        int cnt = q;
        int left = min -  q; // not of left min candidate
        // out.println(" left => " + left + " " + r);
        return cnt + Math.max(solve(left, r), solve2(left, r));
      }
    }

    static int solve2(int p, int m){
      int min = Math.min(p, m);
      int max = Math.max(p, m);

      if(min == 0) return 0;
      if(min == 1){
        if(max >= 3) return 1;
        else return 0;
      }

      if(min == 2 && max == 2){
         return 1;
      }

      if(min%2 == 0){
        return min/2;
      }else{
        return (min - 1)/2 + Math.max(solve(1, max - (min - 1)), solve2(1, max - (min - 1)));
      }

    }
    
    static long dp(long p, long m){
      long min = Math.min(p, m);
      long max = Math.max(p, m);

     if(min == 0) return 0;
      if(min == 1){
        if(max >= 3) return 1;
        else return 0;
      }

      if(min == 2 && max == 2){
         return 1;
      }

      if(max/3 >= min){
        return min;
      }

      if(min == max && min%2 == 0){
        return min/2;
      }
     return 1 + dp(min - 2, max - 2);
    }
    public static void solve()
    {
       long p = sc.nextLong();
       long m = sc.nextLong();
       
      //  int max = Math.max(p, m);
      //  int[][] arr = new int[max][max];
      // HashMap<Integer, HashMap<Integer>> map = new HashMap<>();
       
       out.println(dp(p, m));
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
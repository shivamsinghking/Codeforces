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
       int U = sc.nextInt();
       int R = sc.nextInt();
       int D = sc.nextInt();
       int L = sc.nextInt();
       
    // Observation
    /**
     * 4 corners that has 2^4 options => 1 1 0 1, 1 0 0 1, 1 0 1 1
     *                                   r d l u
     * 1 means corner is black, 0 means it is white  
     */

     // top-left, left-down, donw-right, right-top

     int cnt = 0;
     for(int i = 0; i < 16; i++){
        int u = U, r = R, d = D, l = L;
        
       if(((i >> 0)&1) == 1){
             u--;
             l--;
       }

       if(((i >> 1)&1) == 1){
        l--;
        d--;
      }

      if(((i >> 2)&1) == 1){
        d--;
        r--;
      }

      if(((i >> 3)&1) == 1){
       r--;
       u--;
      }

      if(Math.min(u, Math.min(l, Math.min(r, d))) < 0 || Math.max(r, Math.max(u, Math.max(d, l))) > (n - 2)){
        cnt++;
      }
     }

     if(cnt == 16){
       out.println("NO");
     }else{
       out.println("YES");
     }
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
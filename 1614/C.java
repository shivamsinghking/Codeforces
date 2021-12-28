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

    // Observation => 
    /**
     * 1. arr = [1, 2, 3] => subsequence = 2^n - 1 => 7 => 1,   2,    3,    1 2 , 1 3,  2 3,  1 2 3
     * see the xor of 7 =>                                (1), (10), (11), (11), (10), (01),  (0)
     * every ele of the array is occuring 2^(n-1) => 2^2 = 4 times
     * 1, 2, 3 -> occurs 4 times
     * Suppose => 2 (10) => Our observation is that every ith set-bit is occuring 4 times(2^(n-1)) times.
     * So every ith bit contribution is 4 times
     *  bit =>          1 1 0 1
     * contribution =>  4 4   4  times 
     * So sum will be => 4*(1 + 4 + 8) from above example of bit
     * By doint OR of all "x" we get all set bits present. and each set bit contribute 2^(n-1) times           
     */
    public static void solve()
    {
      int a = (int)Math.pow(10, 5);
      // int x = 0;
      long bits = 0;
      int n = sc.nextInt();
      int m = sc.nextInt();
      int[][] arr = new int[m][3];
      for(int i = 0; i < m; i++){
        int l = sc.nextInt();
        int r = sc.nextInt();
        int x = sc.nextInt();
        arr[i][0] = l;
        arr[i][1] = r;
        arr[i][2] = x;
      }

      for(int i = 0; i < m; i++){
        bits = bits | arr[i][2];
      }

      long mod = 1000000007;
      out.println(qpow(2, n-1, mod)*bits%mod);
    }

    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

   static long qpow(long a, int k, long mod) {
      long res = 1L;
      while (k > 0) {
        if ((k & 1) == 1) res = res * a%mod;
        a = a * a%mod;
        k >>= 1;
      }
      return res%mod;
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
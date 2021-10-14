import java.io.*;
import java.util.*;

/**
 * NOTE: 1 << m ( will not work here ) as m can upto 63 ( 1 << m only work till 31 )
 * 1L << m ( will also not work ), so use Maht.pow instead for getting values
 */

/**
 * valid year with one zero is like = 1111111011
 * Suppose we get a range a - b => 
 * a = 11100010101, b = 1111111010101
 *                      | -> find this index i = 11 
 * then get value with all 1's with of length = index+1 => val = 1111111111111
 * and now this val > b
 * we will loop through this and make every 1's -> 0
 * 111111111110, 1111111111101, 1111111111011 ... so on => 0111111111111 ( invalid doesn't count )
 * only if x - 2*i > 0 ( then only valid ) so cnt++
 * after counting all for this "x", 
 * remove the bit at this index "i", new value = 0111111111111
 * again do the above process
 * we will remove bit from last form "val" if it is > a only
 * print cnt
 */
public class Main
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String... args)
    {
      solve();
      out.close();
    }
    
    public static int mostSignificantBit(long x){
      for(int i = 63; i >= 0; i--){
        if(((x >> i) & 1) == 1) return i;
      }
      return -1;
    }

    public static int getCountYear(long x, long a, long b){
      long i = 1;
      int cnt = 0;
      while((x - i ) > 0){
         if(x - i >= a && (x - i) <= b){
           if(x - 2*i > 0) cnt++;
         }
        i *= 2;
      }
      return cnt;
    }

    public static void solve(){
       long a = sc.nextLong();
       long b = sc.nextLong();
// b > a
       int msb1 = mostSignificantBit(a);
       long msb2 = mostSignificantBit(b)+1;
       int cnt = 0;
       long val = (long)Math.pow(2, msb2)-1; 
       cnt += getCountYear(val, a, b);
       while(val >= a){
         int m = mostSignificantBit(val);
         val = (long)Math.pow(2, m) ^ val;
         cnt += getCountYear(val, a, b);
       }
       out.println(cnt);
      return ;
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
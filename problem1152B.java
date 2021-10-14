import java.io.*;
import java.util.*;

/**
 * Suppose n = 39 => 1 0 0 1 1 1
 *                   5 4 3 2  1 0 <- index
 * msb is at index = 5, and remove this, 1 << (index+1) - 1
 * We will make this bit 0. 
 * So after it   n =   0 1 1 0 0 0
 * if position is odd then n++, otherwise find msb
 * Suppose n = 0010000
 * x < 10^6 , max no. of bits can be 20 in x
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
    
    public static boolean isValid(int x){
      double y = Double.valueOf(x);
      for(int i = 0;  i <= 30; i++){
        if(y == (Math.pow(2, i) - 1)){
          return true;
        }
      }
      return false;
    }

    public static int mostSignificantBit(int x){
      for(int i = 20; i >= 0; i--){
        if(((x >> i) & 1) == 1) return i;
      }
      return -1;
    }
    public static void solve(){
       int n = sc.nextInt();

      //  out.println(isValid(n));
       List<Integer> ll = new ArrayList<>();
       int cnt = 0;
       int i = 0;
       while(!isValid(n)){

        if(i%2 != 0){
          n++;
          i++;
          continue;
        }

        int msb = mostSignificantBit(n);
        if(msb == -1){
          out.println("invalid");
          break;
        }
        if((1 << msb) != n) msb++;

        n = n ^ ((1 << msb) -1);
        ll.add(msb);
        i++;
       }

       out.println(i);
       for(int j: ll){
         out.print(j+ " ");
       }

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
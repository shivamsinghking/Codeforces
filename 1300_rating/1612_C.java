import java.io.*;
import java.util.*;

public class Main
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

    static boolean bs(long lines, long k, long x){
        if(lines > k){
          long res = (k*(k+1))/2;
          long left = (2*k - 1 - lines);
          long kk = k - 1;
          long add_val = ((kk*(kk+1))/2) -  (left*(left+1))/2;
          // out.println(" add " + res + " " + add_val + " " + lines + " " + k);
          if(res + add_val <= x){
            return true;
          }else{
            return false;
          }
        }else{
          // out.println(" line " + lines);
          long res = (lines*(lines+1))/2;
          if(res <= x) return true;
          else return false;
        }
    }

    public static void solve()
    {
       long k = sc.nextLong();
       long x = sc.nextLong();

       long max = 2*k - 1;
       long l = 0;
       long r = max;
       long ans = 0L;
       while(l < r){
         long mid = (l+r)/2;
        //  out.println(" lef " + mid + " "  + l + " " + r);
         if(bs(mid, k, x)){
           l = mid + 1;
           ans = mid;
         }else{
           r = mid;
         }
       }

       if(ans > k){
        long res = (k*(k+1))/2;
        long left = (2*k - 1 - ans);
        long kk = k - 1;
        long add_val = ((kk*(kk+1))/2) -  (left*(left+1))/2;
        if(res + add_val < x){
          out.println(ans+1);
        }else if(res + add_val == x){
          out.println(ans);
        }
       }else{
        long res = (ans*(ans+1))/2;
        if(res < x){
          out.println(ans+1);
        }else if(res == x){
          out.println(ans);
        }
       }
    }

    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    
    public static void reverse(int[] arr) {
    	Arrays.sort(arr);
    	int n = arr.length;
    	for(int i = 0 ; i < arr.length; i++) {
    		int temp = arr[i];
    		arr[i] = arr[n - 1 - i];
    		arr[n - 1 - i] = temp;
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
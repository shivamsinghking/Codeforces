import java.io.*;
import java.lang.reflect.Array;
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

    static Boolean[] get;
    static boolean ln(int n, int l){
       if(n < l){
         return false;
       }

       if(get[n] != null){
         return get[n];
       }

      //  out.println(n + " " + l);
       if(String.valueOf(n).indexOf(String.valueOf(l)) != -1){
         get[n] = true;
        //  out.println(n + " == " + l);
         return true;
       }

       for(int i = l; i < n; i++){
         boolean ans = ln(i, l) && ln(n - i, l);
         if(ans){
           get[n] = true;
           return true;
         }
       }

       get[n] = false;
       return false;
    }
    public static void solve()
    {
      get = new Boolean[1000];
      int n = sc.nextInt();
      int d = sc.nextInt();
      int[] arr = new int[n];

      Arrays.fill(get, null);

      // out.println(Arrays.toString(get));
      for(int i = 0; i < n; i++){
        arr[i] = sc.nextInt();
      }

      for(int i = 0; i < n; i++){
          if(arr[i] >= (10*d+9)){
            out.println("YES");
            continue;
          }
          if(ln(arr[i], d)){
           out.println("YES");
          }else{
            out.println("NO");
          }
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
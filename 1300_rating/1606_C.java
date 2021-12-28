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

    static boolean bs(int[] arr, long s, int k){
       int n = arr.length - 1;
       long cnt = 0;
       while(n >= 0){
         if(s == 0) break;
         if(s%arr[n] < s){
           out.println((s/arr[n]) + " === " + s + " " + arr[n] + " " + cnt);
           cnt += (s/arr[n]);
           s = s%arr[n];
         }
         n--;
       }

       out.println("sum ==> " + s + " " + cnt + " " + k);
       if(cnt >= k){
         return true;
       }else{
         return false;
       }
    }
    public static void solve()
    {
      int n = sc.nextInt();
      int k = sc.nextInt()+1;
      int[] arr = new int[n];
      for(int i = 0; i < n; i++){
        arr[i] = (int)Math.pow(10,sc.nextInt());
      }


      // out.println(Arrays.toString(arr));
      long sum = 0L;
      for(int i = 0; i < n; i++){
        // out.println("sum " + sum + " " + arr[i] + " " + k);
        if(k == 0) break;
        if(i == n - 1){
           sum += (long)(k*(long)arr[i]);
        }else{
          int v = arr[i+1] - arr[i];
          v = v/arr[i];
          // out.println("sum " + v + " " + arr[i] + " " + k);
          if(k >= v){
            k -= v;
            sum += v*arr[i];
          }else{
            // out.println("sum " + v + " " + arr[i]);
            sum += k*arr[i];
            k = 0;
          }
        } 
      }

      out.println(sum);

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
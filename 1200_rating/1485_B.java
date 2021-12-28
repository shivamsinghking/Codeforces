import java.io.*;
import java.util.*;

public class Solution
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      solve();
      out.close();
    }

    public static void solve()
    {
      int n = sc.nextInt();
      int q = sc.nextInt();
      int k = sc.nextInt();

      long[] arr = new long[n];

      for(int i = 0; i <n; i++){
        arr[i] = sc.nextLong();
      }

      int[][] query = new int[q][2];
      for(int i = 0; i < q; i++){
        query[i][0]  = sc.nextInt()-1;
        query[i][1] = sc.nextInt()-1;
      }
      long[][] values = new long[n][3];
      // left ..) , middle (..), right ( ..

      /**
       * summing every middle, because a1, a2, a3, a4, a5, a6
       * if l = 2, r = 5
       * then 3, 4 we will use the middle, l, r we will use from values.
       */
      if(arr.length == 1){
        for(int i = 0; i < q; i++){
          out.println(k - 1);
        }
        return;
      }

      for(int i = 0; i < n; i++){
        if(i == 0){
          values[i][0] = Integer.MAX_VALUE;
          values[i][1] = Integer.MAX_VALUE;
          values[i][2] = arr[i+1] - 1 - 1;
        }else if(i == (n - 1)){
          values[i][0] = k - arr[i-1] - 1;
          values[i][1] = Integer.MAX_VALUE;
          values[i][2] = Integer.MAX_VALUE;
        }else{
          values[i][0] =  k - arr[i-1] - 1;
          values[i][1] = arr[i+1] - arr[i-1] - 1 - 1;
          values[i][2] = arr[i+1] - 1 - 1; 
        }
      }

      long[] sum = new long[n];

      Arrays.fill(sum, 0);
      for(int i = 1; i < n - 1; i++){
         if(i == 1){
           sum[i] = values[i][1];
         }else{
           sum[i] = values[i][1] + sum[i-1];
         }
      }

      // for(long[] i: values){
      //   out.println(Arrays.toString(i));
      // }
      for(int i = 0; i < q; i++){
          int l = query[i][0];
          int r = query[i][1];

          if(l == r){
            out.println(k - 1);
            continue;
          }
          // out.println("sum ==> " + sum[r-1] + " " + sum[l] + " ");
          long s = sum[r-1] - sum[l] + values[l][2] + values[r][0];
          out.println(s);
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
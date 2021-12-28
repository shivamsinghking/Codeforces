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
      int[][] arr = new int[n][2];
      
      for(int i = 0; i < n; i++){
          arr[i][0] = sc.nextInt();
          arr[i][1] = i+1;
      }

      // time, index
      Arrays.sort(arr, new Comparator<int[]>(){
        public int compare(int[] a, int[] b){
          return b[0] - a[0];
        }
      });

      List<Long> ll = new ArrayList<>();
      long[] ans = new long[n+1];
      ll.add(0L);
      ans[0] = 0L;

      long total = 0;
      for(int i = 0; i<n; i++){
        int times = arr[i][0];
        int index = arr[i][1];
        if(i == 0){
          ll.add(1L);
          ans[index] = 1L;
          total += 2*1*times;
        }else{
          long last = ll.get(ll.size() - 1);
          if(last > 0){
            //positive, same value but -ve
            ll.add(-1*last);
            ans[index] = -1*last;
            total += 2*last*times;
          }else{
            // neg, -neg + 1, => +ve
            long val = (-1*last)+1; 
            ll.add(val);
            ans[index] = val;
            total += val*2*times;
          }
        }
        // out.println("total " + total);
      }

      out.println(total);
      for(int i = 0; i <= n; i++){
        out.print(ans[i] + " ");
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
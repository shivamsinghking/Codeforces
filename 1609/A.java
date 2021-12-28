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
      int[] arr = new int[n];
     
      List<Integer> odd = new ArrayList<>();
      List<Integer> even = new ArrayList<>();

      long[] power = new long[n];
      int cnt = 0;
      for(int i = 0 ; i < n; i++){
       arr[i] = sc.nextInt();
       if(arr[i]%2 == 0){
         int val = arr[i];
         while(val%2 == 0){
           val = val/2;
           cnt++;
         }
         power[i] = val;
       }else{
         power[i] = arr[i];
       }
      }


      long sum = 0L;
      for(long i: power){
        sum += i;
      }

      // out.println(Arrays.toString(power) + " " + cnt);

      long ans = Integer.MIN_VALUE;
      for(int i =0; i < n; i++){
        if(arr[i]%2 == 0){
          long sum1 = sum - power[i];
          long val = arr[i];
          long left_cnt = (long)Math.pow(2, cnt)/(long)(val/power[i]);
          sum1 += (val*left_cnt);
          // out.println(" ans " + sum1 + " " + left_cnt);
          ans = Math.max(ans, sum1);
        }else{
          long val = arr[i];
          val = val*(long)Math.pow(2, cnt);
          long sum1 = sum - power[i] + val;
          ans = Math.max(ans, sum1);
        }
      }

      out.println(ans);


    
      // Collections.sort(odd);
      // Collections.sort(even);

      // long sum1 = 0L;
      // if(odd.size() > 0){
      //   long val = odd.get(odd.size() - 1);
      //   long sum = 0L;
      //   for(int i: even){
      //     while(i%2 == 0){
      //       i = i/2;
      //       val = val*2;
      //     }
      //     sum += i;
      //   }

      //   for(int i = 0; i < odd.size() - 1; i++){
      //     sum += odd.get(i);
      //   }

      //   sum1 = sum + val;
      //   // out.println(sum + val);
      // }
      

      // long sum2 = 0L;
      // if(even.size() > 0){
      //   long val = even.get(even.size() - 1);
      //   long sum = 0L;
      //   for(int i = 0; i < even.size()-1; i++){
      //     int e = even.get(i);
      //     while(e%2 == 0){
      //       val = val*2;
      //       e = e/2;
      //     }
      //     sum += e;
      //   }

      //   // adding odd
      //   for(int x: odd){
      //     sum += x;
      //   }
      //   // out.println(val + sum);
      //   sum2 = sum + val;
      // }

      // out.println(Math.max(sum1, sum2));
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
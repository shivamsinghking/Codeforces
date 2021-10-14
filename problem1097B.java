import java.io.*;
import java.util.*;

public class Solution
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String... args)
    {
      solve();
      out.close();
    }

    public static boolean dp(int index, int[] arr, int val, HashMap<Integer, HashMap<Integer, Boolean>> map){

      if(index == arr.length && val == 0) return true;
      else if(index == arr.length && val != 0) return false;

      if(map.containsKey(index) && map.get(index).containsKey(val)){
         return map.get(index).get(val);
      }

        int addValue = val + arr[index] >= 360 ? (val  + arr[index] - 360 ) : val + arr[index];
        int subValue = val - arr[index] <= -360 ? (val  - arr[index] + 360 ) : val - arr[index];
        boolean ans = dp(index+1, arr, addValue, map) || dp(index+1, arr, subValue, map);

      if(map.containsKey(index)){
        HashMap<Integer,Boolean> m = map.get(index);
        m.put(val, ans);
      }else{
        HashMap<Integer,Boolean> m = new HashMap<>();
        m.put(val, ans);
        map.put(index, m);
      }
      return ans;
    }
    public static void solve()
    {
       int n = sc.nextInt();
       int[] arr = new int[n];
       for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
       }

       HashMap<Integer, HashMap<Integer, Boolean>> map = new HashMap<>();
       boolean ans = dp(0, arr, 0, map);
       out.println(ans ? "YES" : "NO");
       
       
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
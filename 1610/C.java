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

    static boolean check(int[][] nums, int mid){
      int rich = mid - 1;
      int poor = 0;
      int cnt = 0;
      for(int i = 0; i < nums.length; i++){
        if(rich <= nums[i][0] &&  poor <= nums[i][1]){
          cnt++;
          rich--;
          poor++;
          // since we are starting from 0, and "mid" is the max. people we can invite, then poor are 0 and rich is mid - 1
          // if this is selected then for next people, "poor++" is poor and rich-- for ith people
        }
      }

      if(cnt >= mid) return true;
      return false;
    }

    public static void solve()
    {
       int n = sc.nextInt();
       int[][] arr = new int[n][2];
       for(int i = 0; i < n; i++){
         arr[i][0] = sc.nextInt();
         arr[i][1] = sc.nextInt();
       }

       // HINT: Usually maximum no. of people, distance b/w two, binary search is used, and max no. < n
       // min = 1, and max = n people can be invited to party
       // Suppose, we can invite 4 people, 0 2 5 7, then first is always less than < other 3, then at second, 1 people, and 2 people are richer
       // then this. 
       // If we can invite 4 people then we can see if  we can invite 5 people or not. if yes go for 6 if not 4 is the max. people invite
       // HINT: Aggresive Cows SPOJ

       int cnt = 0;
      
      int l = 0, r = n, ans = 0;
      while(l <= r){
        int mid = (l+r)/2;
          if(check(arr, mid)){
            l = mid+1;
            ans = mid;
          }else r = mid - 1;
      }
       out.println(ans);
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
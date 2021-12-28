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

    static int min;
    static void dp(int index, String s, int s1, int s2){
      if(index >= s.length()){
        min = Math.min(min, s.length());
        return;
      }
      
       // if s2 can win or not
       int cnt1 = 0, cnt2 = 0;
       int val = (int)Math.ceil((s.length() - index)/Double.valueOf(2));
       if(index%2 == 0){
         // s1 
         cnt1 = val;
         cnt2 = val;
       }else{
         cnt2 = val;
         cnt1 = val - 1;
       }
      //  if(index == 8){
      //   out.println(" index ==> " + index + " " + s1 + " " + s2 + " " + cnt1 + " " + cnt2);
      //  }
      if(s1 > s2){
        if(s2 + cnt2 < s1){
          // 1st based indexing, instead of index - 1, we use index
          min = Math.min(index, min);
          return;
        }
      }else if(s2 > s1){
        if(s1+cnt1 < s2){
          min = Math.min(index, min);
          return;
        }
      }

      if(index%2 == 0){
        // 1st team
        if(s.charAt(index) == '1'){
          dp(index+1, s, s1+1, s2);
        }else if(s.charAt(index) == '0'){
          dp(index+1, s, s1, s2);
        }else{
          // ?, either +1, 0
          dp(index+1, s, s1+1, s2);
          dp(index+1, s, s1, s2);
        }
      }else{
        if(s.charAt(index) == '1'){
          dp(index+1, s, s1, s2+1);
        }else if(s.charAt(index) == '0'){
          dp(index+1, s, s1, s2);
        }else{
          // ?, either +1, 0
          dp(index+1, s, s1, s2+1);
          dp(index+1, s, s1, s2);
        }
      }
    }
    public static void solve()
    {
       String s = sc.nextLine();
       min = Integer.MAX_VALUE;
       dp(0, s, 0, 0);
       out.println(min);

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
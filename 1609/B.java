import java.io.*;
import java.util.*;

public class Solution
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int t = 1;
      solve();

      out.close();
    }

    public static void solve()
    {
       int n = sc.nextInt();
       int q = sc.nextInt();

       StringBuffer s = new StringBuffer();
       s.append(sc.nextLine());
       int[] arr = new int[q];
       char[] arr1 = new char[q];

       for(int i = 0; i < q; i++){
         arr[i] = sc.nextInt();
         arr1[i] = sc.next().toCharArray()[0];
       }

      //  TreeSet<Integer> set = new TreeSet<>();

       int len = 2;
       int cnt = 0;
       for(int i = 0; i <= (n - 1 - len); i++){
         String ss = s.substring(i, i + len + 1);
         if(ss.equals("abc")){
          cnt++;
          i = i+len;
         }
       }

      //  out.println(set);
       for(int i = 0; i < q; i++){
          int index = arr[i]-1;
          char c = arr1[i];
          if(s.charAt(index) != c){
            if(index < n - 2 && s.charAt(index) == 'a' && s.charAt(index+1) == 'b' && s.charAt(index+2) == 'c'){
              cnt--;
            }

            if(index > 0 && index < n-1 && s.charAt(index) == 'b' && s.charAt(index-1) == 'a' && s.charAt(index+1) == 'c'){
              cnt--;
            }

            if(index > 1 && s.charAt(index) == 'c' && s.charAt(index-1) == 'b' && s.charAt(index-2) == 'a'){
              cnt--;
            }

            s.replace(index, index+1, String.valueOf(c));


            if(index < n - 2 && s.charAt(index) == 'a' && s.charAt(index+1) == 'b' && s.charAt(index+2) == 'c'){
              cnt++;
            }

            if(index > 0 && index < n-1 && s.charAt(index) == 'b' && s.charAt(index-1) == 'a' && s.charAt(index+1) == 'c'){
              cnt++;
            }

            if(index > 1 && s.charAt(index) == 'c' && s.charAt(index-1) == 'b' && s.charAt(index-2) == 'a'){
              cnt++;
            }

            out.println(cnt);

          }else{
            out.println(cnt);
          }
          // out.println(s + " -- " + set);
       }
      //  out.println(s+a);
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
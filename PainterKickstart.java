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
      for(int i = 1; i <= t; i++){
        int cnt = solve();
        out.println("Case #"+i+":" + " " + cnt);
      }
      out.close();
    }

    public static int solve()
    {
      int n = sc.nextInt();
      String s = sc.nextLine();
      char[][] arr = new char[n][3];

      for(char i[]: arr){
        Arrays.fill(i, 'z');
      }
      HashMap<Character,char[]> map = new HashMap<>();
      map.put('R', new char[]{'R'});
      map.put('B', new char[]{'B'});
      map.put('Y', new char[]{'Y'});
      map.put('U', new char[]{'U'});
      map.put('O', new char[]{'R', 'Y'});
      map.put('P', new char[]{'R', 'B'});
      map.put('G', new char[]{'Y', 'B'});
      map.put('A', new char[]{'Y','B','R'});

      int len = s.length();
      for(int i = 0; i < len; i++){
        char c = s.charAt(i);
        arr[i] = map.get(c);
      }

      // for(char[] i: arr){
      //   out.println(Arrays.toString(i));
      // }

      char [] cc = { 'Y', 'B', 'R'};
      int cnt = 0;
      for(int i = 0; i < 3; i++){
            char c = cc[i];
            int v = 0;
            for(int j = 0; j < arr.length; j++){
               char[] a = arr[j];
               if(check(a, c)){
                   int k = j;
                   v++;
                   for(; k < arr.length; k++){
                     char[] aa = arr[k];
                     if(!check(aa, c)){
                      break;
                     }
                   }
                   j = k - 1;
               }
            }
            cnt += v;
      }
  
      return cnt;
      // 
    }

    static boolean check(char[] arr, char c){
      for(int i = 0; i < arr.length; i++){
        if(arr[i] == c) return true;
      }
      return false;
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
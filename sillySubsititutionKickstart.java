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
        // out.print("ques " + i);
        String cnt = solve();

        out.println("Case #"+i+":" + " " + cnt);
      }
      out.close();
    }

    public static String solve()
    {
     
      int n = sc.nextInt();
      // String s1 = "6346301368767457972125346632367674577812443467434567672312438990301189190209033013235634233410141223";
      // int n = s1.length();
      String s1 = sc.nextLine();

      // out.println("ques" + s1);
      if(s1.length() == 1) return s1;
      // String ans = "";

      String[] arr = {"01", "12", "23", "34", "45", "56", "67", "78", "89", "90"};
      
      // int index = 0;
      while(true){
        String s = s1.substring(0, s1.length());
        for (int j = 0; j < arr.length; j++) {
          String ss = arr[j];
          String newS = "";
          for (int i = 1; i < s.length(); i++) {
            String sub = s.substring(i - 1, i + 1);
            int a = sub.charAt(0) - '0';
            int b = sub.charAt(1) - '0';
            if (sub.equals(ss)) {
              if (a == 8 && b == 9) {
                newS += '0';
              } else if (a == 9 && b == 0) {
                newS += '1';
              } else {
                newS += String.valueOf(b + 1);
              }
              i++;
              if (i == s.length() - 1) {
                newS += s.charAt(i);
              }
            } else {
              if (i == s.length() - 1) {
                newS += sub;
              } else {
                newS += String.valueOf(a);
              }

            }
          }
          // out.println(newS + " == " + s);
          // if(!s.equals(newS)){
          //   j = -1;
          // }
          s = newS;
          if(s.length() == 1) return s;
        }
       
        if(s.equals(s1)){
          return s;
        }else{
          s1 = s;
        }
      }

      // 
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
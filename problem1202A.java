import java.io.*;
import java.util.*;

public class Main
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String... args)
    {
      int t = 1;
      t = sc.nextInt();
      while (t-- > 0)
      {
          solve();
      }
      out.close();
    }

    static int getValueFromBinary(String s){
      int val = 0;
      int c = 1;
      for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i) == '1'){
              val += c;
            }
            c *= 2;
      }
      return val;
    }
    
    static String getBinaryFromIntInReverse(int val){
      StringBuffer s = new StringBuffer("");
      while(val > 0){
          s.append(String.valueOf(val & 1));
          val = val >> 1;
      }
      return s.toString();
    }
    public static void solve()
    {
       String x = sc.nextLine();
       String y = sc.nextLine();
        //  x + y*2^k
        //  11110 , 100110101
        int posx = 0;
        int posy = 0;

        if(x.length() > y.length()){
          while(x.length() > y.length()){
            y = "0" + y;
          }
        }else{
          while(y.length() > x.length()){
            x = "0" + x;
          }
        }
        // out.println(x + " " + y);
        int xx = getValueFromBinary(x);
        int yy = getValueFromBinary(y);
        // out.println(xx + " " + yy);
        for(int i = x.length()-1; i >= 0; i--){
          if(x.charAt(i) == '1'){
            posx = i;
            break;
          }
        }

        for(int i = y.length()-1; i >= 0; i--){
          if(y.charAt(i) == '1'){
            posy = i;
            break;
          }
        }

        // out.println(posx + " " + posy);
        if(posy < posx){
          // we have to find the next posx in x i.e posy > posx
           for(int i = posx-1; i >= 0; i--){
               if(x.charAt(i) == '1' && i <= posy){
                 posx = i;
                 break;
               }
           }
        }
        // out.println(posx + " - " + posy);
        int diff = posy - posx;
        // out.println("Ans ===> ");
        out.println(diff);
        return ;
    }
// 1010101010101
// 0000011110000
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
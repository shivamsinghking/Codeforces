import java.io.*;
import java.util.*;

public class Main
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
      String[][] s = new String[4][n];
      for(int i = 0; i < 4; i++){
          for(int j = 0; j < n; j++){
            s[i][j] = sc.nextLine();
          }
          if(i != 3){
            sc.nextLine();
          }
      }

      // out.println(" re ");
      // for(String[] i: s){
      //   out.println(Arrays.toString(i));
      // }
      // find x, y
      // if it start from top-right 1
      
      // boolean turn = true;
      // // true means 1, false means 0
      // long x = 0L;
      // for(int i = 0; i < n; i++){
      //     int val = 1;
      //     for(int j = 0; j < n; i++){
      //       if(turn){
      //            x += val;
      //       }
      //       turn = !turn;
      //       val = val << 1;
      //     }
      // }

      // turn = false;
      // long y = 0L;
      // for (int i = 0; i < n; i++) {
      //   int val = 1;
      //   for (int j = 0; j < n; i++) {
      //     if (turn) {
      //       y += val;
      //     }
      //     turn = !turn;
      //     val = val << 1;
      //   }
      // }

      int[] values = {3, 5, 6, 9, 10, 12};

      int min = Integer.MAX_VALUE;

      // O(4*6*n^2)
      for(int i : values){
        // 3 => 0011
        int cnt = 0;
        for(int j = 0; j < 4; j++){
           boolean t = false;
           if(((i >> j)&1) == 1){
             t = true;
           }

          //  out.println("turn " + t + " " + n+ " " + Arrays.toString(s[j]));
           // true means 1
           for(int k = 0; k < n; k++){
             char[] ss = s[j][k].toCharArray();
            //  out.println("char1 " + Arrays.toString(ss));
             for(int l = ss.length - 1; l >= 0; l--){
              //  out.println("char " + ss[l]);
               if(t && ss[l] == '0'){
                 cnt++;
               }else if(!t && ss[l] == '1'){
                 cnt++;
               }
               t = !t;
             }
           }
        }
        // out.println(" cnt " + cnt + " " + i);
        min = Math.min(cnt, min);
      }

      out.println(min);
    }

    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    
    public static void reverse(int[] arr) {
    	Arrays.sort(arr);
    	int n = arr.length;
    	for(int i = 0 ; i < arr.length; i++) {
    		int temp = arr[i];
    		arr[i] = arr[n - 1 - i];
    		arr[n - 1 - i] = temp;
    	}
    	return ;
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
import java.io.*;
import java.util.*;

public class Main
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

    public static int getNumber(char c){
      return c - '0';
    }
    public static void solve()
    {   
       long a = sc.nextLong();
       long s = sc.nextLong();

       String aa = String.valueOf(a);
       String ss = String.valueOf(s);

      //  int j = ss.length() - 1;
       List<Integer> b = new ArrayList<>();

       if(ss.length() < aa.length()){
         out.println(-1);
         return;
       }

       int j = aa.length() - 1;
       int i = ss.length() - 1;
       for(; i >= 0; i--){
         if(j < 0){
           break;
         }
         if(ss.charAt(i) - '0' >= aa.charAt(j) - '0'){
          int v = getNumber(ss.charAt(i)) - getNumber(aa.charAt(j));
          if(v > 9 || v < 0){
            // out.println(" jj -- " + i + " " + j);
            out.println(-1);
            return;
          }
          b.add(v);
         }else{
          i--;
          if(i >= 0 && i+2 < ss.length()+1){
            int v = Integer.parseInt(ss.substring(i, i+2));
            int bb = v - getNumber(aa.charAt(j));
            if(bb > 9 || bb < 0){
              // out.println(" jj 1- " + i + " " + j);
                out.println(-1);
                return;
            }
            b.add(bb);
          }else{
            // out.println(" jj 11-- " + i + " " + j);
            out.println(-1);
            return;
          }
         }

         j--;
       }

       if(j >= 0){
         out.println(-1);
         return;
       }
      //  for(int i = aa.length() - 1; i >= 0; i--){
      //     int v = aa.charAt(i) - '0';
      //     // out.println(" jj " + j + " " + i);
      //     if(j >= 0){
      //       int bb = ss.charAt(j) - '0';
      //       if(bb >= v){
      //         if(bb - v > 9 || ( bb - v ) < 0){
      //           out.println(-1);
      //           return;
      //         }
      //         b.add(bb - v);
      //         j--;
      //       }else{
      //         if(j-1 >= 0){
      //           int u = Integer.parseInt(ss.substring(j-1, j+1));
      //           if(u - v > 9 || (u - v) < 0){
      //             out.println(-1);
      //             return;
      //           }
      //           b.add(u - v);
      //           j = j - 2;
      //         }else{
      //           // out.println(" jj-- " + j + " " + i);
      //           out.println(-1);
      //           return;
      //         }
      //       }
      //     }else{
      //       out.println(-1);
      //       return;
      //     }
      //  }

       while(i >= 0){
         b.add(ss.charAt(i) - '0');
         i--;
       }

       String ans = "";
       for(int k = b.size() - 1; k >= 0; k--){
         ans += b.get(k);
       }
       
       out.println(Long.parseLong(ans));
       return;
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
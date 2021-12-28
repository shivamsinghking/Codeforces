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

    static long getSum(int n){
      return (long)(n)*((long)(n+1))/2;
    }
    public static void solve()
    {
      int n = sc.nextInt();
      if(n%2 == 0){
        int nn = n / 2;
        List<Integer> ll = new ArrayList<>();
        if(nn%2 == 0){
          // ans is 0;
          out.println(0);
          out.print(nn + " ");
          int i = 1;
          int j = n;
          boolean flag = true;
          while(i < j){
            if(flag){
              ll.add(i);
              ll.add(j);
            }
            i++;
            j--;
            flag = !flag;
          }
        }else{
          out.println(1);
          out.print(nn+ " ");
          int i = 1;
          int j = n;
          boolean flag = true;
          while(j - i != 1){
              if(flag){
                ll.add(i);
                ll.add(j);
              }
              i++;
              j--;
              flag = !flag;
          }

          ll.add(i);
        }

        for(int i : ll){
          out.print(i + " ");
        }
      }else{
        // n is odd
        if(n == 3){
          out.println(0);
          out.println(2 + " " + 1 + " " + 2);
        }else{

          int nn = (n - 1)/2;
          List<Integer> ll = new ArrayList<>();
          if(nn%2 == 0){
            int i = 2;
            int j = n;
            boolean flag = true;
            while(i < j){
              if(flag){
                ll.add(i);
                ll.add(j);
              }
              flag = !flag;
              i++;
              j--;
            }
            out.println(1);
            ll.add(1);
          }else{
            int i = 2;
            int j = n;
            boolean flag = true;
            while(j - i != 1){
                if(flag){
                  ll.add(i);
                  ll.add(j);
                }
                i++;
                j--;
                flag = !flag;
            }
            ll.add(i);
            ll.add(1);
            out.println(0);
          }
        
          out.print(ll.size() + " ");
          for(int k: ll){
             out.print(k+ " ");
          }
        }
      }
      out.println();   
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
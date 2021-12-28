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

    static boolean bs(int a, int[][] arr, int n, int m){
      
      int cnt = 0;
      int min_travel = Math.min(n-1, m);
      HashSet<Integer> shop = new HashSet<>();

      for(int j = 0; j < n; j++){
        boolean flag = true;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < m; i++){
          if(arr[i][j]>= a){
            set.add(i);
          }
        }

        if(set.isEmpty()) return false;

        // check if there is any shop common from previous
        for(int x: set){
          if(shop.contains(x)){
            flag = false;
            break;
          }
        }

        if(flag){
          // no common shop available till now
          shop.addAll(set);
          cnt++;
          // return false;
        }
      }

      if(cnt <= min_travel) return true;
      return false;
    }
    public static void solve()
    {
      int m = sc.nextInt();
      int n = sc.nextInt();
      
      int r = Integer.MIN_VALUE;
      int l = Integer.MAX_VALUE;
      int[][] arr = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          arr[i][j] = sc.nextInt();
          r = Math.max(r, arr[i][j]);
          l = Math.min(l, arr[i][j]);
        }
      }

 
      // out.println(" l " + l  + " " + r);
      int ans = 0;
      while(l <= r){
        int mid = (l+r)/2;
        // out.println(" mid " + mid);
        if(bs(mid, arr, n, m)){
          ans = mid;
          l = mid+1;
        }else{
          r = mid-1;
        }
      }
      out.println(ans);
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
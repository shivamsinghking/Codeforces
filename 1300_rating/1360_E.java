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

    static boolean isValid(int i, int j, int r, int c){
      if(i < 0 || j < 0 || i >= r || j >= c) return false;
      return true;
    }

    static int[][] dp;
    static boolean dfs(int i, int j, int n, int[][] arr){
      if(!isValid(i, j, n, n)) return false;
      if(arr[i][j] == 0) return false;

      if((i == n - 1 || j == n - 1) && arr[i][j] == 1) return true;

      if(dp[i][j] != 0){
        if(dp[i][j] == 1){
          return true;
        }else{
          return false;
        }
      }
      // right, down
      // r
      boolean a = dfs(i, j+1, n, arr);
      boolean b = dfs(i+1, j, n, arr);
      boolean ans = a || b;
      if(ans){
        dp[i][j] = 1;
      }else{
        dp[i][j] = -1;
      }
      return ans;
    }
    public static void solve()
    {   
      int n = sc.nextInt();
      int[][] arr = new int[n][n];

      for(int i = 0; i < n; i++){
        String[] s = sc.nextLine().split("");
        for(int j = 0; j < n; j++){
          arr[i][j] = Integer.parseInt(s[j]);
        }
      }

      dp = new int[n+1][n+1];
      for(int[] i: dp){
        Arrays.fill(i, 0);
      }

      boolean flag = true;
      for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          if(arr[i][j] == 1){
            boolean ans = dfs(i, j, n, arr);
            if(!ans){
              flag = false;
              break;
            }
          }
        }
      }

      if(!flag){
        out.println("NO");
      }else{
        out.println("YES");
      }


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
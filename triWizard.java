import java.io.*;
import java.util.*;

public class Solution
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int n = sc.nextInt();
      int p = sc.nextInt();
      int[][] arr = new int[p][2];
      for(int i = 0; i < p; i++){
        arr[i][0] = sc.nextInt();
        arr[i][1] = sc.nextInt();
      }

      int[] damage = new int[n];
      for(int i = 0; i < n; i++){
        damage[i] = sc.nextInt();
      }

      solve(n, p, arr, damage);
      out.close();
    }

    public static int min = Integer.MAX_VALUE;
    public static void dp(int index, int[][] arr1, int[] d, int n, boolean[] visited, int val){
      if(index > n-1) return ;
      if(index == n-1){
        min = Math.min(val, min);
      };

      visited[index] = true;
      int ans = Integer.MAX_VALUE;
      for(int i = 0; i < n; i++){
        int next = arr1[index][i];
        if(next > 0 && !visited[i]){
          dp(i, arr1, d, n, visited, val + d[i]);
        }
      }
  
      visited[index] = false;
      return;
    }
    public static void solve(int n, int p, int[][] arr, int[] d)
    {
      int[][] arr1 = new int[n+1][n+1];
      // for(int[] i: arr1){
      //   Arrays.fill(arr1, -1);
      // }
      for(int i = 0; i < p; i++){
        int s = arr[i][0];
        int e = arr[i][1];
        arr1[s][e] = 1;
      }

      boolean[] visited = new boolean[n];
      dp(0, arr1, d, n, visited, 0);
      out.println(min == Integer.MAX_VALUE ? -1 : min);
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
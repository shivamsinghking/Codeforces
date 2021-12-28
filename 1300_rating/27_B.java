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

    static void dfs(int index, int winner, int n, int[][] gr, List<Integer> ll, boolean[][] visited){
      if(index > n) return;
        for(int i = 1; i <= n; i++){
          if( index != i && gr[index][i] != 0 && gr[index][i] == index && !visited[index][i]){
            // index is winner
            ll.add(i);
            visited[index][i] = true;
            dfs(i, i, n, gr, ll, visited);
          }
        }
        return;
    }

    public static void solve()
    {   
      int n = sc.nextInt();
      int nn = (n*(n-1))/2;
      nn--;
      // out.println(" nn " + nn);
      int[][] arr = new int[nn][2];
      for(int i = 0; i < nn; i++){
        arr[i][0] = sc.nextInt();
        arr[i][1] = sc.nextInt();
      }

      int[][] gr = new int[n+1][n+1];
      for(int i = 0; i < nn; i++){
        int x = arr[i][0];
        int y = arr[i][1];
        gr[x][y] = x;
        gr[y][x] = x;
      }

      int[] aa = new int[2];
      for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
          if(gr[i][j] == 0 && i != j){
             aa[0] = i;
             aa[1] = j;
          }
        }
      }


      boolean flag = false;
      for(int i = 0; i < 2; i++){
        List<Integer> ll = new ArrayList<Integer>();
        // out.println(ll + " " + aa[i]);
        boolean[][] visited = new boolean[n+1][n+1];
        dfs(aa[i], aa[i], n, gr, ll, visited);
        
        // out.println(ll);
        if(i == 0){
          if(ll.contains(aa[i+1])){
            out.println(aa[i]  + " " + aa[i+1]);
            flag = true;
          }
        }else{
          if(ll.contains(aa[i-1])){
            out.println(aa[i] + " " + aa[i-1]);
            flag = true;
          }
        }
      }

      if(!flag){
        Arrays.sort(aa);
        out.println(aa[0] + " " + aa[1]);
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
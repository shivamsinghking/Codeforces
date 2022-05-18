import java.io.*;
import java.util.*;

public class Main {
    static PrintWriter out;
    static Kioken sc;

    public static void main(String[] args) throws FileNotFoundException {
        boolean t = true;
        boolean f = false;
        if (f) {
            out = new PrintWriter("output.txt");
            sc = new Kioken("input.txt");
        } else {
            out = new PrintWriter((System.out));
            sc = new Kioken();
        }

        solve();
        out.flush();
        out.close();
    }

    static int cnt;
    static void dfs(int[][] arr, int u, boolean[][] visited, boolean[] found, int val){
      if(found[u]) return;

      // out.println(" cnt " + cnt);
      found[u] = true;
      for(int i = 1; i < arr.length; i++){
        if(i != u && arr[u][i] == 1 && i != val && visited[u][i] == false){
          visited[u][i] = true;
          visited[i][u] = true;
          cnt++;
          // out.println(" ==> "  + cnt + " " + u + " " + i + "  "  + val);
          dfs(arr, i, visited, found, u);
        }
      }
    }
    public static void solve() {
       int n = sc.nextInt();
       int m = sc.nextInt();
       int[][] arr = new int[n+1][n+1];

       for(int i = 0; i < m; i++){
         int u = sc.nextInt();
         int v = sc.nextInt();
         arr[u][v] = 1;
         arr[v][u] = 1;
       }

       int q = sc.nextInt();
       int[][] query = new int[q][2];

       for(int i = 0; i < q; i++){
         query[i][0] = sc.nextInt();
         query[i][1] = sc.nextInt();
       }

       for(int i = 0; i < q; i++){
         int u = query[i][0];
         int v = query[i][1];
         cnt = 0;
         boolean[][] visited = new boolean[n+1][n+1];
         boolean[] found = new boolean[n+1];
         arr[u][v] = 1;
         arr[v][u] = 1;
         dfs(arr, u, visited, found, 0);
         out.println(cnt);
         arr[u][v] = 0;
         arr[v][u] = 0;
       }
    }

    public static long gcd(long a,long b)
    {  while(b!=0)
        {long rem=a%b;
         a=b;
         b=rem;
        }
        return a;
    }
    
    public static long leftShift(long a) {
        return (long) Math.pow(2, a);
    }

    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < arr.length/2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        return;
    }

    public static int lower_bound(ArrayList<Integer> ar, int k) {
        int s = 0, e = ar.size();
        while (s != e) {
            int mid = s + e >> 1;
            if (ar.get(mid) <= k) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return Math.abs(s) - 1;
    }

    public static int upper_bound(ArrayList<Integer> ar, int k) {
        int s = 0;
        int e = ar.size();
        while (s != e) {
            int mid = s + e >> 1;
            if (ar.get(mid) < k) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        if (s == ar.size()) {
            return -1;
        }
        return s;
    }

    static class Kioken {
        // FileInputStream br = new FileInputStream("input.txt");

        BufferedReader br;
        StringTokenizer st;

        Kioken(String filename) {
            try {
                FileReader fr = new FileReader(filename);
                br = new BufferedReader(fr);
                st = new StringTokenizer("");

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        Kioken() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                st = new StringTokenizer("");

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext() {
            String next = null;
            try {
                next = br.readLine();
            } catch (Exception e) {
            }
            if (next == null || next.length() == 0) {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }
    }
}
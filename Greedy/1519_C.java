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

        int tt = 1;
        tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static boolean isValid(int i, int j, int n, int m){
      if(i < 0 || j  < 0 || i >= n || j >= m){
        return false;
      }
      return true;
    }

    static int findMaxD(char[][] arr, int i, int j, int n, int m, int dd){
      // out.println(" i + j "   + i + " "  + j + " "  + " " + isValid(i, j, n, m));
      if(!isValid(i, j, n, m)){
        return 0;
      }
      if(arr[i][j] == '.'){
        return 0;
      }

      // go diagonally, dd => -1, 1
      // out.println(" i + j "   + i + " "  + j);
      return 1 + findMaxD(arr, i-1, j+dd, n, m, dd);
    }

    static void fill(boolean[][] visited, int i, int j, int d, int n, int m, int dd){
        if(d <= 0) return ;
        if(!isValid(i, j, n, m)){
          return;
        }

        visited[i][j] = true;
        fill(visited, i-1, j+dd, d-1, n, m, dd);
    }

    public static void solve() {
       int n = sc.nextInt();
       int m = sc.nextInt();
       int k = sc.nextInt();

       char[][] arr = new char[n][m];
       for(int i = 0; i < n; i++){
         char[] s = sc.nextLine().toCharArray();
         for(int j = 0; j < m; j++){
           arr[i][j] = s[j];
         }
       }

      boolean[][] visited = new boolean[n][m];

       for(int i = n - 1; i >= 0; i--){
         for(int j = 0; j < m; j++){
           if(arr[i][j] == '*'){
             // this can be a center
             int minD  = Math.min(findMaxD(arr, i, j, n, m, 1), findMaxD(arr, i, j, n, m, -1)) - 1;
            //  out.println(" min " + minD + " " + i + " " + j);
             if(minD > 0 && minD >= k){
               fill(visited, i, j, minD+1, n, m, 1);
               fill(visited, i, j, minD+1, n, m, -1);
             }
           }
         }
       }

       for(int i = 0; i < n; i++){
         for(int j = 0; j < m; j++){
           if(arr[i][j] == '*' && !visited[i][j]){
             out.println("NO");
             return;
           }
         }
       }
       out.println("YES");
       return;
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
        for (int i = 0; i < arr.length; i++) {
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
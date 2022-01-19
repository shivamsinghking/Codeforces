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

    static int[][] mat;
    static int[][] ans;
    static void find(int l, int r){

        if(l >= r){
            ans[l][r] = l;
            return;
        }
        //  finding all possible breakdown
        for(int i = l; i <= r; i++){
            if(i == l){
              int l1 = i+1;
              int r1 = r;
              if(mat[l1][r1] == 1){
                ans[l][r] = l;
                find(l1, r1);
                return;
              }
            }else if(i == r){
                int l1 = l;
                int r1 = r-1;
                if(mat[l1][r1] == 1){
                    ans[l][r] = r;
                    find(l1, r1);
                    return;
                }
            }else{
                int l1 = l;
                int r1 = i - 1;
                int l2 = i+1;
                int r2 = r;
                if(mat[l1][r1] == 1 && mat[l2][r2] == 1){
                    ans[l][r] = i;
                    find(l1, r1);
                    find(l2, r2);
                    return;
                }
            }
        }
    }
    public static void solve() {
       int n = sc.nextInt();
       mat = new int[1001][1001];
       ans = new int[1001][1001];
       List<int[]> ll = new ArrayList<>();
       int[][] arr = new int[n][2];
       for(int i = 0; i < n; i++){
           int l = sc.nextInt();
           int r = sc.nextInt();
           arr[i][0] = l;
           arr[i][1] = r;
           mat[l][r] = 1;
       }

       find(1, n);


       for(int i = 0; i < n; i++){
           int l = arr[i][0];
           int r = arr[i][1];
           out.println(l + " " + r + " " + ans[l][r]);
       }

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
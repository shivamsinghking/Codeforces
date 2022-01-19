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

    // uu, ll, ul, lu
    static boolean flag;
    static int M = 1_000_000_007;
    static int find(int[] arr, boolean turn){
        if(arr[2] == 0 && arr[3] == 0){
            return 0;
        }

        if(turn){
            // uu,
            if(arr[0] > 0){
                int t1 = arr[3];
                int t2 = arr[0];
                arr[0] = 1 + t1;
                arr[3] = t2 - 1;
                int temp = arr[1];
                arr[1] = arr[2];
                arr[2] = temp;
                return 1 + find(arr, !turn);
            }else{
                // flag = true;
                return M;
            }
        }else{
            // ul
            if(arr[2] > 0){
                int t1 = arr[2];
                int t2 = arr[1];
                arr[2] = 1 + t2;
                arr[1] = t1 - 1;
                int temp = arr[0];
                arr[0] = arr[3];
                arr[3] = temp;
                return 1 + find(arr, !turn);
            }else{
                // flag = true;
                return M;
            }
        }
    }
    public static void solve() {
       int n = sc.nextInt();
       String a = sc.nextLine();
       String b = sc.nextLine();

       flag = false;
       // uu, ll, ul, lu
       int[] arr = new int[4];
       for(int i = 0; i < n; i++){
           if(a.charAt(i) == '1' && b.charAt(i) == '1'){
               arr[0]++;
           }else if(a.charAt(i) == '0' && b.charAt(i) == '0'){
               arr[1]++;
           }else if(a.charAt(i) == '1' && b.charAt(i) == '0'){
               arr[2]++;
           }else{
               arr[3]++;
           }
       }

       int[] arr1 = Arrays.copyOf(arr, 4);
       int ans = Math.min(find(arr, true), find(arr1, false));
       if(ans >= M){
           out.println(-1);
       }else{
           out.println(ans);
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
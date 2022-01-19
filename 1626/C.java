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

    static boolean checkOverlap(int s1, int e1, int s2, int e2){
        if(s2 >= s1 && s2 <= e1){
            return true;
        }

        if(s1 >= s2 && s1 <= e2){
            return true;
        }

        return false;
    }
    public static void solve() {
       
        int n = sc.nextInt();
        int[] kk = new int[n];
        int[] hh = new int[n];
        for(int i = 0; i < n; i++){
            kk[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            hh[i] = sc.nextInt();
        }

        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++){
            int e = kk[i];
            int s = kk[i] - (hh[i] - 1);
            arr[i][0] = s;
            arr[i][1] = e;
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int start = arr[0][0], end = arr[0][1];
        List<int[]> ll = new ArrayList<>();

        for(int i = 1; i < n; i++){
            boolean isOverlap = checkOverlap(start, end, arr[i][0], arr[i][1]);
            // out.println(Arrays.toString(arr[i]) + " ===  "  + Arrays.toString(arr[i-1]) + " "  + isOverlap + " " + start + " " + end);
            if(isOverlap){
                start = Math.min(start, Math.min(arr[i-1][0], arr[i][0]));
                end = Math.max(end, Math.max(arr[i-1][1], arr[i][1]));
            }else{
               ll.add(new int[]{start, end});
               start = arr[i][0];
               end =  arr[i][1];
            }
        }
        ll.add(new int[] {start, end});

        long sum = 0L;
        for(int i = 0; i < ll.size(); i++){
            int[] ans = ll.get(i);
            int nn = ans[1] - ans[0] + 1;
            long val = (long)(nn)*((long)(nn+1))/2; 
            sum += val;
        }
        out.println(sum);
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
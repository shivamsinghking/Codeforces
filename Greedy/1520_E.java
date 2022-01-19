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

    // static int getLeft(int prevIndex, String s, int n){

    // }
    public static void solve() {
       int n = sc.nextInt();
       String s = sc.nextLine();

       long[] prefix = new long[n];
       long[] suffix = new long[n];
       long cnt = 0L;
       for(int i = 0; i < n; i++){
         if(i == 0){
           if(s.charAt(i) == '*'){
             cnt++;
           }
         }else{
           if(s.charAt(i) == '.'){
             prefix[i] = prefix[i-1] + cnt;
           }else{
             prefix[i] = prefix[i-1];
             cnt++;
           }
         }
       }
       cnt = 0L;
       for(int i = n -1; i >= 0; i--){
        if(i == n-1){
          if(s.charAt(i) == '*'){
            cnt++;
          }
        }else{
          if(s.charAt(i) == '.'){
            suffix[i] = suffix[i+1] + cnt;
          }else{
            suffix[i] = suffix[i+1];
            cnt++;
          }
        }
       }

      //  out.println();
       long ans = Long.MAX_VALUE;
      //  out.println(Arrays.toString(prefix) + " " + Arrays.toString(suffix));
       for(int i = 0; i < n; i++){
         if(i == 0){
           ans = Math.min(ans, (suffix[i]));
         }else if(i == n - 1){
           ans = Math.min(ans, prefix[i]);
         }else{
           ans = Math.min(ans, (prefix[i] + suffix[i+1]));
         }
       }
       out.println(ans);
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
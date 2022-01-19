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

    public static void solve() {
       String x = sc.nextLine();

       boolean isTwo = false;
       for(int i = 1; i < x.length(); i++){
           int a = x.charAt(i-1) - '0';
           int b = x.charAt(i) - '0';
           if(a+b > 9){
               isTwo = true;
               break;
           }
       }

       int n = x.length();
    //    out.println(" t " +isTwo);
       if(isTwo){
           for(int i = n - 2; i >= 0; i--){
               int a = x.charAt(i) - '0';
               int b = x.charAt(i+1) - '0';
               if(a+b > 9){
                  String ans = "";
                  if(i > 0){
                      ans += x.substring(0, i);
                  }

                  ans += String.valueOf(a+b);

                  if(i+2 < n){
                      ans += x.substring(i+2, n);
                  }
                  out.println(ans);
                  return;
               }
           }
       }else{
           for(int i = 1; i < n; i++){
            int a = x.charAt(i) - '0';
            int b = x.charAt(i-1) - '0';
            if(a+b <= 9){
                String ans = "";
                if(i-1 > 0){
                    ans += x.substring(0, i-1);
                }

                ans += String.valueOf(a+b);

                // out.println("--- " + ans);
                if(i+1 < n){
                    ans += x.substring(i+1, n);
                }
                out.println(ans);
                return;
            }
           }
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
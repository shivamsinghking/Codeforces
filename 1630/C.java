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
       int n = sc.nextInt();
       int k = sc.nextInt();

       HashSet<Integer> set = new HashSet<>();
       for(int i = 0; i < n; i++){
          set.add(i);
       }

       if(k == n - 1){
         if(n == 4){
           out.println(-1);
           return;
         }else{
           out.println((n - 2) + " " + (n - 1));
           out.println(1 + " " + 3);
           set.remove(n-2);
           set.remove(n-1);
           set.remove(1);
           set.remove(3);
         }
       }

 

       if(k < n - 1){
        out.println(k + " " + (n - 1));
        set.remove(k);
        set.remove(n - 1);
       }
      

      
       for(int i = (k == n - 1) ? 2 : 1; i < n - 1 && set.size() > 2; i++){
          if(k == i){
            continue;
          }else{
            String ss = Integer.toBinaryString(i);
            int rev = revert(i, Integer.toBinaryString(n - 1).length());
            if(set.contains(rev) && set.contains(i)){
              out.println(i + " " + rev);
              set.remove(rev);
              set.remove(i);
            }
          }
       }

       // here len is 2
       
       for(int i: set){
         out.print(i + " ");
       }

       out.println();

      //  out.println(" -- " + revert(5));
    }

    static int revert(int val, int len){
      int vv = 1;
      int ans = 0;
      while(len-- > 0){
        if((val&1) == 0){
          ans += vv;
        }
        vv = vv*2;
        val = val >> 1;
      }
      return ans;
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
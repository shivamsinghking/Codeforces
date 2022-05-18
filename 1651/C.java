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
      int[] arr = sc.readArrayInt(n);
      int[] brr = sc.readArrayInt(n);


      /**
       * X
       * | | 
       * L is fixed, both right is free
       * R is fixed, both left is free
       * tl-br, other both are free
       * tr-bl, other both are free
       */

      //  out.println(Arrays.toString(arr) + Arrays.toString(brr));
       long ans = Integer.MAX_VALUE;
       ans = abs(arr[0], brr[0]) + abs(arr[n - 1], brr[n - 1]);
      //  out.println(" ans 1 " + ans);
       ans = Math.min(ans, abs(arr[0], brr[n - 1]) + abs(arr[n - 1], brr[0]));
      //  out.println(" ans 1 " + ans);

       ans = Math.min(ans, abs(arr[0], brr[0]) + minDiff(arr, brr[n-1]) + minDiff(brr, arr[n-1]));
      //  out.println(" ans 1 " + ans + " " + minDiff(arr, brr[n-1]) +  " "  + minDiff(brr, arr[n-1]));

       ans = Math.min(ans, abs(arr[n-1], brr[n - 1]) + minDiff(arr, brr[0]) + minDiff(brr, arr[0]));
      //  out.println(" ans 1 " + ans);

       ans = Math.min(ans, abs(arr[0], brr[n-1]) + minDiff(arr, brr[0]) + minDiff(brr, arr[n-1]));
      //  out.println(" ans 1 " + ans);

       ans = Math.min(ans, abs(arr[n-1], brr[0]) + minDiff(arr, brr[n - 1]) + minDiff(brr, arr[0]));
      //  out.println(" ans 1 " + ans);

       ans = Math.min(ans, abs(arr[n-1], brr[0]) + minDiff(arr, brr[n - 1]) + minDiff(brr, arr[0]));
      //  out.println(" ans " + ans);
       ans = Math.min(ans, minDiff(arr, brr[0]) + minDiff(arr, brr[n-1]) + minDiff(brr, arr[0]) + minDiff(brr, arr[n-1]));
       out.println(ans);
    }

    static long minDiff(int[] arr, int x){
      long ans = abs(arr[0],x);
      for(int i : arr) ans = Math.min(ans, abs(i, x));
      return ans;
    }

    static long abs(int a, int b){
      return Math.abs(a - b);
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    static long MOD = 1000000007;
    static void reverseSort(int[] arr){List<Integer> list = new ArrayList<>();for (int i=0; i<arr.length; i++){list.add(arr[i]);}Collections.sort(list, Collections.reverseOrder());for (int i = 0; i < arr.length; i++){arr[i] = list.get(i);}}
    static void sort(int[] a) {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}
    static void sort(long[] a){
        ArrayList<Long> l=new ArrayList<>();
		for (long i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
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

        public int[] readArrayInt(int n){
           int[] arr = new int[n];
           for(int i = 0; i < n; i++){
             arr[i] = nextInt();
           }
           return arr;
        }
        public long[] readArrayLong(int n){
          long[] arr = new long[n];
          for(int i = 0; i < n; i++){
            arr[i] = nextLong();
          }
          return arr;
        }
    }
}
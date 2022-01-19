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

    static int[] fill(int k){
      int[] arr = new int[k];
      for(int i = 0; i < k; i++){
        arr[i] = sc.nextInt();
      }
      return arr;
    }

    
    static long getMax(int[] v1, int height){
      if(v1.length <= 1){
        return Integer.MIN_VALUE;
      }
      long max =  (long)(height)*(v1[v1.length - 1] - v1[0]);
      return max;
    }
    public static void solve() {
       int w = sc.nextInt();
       int h = sc.nextInt();
       List<int[]> ll = new ArrayList<>();
       for(int i = 0; i < 4; i++){
            int k = sc.nextInt();
             ll.add(fill(k));
       }

       long max = Integer.MIN_VALUE;
       // 4 CASES
       int[] u1 = ll.get(0); // horizontal
       int[] v1 = ll.get(1);
       Arrays.sort(v1);
       Arrays.sort(u1);
      
       max = Math.max(max, getMax(v1, h));
       max = Math.max(max, getMax(u1, h));
       
       u1 = ll.get(2);
       v1 = ll.get(3);
       Arrays.sort(u1);
       Arrays.sort(v1);
       max  = Math.max(max, getMax(v1, w));
       max = Math.max(max, getMax(u1, w));
       out.println(max);
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
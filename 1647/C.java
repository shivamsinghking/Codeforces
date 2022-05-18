import java.io.*;
import java.util.*;

public class Main {
    static PrintWriter out;
    static Kioken sc;
    static boolean checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null;

    public static void main(String[] args) throws FileNotFoundException {
        boolean t = true;
        boolean f = false;
        if (checkOnlineJudge) {
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
      int m = sc.nextInt();

      int[][] arr = new int[n][m];
      for(int i = 0; i < n; i++){
        String s = sc.nextLine();
        for(int j = 0; j < m; j++){
            arr[i][j] = s.charAt(j) - '0';
        }
      }

      ArrayDeque<int[]> q = new ArrayDeque<>();

      for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
          if(arr[i][j] == 1){
            // if top, left is white
            if(i - 1 >= 0 && arr[i-1][j] == 0){
              arr[i][j] = 0;
              q.add(new int[]{i-1, j, i, j});
            }else if(j-1 >= 0 && arr[i][j-1] == 0){
              arr[i][j] = 0;
              q.add(new int[]{i, j-1, i, j});
            }
          }
        }
      }

      for(int[] i: arr){
        out.println(Arrays.toString(i));
      }

      for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
          if(arr[i][j] == 1){
            out.println(-1);
            return;
          }
        }
      }

      out.println(q.size());

      while(q.size() > 0){
        int[] v = q.pop();
        out.println((v[0] + 1) + " " +  (v[1] + 1) + " " + (v[2] + 1) + " "  + (v[3] + 1));
        arr[v[2]][v[3]] = 1;
      }

      for(int[] i: arr){
          out.println(Arrays.toString(i));
      }
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
    }
}
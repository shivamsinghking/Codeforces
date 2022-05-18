import java.io.*;
import java.util.*;

public class Main {
    static PrintWriter out;
    static Kioken sc;

    public static void main(String[] args) throws FileNotFoundException {
        boolean t = true;
        boolean f = false;
        if (t) {
            out = new PrintWriter("output.txt");
            sc = new Kioken("input.txt");
        } else {
            out = new PrintWriter((System.out));
            sc = new Kioken();
        }

        int tt = 1;
        // tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static class Vec implements Comparable<Vec>{
      int x, y, index;
      Vec(int x, int y){
        this.x = x;
        this.y = y;
      }

      @Override
      public int compareTo(Vec o){
        if(o.x != x){
          return Integer.compare(o.x, x);
        }else{
          return Integer.compare(o.y, y);
        }
      }
    }

    static int range = 1000000;
    static int[] dfs(Vec v, TreeSet<Vec> set, Vec prev, boolean[] visited){
      if(set.contains(v) == false) return new int[]{v.x, v.y, 0};

      if(v.x > range || v.y > range) return new int[]{-1, -1, Integer.MAX_VALUE};

      // out.println(v.x + " " + v.y);

      int[] dx = {1, -1, 0, 0};
      int[] dy = {0, 0, 1, -1};

      int[] min = {-1, -1, Integer.MAX_VALUE};
      
      for(int i = 0; i < 4; i++){
        Vec u = new Vec(v.x+dx[i], v.y + dy[i]);

        int[] ans = dfs(u, set, v, visited);
        ans[2]++;
        if(ans[2] < min[2]){
          min = ans;
        }
      }
      
      return min;
    }

    public static void solve() {
      int n = sc.nextInt();
      TreeSet<Vec> set = new TreeSet<>();

      Vec[] arr = new Vec[n];
      for(int i = 0; i < n; i++){
        Vec v = new Vec(sc.nextInt(), sc.nextInt());
        arr[i] = v;
        set.add(v);
      }

      // Vec v = new Vec(2, 8);
      // out.println(set.contains(v));
      for(int i = 0; i < n; i++){
        Vec prev = new Vec(range+1, range+1);
        int[] v = dfs(arr[i], set, prev);
        Vec o = new Vec(v[0], v[1]);
        set.add(o);
        out.println(v[2]);
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
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


    static int cnt;
    static int dfs(int index, boolean[] visited, List<Integer>[] ll, int[] arr, int val){
      // if(visited[index]) return;

      visited[index] = true;
      int xor = arr[index - 1];
      for(int i: ll[index]){
         if(visited[i] == false){
          int x = dfs(i, visited, ll, arr, val);
          xor ^= x;
         }
      }

      // out.println(" xor => " + xor);

      if(xor == val){
        // we need to break the tree here....
        cnt++;
        return 0;
      }else{
        return xor;
      }
    }

    public static void solve() {
       int n = sc.nextInt();
       int k = sc.nextInt();

       cnt = 0;
       int[] arr = new int[n];
       int xor = 0;
       for(int i = 0; i < n; i++){
         arr[i] = sc.nextInt();
         xor ^= arr[i];
       }

       List<Integer>[] ll = new List[n+1];
       for(int i = 0; i < n+1; i++){
         ll[i] = new ArrayList<>();
       }

       for(int i = 0; i < n-1; i++){
         int u = sc.nextInt();
         int v = sc.nextInt();
         ll[u].add(v);
         ll[v].add(u);
       }

       if(xor == 0){
         out.println("YES");
         return;
       }

       // divinding tree into m sub tree.
       // m subtree can be merged to form only 3 subtree 
       boolean[] visited = new boolean[n+1];

      int x = dfs(1, visited, ll, arr, xor);
      // out.println(" cnnt " + cnt + "  "  + xor);
      if(cnt > 1 && k > 2){
        out.println("YES");
      }else{
        out.println("NO");
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
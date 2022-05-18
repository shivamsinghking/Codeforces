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
        solve();

        out.flush();
        out.close();
    }

    static boolean isValid(int x, int y, int n, int m){
      if(x < 0 || x >= n || y < 0 || y >=  m) return false;
      return true;
    }

    public static void solve() {
      int n = sc.nextInt(), m = sc.nextInt();
      int[][] arr = new int[n][m];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
          arr[i][j] = sc.nextInt();
        }
      }

      ArrayDeque<int[]> q = new ArrayDeque<>();

      ArrayDeque<int[]> ans = new ArrayDeque<>();
      for(int i = 0; i < n-1; i++){
         for(int j = 0; j < m - 1; j++){
           if(arr[i][j] == arr[i+1][j] && (arr[i][j] == arr[i][j+1]) && (arr[i][j] == arr[i+1][j+1])){
             q.add(new int[]{i, j});
             ans.add(new int[]{i, j, arr[i][j]});
             arr[i][j] = 0;
             arr[i+1][j] = 0;
             arr[i][j+1] = 0;
             arr[i+1][j+1] = 0;
           }
         }
      }

      if(q.isEmpty()) {
        out.println(-1);
        return;
      }

      int[] dx = {0, 0, 1, 1};
      int[] dy = {0, 1, 0, 1};

      int[] dx1 = new int[]{0, -1, 0, -1};
      int[] dy1 = new int[]{0, -1 , -1, 0};

      int[] dx2 = new int[]{0, -1, -1, 0};
      int[] dy2 = new int[]{0, 0, 1, 1};

      int[] dx3 = new int[]{0, 0, 1, 1};
      int[] dy3 = new int[]{0, -1, 0, -1};

      int[] dx4 = new int[]{0, 1, 0, 1};
      int[] dy4 = new int[]{0, 0, 1, 1};


      int[][] mat = new int[8][4];
      mat[0] = dx1;
      mat[1] = dy1;

      mat[2] = dx2;
      mat[3] = dy2;
      
      mat[4] = dx3;
      mat[5] = dy3;

      mat[6] = dx4;
      mat[7] = dy4;

      // out.println(q.size());
      while(!q.isEmpty()){
        int [] v = q.poll();
        out.println(" start " + " "   + Arrays.toString(v));
        int x = v[0];
        int y = v[1];
        for(int i = 0; i < 4; i++){
          int nx = x + dx[i];
          int ny = y + dy[i];
          arr[nx][ny] = 0;
        }



        for(int i = 0; i < 4; i++){
          int nx = x + dx[i];
          int ny = y + dy[i];

          for(int j = 0; j < 8; j = j+2){
              int[] ddx = mat[j];
              int[] ddy = mat[j+1];
              HashSet<Integer> set = new HashSet<>();
              PriorityQueue<int[]> p = new PriorityQueue<>((a, b) -> (a[0] == b[0]) ? a[1] - b[1]: a[0] - b[0]);
              boolean flag = true;
              out.println(" ============= " + nx + " " + ny);
              for(int k = 0; k < 4; k++){
                int nnx = nx + ddx[k];
                int nny = ny + ddy[k];
                // out.println(nnx + " --- " + nny);
                if(isValid(nnx, nny, n, m)){
                 set.add(arr[nnx][nny]);
                 p.add(new int[]{nnx, nny});
                }else{
                  flag = false;
                  break;
                }
              }
              // out.println(flag);
              if(flag){
                // out.println(" set 1 ==>  "  + set + " " + nx  + " " + ny + " ");

                // for(int[] qq: p){
                //   out.println(Arrays.toString(qq));
                // }
                if(set.size() <= 2 && set.contains(0)){
               
                  out.println(set + " "  + Arrays.toString(v));
                  int val = 0;
                  // getting the unique value
                  for(int kk : set){
                    if(kk != 0){
                      val = kk;
                    }
                  }

                  // no, unique ele present
                  if(val == 0) continue;
                  // out.println("set -> " + set + " "  + q.size());

                  for(int k = 0; k < 4; k++){
                    int nnx = nx + ddx[k];
                    int nny = ny + ddy[k];
                    arr[nnx][nny] = 0;
                  }

                  for(int[] kk: arr){
                    out.println(Arrays.toString(kk));
                  }
                  // out.println(Arrays.toString(p.peek()) + " -->" );
                 int[] vv = p.peek();
                  ans.add(new int[]{vv[0], vv[1], val});
                  q.add(p.poll());
                  // out.println(" size " + q.size());
                }
              }
          }
        }
      }

      for(int i  = 0; i < n; i++){
        for(int j = 0; j < m; j++){
          if(arr[i][j] != 0){
            out.println(-1);
            return;
          }
        }
      }

      out.println(ans.size());
      while(ans.size() > 0){
        int[] vv = ans.poll();
        out.println(vv[0] + " " + vv[1] + " " + vv[2]);
      }
      out.println("YES");
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    public static long leftShift(long a) {
        return (long) Math.pow(2, a);
    }

    public static boolean[] sieve(int n) {
        boolean isPrime[] = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i * 2; j <= n; j = j + i) {
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < arr.length / 2; i++) {
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

    class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findParent(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = findParent(parent[i]);
        }

        void Union(int u, int v) {
            int parent_u = findParent(u);
            int parent_v = findParent(v);
            if (parent_u == parent_v)
                return;

            // small attached to big, since we want to reduce overall size
            if (size[parent_u] < size[parent_v]) {
                parent[parent_u] = parent_v;
                size[parent_v]++;
            } else {
                parent[parent_v] = parent_u;
                size[parent_u]++;
            }
        }
    }

    // SEGMENT-TREE
    static class SegmentTree {
        int[] arr = new int[4 * 100000];
        int[] givenArr;

        // HINT: This can be updated with ques.
        int build(int index, int l, int r) {
            if (l == r) {
                return arr[index] = givenArr[l];
            }
            int mid = (l + r) / 2;
            return arr[index] = build(2 * index + 1, l, mid) + build(2 * index + 2, mid + 1, r);
        }

        SegmentTree(int[] nums) {
            givenArr = nums;
            build(0, 0, nums.length - 1);
        }

        // HINT: This can be updated with ques.
        void update(int index, int l, int r, int diff, int i) {
            if (i >= arr.length) {
                return;
            }
            if (index >= l && index <= r) {
                arr[i] = arr[i] + diff;
            }
            if (index < l || index > r) {
                return;
            }
            int mid = (l + r) / 2;
            update(index, l, mid, diff, 2 * i + 1);
            update(index, mid + 1, r, diff, 2 * i + 2);
            return;
        }

        void update(int index, int val) {
            int diff = val - givenArr[index];
            givenArr[index] = val;
            update(index, 0, givenArr.length - 1, diff, 0);
        }

        int query(int left, int right, int l, int r, int i) {
            // not overlapping
            if (r < left || l > right) {
                return 0;
            }

            // total - overlapping
            if (l >= left && r <= right) {
                return arr[i];
            }

            // partial overlapping
            int mid = (l + r) / 2;
            int le = query(left, right, l, mid, 2 * i + 1);
            int ri = query(left, right, mid + 1, r, 2 * i + 2);
            return le + ri;
        }

        // HINT: for max sum, can be changed according to ques.
        int query(int l, int r) {
            return query(l, r, 0, givenArr.length - 1, 0);
        }
    }

}
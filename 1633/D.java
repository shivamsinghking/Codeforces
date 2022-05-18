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
        dp = new int[1001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        find(1, 0);

        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static int[] dp;

    static int[] pre;
    static void find(int val, int step){
      if(val > 1000) return;
      // out.println("---> " + val + " " + step);
      for(int i = 1; i <= val; i++){
        int vv = val + (int)Math.floor(val/i);
        if(vv > 1000) continue;
        if(step+1 < dp[vv]){
          dp[vv] = step+1;
          find(vv, step+1);
        }
      }
      return;
    }

    static int max = Integer.MIN_VALUE;
    static int[][] store;
    static int find1(int index, int k, int[] arr, int[] crr){
       if(index < 0){
        //  out.println(" sum "  + sum);
         return 0;
       }

       if(k < 0) return Integer.MIN_VALUE;

       if(store[index][k] != Integer.MIN_VALUE){
        //  out.println(store[index][k] + " --> ");
        return store[index][k];
       }
       // not choose karna hai
       if(arr[index] == 1){
        //  out.println(" sum 11 " + sum);
        int ans1 = crr[index] + find1(index-1, k, arr, crr);
        return store[index][k] = ans1;
       }

       int aa = Integer.MIN_VALUE;
       if(k - dp[arr[index]] >= 0){
         aa = crr[index] + find1(index-1, k - dp[arr[index]], arr, crr);
       }
       int bb = find1(index-1, k, arr, crr);

       return store[index][k] = Math.max(aa, bb);
    }
    public static void solve() {
      
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] arr = new int[n];
      int[] crr = new int[n];

      for(int i = 0; i < n; i++){
        arr[i] = sc.nextInt();
      }

      for(int i = 0 ; i < n; i++){
        crr[i] = sc.nextInt();
      }

      if(k >= 15*n){
        long sum = 0L;
        for(int i : crr) sum = sum + i;
        out.println(sum);
        return;
      }

      store = new int[n+1][k+1];

      for(int[] i: store){
        Arrays.fill(i, Integer.MIN_VALUE);
      }
    

      // for(int i = 0 ; i < 1001; i++){
      //   out.println(i + " " + dp[i] + " " + pre[i]);
      // }

      int ans = find1(n - 1, k, arr, crr);

      out.println(ans);
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
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
        // tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    public static void solve() {
       int n = sc.nextInt();
       List[] ll = new List[n+1];

       for(int i = 0; i < n+1; i++){
         ll[i] = new ArrayList<>();
       }

       HashSet<Integer> set = new HashSet<>();
       for(int i = 0; i <= n - 2; i++){
         set.add(i);
       }

       int[][] arr = new int[n][2];
        for(int i = 0; i < n-1; i++){
         int u = sc.nextInt();
         int v = sc.nextInt();
         arr[i][0] = u;
         arr[i][1] = v;
         ll[u].add(v);
         ll[v].add(u);
       }

       boolean ok = false;
       HashMap<Integer,HashMap<Integer,Integer>> map = new HashMap<>();

       for(int i = 1; i <= n; i++){
         if(ll[i].size() >= 3){
          //  out.println(" oo "  + i);
            ok = true;
            List<Integer> l1 = ll[i];
            int v = 0;
            for(int k = 0; k < l1.size(); k++){
              int j = (int)l1.get(k);
            if(map.containsKey(i)){
              HashMap<Integer,Integer> m = map.get(i);
              m.put(j, v);
              set.remove(v);
              v++;
              map.put(i, m);
            }else{
              HashMap<Integer,Integer> m = new HashMap<>();
              m.put(j, v);
              map.put(i, m);
              set.remove(v);
              v++;
            }
          }
          ok = true;
          break;
         }
       }

       Stack<Integer> st = new Stack<>();

      //  out.println(set.size());
       for(int i: set){
         st.push(i);
       }

       for(int i = 0; i < n-1; i++){
          int[] a = arr[i];
          if(map.containsKey(a[0])){
            int v = map.get(a[0]).get(a[1]);
            out.println(v);
          }else if(map.containsKey(a[1])){
            int v = map.get(a[1]).get(a[0]);
            out.println(v);
          }else{
            out.println(st.pop());
          }
       }

      //  List[] root = new List[n+1];
      //  for(int i = 0; i < n+1; i++){
      //    root[i] = new ArrayList<>();
      //  }

      //  for(int i = 0; i < n+1; i++){
      //    if(ll[i].size() == 1){
      //      int v = (int)ll[i].get(0);
      //      root[v].add(i);
      //    }
      //  }

      //  boolean isAllOne = true;
      //  int val = 0;
      //  for(int i = 0; i < n+1; i++){
      //      if(root[i].size() >= 2){
      //        isAllOne = false;
      //        val = i;
      //        break;
      //      }
      //  }

      //  if(isAllOne == true){
      //     // finding root with highest children
      //     int r  = 0;
      //     int s = 0;
      //     for(int i = 0; i < n+1; i++){
      //       if(root[i].size() == 1){
      //         if(ll[i].size() > s){
      //           r = i;
      //           s  = ll[i].size();
      //         }
      //       }
      //     }
      //  }
    
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
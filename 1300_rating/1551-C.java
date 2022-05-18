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

    static class Group{
      String s;
      int index;
      Group(String s, int index){
        this.s = s;
        this.index = index;
      }
    }

    public static void solve() {
        int n = sc.nextInt();
        String[] arr = new String[n];
        for(int i = 0; i < n; i++){
          arr[i] = sc.nextLine();
        }

        int[] count = new int[26];
        for(String i : arr){
          for(char j : i.toCharArray()){
            count[j-'a']++;
          }
        }

        // int max = 0;
        // for(int i: count){
        //   max = Math.max(max, i);
        // }

        Group[] brr = new Group[n];
        for(int i = 0; i < n; i++){
          Group g = new Group(arr[i], i);
          brr[i] = g;
        }

        int[][] store = new int[n][26];
        int total = 0;
        for(int i = 0; i < n; i++){
          String s = arr[i];
          total += s.length();
          for(char j : s.toCharArray()){
            store[i][j - 'a']++;
          }
        }

        int[][] store1 = new int[n][26];
        for(int i = 0; i < n; i++){
          String s = arr[i];
          for(int j = 'a'; j <= 'z'; j++){
            store1[i][j-'a'] = s.length() - store[i][j - 'a']; 
          }
        }
        int maxCount = Integer.MIN_VALUE;
        for(char i = 'a'; i <= 'z'; i++){
           if(count[i-'a'] > 0){
             int c = count[i - 'a']; 
             int index = i - 'a';
             Arrays.sort(brr, (a, b) -> {
               if(b.s.length() - store[b.index][index] == (a.s.length() - store[a.index][index])){
                  return store[a.index][index] - store[b.index][index];
               }else{
                 return b.s.length() - store[b.index][index] - (a.s.length() - store[a.index][index]);
               }
             });
            //  for(Group b : brr){
            //    out.println("--> " + b.s);
            //  }
            //  out.println(" index" + index);
             int j = 0;
             int left = total - c;
             for(; j < n; j++){
              //  out.println(" cc " + c + " left -> " + left);
               if(c > left){
                 maxCount = Math.max(maxCount, n - j);
                //  out.println(" --> max " + maxCount + " " + j + " " + n);
                 break;
               }
               Group g = brr[j];
               c -= store[g.index][index];
               left -= store1[g.index][index];
             }
              if(c > left){
               maxCount = Math.max(maxCount, n - j);
             }
           }
        }

        // out.println(maxCount);
        // out.println(" ans "); 
        out.println(maxCount == Integer.MIN_VALUE ? 0 : maxCount);
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
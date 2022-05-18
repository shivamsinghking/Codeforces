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
        tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }


    static  int[] maxSubArray(int[] nums) {
        
      if(nums.length == 1)return new int[]{nums[0], 0, 0};
      int max = 0;
      int count = 0;
      int index = 0;
      int prev = 0;
      int end = 0;
      int start = 0;
      for(int i=0; i<nums.length; i++){
          int val = nums[i];
          count += val;
          if(count>max){
            max = count;
            prev = start;
            end = i;
            // out.println(max + " " + prev +  " " + end + " " + count);
          }else if(count == max){
            // out.println(max + " - " + prev +  " " + end + " " + count + " "  + i + " " + start);
            if(i - start + 1 > end - prev + 1){
              prev = start;
              end = i;
            }
          }

          if(count < 0){
              count = 0;
              start = i+1;
          }
      }

      if(max > 0){
        return new int[]{max, prev, end};
      }else return new int[]{max, -1, -1};
      // if(max == 0){
      //    Arrays.sort(nums);
      //    return nums[nums.length - 1];
          
      // }
      // return max;
  }

    static int max;
    static Integer[][] dp;
    static int find(int index, int k, int[] arr, int x){
      if(index >= arr.length && k == 0) return  0;
      if(index >= arr.length && k > 0) return Integer.MIN_VALUE + 100000;
      
      if(k == 0){
          int v = find(index+1, k, arr, x);
          if(arr[index] < 0){
              return Math.max(v, 0);
          }else {
              return Math.max(v+arr[index], 0);
          }
        }

      if(arr.length - index < k) return Integer.MIN_VALUE + 100000;

       int a = Integer.MIN_VALUE;
       int b = Integer.MIN_VALUE;

       int v1 = find(index+1, k, arr, x);
       int v2 = find(index+1, k - 1, arr, x);
       if(arr[index] < 0){
           a = v1;
       }else{
           a = Math.max(arr[index], arr[index] + v1);
       }
     
       if(arr[index] + x < 0){
           b = v2;
       }else{
           b = Math.max(arr[index] + x, v2 + arr[index] + x);
       }
      return Math.max(a, Math.max(b, 0));

    //   int a = Math.max(arr[index], arr[index] + find(index+1, k, arr, x));

    //   int b = Math.max(arr[index] + x, arr[index] + x + find(index+1, k-1, arr, x));

    //   return Math.max(a, Math.max(b, 0));

    }

    public static void solve() {
        int n = sc.nextInt();
        int x = sc.nextInt();

        int[] arr = new int[n];
        int[][] brr = new int[n][2];
        for(int i = 0; i < n; i++){
          arr[i] = sc.nextInt();
          brr[i][0] = arr[i];
          brr[i][1] = i;
        }

        Arrays.sort(brr, (a, b) -> b[0] - a[0]);
        for(int i = 0; i <= n; i++){
            int ans = find(0, i, arr, x);
            out.print(ans + " ");
        }
        out.println();
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
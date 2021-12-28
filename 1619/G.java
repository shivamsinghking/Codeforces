import java.io.*;
import java.util.*;

import javax.swing.text.Segment;

public class Main
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int t = 1;
      t = sc.nextInt();
      while (t-- > 0)
      {
          solve();
      }
      out.close();
    }
 
    public static void solve()
    {
      int n = sc.nextInt();
      int k = sc.nextInt();

      int[] x = new int[n];
      int [] y = new int[n];
      int[] timer = new int[n];

      List<int[]> ll = new ArrayList<>();
      ll.add(new int[]{0, 0, 0});

      HashMap<Integer,List<Integer>> mapx = new HashMap<>();
      HashMap<Integer,List<Integer>> mapy = new HashMap<>();

      for(int i = 0; i < n; i++){
        x[i] = sc.nextInt();
        y[i] = sc.nextInt();
        timer[i] = sc.nextInt();
        ll.add(new int[]{x[i], y[i], timer[i]});
        // x, y , timer
        
        if(mapx.containsKey(x[i])){
          List<Integer> l = mapx.get(x[i]);
          l.add(i+1);
          mapx.put(x[i], l);
        }else{
          List<Integer> l = new ArrayList<>();
          l.add(i+1);
          mapx.put(x[i], l);
        }

        if(mapy.containsKey(y[i])){
          List<Integer> l = mapy.get(y[i]);
          l.add(i+1);
          mapy.put(y[i], l);
        }else{
          List<Integer> l = new ArrayList<>();
          l.add(i+1);
          mapy.put(y[i], l);
        }
      }

      DSU dsu = new DSU(n+1, timer);
      
      for(Map.Entry<Integer,List<Integer>> m : mapx.entrySet()){
        List<Integer> l = m.getValue();
        l.sort((a, b) -> ll.get(a)[1] - ll.get(b)[1]);
        for(int i = 1; i < l.size(); i++){
          int v = l.get(i);
          int u = l.get(i - 1);
          if(Math.abs(ll.get(u)[1] - ll.get(v)[1]) <= k){
              dsu.Union(u, v);
          }
        }
      }

      for(Map.Entry<Integer,List<Integer>> m : mapy.entrySet()){
        List<Integer> l = m.getValue();
        l.sort((a, b) -> ll.get(a)[0] - ll.get(b)[0]);
        for(int i = 1; i < l.size(); i++){
          int v = l.get(i);
          int u = l.get(i - 1);
          if(Math.abs(ll.get(u)[0] - ll.get(v)[0]) <= k){
              dsu.Union(u, v);
          }
        }
      }

    //   out.println("---"  + dsu.parent[3] + " " + dsu.parent[1] + " "+ " " + dsu.parent[5]);

     List<Integer> parent = new ArrayList<>();
     for(int i = 1; i <= n; i++){
       if(dsu.parent[i] == i){
           parent.add(i);
       }       
      }

      parent.sort((a, b) -> dsu.min_time[b] - dsu.min_time[a]);
      // descending order of min_time

    //   for(int i = 1; i <= n; i++){
    //       out.println("");
    //   }
      // parent contain no. of component made
      int t = 0;
      int ans = 0;
    //   out.println(" == " + parent);
      for(int i : parent){
        if(dsu.min_time[i] > t){
            ans = t;
            t++;
        }else if(dsu.min_time[i] < t){
            continue;
        }else{
            ans = t;
        }
      }

      out.println(ans);
    }

    static ArrayList<Long> prime_factors(long n) {
        ArrayList<Long> ans = new ArrayList<Long>();
        while (n % 2 == 0) {
            ans.add(2L);
            return ans;
        }
        for (long i = 3; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                ans.add(i);
                return ans;
            }
        }
        if (n > 2){
            ans.add(n);
        }
        return ans;
    }
    
    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    public static int lower_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0, e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) <= k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        return Math.abs(s) - 1;
    }

    public static int upper_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0;
        int e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) < k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        if (s == ar.size())
        {
            return -1;
        }
        return s;
    }

    public static int[] z_function(String s) {
        int n = (int) s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
                ++z[i];
            if (i + z[i] - 1 > r){
                l = i; r = i + z[i] - 1;
            }
        }
        return z;
    }

    static class Kioken
    {
        // FileInputStream br = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next()
        {
            while (!st.hasMoreTokens())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt()
        {
            return Integer.parseInt(next());
        }

        public long nextLong()
        {
            return Long.parseLong(next());
        }

        public double nextDouble()
        {
            return Double.parseDouble(next());
        }

        public String nextLine()
        {
            try
            {
                return br.readLine();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext()
        {
            String next = null;
            try
            {
                next = br.readLine();
            }
            catch (Exception e)
            {
            }
            if (next == null || next.length() == 0)
            {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }
    }


    static class FenwickTree{
        int[] arr;
        int N = 0;
       
        FenwickTree(int n, int[] aa) {
            // index in FT is always 1 more than in array
            N = n + 1;
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = 0;
            }

            for (int i = 0; i < aa.length; i++) {
                update(i, aa[i]);
            }
        }

        void update(int idx, int val) {
            idx++;
            while (idx < N) {
                // Add 'val' to current node of BIT Tree
                arr[idx] += val;
                // Update index to that of parent
                // in update View
                idx += idx & (-idx);
            }
        }

        int sum(int index) {
            int sum = 0; // Initialize result

            // index in BITree[] is 1 more than
            // the index in arr[]
            index = index + 1;

            // Traverse ancestors of BITree[index]
            while (index > 0) {
                // Add current element of BITree
                // to sum
                sum += arr[index];

                // Move index to parent node in
                // getSum View
                index -= index & (-index);
            }
            return sum;
        }

        // just like prefix sum
        int rangeSum(int l, int r){
            return sum(r) - sum(l-1);
        }

        // binary lifting
        // return the lowerBound index for target sum
        int getLBSumIndex(int sum, int target) {
            int curr = 0, prevSum = 0;
            for (int i = N; i >= 0; i--) {
                if (curr + (1 << i) < N) {
                    if (arr[curr + (1 << i)] + prevSum < target) {
                        curr = curr + (1 << i);
                        prevSum = arr[curr];
                    }
                }
            }
            return (curr + 1);
        }

        void print() {
            out.println(Arrays.toString(arr));
        }
    }

    static class SegmentTree{
        int[] arr = new int[4*100000];
        int[] givenArr;
        // HINT: This can be updated with ques.
        int build(int index, int l, int r){
            if(l == r){
                return arr[index] = givenArr[l];
            }
            int mid = (l+r)/2;
           return arr[index] = build(2*index+1, l, mid) + build(2*index+2, mid+1, r);
        }
        SegmentTree(int[] nums) {
            givenArr = nums;
            build(0, 0, nums.length - 1);
        }

        // HINT: This can be updated with ques.
        void update(int index, int l, int r, int diff, int i) {
            if(i>=arr.length){return ;}
            if(index>=l && index<=r){
                arr[i] = arr[i]+diff;
            }
            if(index<l || index>r){
                return;
            }
            int mid = (l+r)/2;
            update(index,l,mid, diff, 2*i+1);
            update(index,mid+1, r, diff, 2*i+2);
            return;
        }

        void update(int index, int val){
            int diff = val - givenArr[index];
            givenArr[index] = val;
            update(index, 0, givenArr.length-1, diff, 0);
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
        int query(int l , int r){
            return query(l, r, 0, givenArr.length-1, 0);
        }
    }

    static class DSU{
        int[] parent, size, min_time;
        
       DSU(int n, int[] time){
           parent = new int[n];
           size = new int[n];
           min_time = new int[n];
           
           for(int i = 0;i < n; i++){
               parent[i] = i;
               size[i] = 1;
           }

           for(int i = 0; i < time.length; i++){
             min_time[i+1] = time[i];
           }
       }
       
       int findParent(int i){
           if(parent[i] == i){
               return i;
           }
           return parent[i] = findParent(parent[i]);
       }
       
       void Union(int u,int v){
           int parent_u = findParent(u);
           int parent_v  = findParent(v);
           if(parent_u == parent_v) return;
           
           // small attached to big, since we want to reduce overall size
           if(size[parent_u] < size[parent_v]){
               parent[parent_u] = parent_v;
               size[parent_v]++;
               min_time[parent_v] = Math.min(min_time[parent_u], min_time[parent_v]);
           }else{
               parent[parent_v] = parent_u;
               size[parent_u]++;
               min_time[parent_u] = Math.min(min_time[parent_u], min_time[parent_v]);
           }
       }
   }
}
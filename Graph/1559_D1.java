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

        solve();

        out.flush();
        out.close();
    }

    public static void solve() {
       int n = sc.nextInt();
       int m1 = sc.nextInt();
       int m2 = sc.nextInt();

       DSU d1 = new DSU(n+2);
       DSU d2 = new DSU(n+2);

       for(int i = 0; i < m1; i++){
         int u = sc.nextInt();
         int v = sc.nextInt();
         d1.Union(u, v);
       }

       for(int i = 0; i < m2; i++){
         int u = sc.nextInt();
         int v = sc.nextInt();
         d2.Union(u, v);
       }

       List<int[]> ans = new ArrayList<>();
       for(int i = 1; i <= n; i++){
         for(int j = i+1; j <= n; j++){
           if(d1.findParent(i) != d1.findParent(j) && (d2.findParent(i) != d2.findParent(j))){
             d1.Union(i, j);
             d2.Union(i, j);
             ans.add(new int[]{i, j});
           }
         }
       }

       out.println(ans.size());
       for(int[] i: ans){
         out.println(i[0] + " " + i[1]);
       }

       return;
    }

   static class DSU{
      int[] parent, size;
      
     DSU(int n){
         parent = new int[n];
         size = new int[n];
         for(int i = 0;i < n; i++){
             parent[i] = i;
             size[i] = 1;
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
         }else{
             parent[parent_v] = parent_u;
             size[parent_u]++;
         }
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
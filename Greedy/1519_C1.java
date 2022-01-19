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

    public static void solve() {
       int n = sc.nextInt();
       int[] u = new int[n];
       int[] s = new int[n];

       for(int i = 0; i < n; i++){
         u[i] = sc.nextInt();
       }

       for(int i = 0; i < n; i++){
         s[i] = sc.nextInt();
       }
       HashMap<Integer,List<Integer>> map = new HashMap<>();
       for(int i = 0;  i < n; i++){
         int ui = u[i];
         int si = s[i];
        if(map.containsKey(ui)){
          List<Integer> ll  = map.get(ui);
          ll.add(si);
          map.put(ui, ll);
        }else{
          List<Integer> ll = new ArrayList<>();
          ll.add(si);
          map.put(ui, ll);
        }
       }

       HashMap<Integer,long[]> m1 = new HashMap<>();
      //  out.println(" j " +  " " + map.keySet());
       for(int j: map.keySet()){
         List<Integer> ll = map.get(j);
         Collections.sort(ll, (a, b) -> b - a);
         long[] arr = new long[ll.size()];
         
         for(int i = 0; i < ll.size(); i++){
            if(i == 0){
              arr[i] = (long)ll.get(i);
            }else{
              arr[i] = arr[i-1] + (long)ll.get(i);
            }
         }
        //  out.println(" j " + j + " " + map.keySet());
         m1.put(j, arr);
       }


       long[] ans = new long[n+1];
       for(int i = 1; i <= n; i++){
        //  out.println(m1.containsKey(i) + " " + i);
         if(m1.containsKey(i)){
          long[] arr = m1.get(i);
          // out.println(Arrays.toString(arr));
          for(int k = 1; k <= arr.length; k++){
            int rem = (arr.length%k);
            int index = arr.length - 1 - rem;
            // out.println(" -- "  + arr.length + " " + index + " "  + rem + " " + k + " " + arr[index]);
            ans[k] += (long)arr[index];
          }
         }
         
       }

       for(int i = 1; i <= n; i++){
         out.print(ans[i] + " ");
       }
       out.println();
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
        for (int i = 0; i < arr.length; i++) {
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
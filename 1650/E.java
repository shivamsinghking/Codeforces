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

    static int solve(List<Integer> ll, int mid, boolean checkLast){
        if(ll.size() == 0) return 0;

        // check if all difference is greater that mid
        for(int i = 1; i < ll.size() - (checkLast ? 0 : 1); i++){
            int v = ll.get(i) - ll.get(i - 1) - 1;
            if(v < mid) return 0;
        }

        // check if we can find at least one gap/2 which is greater >= mid
        int cnt = 0;
        for(int i = 1; i < ll.size() - (checkLast ? 0: 1); i++){
            int v = ll.get(i) - ll.get(i - 1) - 1;
            int m = ll.get(i-1) + v/2;
            int u = Math.min(ll.get(i) - m - 1, m  - ll.get(i - 1) - 1);
            if(u >= mid) cnt++;
        }
        return cnt;
    }

    static boolean check(List<Integer> l1, List<Integer> l2, int mid, boolean checkLast){
        int cnt1 = solve(l1, mid, checkLast);
        if(cnt1 > 0) return true;

        int cnt2 = solve(l2, mid, checkLast);
        if(cnt2 > 0) return true;

        return false;
    }

    public static void solve() {
      int n = sc.nextInt();
      int d = sc.nextInt();
      int[] arr = new int[n];
      for(int i = 0; i < n; i++){
        arr[i] = sc.nextInt();
      }

     
      if(arr[0] == 1){
        // we only have to shift this one
        int prev = 0;
        int max = 0;

        int minGap = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++){
            int m = arr[i] - prev - 1;
            minGap = Math.min(m, minGap);
            prev = arr[i];
        }


        prev = 0;
        for(int i = 1; i < n; i++){
          int m = prev + (arr[i] - prev)/2;
          max = Math.max(m - prev - 1, arr[i] - m - 1);
        //   max = Math.min(max, minGap);
          prev = arr[i];
        }

        if(arr[n - 1] < d){
          int m = prev + (d - prev)/2;
          max = Math.max(m - prev - 1, d - m - 1);
        }

        // check if other gap is smaller than or not
      
        out.println(Math.min(minGap, max));
        return;
      }

      List<Integer> ll = new ArrayList<>();
      List<Integer> l1 = new ArrayList<>();
      List<Integer> l2 = new ArrayList<>();

      ll.add(0);
      for(int i: arr) ll.add(i);


      int min = Integer.MAX_VALUE;
      int index = -1;
      for(int i = 1; i < ll.size(); i++){
       int v = ll.get(i) - ll.get(i - 1) - 1;
       if(v < min){
           min = v;
           index = i;
       }
      }

      for(int i = 0; i < ll.size(); i++){
          if(i == index) continue;
          l1.add(ll.get(i));
      }

      // may be empty
      if(index - 1 > 0){
          for(int i = 0; i < ll.size(); i++){
              if(i == index - 1){
                 continue;
              }
              l2.add(ll.get(i));
          }
      }

      boolean checkLast = true;
      if(arr[n - 1] < d){
        ll.add(d);
        l1.add(d);
        l2.add(d);
        checkLast = false;
      }


      int l = 0;
      int r = (int)1e9;

      out.println(l1 + " " + l2 + " " + index + " "   + ll);
      int ans = 0;
      while(l <= r){
        int mid = (l+r)/2;
        if(check(l1, l2, mid, checkLast)){
            l = mid + 1;
            ans = mid;
        }else{
            r = mid - 1;
        }
      }
      out.println(" mid " + Math.max(ans, min) + " --> " + min);
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
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

    public static long getVal(List<Integer> pp, boolean isLeft, int k){
      long sum_pos = 0L;
      long last = 0L;
      for(int i = 0; i < pp.size(); i++){
        int kk = k-1;
        int prev = pp.get(i);
        sum_pos += prev;
        if(kk > 0){
          i++;
        }
        for(; i < pp.size() && kk > 0; i++, kk--){
          sum_pos += (pp.get(i) - prev);
          prev = pp.get(i);
        }
        sum_pos += prev;
        out.println("sum ==> " + sum_pos + " " + pp);
        if(i >= pp.size() - 1){
          last = prev;
        }
      }

      // out.println(" -->>last "  + last);
      return sum_pos + ((isLeft == false) ? (-1*last) : 0);
    }
    public static void solve() {
       int n = sc.nextInt();
       int k = sc.nextInt();

       int[] arr = new int[n];
      //  HashMap<Integer,Integer> pos = new HashMap<>();
      //  HashMap<Integer,Integer> neg = new HashMap<>();

       List<Integer> pp = new ArrayList<>();
       List<Integer> nn = new ArrayList<>();
       for(int i = 0; i < n; i++){
         arr[i] = sc.nextInt();
         if(arr[i] >= 0){
           pp.add(arr[i]);
          //  if(pos.containsKey(arr[i])){
          //    pos.put(arr[i], pos.get(arr[i])+1);
          //  }else{
          //    pos.put(arr[i], 1);
          //  }
         }else{
           nn.add(-1*arr[i]);
          //  if(neg.containsKey(arr[i])){
          //    neg.put(arr[i], neg.get(arr[i])+1);
          //  }else{
          //    neg.put(arr[i], 1);
          //  }
         }
       }

       Collections.sort(pp);
       Collections.sort(nn);

       long sum_pos = getVal(pp, nn.size() > 0 ? true: false, k);
       long sum_ng = getVal(nn, false, k);

      //  out.println(" -- " + sum_pos);
       out.println(sum_ng + sum_pos);
      
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
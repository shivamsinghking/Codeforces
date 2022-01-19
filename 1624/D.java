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

    static boolean bs(long even, long odd, long val, int k){
      // min palindrom is poss or not

      // out.println(" Mid " + val + " " + even + " " + odd);
      // if(val%2 == 0){
      //   even = even - val;
      // }else{
      //   even = even - val + 1;
      //   odd--;
      // }

      // val++;
      // out.println(" mid == " + val + " "  + even + " " + odd + " " + k);
      for(int i = 0; i < k; i++){
         if(val%2 == 0){
           if(even >= val){
             even = even - val;
           }else{
             return false;
           }
         }else{
           if(odd > 0){
             if(even >= val - 1){
               even = even - val +1;
               odd--;
             }else{
               return false;
             }
           }else{
             if(even >= val){
               even = even - val;
             }else{
               return false;
             }
           }
         }
        //  val++;
      }

      return true;
    }
    public static void solve() {
       int n = sc.nextInt();
       int k = sc.nextInt();
       String s = sc.nextLine();
       int[] arr = new int[26];
       for(int x: s.toCharArray()){
         arr[x - 'a']++;
       }

       List<Integer> even = new ArrayList<>();
       List<Integer> odd = new ArrayList<>();

       for(int i = 0; i < 26; i++){
         if(arr[i] > 0){
           if(arr[i]%2 == 0){
             even.add(arr[i]);
           }else{
             if(arr[i] > 1){
               odd.add(1);
               even.add(arr[i]-1);
             }else{
               odd.add(1);
             }
           }
         }
       }

      long sum_even = 0L;
      for(long x: even){
        sum_even += x;
      }

      long sum_odd = 0L;
      for(long x: odd){
        sum_odd += x;
      }

      long r = sum_even + 1;
      long l = 1;

      long ans = 1;
      while(l <= r){
        long mid = (r+l)/2;
        // out.println("Mid ---- " + mid + " " + l + " " + r);
        if(bs(sum_even, sum_odd, mid, k)){
           ans = mid;
          //  out.println(" mid " + mid);
           l = mid+1;
        }else{
          r = mid - 1;
        }
      }
      out.println(ans);
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
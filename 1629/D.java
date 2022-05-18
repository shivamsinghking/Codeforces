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
       String[] s = new String[n];
       for(int i = 0; i < n; i++){
         s[i] = sc.nextLine();
       }

      //  List<Integer>[][] store = new List[3][26];

       HashMap<String,Integer> map = new HashMap<>();
       for(int i = 0; i < n; i++){
         map.put(s[i], i);
       }

      //  for(String x : map.keySet()){
      //   //  out.println(" == max " + map.get(x) + " " + x);
      //  }
      //  for(int i = 0; i < 3; i++){
      //   for(int j = 0; j < 26; j++){
      //    store[i][j] = new ArrayList<>();
      //   }
      // }
      
      // for(int i = 0 ; i < n; i++){
      //   String s1 = s[i];
      //   for(int j = 0; j < s1.length(); j++){
      //     store[j][s1.charAt(j) - 'a'].add(i);
      //   }
      // }

      for(int i = 0; i < n; i++){
        String s1 = s[i];
        if(s1.length() == 1){
          out.println("YES");
          return;
        }else if(s1.length() == 2){
          if(s1.charAt(0) == s1.charAt(1)){
            out.println("YES");
            return;
          }
        }else{
          if(s1.charAt(0) == s1.charAt(2)){
            out.println("YES");
            return;
          }
        }
      }


      // String ans = "poi";
      // StringBuffer ss = new StringBuffer(ans);
      // ss.reverse();
      // out.println("  === " + ss + " " + ans);
      for(int i = 0; i < n; i++){
        String s1 = s[i];
        if(s1.length() == 3){
          // find yxz => xy
          String s2 = String.valueOf(s1.charAt(1)) + String.valueOf(s1.charAt(0));
          if(map.containsKey(s2) && map.get(s2) > i){
            out.println("YES");
            return;
          }
          // List<Integer> ll = store[0][s1.charAt(1) - 'a'];
         
          // for(int k : ll){
          //   if(k > i){
          //     String s2 = s[k];
          //     if(isPal(s1+s2)){
          //       out.println("YES");
          //       return;
          //     }
          //   }
          // }

          // zxy =>
          StringBuffer s3 = new StringBuffer(s1);
          s3.reverse();
          String s_string = s3.toString();
          if(map.containsKey(s_string) && map.get(s_string) > i){
            out.println("YES");
            return;
          }

          // List<Integer> l1 = store[2][s1.charAt(0) - 'a'];
          // for(int k : l1){
          //   if(k > i){
          //     String s2 = s[k];
          //     if(isPal(s1+s2)){
          //       out.println("YES");
          //       return;
          //     }
          //   }
          // }
        }else{
            // xz => zx
            StringBuffer s2 = new StringBuffer(s1);
            s2.reverse();

            // out.println(" max ==> " + map.containsKey(s2) + " " + s2);
            String s_string = s2.toString();
            if(map.containsKey(s_string) && map.get(s_string) > i){
              out.println("YES");
              return;
            }
            // List<Integer> ll = store[0][s1.charAt(1) - 'a'];
            // // out.println(" 111 " + ll);
            // for(int k: ll){
            //   if(k > i){
            //     String s2 = s[k];
            //     if(isPal(s1+s2)){
            //       out.println("YES");
            //       return;
            //     }
            //   }
            // }

            // y + zx

            for(char k = 'a'; k <= 'z'; k++){
              String s3 = String.valueOf(k) + s2.toString();
              if(map.containsKey(s3) && map.get(s3) > i){
                out.println("YES");
                return;
              }
            }
            // ll = store[2][s1.charAt(0) - 'a'];
            // out.println(" ll 1 " + s1 + " " + ll);
           
            // for(int k: ll){
            //   if(k > i){
            //     String s2 = s[k];
            //     if(isPal(s1+s2)){
            //       out.println("YES");
            //       return;
            //     }
            //   }
            // }
        }
      }
       out.println("NO");
    }

    public static boolean isPal(String s1){
      int n = s1.length();
      for(int i = 0; i < n/2; i++){
        if(s1.charAt(i) != s1.charAt(n - i - 1)){
              return false;
        }
      }
      return true;
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
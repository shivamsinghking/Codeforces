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

    static boolean remainder(int a, int[] arr){
      for(int i: arr){
        if(i%3 != a){
          return false;
        }
      }
      return true;
    }
    public static void solve() {
       int n = sc.nextInt();
       int[] arr = new int[n];
       for(int i = 0; i < n; i++){
         arr[i] =  sc.nextInt();
       }

       reverse(arr);
       
       int three = 0;
       int two = 0;
       int one = 0;

       int val = arr[0];
       if(val < 3){
        int cnt = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i : arr){
          set.add(i);
        }
        out.println(set.size());
        return;
       }

       three = val/3;
       if(val%3 == 1){
         one = 1;
       }else if(val%3 == 2){
         two = 1;
       }
      //  out.println("thee " + three + " " + val + " " + Arrays.toString(arr));
       if(two == 1){
         // if every no. rem is 2
        boolean check = remainder(2, arr);
        if(check){
          out.println(three+1);
          return;
        }
        boolean flag = true;
        for(int i : arr){
          if(i < 2){
            flag = false;
            break;
          }
        }
        if(flag){
          out.println(three + 1);
        }else{
          out.println(three+2);
        }
       }else if(two == 0 && one == 0){
         // if every no. of divisible by 3 then ans == three else three + 1
         boolean flag = true;
         for(int i : arr){
           if(i%3 != 0){
             flag = false;
             break;
           }
         }
         if(flag){
           out.println(three);
         }else{
           out.println(three+1);
         }
        }else if(one == 1){
          boolean check = remainder(1, arr);
          if(check){
            out.println(three+1);
            return;
          }
          boolean flag = true;
          for(int i : arr){
            if(i < 2){
              flag = false;
              break;
            }
          }
          if(flag){
            out.println(three + 1);
          }else{
            out.println(three + 2);
          }
        }
    }

    public static long leftShift(long a) {
        return (long) Math.pow(2, a);
    }

    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i <= arr.length/2; i++) {
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
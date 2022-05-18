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
       int[] arr = new int[n];
       for(int i = 0; i < n; i++){
         arr[i] = sc.nextInt();
       }

       int[] ans = new int[n];
       if(n%2 == 0){
         for(int i = 1; i < n; i = i+2){
           ans[i-1] = -arr[i];
           ans[i] = arr[i-1];
         }
       }else{
         for(int i = 1; i <n - 3; i = i+2){
           ans[i-1] = -arr[i];
           ans[i] = arr[i-1];
         }

         int val1, val2, val3;
         if(arr[n-3] + arr[n-2] == 0){
           if(arr[n-3] + arr[n-1] == 0){
             val1 = n-2;
             val2 = n-1;
             val3 = n-3;
           }else{
             val1 = n-3;
             val2 = n-1;
             val3 = n-2;
           }
         }else{
           val1 = n-3;
           val2 = n-2;
           val3 = n-1;
         }
         int vv1 = arr[val3];
         int vv2 = arr[val3];
         int vv3 = -1*(arr[val1] + arr[val2]);
         ans[val1] = vv1;
         ans[val2] = vv2;
         ans[val3] =  vv3;
        //  out.println(" -= " + val1 + " " + val2 + " " + val3);
        //  ans[n-1] = -1*(val1+val2);
       }
       
       boolean check = false;
       long sum = 0;
       for(int i = 0; i < n; i++){
         sum += (arr[i]*ans[i]);
       }

      //  out.println(" sum " + sum);
      if(sum != 0){
        out.println(-1 + " ==> " + " " + Arrays.toString(ans));
        return;
      }
       for(int i: ans){
         out.print(i + " ");
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
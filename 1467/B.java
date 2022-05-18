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

       boolean[] isHiill = new boolean[n];
       int total = 0;
       for(int i = 1; i < n - 1; i++){
         if(arr[i] < arr[i-1] && arr[i] < arr[i+1]){
           isHiill[i] = true;
           total++;
         }else if(arr[i] > arr[i-1] && arr[i] > arr[i+1]){
           isHiill[i] = true;
           total++;
         }
       }


       int min = total;
       int cnt = 0;
       for(int i = 1; i < n-1; i++){
         if(isHiill[i]){
           if(isHiill[i-1] && isHiill[i+1]){
                isHiill[i] = false;
                isHiill[i-1] = false;
                isHiill[i+1] = false;
                cnt += 3;
                break;
           }
         }
       }

       if(cnt == 3){
         out.println(total - 3);
         return;
       }

      //  int min = total;
       for(int i = 1; i < n-1; i++){
         if(isHiill[i]){
           // check left
           int left = total - 1;
           int val = arr[i-1];
           if(isHiill[i-1]){
             left--;
           }

           if(i+2 < n && ishill(val, arr[i+1], arr[i+2])){
             if(isHiill[i+1] == false){
               left++;
             }
           }else{
             if(isHiill[i+1]){
               left--;
             }
           }


           //check right
           int right = total - 1;
           val = arr[i+1];
           if(isHiill[i+1]){
             right--;
           }

           if(i-2 >= 0 && ishill(arr[i-2],arr[i-1], val)){
             if(isHiill[i-1] == false){
               right++;
             }
           }else{
             if(isHiill[i-1]){
               right--;
             }
           }

           min = Math.min(min, Math.min(left, right));
         }
       }

      //  out.println(" total ==> " + total);
       out.println(min);
    }

    static boolean ishill(int l, int m, int r){
      if(m < l && m < r) return true;
      if(m > l && m > r) return true;
      else return false;
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
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

    static int lis( int arr[], int n ) 
    { 
        int result = 0; 
        int[] lis = new int[n]; 
      
        /* Initialize LIS values  
        for all indexes */
        for (int i = 0; i < n; i++ ) 
            lis[i] = 1; 
      
        /* Compute optimized LIS  
           values in bottom up manner */
        for (int i = 1; i < n; i++ ) 
            for (int j = 0; j < i; j++ ) 
                if ( arr[i] > arr[j] && 
                    lis[i] < lis[j] + 1) 
                    lis[i] = lis[j] + 1; 
      
        /* Pick resultimum of 
        all LIS values */
        for (int i = 0; i < n; i++ ) 
            if (result < lis[i]) 
                result = lis[i]; 
      
        return result; 
    } 

    static int LIS(int n, int[] arr, int[] s) {

      int i = 0, k = 0, index = 0;

      // memset(s, 0, sizeof(s));

      index = 1;
      s[1] = 0; /*
                 * s[i] = 0 is the index of the element that ends an increasing sequence of
                 * length i = 1
                 */

      for (i = 1; i < n; i++) {

        if (arr[i] >= arr[s[index]]) { /* larger element, extend the sequence */

          index++; /* increase the length of my subsequence */
          s[index] = i; /* the current doll ends my subsequence */

        }
        /*
         * else find the smallest element in s >= a[i], basically insert a[i] in s such
         * that s stays sorted
         */
        else {
          k = bs(1, index, arr[i], arr, s);

          if (arr[s[k]] >= arr[i]) { /* if truly >= greater */
            s[k] = i;
          }
        }
      }
      return index;
    }

    static int bs(int first, int last, int x,int[] arr, int[] s) {

      while(last > first)
      {
          int mid = first + (last - first) / 2;
          if(arr[s[mid]] > x)
              last = mid;
          else
              first = mid + 1;
      }

      return first; /* or last */
  }

    static int minimumNumberOfDeletions(int arr[],
        int n) {
      // Find longest
      // increasing subsequence
      int[] s = new int[n+1];
      int len = LIS(n, arr, s);

      // After removing elements
      // other than the lis, we get
      // sorted sequence.
      return (n - len);
    }

    public static void solve() {
       int n = sc.nextInt();
       int[] arr = new int[n];
       for(int i = 0; i <n ; i++){
         arr[i] = sc.nextInt();
       }

       int ans = minimumNumberOfDeletions(arr, n);
       out.println((ans == 0) ? ans : ans - 1);
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
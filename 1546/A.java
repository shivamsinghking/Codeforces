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

      int[] brr = new int[n];
      for(int i = 0; i < n; i++){
        brr[i] = sc.nextInt();
      }
      long sum = 0;
      long sum1 = 0;
      for(int i: arr) sum += i;
      for(int i: brr) sum1 += i;
      if(sum1 != sum){
        out.println(-1);
        return;
      }

      List<int[]> ll = new ArrayList<>();
      for(int i = 0; i < n-1; i++){
        if(arr[i] == brr[i]) continue;

        if(arr[i] > brr[i]){
          int j = i+1;
          while(arr[i] > brr[i]){
           arr[i] -= 1;
           arr[j] += 1;
           ll.add(new int[]{i, j});
          }
        }else{
         int j = i+1;
         while(arr[i] < brr[i]){
           arr[i] += 1;
           arr[j] -= 1;
           ll.add(new int[]{i, j});
           if(arr[j] <= 0){
             j++;
           }
         }
        }
      }

      // out.println(Arrays.toString(arr) + " "  + Arrays.toString(brr));
      // for(int i = 0; i < n; i++){
      //   if(arr[i] != brr[i]){
      //     out.println("NO");
      //     break;
      //   }else{
      //   }
      // }
      out.println(ll.size());
      for(int i = 0; i < ll.size(); i++){
        out.println((ll.get(i)[0]+1)  + " " + (ll.get(i)[1]+1));
      }
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
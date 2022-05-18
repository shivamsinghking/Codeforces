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
      int[] arr = new int[n+1];
      for(int i = 1; i <= n; i++){
        arr[i] = sc.nextInt();
      }
      // out.println(Arrays.toString(arr));

      List<Integer> ans = new ArrayList<>();

      // out.println(arr.length);
      for(int i = n; i >= 1; i--){
        // finding 6
        int index = -1;
        for(int j = 1; j <= n; j++){
          if(arr[j] == i){
            index = j;
            break;
          }
        }

        List<Integer> ll = new ArrayList<>();
        ans.add(index);

        for(int j = index+1; j <= i; j++){
          ll.add(arr[j]);
        }

        for(int j = 1; j <= index; j++){
          ll.add(arr[j]);
        }

        for(int j = 1; j <= i; j++){
          arr[j] = ll.get(j-1);
        }
      }
      // out.println(ans);
      boolean ok = true;
      for(int i = 1; i <= n; i++){
        if(arr[i] != i){
             ok = false;
        }
      }
      if(ok == false){
        out.println(-1);
        return;
      }

      List<Integer> ans1 = new ArrayList<>();
      for(int i = ans.size() - 1;i >= 0; i--){
        ans1.add(ans.get(i));
      }
      // for(int i: ans){
      //   ans1.add(i);
      // }

      // out.println(ans);
      for(int i = 0; i < n; i++){
        int v = ans1.get(i);
        if((i+1) == 1){
          out.print(0 + " ");
          continue;
        }
        if(v == (i+1)){
          out.print(0 + " ");
        }else{
          out.print(v + " ");
        }
      }
      out.println();
      // out.println(Arrays.toString(arr));
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
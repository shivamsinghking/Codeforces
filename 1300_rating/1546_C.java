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

       TreeMap<Integer,List<Integer>> map = new TreeMap<>();
       for(int i = 0; i < n; i++){
         arr[i] = sc.nextInt();
        if(map.containsKey(arr[i])){
          List<Integer> ll = map.get(arr[i]);
          ll.add(i);
          map.put(arr[i], ll);
        }else{
          List<Integer> ll = new ArrayList<>();
          ll.add(i);
          map.put(arr[i],ll);
        }
       }

       boolean flag = true;

       // TLE => BECAUSE OF LIST
       for(int i = 0; i < n; i++){
        int key = map.firstKey();
        List<Integer> ll = map.get(map.firstKey());
        boolean ok = false;
        int rem = 0;
        // out.println(ll);
        for(int j = 0; j < ll.size(); j++){
          int jj = ll.get(j);
          if(Math.abs(jj - i)%2 == 0){
             ok = true;
             rem = j;
             break;
          }
         }

        //  out.println(" oo " + i + " " + rem + " " + ll + " k " + key);
         if(ok){
          //  out.println(" ii " + i   + " " + rem + " " + ll.size());
          ll.remove(rem);
          if(ll.size() == 0){
            map.remove(key);
          }else{
            map.put(key, ll);
          }
          continue;
         }else{
           flag = false;
           break;
         }
       }

       if(flag){
         out.println("YES");
       }else{
         out.println("NO");
       }

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
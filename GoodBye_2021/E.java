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

    static char getChar(int a){
      return (char)(a + 'a');
    }

    static int getInt(char a){
      return a - 'a';
    }

    static int find(int[] arr, int i, boolean[] isAva){
       while(i < arr.length){
         int index = arr[i];
         if(index >= arr.length){
           return arr.length;
         }
         if(isAva[index]){
           return index;
         }else{
           i = arr[index];
         }
       }
       return i;
    }
    public static void solve() {
       int n = sc.nextInt();
       String s = sc.nextLine();
       String t = sc.nextLine();

       if(s.equals(t)){
         out.println(-1);
         return;
       }
      //  PriorityQueue<int[]> p = new PriorityQueue<>((a, b) -> a[0] - b[0]);
      boolean[] isAvailable = new boolean[n];
      int[] getMin = new int[n];

      Arrays.fill(isAvailable, true);
       for(int i  = n - 1; i >= 0; i--){
         if(i == n - 1){
          getMin[i] = n;
         }else{
           if(getInt(s.charAt(i+1)) < getInt(s.charAt(i))){
             getMin[i] = i+1;
           }else{
             getMin[i] = getMin[i+1];
           }
         }
        // p.add(new int[]{s.charAt(i) - 'a', i});
       }
       out.println(Arrays.toString(getMin));

       int i = 0, j = 0, cnt = 0;
       while(j < n && i < n){
          // while(p.size() > 0){
          //   if(p.peek()[1] < i){
          //     p.poll();
          //   }
          // }

          if(!isAvailable[i]){
            i++;
            continue;
          }
          if(s.charAt(i) < t.charAt(j)){
            break;
          }else if(s.charAt(i) > t.charAt(j)){
            int index = find(getMin, i, isAvailable);
            if(index >= n){
              out.println(-1);
              return;
            }
            isAvailable[index] = false;
            j++;
            cnt++;
          }else{
           int index = find(getMin, i, isAvailable);
          //  out.println(" => " + index);
           if(index >= n){
             i++;
             j++;
           }else{
             if(s.charAt(index) >= t.charAt(index)){
              isAvailable[index] = false;
              j++;
              cnt++;
             }else{
               i++;
               j++;
             }
           }
          }

          out.println(" === > " + Arrays.toString(getMin) + " " + Arrays.toString(isAvailable) + " " + i + " " + j);
       }
       out.println(cnt);
    }

    public static long leftShift(long a) {
        return (long) Math.pow(2, a);
    }

    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
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
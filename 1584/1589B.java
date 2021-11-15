import java.io.*;
import java.util.*;

public class Solution {
  static PrintWriter out = new PrintWriter((System.out));
  static Kioken sc = new Kioken();

  public static void main(String[] args) {
    int t = 1;
    t = sc.nextInt();
    while (t-- > 0) {
      solve();
    }
    out.close();
  }

  public static int check(int max, int min){
    if (max < 3) {
      // 1,2 , 2,2
      if (min == 1) {
        // out.println(1);
        return 1;
      } else {
        // out.println(2);
        return 2;
      }
    }
    return 0;
  }
  public static void solve() {
    int r = sc.nextInt();
    int c = sc.nextInt();

    int max = Math.max(r, c);
    int min = Math.min(r, c);

    int ans = solve(max, min);
    out.println(ans);
  }

  static int solve(int max, int min) {

    if(check(max, min) > 0){
     return check(max, min);
    }
    
    int q = max % 3;
    int qq = max / 3;

    // out.println(q + " min " + qq);
    if (q > 0) {
      int total = qq*min;
      if (q == 1) {
        if (min == 1) {
          total += 1;
        } else {
          int newMin = Math.min(q, min);
          int newMax = Math.max(q, min);
          total += solve(newMax, newMin);
        }
      } else if (q == 2) {
        int newMin = Math.min(q, min);
        int newMax = Math.max(q, min);
        total += solve(newMax, newMin);
        // total += solve(q, min);
      }
      return total;
      // out.println(total);
    } else {
      int total = qq * min;
      return total;
      // out.println(total);
    }
  }

  public static long leftShift(long a) {
    return (long) Math.pow(2, a);
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
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

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
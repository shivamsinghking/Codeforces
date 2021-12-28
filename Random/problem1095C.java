import java.io.*;
import java.util.*;

import javax.swing.text.html.parser.Entity;

public class Main
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String... args)
    {
      solve();
      out.close();
    }
/**
 * 1. if n is odd , n-- , k-- as 1 is reversed odd number
 * 2. If evenCheck for n is even
 * 3. Checking for bit and count it , 2 4 8 16 
 *                                    1 1 1  1
 * 4. Higher bit can be converted into lower bit by +2 , 8 -> 2 * ( 4 )
 * 5. Reduce higher bits to lower and increase count + 1 every time
 * 6. If only q.size() == 1 , and only '1' is available in Priorityqueue, then it can't be reduced further
 * 7. Queue will contains valid bits -> print it
 */
    public static void evenCheck(int n, int k, HashMap<Integer,Integer> map, boolean isOdd){
      if(n == k && k == 0 && !isOdd){
        out.println("YES");
        out.println("0");
        return ;
      }
      // n == even
      int nn = n;
      int cnt = 0;
      int v = 1;
      // map.put(1, 0);
      while(nn > 0){
        // out.print(" nn " + nn);
        // out.print(" xx" + (nn & 1));
        if((nn & 1) == 1){
          cnt++;
          // out.println(" v - "  + v);
          map.put(v, 1);
        }
        nn = nn >> 1;
        v *= 2;
      }

    if(k < cnt){
      out.println("NO");
      return;
    }

    PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    // key, value
    for(Map.Entry<Integer,Integer> m : map.entrySet()){
       q.add(new int[]{m.getKey(), m.getValue()});
      //  out.print(" -------> " + m.getKey() + " - " + m.getValue() + " " + k + " " + cnt);
    }

    if(cnt == 0 && k > 0){
      out.println("NO");
      return ;
    }

    int val = cnt;
    while(q.size() > 0){
      if(val == k){
       break;
      }

      if(q.size() == 1 && q.peek()[0] == 1){
        out.println("NO");
        return;
      }
    int[] a = q.poll(); // bigger
    int[] u; // smaller
    if(q.peek() != null && q.peek()[0] == (a[0]/2)){
      u = q.poll();
    }else{
      u = new int[]{a[0]/2, 0};
    }

    // out.println(" " + a[0] + " " + u[0] + " " + u[1]);

    a[1]--;
    u[1] += 2;
    if(a[1] != 0){
      q.add(a);
    }
    // out.println(" -- " + a[0] + " " + u[0] + " " + u[1] + " " + a[1]);
    q.add(u);
    val++;
    }

    out.println("YES");
    if(isOdd){
      out.print(1 + " ");
    }

    while(q.size() > 0){
     int[] vv = q.poll();
     while(vv[1]-- > 0){
       out.print(vv[0] + " ");
     }
    }
    return ;
  }
    
    public static void solve()
    {
      int n = sc.nextInt();
      int k = sc.nextInt();
      HashMap<Integer,Integer> map = new HashMap<>(); 
      boolean isOdd = false;
      if(n%2 != 0){
        // odd
        n--;
        k--;
        isOdd = true;
      }

      evenCheck(n, k, map, isOdd);
    }

    public static int lower_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0, e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) <= k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        return Math.abs(s) - 1;
    }

    public static int upper_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0;
        int e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) < k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        if (s == ar.size())
        {
            return -1;
        }
        return s;
    }

    static class Kioken
    {
        // FileInputStream br = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next()
        {
            while (!st.hasMoreTokens())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt()
        {
            return Integer.parseInt(next());
        }

        public long nextLong()
        {
            return Long.parseLong(next());
        }

        public double nextDouble()
        {
            return Double.parseDouble(next());
        }

        public String nextLine()
        {
            try
            {
                return br.readLine();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext()
        {
            String next = null;
            try
            {
                next = br.readLine();
            }
            catch (Exception e)
            {
            }
            if (next == null || next.length() == 0)
            {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }
    }
}
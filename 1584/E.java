import java.io.*;
import java.util.*;

public class Solution
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int t = 1;
      t = sc.nextInt();
      while (t-- > 0)
      {
          solve();
      }
      out.close();
    }

    public static void solve()
    {
      int n = sc.nextInt();
      int[] arr = new int[n];

      for(int i = 0; i < n; i++){
        arr[i]  = sc.nextInt();
      }

      HashMap<Integer,List<Integer>> map = new HashMap<>();
      // len = 2, 3
      // for 2
      // out.println("size " + map.size() + " " + n + " " + Arrays.toString(arr));
      for(int i = 0; i < n-1; i++){
        if(arr[i] == arr[i+1]){
          // this is possible mash
          find(i, i+1, n,  arr, map);
        }
      }
      out.println("ans1 " + map.size());

      for(int i = 0; i < n - 2; i++){
        if(arr[i+1] == 2*arr[i] && arr[i+2] == arr[i]){
          find(i, i+2, n, arr, map);
        }
      }

      int cnt = 0;
      for(Map.Entry<Integer,List<Integer>> m: map.entrySet()){
        cnt += m.getValue().size();
        // out.println(m.getKey() + " " + m.getValue());
      }
      // for()
      out.println("ans " +  cnt);

    }

    static void find(int l, int r, int n, int[] arr, HashMap<Integer,List<Integer>> map){
      // same same match karna hai

      while(l >= 0 && r < n){
        if(arr[l] == arr[r]){
          if(map.containsKey(l)){
            List<Integer> ll = map.get(l);
            if(!ll.contains(r)){
              ll.add(r);
              map.put(l, ll);
            }
          }else{
            List<Integer> ll = new ArrayList<>();
            ll.add(r);
            map.put(l, ll);
          }
          l--;
          r++;
        }else break;
      }
    }
    public static long leftShift(long a){
        return (long)Math.pow(2, a);
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
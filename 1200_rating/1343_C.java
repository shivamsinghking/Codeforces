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

    static int getPos(int index, int n, int[] arr){
        while(index < n && arr[index] < 0){
          index++;
        }
        return index;
    }

    static int getNeg(int index, int n, int[] arr){
      while(index < n && arr[index] > 0){
        index++;
      }
      return index;
    }

    public static void solve()
    {
      int n = sc.nextInt();
      int[] arr = new int[n];

      for(int i = 0; i < n; i++){
        arr[i] = sc.nextInt();
      }


      long sum = 0L;
      for(int i = 0; i < n; i++){
        int posIndex = getPos(i, n, arr);
        int max = Integer.MIN_VALUE;
        if(posIndex >= n) break;
        while(posIndex < n && arr[posIndex] > 0){
          max = Math.max(max, arr[posIndex]);
          posIndex++;
        }
        sum += max; 
        int negIndex = getNeg(posIndex, n, arr);
        int max_neg = Integer.MIN_VALUE;
        if(negIndex >= n) break;
        while(negIndex < n && arr[negIndex] < 0){
          max_neg = Math.max(max_neg, arr[negIndex]);
          negIndex++;
        }
        // out.println(" sum ==> -ve " + max_neg) 
        sum += max_neg;
        i = negIndex - 1;
      }

      int i = 0;
      int maxneg = Integer.MIN_VALUE; 
      boolean neg = false;
      while(i < n && arr[i] < 0){
        maxneg = Math.max(maxneg, arr[i]);
        neg = true;
        i++;
      }

      if(neg){
        sum += maxneg;
      }

      out.println(sum);

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
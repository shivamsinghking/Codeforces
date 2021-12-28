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
         arr[i] = sc.nextInt();
       }

       String ss = sc.nextLine();

       StringBuffer s = new StringBuffer();
       s.append(ss);


       // r +1
       // b -1

       PriorityQueue<Integer> b = new PriorityQueue<>();
       PriorityQueue<Integer> a = new PriorityQueue<>();

       HashMap<Integer,Integer> map = new HashMap<>();
       for(int i = 0; i < n; i++){
         if(arr[i] > n && s.charAt(i) == 'R'){
           out.println("NO");
           return;
         }

         if(arr[i] < 1 && s.charAt(i) == 'B'){
           out.println("NO");
           return;
         }

         if(s.charAt(i) == 'B'){
           b.add(arr[i]);
         }else{
           a.add(arr[i]);
         }
       }


       //   -----------------blue ----- | ---red only---
       //   --blue only---|---------- red -----------------
       // let see the middle one where either blue or red either can be used.
       //  B1_ _ _ B2 _ _ B3
       //  R1    R2 R3 _ _ _
       // Here the observation is that, B1 can't be use after that, but R1 can be used, since it is increasing
       // So we will try to reduce B1 to get the value, 
       // Then again R1 can be used after B2, by can be used till <= B2, so will we reduce B2 first and so on.
       // And when B is finished then red is increased.

       int index = 1;
       while(b.size() > 0){
         int val = b.poll();
         if(val >= index){
           index++;
         }else{
           // this b is getting wasted, but that can't happen, otherwise permutation can't be made
           out.println("NO");
           return;
         }
       }

       while(a.size() > 0){
         int val = a.poll();
         if(index >= val){
           index++;
         }else{
           out.println("NO");
           return;
         }
       }
        
       out.println("YES");
       return;
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
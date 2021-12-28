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

    // true => alice
    

    static int dp(int s1, int s2, PriorityQueue<Integer> even, PriorityQueue<Integer> odd, boolean turn){

      if(odd.size() == 0 && even.size() == 0){
        if(s1 == s2){
         return 0;
        }else if(s1 > s2){
         return 1;
        }else{
          return -1;
        }
      }

      if(turn){
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if(even.size() > 0){
          int val  = even.poll();
          a = dp(s1+val, s2, even, odd, !turn);
          even.add(val);
          if(a == 1){
            return 1;
          }
        }


        if(odd.size() > 0){
          int val1 = odd.poll();
          b = dp(s1, s2, even, odd, !turn);
          odd.add(val1);
          if(b == 1){
            return 1;
          }
        }
        if(a == 0 || b == 0){
          return 0;
        }
        return -1;
      }else{
         
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if(odd.size() > 0){
          int val1 = odd.poll();
          b = dp(s1, s2+val1, even, odd, !turn);
          odd.add(val1);
          if(b == -1){
            return -1;
          }
        }
        if(even.size() > 0){
          int val  = even.poll();
          a = dp(s1, s2, even, odd, !turn);
          even.add(val);
          if(a == -1){
            return -1;
          }
        }

        if(a == 0 || b == 0){
          return 0;
        }

        return 1;
      }
    }
    public static void solve()
    {
      int n = sc.nextInt();
     
      PriorityQueue<Integer> even = new PriorityQueue<>((a, b) -> b - a);
      PriorityQueue<Integer> odd = new PriorityQueue<>((a, b) -> b - a);

      // List<Integer> ll = new ArrayList<>();
      for(int i = 0; i < n; i++){
        int val = sc.nextInt();
        if(val%2 == 0){
          even.add(val);
        }else{
          odd.add(val);
        }
        // ll.add(val);
      }

      // Collections.sort(ll, Collections.reverseOrder());

      long s1 = 0L;
      long s2 = 0L;


      // long ans = 0L;
      // for(int i = 0; i < n; i++){
      //   if(i%2 == 0){
      //     if(ll.get(i)%2 == 0){
      //       ans += ll.get(i);
      //     }
      //   }else{
      //     if(ll.get(i)%2 == 1){
      //       ans -= ll.get(i);
      //     }
      //   }
      // }
      // out.println(even + " " + odd);

      boolean turn = true;
      while(even.size() != 0 && odd.size() != 0){
         if(turn){
           if(s1 >= s2){
             if(s1 + even.peek() > s2 + odd.peek()){
               s1 += even.poll();
             }else{
               odd.poll();
             }
           }else{
             if(s1 + even.peek() >= s2 + odd.peek()){
               s1 += even.poll();
             }else{
              odd.poll();
             }
           }
         }else{
           if(s2 >= s1){
             if(s2+odd.peek() > s1 + even.peek()){
               s2 += odd.poll();
             }else{
               even.poll();
             }
           }else{
             if(s2 + odd.peek() >= s1 + even.peek()){
               s2 += odd.poll();
             }else{
              even.poll();
             }
           }
         }
         turn = !turn;
      }

       while(even.size() > 0){
         if(turn){
           s1 += even.poll();
         }else{
           even.poll();
         }
         turn = !turn;
       }

       while(odd.size() > 0){
         if(!turn){
           s2 += odd.poll();
         }else{
           odd.poll();
         }
         turn = !turn;
       }

       if(s1 > s2){
         out.println("Alice");
       }else if(s1 == s2){
         out.println("Tie");
       }else{
         out.println("Bob");
       }
      // int ans = dp(0, 0, even, odd, true);
      
      // if(ans == 1){
      //   out.println("Alice");
      // }else if(ans == 0){
      //   out.println("Tie");
      // }else{
      //   out.println("Bob");
      // }
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
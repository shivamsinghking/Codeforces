import java.io.*;
import java.util.*;

public class Main
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

    static long getSelectTwo(int n){
      return (long)(n)*((long)(n - 1))/2;
    }

    public static void solve()
    {   
      int a = sc.nextInt();
      int b = sc.nextInt();
      int k = sc.nextInt();
      int[] boys = new int[k];
      int[] girls = new int[k];

      for(int i = 0 ; i < k; i++){
         boys[i] = sc.nextInt();
      }

      for(int i = 0; i < k; i++){
        girls[i] = sc.nextInt();
      }

      HashMap<Integer,Integer> boy = new HashMap<>();
      HashMap<Integer,Integer> girl = new HashMap<>();
   
      for(int i = 0; i < k; i++){
        if(boy.containsKey(boys[i])){
          boy.put(boys[i], boy.get(boys[i])+1);
        }else{
          boy.put(boys[i], 1);
        }


        if(girl.containsKey(girls[i])){
          girl.put(girls[i], girl.get(girls[i])+1);
        }else{
          girl.put(girls[i], 1);
        }
      }


      long total = getSelectTwo(k);

      for(Map.Entry<Integer,Integer> m : boy.entrySet()){
        total -= getSelectTwo(m.getValue());
      }

      for(Map.Entry<Integer,Integer> m : girl.entrySet()){
        total -= getSelectTwo(m.getValue());
      }

      out.println(total <= 0 ? 0 : total);
    }

    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    
    public static void reverse(int[] arr) {
    	Arrays.sort(arr);
    	int n = arr.length;
    	for(int i = 0 ; i < arr.length; i++) {
    		int temp = arr[i];
    		arr[i] = arr[n - 1 - i];
    		arr[n - 1 - i] = temp;
    	}
    	return ;
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
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

    public static void solve()
    {   
     int n = sc.nextInt();
     int[] arr = new int[n];
     HashMap<Integer,Integer> map  = new HashMap<>(); 
     for(int i = 0; i < n; i++){
       arr[i] = sc.nextInt();
       if(map.containsKey(arr[i])){
         map.put(arr[i], map.get(arr[i])+1);
       }else{
         map.put(arr[i], 1);
       }
     }
     Stack<long[]> ll = new Stack<>();
     List<Long> ans = new ArrayList<>();
     // for 0
     boolean minusOne = false;
     if(map.containsKey(0)){
       ans.add((long)map.get(0));
       if(map.get(0) > 1){
         ll.push(new long[]{0, map.get(0) - 1});
       }
     }else{
       ans.add(0L);
       minusOne = true;
     }


     long prevEnergy = 0L;
     for(int i = 1; i <= n; i++){
       if(minusOne){
         ans.add(-1L);
       }else{
         if(map.containsKey(i)){
           long v = map.get(i) + prevEnergy;
           ans.add(v);
           if(map.get(i) > 1){
             ll.push(new long[]{i, map.get(i) - 1});
           }
         }else{
           ans.add(prevEnergy);
           long v = prevEnergy;
           if(ll.isEmpty()){
             // this can't be made.
             minusOne = true;
             continue;
           }
           long[] u = ll.pop();
           v += (i - u[0]);
           u[1]--;
           if(u[1] > 0){
             ll.push(u);
           }
           prevEnergy = v;
         }
       }
     }

     for(long i: ans){
       out.print(i + " ");
     }

     out.println();
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
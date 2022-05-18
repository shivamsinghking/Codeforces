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

    public static void solve() {

    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    public static long leftShift(long a) {
        return (long) Math.pow(2, a);
    }

    // n(log(lon(n)))
    public static boolean[] sieve(int n) {
        boolean isPrime[] = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i * 2; j <= n; j = j + i) {
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        return;
    }

    // NOTE: Used to give prime number b/w 2 - sqrt(high), using simpel seive.
    public static void fillPrime(ArrayList<Integer> chprime, int high) {
        boolean[] ck = new boolean[high + 1];
        Arrays.fill(ck, true);
        ck[1] = false;
        ck[0] = false;

        for (int i = 2; (i * i) <= high; i++) {
            if (ck[i] == true) {
                for (int j = i * i; j <= Math.sqrt(high); j = j + i) {
                    ck[j] = false;
                }
            }
        }
        for (int i = 2; i * i <= high; i++) {
            if (ck[i] == true) {
                // cout<< i<<"\n";
                chprime.add(i);
            }
        }
    }

    // in segmented sieve we check for prime from range [low, high]
    public static List<Integer> segmentedSieve(int low, int high) {
        ArrayList<Integer> chprime = new ArrayList<Integer>();
        fillPrime(chprime, high);
        // chprimes has primes in range [2,sqrt(n)]
        // we take primes from 2 to sqrt[n] because the multiples of all primes below
        // high are marked by these
        // primes in range 2 to sqrt[n] for eg: for number 7 its multiples i.e 14 is
        // marked by 2, 21 is marked by 3,
        // 28 is marked by 4, 35 is marked by 5, 42 is marked 6, so 49 will be first
        // marked by 7 so all number before 49
        // are marked by primes in range [2,sqrt(49)]

        boolean[] prime = new boolean[high - low + 1];
        Arrays.fill(prime, true);
        // here prime[0] indicates whether low is prime or not similarly prime[1]
        // indicates whether low+1 is prime or not
        for (int i : chprime) {
            int lower = (low / i);
            // here lower means the first multiple of prime which is in range [low,high]
            // for eg: 3's first multiple in range [28,40] is 30
            if (lower <= 1) {
                lower = i + i;
            } else if (low % i != 0) {
                lower = (lower * i) + i;
            } else {
                lower = (lower * i);
            }
            for (int j = lower; j <= high; j = j + i) {
                prime[j - low] = false;
            }
        }
        List<Integer> ll = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            if (prime[i - low] == true) {
                ll.add(i);
            }
        }
        return ll;
    }

    
      
    // Calculating SPF (Smallest Prime Factor) for every
    // number till MAXN.
    // Time Complexity : O(nloglogn)
    static int[] findingSpf(int n)
    {
        
        int MAXN = Math.min(n, 100001);
        int spf[] = new int[MAXN];
        spf[1] = 1;
        for (int i=2; i<MAXN; i++)
      
            // marking smallest prime factor for every
            // number to be itself.
            spf[i] = i;
      
        // separately marking spf for every even
        // number as 2
        for (int i=4; i<MAXN; i+=2)
            spf[i] = 2;
      
        for (int i=3; i*i<MAXN; i++)
        {
            // checking if i is prime
            if (spf[i] == i)
            {
                // marking SPF for all numbers divisible by i
                for (int j=i*i; j<MAXN; j+=i)
      
                    // marking spf[j] if it is not
                    // previously marked
                    if (spf[j]==j)
                        spf[j] = i;
            }
        }
        return spf;
    }
      
    // A O(log n) function returning primefactorization
    // by dividing by smallest prime factor at every step

    // HINT: Use findingSpf to find min factor for every no. till MAX = 100001, and pass it here.
     static Vector<Integer> getFactorization(int x, int[] spf)
    {
        int MAX = 100001;
        Vector<Integer> ret = new Vector<>();
        while (x != 1)
        {
            ret.add(spf[x]);
            x = x / spf[x];
        }
        return ret;
    }
    
    static long exp(long base, long exp) {
		if (exp==0) return 1;
		long half=exp(base, exp/2);
		if (exp%2==0) return mul(half, half);
		return mul(half, mul(half, base));
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

    static long MOD = 1000000007;
    static long mul(long x, long y) {long res = x * y; return (res >= MOD ? res % MOD : res);}
    static long power(long x, long y) {if (y < 0) return 1; long res = 1; x %= MOD; while (y!=0) {if ((y & 1)==1)res = mul(res, x); y >>= 1; x = mul(x, x);} return res;}
    static void ruffleSort(int[] a) {int n=a.length;Random r=new Random();for (int i=0; i<a.length; i++) {int oi=r.nextInt(n), temp=a[i];a[i]=a[oi];a[oi]=temp;}Arrays.sort(a);}
    static void reverseSort(int[] arr){List<Integer> list = new ArrayList<>();for (int i=0; i<arr.length; i++){list.add(arr[i]);}Collections.sort(list, Collections.reverseOrder());for (int i = 0; i < arr.length; i++){arr[i] = list.get(i);}}
    static int lowerBound(int[] arr, int x){int n = arr.length, si = 0, ei = n - 1;while(si <= ei){int mid = si + (ei - si)/2;if(arr[mid] == x){if(mid-1 >= 0 && arr[mid-1] == arr[mid]){ei = mid-1;}else{return mid;}}else if(arr[mid] > x){ei = mid - 1; }else{si = mid+1;}}return si; }
    static int upperBound(int[] arr, int x){int n = arr.length, si = 0, ei = n - 1;while(si <= ei){int mid = si + (ei - si)/2;if(arr[mid] == x){if(mid+1 < n && arr[mid+1] == arr[mid]){si = mid+1;}else{return mid + 1;}}else if(arr[mid] > x){ei = mid - 1; }else{si = mid+1;}}return si; }
    static void sort(int[] a) {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}
    static void sort(long[] a){
        ArrayList<Long> l=new ArrayList<>();
		for (long i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }

    static long add(long a, long b) {
		return (a+b)%MOD;
	}
	static long sub(long a, long b) {
		return ((a-b)%MOD+MOD)%MOD;
	}
	static long[] factorials=new long[2_000_001];
	static long[] invFactorials=new long[2_000_001];
	static void precompFacts() {
		factorials[0]=invFactorials[0]=1;
		for (int i=1; i<factorials.length; i++) factorials[i]=mul(factorials[i-1], i);
		invFactorials[factorials.length-1]=exp(factorials[factorials.length-1], MOD-2);
		for (int i=invFactorials.length-2; i>=0; i--)
			invFactorials[i]=mul(invFactorials[i+1], i+1);
	}
	
	static long nCk(int n, int k) {
		return mul(factorials[n], mul(invFactorials[k], invFactorials[n-k]));
	}

    static long ceil(long x, long y){
        if(x%y == 0){
            return x/y;
        }else{
            return (x/y) + 1;
        }
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

    class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findParent(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = findParent(parent[i]);
        }

        void Union(int u, int v) {
            int parent_u = findParent(u);
            int parent_v = findParent(v);
            if (parent_u == parent_v)
                return;

            // small attached to big, since we want to reduce overall size
            if (size[parent_u] < size[parent_v]) {
                parent[parent_u] = parent_v;
                size[parent_v]++;
            } else {
                parent[parent_v] = parent_u;
                size[parent_u]++;
            }
        }
    }

    // SEGMENT-TREE
    static class SegmentTree {
        int[] arr = new int[4 * 100000];
        int[] givenArr;

        // HINT: This can be updated with ques.
        int build(int index, int l, int r) {
            if (l == r) {
                return arr[index] = givenArr[l];
            }
            int mid = (l + r) / 2;
            return arr[index] = build(2 * index + 1, l, mid) + build(2 * index + 2, mid + 1, r);
        }

        SegmentTree(int[] nums) {
            givenArr = nums;
            build(0, 0, nums.length - 1);
        }

        // HINT: This can be updated with ques.
        void update(int index, int l, int r, int diff, int i) {
            if (i >= arr.length) {
                return;
            }
            if (index >= l && index <= r) {
                arr[i] = arr[i] + diff;
            }
            if (index < l || index > r) {
                return;
            }
            int mid = (l + r) / 2;
            update(index, l, mid, diff, 2 * i + 1);
            update(index, mid + 1, r, diff, 2 * i + 2);
            return;
        }

        void update(int index, int val) {
            int diff = val - givenArr[index];
            givenArr[index] = val;
            update(index, 0, givenArr.length - 1, diff, 0);
        }

        int query(int left, int right, int l, int r, int i) {
            // not overlapping
            if (r < left || l > right) {
                return 0;
            }

            // total - overlapping
            if (l >= left && r <= right) {
                return arr[i];
            }

            // partial overlapping
            int mid = (l + r) / 2;
            int le = query(left, right, l, mid, 2 * i + 1);
            int ri = query(left, right, mid + 1, r, 2 * i + 2);
            return le + ri;
        }

        // HINT: for max sum, can be changed according to ques.
        int query(int l, int r) {
            return query(l, r, 0, givenArr.length - 1, 0);
        }
    }

       static class Graph {
         ArrayList<Integer>[] g;
 
        public Graph(int n) {
            g = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }
        }
 
        public void addDirect(int u, int v) {
            g[u].add(v);
        }
 
        public void addIndirect(int u, int v) {
            g[u].add(v);
            g[v].add(u);
        }
 
        public ArrayList<Integer> neighbors(int v) {
            return g[v];
        }
    }

}
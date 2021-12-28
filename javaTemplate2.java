import java.io.*;
import java.util.*;

public class Solution {
    static int M = 1_000_000_007;
    static Random rng = new Random();

    private static int testCase(int n, String a, String b) {
        int ans, ll = 0, uu = 0, lu = 0, ul = 0;
        Queue<int[]> q = new LinkedList<>();
        Set<List<Integer>> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (a.charAt(i) == '0' && b.charAt(i) == '0') {
                uu++;
            } else if (a.charAt(i) == '0' && b.charAt(i) == '1') {
                ul++;
            } else if (a.charAt(i) == '1' && b.charAt(i) == '0') {
                lu++;
            } else {
                ll++;
            }
        }

        ans = Math.min(luTolu(uu, ul, lu, ll), llToll(uu, ul, lu, ll));

        return ans >= M ? -1 : ans;
    }

    private static int luTolu(int uu, int ul, int lu, int ll) {
        if (ul == 0 && lu == 0) {
            return 0;
        } else {
            return lu > 0 ? llToll(lu - 1, ll, uu + 1, ul) + 1 : M;
        }
    }

    private static int llToll(int uu, int ul, int lu, int ll) {
        if (ul == 0 && lu == 0) {
            return 0;
        } else {
            return ll > 0 ? luTolu(lu, ll - 1, uu, ul + 1) + 1 : M;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        //in.nextLine();
        for (int tt = 1; tt <= t; ++tt) {
            int n = in.nextInt();
            String a = in.next(), b = in.next();

            out.println(testCase(n, a, b));
        }

        out.close();
    }

    private static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        boolean hasNext() {
            return st.hasMoreTokens();
        }

        char[] readCharArray(int n) {
            char[] arr = new char[n];
            try {
                br.read(arr);
                br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arr;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    private static void sort(int[] arr) {
        int temp, idx;

        for (int i = arr.length - 1; i > 0; i--) {
            idx = rng.nextInt(i + 1);

            temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }

        Arrays.sort(arr);
    }

    private static void sort(long[] arr) {
        long temp;
        int idx;

        for (int i = arr.length - 1; i > 0; i--) {
            idx = rng.nextInt(i + 1);

            temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }

        Arrays.sort(arr);
    }

    private static <T> void sort(T[] arr) {
        T temp;
        int idx;

        for (int i = arr.length - 1; i > 0; i--) {
            idx = rng.nextInt(i + 1);

            temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }

        Arrays.sort(arr);
    }

    private static <T> void sort(T[] arr, Comparator<? super T> cmp) {
        T temp;
        int idx;

        for (int i = arr.length - 1; i > 0; i--) {
            idx = rng.nextInt(i + 1);

            temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }

        Arrays.sort(arr, cmp);
    }

    private static <T extends Comparable<? super T>> void sort(List<T> list) {
        T temp;
        int idx;

        for (int i = list.size() - 1; i > 0; i--) {
            idx = rng.nextInt(i + 1);

            temp = list.get(i);
            list.set(i, list.get(idx));
            list.set(idx, temp);
        }

        Collections.sort(list);
    }

    private static <T> void sort(List<T> list, Comparator<? super T> cmp) {
        T temp;
        int idx;

        for (int i = list.size() - 1; i > 0; i--) {
            idx = rng.nextInt(i + 1);

            temp = list.get(i);
            list.set(i, list.get(idx));
            list.set(idx, temp);
        }

        Collections.sort(list, cmp);
    }

    class DSU {
        int[] parent, rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            } else {
                int res = find(parent[i]);
                parent[i] = res;

                return res;
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int i, int j) {
            int iParent = find(i), jParent = find(j);

            if (iParent != jParent) {
                if (rank[iParent] > rank[jParent]) {
                    parent[jParent] = iParent;
                } else {
                    parent[iParent] = jParent;

                    if (rank[iParent] == rank[jParent]) {
                        rank[jParent]++;
                    }
                }
            }
        }
    }
}
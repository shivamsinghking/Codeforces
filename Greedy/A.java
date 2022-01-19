import java.util.*;
import java.lang.*;
import java.io.*;
public class A {
 
	public static void main(String[] args) throws IOException {
		
		InputStreamReader r=new InputStreamReader(System.in);    
        BufferedReader br=new BufferedReader(r);  
		
		int t = Integer.parseInt(br.readLine());
		int i,j;

		for(i=0;i<t;i++) {
			
			String s = br.readLine();
			char []ch = s.toCharArray();
			int n = s.length();
			
			
			
			int [] arr = new int[n];
			
			int st = -1;
			
			for(j=0;j<n;j++) {
				if(ch[j]=='?' && st==-1)
					st = j;
				else if(ch[j]!='?' && st!=-1) {
					arr[st] = j-st;
					st = -1;
				}
			}
			if(st!=-1)
				arr[st] = j-st;
			
			
			/* for(j=0;j<n;j++) System.out.print(arr[j]+" "); System.out.println(); */
			 
			
			int c = 0;
			long res = 0;
			for(j=0;j<n;j++) {
				if(ch[j]=='0') {
					if(j-1>=0 && ch[j-1]=='0')
						c=1;
					else
						c++;
					
					res += c;
				}
				else if(ch[j]=='1') {
					if(j-1>=0 && ch[j-1]=='1')
						c=1;
					else
						c++;
					res+=c;
				}
				else {
					for(int k = 0;k<arr[j];k++) {
						c++;
						res+=c;
					}
					

					
					if(j!=0 && j+arr[j]<n) {
						if(arr[j]%2==0 && ch[j-1]==ch[j+arr[j]])
							c=arr[j];
						else if(arr[j]%2!=0 && ch[j-1]!=ch[j+arr[j]])
							c = arr[j];
					}
					j+=arr[j]-1;
				}
				//System.out.println(res+" "+arr[j]+" "+j);
			}

			System.out.println(res);
		}

		
	}
			
	

	
	static long setbitNumber(long n)
    {
 
        
       long k = (int)(Math.log(n) / Math.log(2));
 
        
        return 1 << k;
    }
	
	static void leftrotate(int arr[],int i, int n)
    {
        int t = arr[i];
        int f;
        int j = i+1;
        for(;j<=n;j++) {
        	f = arr[j];
        	arr[j] = t;
        	t = f;
        	
        }
        arr[i] = t;
    }
	
	
	
	static int search(int l, int r,int x, int[]arr) {
		if (r >= l) {
            int mid = l + (r - l) / 2;
  
            
            if (arr[mid] == x)
                return mid;
  
           
            if (arr[mid] > x)
                return search( l, mid - 1, x,arr);
  
            
            return search( mid + 1, r, x,arr);
		}
		return -1;
	}
		
	
		
	static long coeff(int n, int k){    // nCr combination
		    
	    long res = 1;
	 

	        if (k > n - k)
	            k = n - k;
	 
	        // Calculate value of
	        // [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
	        for (int i = 0; i < k; ++i) {
	            res *= (n - i);
	            res /= (i + 1);
	        }
	 
	        return res;
	 }
	
	
	static long modpower(long x, long y, long p)
	  {
	    long res = 1; // Initialize result
	 
	    x = x % p; // Update x if it is more than or
	    // equal to p
	 
	    if (x == 0)
	      return 0; // In case x is divisible by p;
	 
	    while (y > 0)
	    {
	 
	      // If y is odd, multiply x with result
	      if ((y & 1) != 0)
	        res = (res * x) % p;
	 
	      // y must be even now
	      y = y >> 1; // y = y/2
	      x = (x * x) % p;
	    }
	    return res;
	  }
	
	
	static int xor(int n){
	        // If n is a multiple of 4
	        if (n % 4 == 0)
	            return n;
	      
	        // If n%4 gives remainder 1
	        if (n % 4 == 1)
	            return 1;
	      
	        // If n%4 gives remainder 2
	        if (n % 4 == 2)
	            return n + 1;
	      
	     return 0;
	 }
		
		
	static int getsum(int n)        // sum of digits
	   {    
	        int sum = 0;
	          
	        while (n != 0)
	        {
	            sum = sum + n % 10;
	            n = n/10;
	        }
	      
	   return sum;
	}
		
		
		
	static boolean isPrime(int n)			//  check prime
	    {
	 
	        if (n <= 1)
	            return false;
	        else if (n == 2)
	            return true;
	        else if (n % 2 == 0)
	            return false;
	 

	        for (int i = 3; i <= Math.sqrt(n); i += 2)
	        {
	            if (n % i == 0)
	                return false;
	        }
	        return true;
	    }
	
	static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
 
		
	}	
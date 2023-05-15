package march.boj_3020;
import java.util.*;
import java.io.*;

public class boj_3020_junseo{
	public static void main(String[] args) throws IOException{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(bf.readLine());
		
		int N=Integer.parseInt(stk.nextToken());
		int H=Integer.parseInt(stk.nextToken());
		
		int[] up=new int[H+1];
		int[] down=new int[H+1];
		
		int min=Integer.MAX_VALUE;
		int result=0;
		
		for(int i=0;i<N/2;i++) {
			down[Integer.parseInt(bf.readLine())]++;
			up[Integer.parseInt(bf.readLine())]++;
		}
		
		int[] sumup=new int[H+1];
		int[] sumdown=new int[H+1];
		
		for(int i=1;i<=H;i++) {
			sumup[i]=sumup[i-1]+up[i];
			sumdown[i]=sumdown[i-1]+down[i];
		}
		
		for(int i=1;i<=H;i++) {
			int block=0;
			block+=sumdown[H]-sumdown[i-1];
			block+=sumup[H]-sumup[H-i];
			
			if(min>block) {
				min=block;
				result=1;
			}else if(min==block) result++;
		}
		System.out.println(min+" "+result);
	}
}
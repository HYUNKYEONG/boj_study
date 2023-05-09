package may.boj_11660;

import java.util.*;
import java.io.*;

public class boj_11660{
	public static void main(String[] args) throws IOException{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(bf.readLine());
		int N=Integer.parseInt(stk.nextToken());
		int M=Integer.parseInt(stk.nextToken());
		
		int A[][]=new int[N+1][N+1];
		for(int i=1;i<N+1;i++) {
			stk=new StringTokenizer(bf.readLine());
			for(int j=1;j<N+1;j++) {
				A[i][j]=Integer.parseInt(stk.nextToken());
			}
		}
		
		int D[][]=new int[N+1][N+1];
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				D[i][j]=D[i-1][j]+D[i][j-1]-D[i-1][j-1]+A[i][j];
			}
		}
		
		for(int i=0;i<M;i++) {
			stk=new StringTokenizer(bf.readLine());
			int x1=Integer.parseInt(stk.nextToken());
			int y1=Integer.parseInt(stk.nextToken());
			int x2=Integer.parseInt(stk.nextToken());
			int y2=Integer.parseInt(stk.nextToken());
			
			int result=D[x2][y2]-D[x1-1][y2]-D[x2][y1-1]+D[x1-1][y1-1];
			System.out.println(result);
		}
	}
}

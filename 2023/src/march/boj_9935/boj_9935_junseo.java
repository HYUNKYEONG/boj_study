package march.boj_9935;

import java.util.*;
import java.io.*;

public class boj_9935_junseo{
	public static void main(String[] args) throws IOException{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		
		String str=bf.readLine();
		String target=bf.readLine();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i);
			sb.append(ch);
			
			if(sb.length()>=target.length()) {
				boolean isSame=true;
				for(int j=0;j<target.length();j++) {
					char ch1=sb.charAt(sb.length()-target.length()+j);
					char ch2=target.charAt(j);
					if(ch1!=ch2) {
						isSame=false;
						break;
					}
				}
				if(isSame) sb.delete(sb.length()-target.length(), sb.length());
			}
		}
		
		System.out.print((sb.length()==0)?"FRULA":sb.toString());
	}
}
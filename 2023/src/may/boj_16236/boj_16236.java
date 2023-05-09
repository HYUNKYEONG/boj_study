package may.boj_16236;
import java.util.*;
import java.io.*;

public class boj_16236{
	static class Fish{
		int x,y,time;
		public Fish(int x,int y,int time) {
			super();
			this.x=x;
			this.y=y;
			this.time=time;
		}
	}
	
	static int N;
	static int map[][];
	static boolean visited[][];
	
	static int dx[]= {0,1,0,-1};
	static int dy[]= {1,0,-1,0};
	
	static Fish shark;
	static int size=2;
	static int eatFish=0;

	static int answer=0;
	static List<Fish>fishList=new ArrayList<>();
	
	public static void main(String[] args)throws IOException{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(bf.readLine());
		
		map=new int[N][N];
		visited=new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer stk=new StringTokenizer(bf.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(stk.nextToken());
				
				if(map[i][j]==9) {
					shark=new Fish(i,j,0);
					map[i][j]=0;
				}
			}
		}
		
		BFS();
	
		System.out.println(answer);
	}
	
	public static void BFS() {
		Queue<Fish>q=new ArrayDeque<>();
		q.add(shark);
		visited[shark.x][shark.y]=true;
		
		while(true) {
			while(!q.isEmpty()) {
				Fish now=q.poll();
				int time=now.time;
				
				for(int i=0;i<4;i++) {
					int nx=now.x + dx[i];
					int ny=now.y + dy[i];
					
					if(!(0<=nx && nx<N && 0<=ny && ny<N)||visited[nx][ny]) continue;
					
					// 식사 가능 -> 먹이 리스트에 추가
					if(map[nx][ny]<size && map[nx][ny]!=0) {
						q.add(new Fish(nx,ny,time+1));
						visited[nx][ny]=true;
						fishList.add(new Fish(nx,ny,time+1));
					}
					
					// 이동 가능
					if(map[nx][ny]==size || map[nx][ny]==0) {
						q.add(new Fish(nx,ny,time+1));
						visited[nx][ny]=true;
					}
				}
			}
			
			if(!fishList.isEmpty()) {
				eat();
				
				q.clear();
				visited=new boolean[N][N];
				
				q.add(shark);
				visited[shark.x][shark.y]=true;
			}else {
				return;
			}
		}
	}
	
	public static void eat() {
		Collections.sort(fishList,new Comparator<Fish>(){
			@Override
			public int compare(Fish o1,Fish o2) {
				if(o1.time == o2.time) {
					if(o1.x == o2.x) {
						if(o1.y > o2.y) return 1;
						else return -1;
					}else {
						if(o1.x > o2.x) return 1;
						else return -1;
					}
				}else if(o1.time > o2.time) return 1;
				else return -1;
			}
		});
		
		Fish now=fishList.get(0);
		
		shark.x = now.x;
		shark.y = now.y;
		
		if(++eatFish==size) {
			size++;
			eatFish=0;
		}
		
		answer+=now.time;
		
		map[now.x][now.y]=0;
		
		fishList.clear();
	}
}

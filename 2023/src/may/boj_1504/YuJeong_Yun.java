package may.boj_1504;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int vex, cost;

    public Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) { // 가중치 순으로 오름차순
        return this.cost - e.cost;
    }
}

public class YuJeong_Yun {
    static final int INF = 200000000; // 간선:200,000 * 가중치:1,000
    static int N, E, v1, v2;
    static List<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
    static int[] dis; // 정점에서부터 거리 담을 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c)); // 무방향 그래프라 a,b 시작점으로 각각 추가
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점
        v2 = Integer.parseInt(st.nextToken());

        // 1 - v1 - v2 - N
        // 1 - v2 - v1 - N
        int v1_1 = dijkstra(1, v1);
        int v2_1 = dijkstra(1, v2);
        int v1_N = dijkstra(N, v1);
        int v2_N = dijkstra(N, v2);
        int v1_v2 = dijkstra(v1, v2);

        int result1 = v1_1 + v1_v2 + v2_N; // 1-v1-v2-N
        int result2 = v2_1 + v1_v2 + v1_N; // 1-v2-v1-N
        int answer = 0;
        if (result1 >= INF && result2 >= INF) {
            answer = -1;
        } else {
            answer = Math.min(result1, result2);
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(start, 0));

        dis = new int[N + 1]; // 시작점부터 정점까지 최단 거리 담을 배열
        Arrays.fill(dis, INF); // 최댓값으로 초기화
        dis[start] = 0;

        while (!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            for (Edge e : graph.get(tmp.vex)) {
                if (dis[e.vex] > tmp.cost + e.cost) { // 가중치 합이 현재 담긴 dis 보다 더 작으면 업데이트
                    dis[e.vex] = tmp.cost + e.cost;
                    pQ.offer(new Edge(e.vex, tmp.cost + e.cost)); // tmp.cost+e.cost : 시작 정점부터 해당 정점까지 총 가중치
                }
            }
        }

        return dis[end]; // end로 가는 길이 없으면 INF 값 return
    }

}
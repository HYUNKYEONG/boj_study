package may.boj_1753;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int v, w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge e) { // 가중치 순으로 오름차순
        return this.w - e.w;
    }
}

public class YuJeongYun {
    static List<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>(); // 하나의 정점에서 Edge(도착점, 가중치) 담고 있는 ArrayList
    static int V, E; // 정점 개수, 간선 개수
    static int[] dis; // 시작 정점 부터 거리 담을 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        dis = new int[V + 1];

        for (int i = 0; i <= V; i++) { // 그래프에 각 정점마다 ArrayList 추가
            graph.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, w)); // u 정점의 ArrayList에 Edge(도착정점, 가중치) 추가
        }

        Arrays.fill(dis, Integer.MAX_VALUE); // 최대값으로 초기화
        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                bw.write("INF\n");
            } else {
                bw.write(dis[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra(int K) {
        PriorityQueue<Edge> pQ = new PriorityQueue<>(); // Edge 넣으면 가중치 순으로 오름차순 정렬
        pQ.offer(new Edge(K, 0));

        while (!pQ.isEmpty()) {
            Edge tmp = pQ.poll();
            if (dis[tmp.v] == Integer.MAX_VALUE) { // dis배열의 값이 Integer.MAX_VALUE이면 방문 안 한 상태
                dis[tmp.v] = tmp.w; // w = 시작 지점부터 v 정점까지 총 가중치 합

                for (Edge e : graph.get(tmp.v)) { // 방문 정점의 edge 가져와서
                    if (dis[e.v] == Integer.MAX_VALUE) { // 도착 정점에 방문 안 한 상태이면 pQ에 추가
                        pQ.offer(new Edge(e.v, tmp.w + e.w)); // w값은 시작 정점부터 도착 정점까지 가중치 합
                    }
                }
            }
        }


    }

}
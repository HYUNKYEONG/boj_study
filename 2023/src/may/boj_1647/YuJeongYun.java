package may.boj_1647;

import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int v1, v2, cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    } // 가중치 순 오름차순
}

public class YuJeongYun {
    static int N, M;
    static int answer = 0;
    static int[] unf;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집의 개수
        M = Integer.parseInt(st.nextToken()); // 길의 개수

        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list.add(new Edge(A, B, C));
        }
        Collections.sort(list);

        unf = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            unf[i] = i;
        }
        List<Edge> edgeList = new ArrayList<>(); // 선택된 간선 담을 리스트
        for (Edge e : list) {
            if (find(e.v1) != find(e.v2)) {
                union(e.v1, e.v2);
                edgeList.add(e);
                answer += e.cost;
            }
        }

        Collections.sort(edgeList);
        answer -= edgeList.get(edgeList.size() - 1).cost; // 두 집단 나누면 그 사이 간선은 필요없으므로 가장 큰 가중치 값 빼줌

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int a) {
        if (unf[a] == a) return unf[a];
        return unf[a] = find(unf[a]);
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            unf[fa] = fb;
        }

    }

}
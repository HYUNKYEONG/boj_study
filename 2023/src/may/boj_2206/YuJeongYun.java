package may.boj_2206;

import java.io.*;
import java.util.*;

class Point {
    int x, y, dist, drill; // dist : 현재 위치까지 이동 거리, drill : 벽 뚫었는지 여부

    public Point(int x, int y, int dist, int drill) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.drill = drill;
    }
}

public class YuJeongYun {
    static int N, M;
    static int[][] board;
    static boolean[][][] ch; // 방문 체크 배열 - 그냥 왔는지:0 / 벽 뚫고 왔는지:1
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ch = new boolean[2][N][M];
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        int answer = BFS(0, 0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int x, int y) {
        Queue<Point> que = new ArrayDeque<>();
        que.offer(new Point(x, y, 1, 0));

        while (!que.isEmpty()) {
            Point tmp = que.poll();
            if (tmp.x == N - 1 && tmp.y == M - 1) {
                return tmp.dist;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = tmp.x + dx[i], ny = tmp.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (board[nx][ny] == 0 && !ch[tmp.drill][nx][ny]) { // 다음 칸이 길이고 && 벽 뚫은 여부에 따라 방문 여부 확인
                        que.offer(new Point(nx, ny, tmp.dist + 1, tmp.drill)); // 거리만+1 해서 큐에 추가
                        ch[tmp.drill][nx][ny] = true; // 방문 체크
                    } else { // 다음 칸이 벽일 경우
                        if (tmp.drill == 0 && !ch[1][nx][ny]) { // tmp 위치까지 벽 뚫은 적 없고 && 현재 위치까지 벽 뚫고 온 적 없으면
                            que.offer(new Point(nx, ny, tmp.dist + 1, 1));
                            ch[1][nx][ny] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

}
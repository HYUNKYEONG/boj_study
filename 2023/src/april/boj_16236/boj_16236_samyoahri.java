package april.boj_16236;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Mat{
    int i;
    int j;
    int cnt;

    public Mat(int i, int j, int cnt) {
        this.i = i;
        this.j = j;
        this.cnt = cnt;
    }
}
public class boj_16236_samyoahri {
    static int babySharkSize = 2;
    static boolean[][] visited;
    static int[][] matrix;
    static int N;
    static int sharkI;
    static int sharkJ;
    static int time;
    static int cntFish;
    static int leftFish;
    static void bfs(int i, int j) {
        visited = new boolean[N][N];

        Queue<Mat> queue = new ArrayDeque<>();
        queue.offer(new Mat(i, j, 0));
        visited[i][j] = true;

        int nextI = -1, nextJ = -1, nextCnt = Integer.MAX_VALUE;
        int[] di = {1, 0, -1, 0};
        int[] dj = {0, -1, 0, 1};
        while (!queue.isEmpty()) {
            Mat now = queue.poll();
            if(nextCnt<now.cnt){
                ++nextCnt;
                break;
            }

            for (int idx = 0; idx < 4; ++idx) {
                int ni = now.i + di[idx];
                int nj = now.j + dj[idx];
                if (ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if (!visited[ni][nj] && matrix[ni][nj] <= babySharkSize) {
                    if (matrix[ni][nj] > 0 && babySharkSize > matrix[ni][nj]) {
                        if (nextI == -1) {
                            nextI = ni;
                            nextJ = nj;
                            nextCnt = now.cnt;
                        }
                        //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
                        else{
                            //다음 먹을 물고기보다 현재 물고기가 더 위쪽에 위치 -> 다음 먹을 물고기 변경
                            if (ni < nextI) {
                                nextI = ni;
                                nextJ = nj;
                            }
                            //다음 먹을 물고기보다 현재 물고기보다 더 왼쪽에 위치
                            else if (nextI == ni) {
                                if (nj < nextJ) {
                                    nextI = ni;
                                    nextJ = nj;
                                }
                            }
                        }
                    }
                    visited[ni][nj] = true;
                    queue.offer(new Mat(ni, nj, now.cnt + 1));
                }
            }
        }
        if (nextI != -1) {
            matrix[nextI][nextJ] = 0;
            time += nextCnt;
            --leftFish;
            sharkI = nextI;
            sharkJ = nextJ;
            ++cntFish;
            if (cntFish == babySharkSize) {
                cntFish = 0;
                ++babySharkSize;
            }
        }

    }

    static boolean isPossibleToEat() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] < babySharkSize) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        visited = new boolean[N][N];
        matrix = new int[N][N];
        leftFish = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 9) {
                    sharkI = i;
                    sharkJ = j;
                    matrix[i][j] = 0;
                } else if (matrix[i][j] > 0) {
                    ++leftFish;
                }
            }
        }
        while (leftFish != 0) {
            int temp = leftFish;
            bfs(sharkI, sharkJ);
            if(temp==leftFish)break;
        }

//        for (int[] arr : matrix) {
//            for (int integer : arr) {
//                System.out.print(integer + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("sharkI = " + sharkI);
//        System.out.println("sharkJ = " + sharkJ);
//        System.out.println("babySharkSize = " + babySharkSize);
//        System.out.println("cntFish = " + cntFish);

        ans.append(time);
        out.write(ans.toString());
        out.flush();
        in.close();
        out.close();
    }
}

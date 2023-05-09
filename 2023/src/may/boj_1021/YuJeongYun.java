package may.boj_1021;

import java.io.*;
import java.util.*;

public class YuJeongYun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            que.offer(i);
        }

        int answer = 0;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken()); // 꺼내야할 수

            int cnt = 0; // 왼쪽으로 이동 횟수 담을 변수
            while (que.peek() != num) { // 꺼내야할 수 제일 앞에 올 때 까지 왼쪽으로 이동
                que.offer(que.poll());
                cnt++; // 이동 횟수 증가
            }

            answer += Math.min(cnt, que.size() - cnt); // 오른쪽 이동 시 이동 횟수 = que.size()-cnt
            que.poll();
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
package may.boj_4716;

import java.io.*;
import java.util.*;

class Balloon implements Comparable<Balloon> {
    int team, dist, diff; // team:팀 번호, dist:A or B에 따른 거리, diff: 팀에서 A와 B방 거리 차이
    char place; // A,B

    public Balloon(int team, int dist, int diff, char place) {
        this.team = team;
        this.dist = dist;
        this.diff = diff;
        this.place = place;
    }

    @Override
    public int compareTo(Balloon b) { // 거리 차이 내림차순으로 정렬
        if (b.diff == this.diff) {
            return this.dist - b.dist;
        }
        return b.diff - this.diff;
    }

}

public class YuJeongYun {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        while (!s.equals("0 0 0")) {
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken()); // 팀의 수
            int A = Integer.parseInt(st.nextToken()); // A에 있는 풍선 수
            int B = Integer.parseInt(st.nextToken()); // B에 있는 풍선 수

            PriorityQueue<Balloon> pQ = new PriorityQueue<>();
            int[] team = new int[N]; // 각 팀에 달아줘야 하는 풍선 수 담은 배열
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken()); // 달아줘야 하는 풍선 수
                int DA = Integer.parseInt(st.nextToken()); // A로부터의 거리
                int DB = Integer.parseInt(st.nextToken()); // B로부터의 거리
                team[i] = K;

                for (int j = 0; j < K; j++) {
                    int diff = Math.abs(DA - DB);
                    pQ.offer(new Balloon(i, DA, diff, 'A')); // A로부터 가져오는 경우
                    pQ.offer(new Balloon(i, DB, diff, 'B')); // B로부터 가져오는 경우
                }
            }

            int answer = 0;
            while (!pQ.isEmpty()) {
                Balloon bal = pQ.poll();
                if (team[bal.team] == 0) { // 해당 팀 풍선 다 달아준 경우
                    continue;
                }

                if (bal.place == 'A') {
                    if (A != 0) { // A에 풍선 남아있으면
                        A--;
                        answer += bal.dist;
                        team[bal.team]--;
                    }
                } else {
                    if (B != 0) { // A에 풍선 남아있으면
                        B--;
                        answer += bal.dist;
                        team[bal.team]--;
                    }
                }
            }

            sb.append(answer + "\n");
            s = br.readLine();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
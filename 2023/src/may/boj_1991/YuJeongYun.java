package may.boj_1991;

import java.io.*;
import java.util.*;

class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Node findNode(Node node, char c) { // 해당 문자 가진 노드 찾는 함수
        if (root == null) {
            return null;
        }
        if (node.word == c) {
            return node;
        }

        Node find = null;
        if (node.left != null) { // 왼쪽 자식 있으면 왼쪽 서브 트리 탐색해서 찾음
            find = findNode(node.left, c);
        }
        if (find == null && node.right != null) { // 왼쪽 탐색 결과 null이고, 오른쪽 자식 있으면 오른쪽 서브 트리 탐색
            find = findNode(node.right, c);
        }
        return find;
    }

    public void add(char p, char l, char r) { // 자식 노드 추가하는 함수
        Node parent = findNode(root, p) == null ? new Node(p) : findNode(root, p); // p 문자 가진 노드 없으면 새로 생성
        if (l != '.') {
            parent.left = new Node(l);
        }
        if (r != '.') {
            parent.right = new Node(r);
        }
    }

    public void preOrder(Node node) { // 전위 순회 함수
        if (node == null) {
            return;
        }

        System.out.print(node.word);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) { // 중위 순회 함수
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.word);
        inOrder(node.right);
    }

    public void postOrder(Node node) { // 후위 순회 함수
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.word);
    }
}

class Node {
    char word;
    Node left, right;

    public Node(char word) {
        this.word = word;
    }
}

public class YuJeongYun {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Node root = new Node('A');
        Tree tree = new Tree(root); // 루트 노드로 'A' 문자열 가진 트리 생성

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);

            tree.add(c, l, r);
        }

        tree.preOrder(root);
        System.out.println();
        tree.inOrder(root);
        System.out.println();
        tree.postOrder(root);

        br.close();
    }

}
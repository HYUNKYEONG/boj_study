# 재귀 할 때마다 탐색을 한번 더 함
# 함수 안의 ~~order(tree[root][0]) 왼쪽 끝까지 탐색 
# 함수 안의 ~~order(tree[root][1]) 오른쪽 끝까지 탐색 


import sys
 
N = int(sys.stdin.readline().strip())
tree = {}
 
for n in range(N):
    root, left, right = sys.stdin.readline().strip().split()
    tree[root] = [left, right]
 
 
def preorder(root): # 전위순회 (루트-왼-오)
    if root != '.':
        print(root, end='')  # 루트
        preorder(tree[root][0])  # 왼쪽
        preorder(tree[root][1])  # 오른쪽
 
 
def inorder(root): # 중위순회 (왼-루트-오)
    if root != '.':
        inorder(tree[root][0])  # 왼쪽
        print(root, end='')  # 루트
        inorder(tree[root][1])  # 오른쪽
 
 
def postorder(root): # 후위순회 (왼-오-루트)
    if root != '.':
        postorder(tree[root][0])  # 왼쪽
        postorder(tree[root][1])  # 오른쪽
        print(root, end='')  # 루트
 
 
preorder('B')
print()
inorder('B')
print()
postorder('B')
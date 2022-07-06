shell: int = 0
biggestInShell: int = 1

room: int = int(input())

while(room > biggestInShell + 6 * (shell)):
    biggestInShell += 6 * shell
    shell += 1

print(shell + 1)
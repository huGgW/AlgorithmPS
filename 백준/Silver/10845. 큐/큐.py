import sys

queue = []

n = int(sys.stdin.readline())
for _ in range(n):
    cmd = sys.stdin.readline().split()
    if cmd[0] == "push":
        queue.append(int(cmd[1].strip()))
    elif cmd[0] == "pop":
        sys.stdout.write(str(queue.pop(0)) if len(queue) != 0 else "-1")
        sys.stdout.write("\n")
    elif cmd[0] == "size":
        sys.stdout.write(str(len(queue)))
        sys.stdout.write("\n")
    elif cmd[0] == "empty":
        sys.stdout.write("1" if len(queue) == 0 else "0")
        sys.stdout.write("\n")
    elif cmd[0] == "front":
        sys.stdout.write(str(queue[0]) if len(queue) != 0 else "-1")
        sys.stdout.write("\n")
    elif cmd[0] == "back":
        sys.stdout.write(str(queue[-1]) if len(queue) != 0 else "-1")
        sys.stdout.write("\n")

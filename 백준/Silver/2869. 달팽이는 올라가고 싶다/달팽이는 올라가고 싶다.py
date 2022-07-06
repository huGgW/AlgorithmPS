from math import ceil

s = input()
sL = s.split()
A = int(sL[0])
B = int(sL[1])
V = int(sL[2])

V_exceptLast = V - A
if (V_exceptLast > 0):
    time_exceptLast = ceil(V_exceptLast / (A - B))
    print(time_exceptLast + 1)
else:
    print(1)
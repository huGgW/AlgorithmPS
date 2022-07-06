alphabets = "abcdefghijklmnopqrstuvwxyz"
inputString = input()
posList = [-1 for _ in range(len(alphabets))]

for i in range(len(alphabets)):
    posList[i] = inputString.find(alphabets[i])

for n in posList:
    print(n, end=" ")
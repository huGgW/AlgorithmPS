s = input()

alphabets = {}
alphabets['A'] = 0
alphabets['B'] = 0
alphabets['C'] = 0
alphabets['D'] = 0
alphabets['E'] = 0
alphabets['F'] = 0
alphabets['G'] = 0
alphabets['H'] = 0
alphabets['I'] = 0
alphabets['J'] = 0
alphabets['K'] = 0
alphabets['L'] = 0
alphabets['M'] = 0
alphabets['N'] = 0
alphabets['O'] = 0
alphabets['P'] = 0
alphabets['Q'] = 0
alphabets['R'] = 0
alphabets['S'] = 0
alphabets['T'] = 0
alphabets['U'] = 0
alphabets['V'] = 0
alphabets['W'] = 0
alphabets['X'] = 0
alphabets['Y'] = 0
alphabets['Z'] = 0

for c in s:
    alphabets[c.upper()] += 1

isMultipleMax = False
max = (None, -1)
for (k, v) in alphabets.items():
    if (v > max[1]):
        max = (k, v)
        isMultipleMax = False
    elif (v == max[1]):
        isMultipleMax = True

if (isMultipleMax):
    print("?")
else:
    print(max[0])
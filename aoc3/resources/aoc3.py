import re

pattern = r"mul\(\d{1,3},\d{1,3}\)|do\(\)|don't\(\)"
matches = re.findall(pattern, open("input.txt").read())

res = 0
flag = True
for match in matches:
    if match == "do()":
        flag = True
    elif match == "don't()":
        flag = False
    else:
        if flag:
            x, y = map(int, match[4:-1].split(","))
            res += x * y
print(res)

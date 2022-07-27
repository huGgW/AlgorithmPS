import sys
from enum import Enum

class Color(Enum):
    NONE = -1
    RED = 0
    GREEN = 1
    BLUE = 2

class House:
    def __init__(self, rp: int, gp: int, bp: int):
        self.red_price: int = rp
        self.green_price: int = gp
        self.blue_price: int = bp
    def getPrice(self, c: int) -> int:
        if c == Color.RED.value:
            return self.red_price
        elif c == Color.GREEN.value:
            return self.green_price
        elif c == Color.BLUE.value:
            return self.blue_price
        else:
            raise Exception

# initialize
n: int = int(sys.stdin.readline())
houses: list[House] = []
for i in range(n):
    houses.append(
        (lambda l : House(int(l[0]), int(l[1]), int(l[2]))) \
            (sys.stdin.readline().split(" "))
    )

# DP substructure
min_prices: list[list[int]] = [[] for _ in range(n)]
# for house 1
min_prices[0] = [houses[0].red_price, houses[0].green_price, houses[0].blue_price,]
# else
for i in range(1, n):
    for c in range(3):
            # v(i, c) = min(v(i-1, colors except c)) + price(i, c)
        min_prices[i].append(
            min(
                map(
                    lambda j : min_prices[i-1][j],
                    filter(
                        lambda x : x != c,
                        range(3)
                    )
                )
            )
            +
            houses[i].getPrice(c)
        )

print(min(min_prices[n-1]))
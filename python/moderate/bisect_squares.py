class Point:
    def __init__(self, x: float, y: float):
        self.x = x
        self.y = y

    def __str__(self):
        return f"Point({self.x}, {self.y})"

    def __sub__(self, other):
        return ((other.y - self.y) ** 2 + (other.x - self.x) ** 2) ** 0.5


class Square:
    def __init__(self, p1: Point, p2: Point):
        assert (abs(p1 - Point(p2.x, p1.y)) == abs(p1 - Point(p1.x, p2.y)))
        self.p1 = p1
        self.p2 = p2

    def mid(self) -> Point:
        x = self.p1.x + (self.p2.x - self.p1.x) / 2
        y = self.p1.y + (self.p2.y - self.p1.y) / 2
        return Point(x, y)


class Line:
    def __init__(self, p1: Point, p2: Point):
        self.p1 = p1
        self.p2 = p2

    def slope(self) -> [float, None]:
        if self.p2.x == self.p1.x:
            return None
        return (self.p2.y - self.p1.x) / (self.p2.x - self.p1.x)

    def intercept(self):
        s = self.slope()
        if s:
            return self.p2.y - self.slope() * self.p2.x
        else:
            return 0


if __name__ == '__main__':
    square1 = Square(Point(5, 20), Point(10, 15))
    square2 = Square(Point(5, 5), Point(10, 0))

    # The line that bisects two squares into half, should pass through their center
    point1: Point = square1.mid()
    point2: Point = square2.mid()

    line = Line(point1, point2)
    print('midpoints', point1, point2)
    slope = line.slope()
    intercept = line.intercept()

    if slope:
        print(f"y = {slope}x + {intercept}")
        print(square1.p1.x * slope + intercept)
        print(square1.p1.y)
        assert point1.x * slope + intercept == point1.y and point2.x * slope + intercept == point2.y
    else:
        print(f"x= {point1.x}")

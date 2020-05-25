class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __repr__(self):
        return f"Point({self.x}, {self.y})"


class Line:
    def __init__(self, start, end):
        self.start = start
        self.end = end

    def slope(self):
        return (self.end.y - self.start.y) / (self.end.x - self.start.x)

    def eqn(self):
        print(f"y = {self.slope()}x + {self.intercept()}")

    def intercept(self):
        return self.end.y - (self.slope() * self.end.x)

    def contains_point(self, point):
        # substituting value of x & y from point into line must satisy equation y=mx+c
        is_satisfy_equation = point.y == self.slope() * point.x + self.intercept()
        # then check if end point is greater than the point given
        return is_satisfy_equation and (self.end.y >= point.y) and (self.end.x >= point.x)

    def __repr__(self):
        return f"Line({self.start}, {self.end})"


def swap_points(p1, p2):
    (tempx, tempy) = p2.x, p2.y
    (p2.x, p2.y) = (p1.x, p1.y)
    (p1.x, p1.y) = (tempx, tempy)


def print_intersection(point):
    print(f"********** The interception is {point} ****+***")


def find_intersection(line1, line2):
    # if slope are equal and intercept are not, they are parallel lines so no intersections
    if line1.slope() == line2.slope():
        # if point lies inside line, then intersection
        if line1.intercept() == line2.intercept() and line1.contains_point(line2.start):
            print_intersection(line2.start)
            return
        else:
            raise Exception("No intersection")

    # Point of intersection is where x1=x2 and y1=y2
    x_intersection = (line2.intercept() - line1.intercept()) / (line1.slope() - line2.slope())
    y_intersection = line1.slope() * x_intersection + line1.intercept()
    point_of_intersection = Point(x_intersection, y_intersection)
    if line1.contains_point(point_of_intersection):
        print_intersection(point_of_intersection)
        return
    raise Exception("No intersection")


if __name__ == '__main__':
    line1 = Line(Point(0, 0), Point(5, 5))
    line2 = Line(Point(3, 0), Point(0, 3))
    line1.eqn()
    line2.eqn()

    if line1.start.x > line1.end.x:
        swap_points(line1.start, line1.end)
    if line2.start.x > line2.end.x:
        swap_points(line2.start, line2.end)
    if line1.start.x > line2.start.y:
        swap_points(line1.start, line2.start)
        swap_points(line1.end, line2.end)

    print(line1)
    print(line2)

    find_intersection(line1, line2)

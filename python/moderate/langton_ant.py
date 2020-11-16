"""
This is incomplete
"""
import enum
from random import Random
from typing import Set, Dict


class Orientation(enum.Enum):
    Left = "Left"
    Right = "Right"
    Up = "Up"
    Down = "Down"


class Color(enum.Enum):
    White = "white"
    Black = "black"


class Ant:
    def __init__(self):
        self.position = Position(0, 0)
        self.orientation = Orientation.Right
        self.movement = []

    def turn_clockwise(self):
        if self.orientation == Orientation.Right:
            self.orientation = Orientation.Down
        elif self.orientation == Orientation.Down:
            self.orientation = Orientation.Left
        elif self.orientation == Orientation.Up:
            self.orientation = Orientation.Right
        elif self.orientation == Orientation.Left:
            self.orientation = Orientation.Up

    def turn_ccw(self):
        if self.orientation == Orientation.Right:
            self.orientation = Orientation.Up
        elif self.orientation == Orientation.Down:
            self.orientation = Orientation.Right
        elif self.orientation == Orientation.Up:
            self.orientation = Orientation.Left
        elif self.orientation == Orientation.Left:
            self.orientation = Orientation.Down

    def forward(self):
        self.movement.append(self.orientation)
        if self.orientation == Orientation.Right:
            self.position.x += 1
        elif self.orientation == Orientation.Down:
            self.position.y -= 1
        elif self.orientation == Orientation.Up:
            self.position.y += 1
        elif self.orientation == Orientation.Left:
            self.position.x -= 1


class Position:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y


class Cell:
    def __init__(self, row, col, color):
        self.position = Position(row, col)
        self.color = color

    @classmethod
    def create(cls, position, color):
        return cls(position.x, position.y, color)


class Board:
    def __init__(self):
        self.ant: Ant = Ant()
        self.grid: Set[Cell] = set()

        self.cell_map: Dict[str, Cell] = {}

        random = Random()

        for row in range(0, 4):
            for col in range(0, 4):
                cell = Cell(row, col, Color.Black if random.randint(0, 1) == 0 else Color.White)
                self.grid.add(cell)
                self.cell_map[str(row) + str(col)] = cell

    def __expand(self, position):
        pass

    def _get_cell_at(self, position: Position) -> Cell:
        key = str(position.x) + str(position.y)
        return self.cell_map[key]

    def flip(self, color: Color):
        self.grid.add(
            Cell.create(
                self.ant.position,
                Color.Black if color == Color.White else Color.White
            )
        )

    def move(self):
        color = self._get_cell_at(self.ant.position).color
        self.flip(color)
        if color == Color.White:
            self.ant.turn_clockwise()
        else:
            self.ant.turn_ccw()

        self.ant.forward()

    def print(self):
        print(self.ant.movement)


if __name__ == "__main__":
    board = Board()
    for i in range(0, 10):
        board.move()

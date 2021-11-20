import dataclasses
import enum
from random import Random
from typing import Set, Dict, List


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
        self.position: Position = Position(2, 2)
        self.orientation: Orientation = Orientation.Right
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
        if self.orientation == Orientation.Right:
            self.position.col += 1
        elif self.orientation == Orientation.Down:
            self.position.row += 1
        elif self.orientation == Orientation.Up:
            self.position.row -= 1
        elif self.orientation == Orientation.Left:
            self.position.col -= 1

    def __str__(self):
        return f'Ant({self.position}, {self.orientation})'


@dataclasses.dataclass
class Position:
    col: int
    row: int


class Cell:
    def __init__(self, color):
        self.color: Color = color

    @classmethod
    def create(cls, color: Color):
        return cls(color)

    def __str__(self):
        return '◽' if self.color == Color.White else '◾'

    def as_html(self):
        return '&#9726;' if self.color == Color.Black else '&#9725;'


class Board:
    def __init__(self):
        self.ant: Ant = Ant()
        self.grid: List[List[Cell]] = []
        for row in range(0, 4):
            for col in range(0, 4):
                if row > len(self.grid) - 1:
                    self.grid.append([])
                cell = Cell(Color.White)
                self.grid[row].append(cell)

    def _get_cell_at(self, position: Position) -> Cell:
        try:
            return self.grid[position.row][position.col]
        except Exception:
            print('exception at ', position)
            raise Exception()

    @classmethod
    def __generate_board(cls, old_grid: List[List[Cell]], keep_rows: bool) -> List[List[Cell]]:
        grid: List[List[Cell]] = []
        rows = len(old_grid)
        cols = len(old_grid[0])
        for row in range(0, rows if keep_rows else 3):
            for col in range(0, 3 if keep_rows else cols):
                if row > len(grid) - 1:
                    grid.append([])
                cell = Cell(Color.White)
                grid[row].append(cell)
        return grid

    def flip(self):
        cell = self._get_cell_at(self.ant.position)
        if cell.color is Color.White:
            cell.color = Color.Black
        else:
            cell.color = Color.White

    def move(self):
        cell = self._get_cell_at(self.ant.position)
        if cell.color == Color.White:
            self.flip()
            self.ant.turn_clockwise()
        else:
            self.flip()
            self.ant.turn_ccw()
        self.ant.forward()  # Updates the position of the ant

        last_col_index = len(self.grid[0]) - 1

        if self.ant.position.col < 0:
            self.__expand_left()
        elif last_col_index < self.ant.position.col:
            self.__expand_right()

        last_row_index = len(self.grid) - 1
        if self.ant.position.row < 0:
            self.__expand_top()
        elif last_row_index < self.ant.position.row:
            self.__expand_down()

    def __expand_right(self):
        print(f"Expanding right {self.ant.position}")
        board = Board.__generate_board(self.grid, True)
        for row in range(0, len(self.grid)):
            self.grid[row] = self.grid[row] + board[row]

    def __expand_left(self):
        board = Board.__generate_board(self.grid, True)
        self.ant.position = Position(self.ant.position.col + len(board[0]) - 1, self.ant.position.row)
        print(f"Expanding left {self.ant.position}")
        for row in range(0, len(self.grid)):
            self.grid[row] = board[row] + self.grid[row]

    def __expand_top(self):
        board = Board.__generate_board(self.grid, False)
        self.ant.position = Position(col=self.ant.position.col, row=self.ant.position.row + len(board) - 1)
        print(f"Expanding top {self.ant.position}")
        for row in range(0, len(self.grid)):
            board.append(self.grid[row])
        self.grid = board

    def __expand_down(self):
        board = Board.__generate_board(self.grid, False)
        print(f"Expanding down {self.ant.position}")
        for row in range(0, len(board)):
            self.grid.append(board[row])

    def print(self):
        for i in range(0, len(self.grid)):
            print(' '.join(map(lambda x: str(x), self.grid[i])))
        print('\n')


def langton_ant():
    """
    An ant is sitting on an infinite grid of white and black squares. It initially faces right.
    At each step, it does the following:
    (1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.
    (2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one unit.
    Write a program to simulate the first K moves that the ant makes and print the final board as a grid.
    """
    board = Board()
    for i in range(1000):
        board.move()
    # board.print()
    return board.grid


if __name__ == "__main__":
    grid: List[List[Cell]] = langton_ant()
    with open("langton_ant.html", "w") as f:
        f.write('<html>')
        f.write('''
        <style>
            .scroll {
                margin: 4px, 4px;
                padding: 4px;
                width: 300px;
                overflow-x: auto;
                overflow-y: auto;
                white-space: nowrap;
             }
             </style>
        ''')

        f.write('<body class="scroll">')
        for i in grid:
            f.write('<div>')
            f.write('<span>')
            f.write(''.join(map(lambda x: x.as_html(), i)))
            f.write('</span>')
            f.write('</div>')
            f.write('\n')
        f.write('</body></html>')

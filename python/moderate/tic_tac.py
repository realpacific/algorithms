class Game:
    def __init__(self, n=3):
        assert n > 0
        self.n = n
        self.turn = 'X'
        self.winner = None
        self.board = self.make_board()
        self.counter = 0

    def make_board(self):
        board = []
        for i in range(0, self.n):
            temp = []
            for j in range(0, self.n):
                temp.append('-')
            board.append(temp)
        return board

    def make_move(self, row, col):
        if row >= self.n or col >= self.n:
            raise Exception('Invalid move')
        if self.board[row][col] != '-':
            raise Exception('Occupied')
        self.board[row][col] = self.turn
        self.turn = 'X' if self.turn != 'X' else 'O'
        print('___________')
        self.print_board()
        self.counter += 1

    def print_board(self):
        for i in range(0, self.n):
            print('  '.join(self.board[i][0:]))

    def has_won(self):
        if self.counter == self.n * self.n:
            return True
        log = []
        # Check for rows match
        for i in range(0, self.n):
            for j in range(1, self.n):
                # Since j starts from 1, always log 0th index
                if (j - 1) == 0:
                    log.append(self.board[i][j])
                if self.board[i][j - 1] != self.board[i][j] or self.board[i][j] == '-':
                    log.clear()
                    break
                else:
                    log.append(self.board[i][j])

        # Column
        if len(log) == 0:
            for i in range(0, self.n):
                for j in range(1, self.n):
                    if (i - 1) == 0:
                        log.append(self.board[i][j])
                    if self.board[j - 1][i] != self.board[j][i] or self.board[j][i] == '-':
                        log.clear()
                        break
                    else:
                        log.append(self.board[i][j])
        # Diagonal
        if len(log) == 0:
            for i in range(1, self.n):
                if (i - 1) == 0:
                    log.append(self.board[i][i])
                if self.board[i - 1][i - 1] != self.board[i][i] or self.board[i][i] == '-':
                    log.clear()
                    break
                else:
                    log.append(self.board[i][i])

        # RTL Diagonal
        if len(log) == 0:
            # only need to loop half the time
            for i in range(0, int(self.n / 2) + 1):
                col = self.n - i - 1
                if i == 0:
                    log.append(self.board[i][col])
                if self.board[i][col] == self.board[col][i] and self.board[i][col] != '-':
                    log.append(self.board[i][col])
                else:
                    log.clear()
                    break

        if len(log) == self.n:
            self.winner = log[0]
            log.clear()
            return True
        else:
            log.clear()
            return False


def simulation_config(option):
    if option == 'diag':
        return [(0, 0), (1, 2), (1, 1), (2, 1), (2, 2)]
    elif option == 'rtl':
        return [(0, 2), (1, 2), (1, 1), (2, 1), (2, 0)]
    elif option == 'draw':
        return [(0, 2), (1, 2), (1, 1), (2, 0), (1, 0), (0, 0), (2, 2), (0, 1), (2, 1)]


if __name__ == '__main__':
    game = Game(n=3)

    simulation = simulation_config('draw')
    count = 0
    while not game.has_won():
        if (len(simulation) - 1) < count:
            x = int(input("X: "))
            y = int(input("Y: "))
        else:
            x, y = simulation[count]
        try:
            game.make_move(x, y)
            count += 1
        except Exception as e:
            print(e.__cause__)
            continue
    print("WINNER IS >>>>>>> ", game.winner if game.winner is not None else 'NO ONE')

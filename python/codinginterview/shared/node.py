class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

    def __str__(self):
        return f"Node({self.data}, {self.next})"

    def __repr__(self):
        return str(self)

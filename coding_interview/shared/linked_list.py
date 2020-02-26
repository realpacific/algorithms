class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

    def __str__(self):
        return f"Node({self.data}, {self.next})"

    def __repr__(self):
        return str(self)


class LinkedList:
    def __init__(self):
        self.node = None
        self.length = 0

    def add(self, data):
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node = data
        else:
            current = self.node
            while current.next is not None:
                current = current.next
            current.next = data
        self.length += 1

    def __iter__(self):
        for each in self.__dict__.keys():
            yield self.__getattribute__(each)

    def __str__(self):
        return f"{self.node}"

    def __len__(self):
        return self.length

    def print(self):
        current = self.node
        while current is not None:
            print(current.data)
            current = current.next

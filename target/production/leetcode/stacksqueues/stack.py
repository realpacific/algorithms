class Stack:
    def __init__(self):
       self.data = []
    
    def peek(self):
        if len(self.data) == 0:
            return None
        return self.data[-1]

    def push(self, value):
        self.data.append(value)

    def isempty(self):
        return len(self) == 0
    
    def pop(self):
        if len(self.data) > 0:
            return self.data.pop()
        return None
    

    def __len__(self):
        return len(self.data)

    def __str__(self):
        return f"Stack({self.data})"

    def __repr__(self):
        return str(self)
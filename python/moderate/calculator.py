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


class Operator:
    Add = "+"
    Subtract = "-"
    Divide = "/"
    Multiply = "*"

    def __init__(self):
        self.operation = {
            Operator.Add: lambda left, right: left + right,
            Operator.Subtract: lambda left, right: left - right,
            Operator.Divide: lambda left, right: left / right,
            Operator.Multiply: lambda left, right: left * right
        }
        self.priority = {
            Operator.Add: 10,
            Operator.Subtract: 10,
            Operator.Divide: 1000,
            Operator.Multiply: 1000
        }

    def get(self, opr: str):
        return self.operation[opr]


class Calculator:
    def __init__(self, equation: str):
        self.equation: str = equation.strip().replace(" ", "")

        self.operator_stack: Stack = Stack()
        self.operand_stack: Stack = Stack()
        self.operator = Operator()

    def process(self):
        operand = ''
        current_index = 0
        digit = ''
        for i in range(0, len(self.equation)):
            if current_index >= len(self.equation):
                break
            if self.equation[current_index].isdigit():
                while current_index < len(self.equation) and self.equation[current_index].isdigit():
                    digit += self.equation[current_index]
                    current_index += 1
                self.add_operand(float(digit))
                digit = ''

            elif self.equation[current_index] == '-':
                if current_index == 0:
                    digit += '-'
                else:
                    # if previous character is digit, then it is an operand
                    if self.equation[current_index - 1].isdigit():
                        self.add_operator(self.equation[current_index])
                        digit = ''
                    # else it could be a negative value
                    else:
                        digit += '-'
                current_index += 1

            else:
                self.add_operator(self.equation[current_index])
                current_index += 1
                digit = ''

        while not self.operator_stack.isempty():
            right = self.operand_stack.pop()
            left = self.operand_stack.pop()
            opr = self.operator_stack.pop()
            result = self.operator.operation[opr](left, right)
            self.operand_stack.push(result)
        final = self.operand_stack.pop()
        print(self.equation, '=', final)
        return final

    def add_operator(self, operator: str):
        current_priority = self.operator.priority[operator]

        prev_operator = self.operator_stack.peek()
        if prev_operator is None:
            self.operator_stack.push(operator)
        else:
            previous_priority = self.operator.priority[prev_operator]
            if current_priority <= previous_priority:
                right = self.operand_stack.pop()
                left = self.operand_stack.pop()
                prev_operator = self.operator_stack.pop()
                result = self.operator.operation[prev_operator](left, right)
                self.operand_stack.push(result)
                self.operator_stack.push(operator)
            else:
                self.operator_stack.push(operator)

    def add_operand(self, operand):
        self.operand_stack.push(operand)


if __name__ == "__main__":
    Calculator("100-10+5").process()
    Calculator("100*10+5").process()
    Calculator("100-10*5").process()
    Calculator("100-10/5").process()
    Calculator("100--10/5").process()
    Calculator("-100--10/5").process()

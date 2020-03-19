import datetime

from .queueviastacks import QueueViaStack


class Animal:
    def __init__(self, name):
        self.name = name
        self.arrival_time = datetime.datetime.now()

    def __repr__(self):
        return f"Animal({self.name})"


class Cat(Animal):
    def __init__(self, name):
        super().__init__(name)


class Dog(Animal):
    def __init__(self, name):
        super().__init__(name)


class AnimalShelter:
    def __init__(self):
        self.dog_queue = QueueViaStack()
        self.cat_queue = QueueViaStack()

    def add(self, animal):
        assert isinstance(animal, Animal)
        if isinstance(animal, Cat):
            self.cat_queue.enqueue(animal)
        elif isinstance(animal, Dog):
            self.dog_queue.enqueue(animal)

    def dequeue_dog(self):
        return self.dog_queue.dequeue()

    def dequeue_cat(self):
        return self.cat_queue.dequeue()

    def dequeue_any(self):
        dog = self.dog_queue.first_element()
        cat = self.cat_queue.first_element()
        if dog is None and cat is None:
            return None
        elif cat is None:
            return self.dequeue_dog()
        elif dog is None:
            return self.dequeue_cat()

        if dog.arrival_time > cat.arrival_time:
            return self.dequeue_dog()
        elif dog.arrival_time < cat.arrival_time:
            return self.dequeue_cat()
        else:
            if len(self.dog_queue) > len(self.cat_queue):
                return self.dequeue_dog()
            elif len(self.dog_queue) < len(self.cat_queue):
                return self.dequeue_cat()
            else:
                return self.dequeue_dog()

    def __repr__(self):
        return f"AnimalShelter(dog={self.dog_queue}, cat={self.cat_queue})"


shelter = AnimalShelter()
shelter.add(Cat("Tom"))
shelter.add(Dog("Pluto"))
assert shelter.cat_queue.first_element().name == "Tom"
assert shelter.dog_queue.first_element().name == "Pluto"
shelter.add(Dog("Brutus"))
shelter.add(Dog("Perry"))
shelter.add(Dog("Donald"))
assert shelter.dog_queue.first_element().name == "Pluto"
shelter.dequeue_dog()
shelter.dequeue_dog()
shelter.dequeue_dog()
assert shelter.dog_queue.first_element().name == "Donald"
shelter.dequeue_cat()
assert shelter.cat_queue.first_element() is None

print(shelter)
shelter.dequeue_any()
assert shelter.dog_queue.first_element() is None
shelter.add(Cat("Tom"))
shelter.add(Cat("Jerry"))
shelter.add(Cat("X"))
shelter.add(Dog("Pluto"))
shelter.dequeue_any()
assert shelter.cat_queue.first_element().name == "Jerry"

from random import Random


class Person:
    def __init__(self, date_of_birth, date_of_death):
        self.birth = date_of_birth
        self.death = date_of_death

    def __str__(self):
        return f"Person({birth}, {death})"


if __name__ == '__main__':
    people = []
    rand = Random()
    for i in range(0, 10):
        birth = rand.randint(1900, 2000)
        death = 2000 if birth == 2000 else rand.randint(birth, 2000)
        person = Person(birth, death)
        people.append(person)
        print(person)

    birth_years = sorted(list(map(lambda x: x.birth, people)))
    death_years = sorted(list(map(lambda x: x.death, people)))
    print(birth_years)
    print(death_years)
    death_count = 0
    birth_count = 0

    max_alive = (1900, 0)

    number_of_alive = 0
    birth_year_index = 0
    death_years_index = 0

    while birth_year_index < len(people):
        if birth_years[birth_year_index] < death_years[death_years_index]:
            number_of_alive += 1
            if number_of_alive > max_alive[1]:
                max_alive = (birth_years[birth_year_index], number_of_alive)
            birth_year_index += 1
        else:
            number_of_alive -= 1
            death_years_index += 1

    print(max_alive)

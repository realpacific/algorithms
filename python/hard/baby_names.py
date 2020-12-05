from typing import Dict


class NameSet:
    def __init__(self, root_name, freq):
        self.ns = set()
        self.frequency = freq
        self.root_name = root_name
        self.alternates = set()
        self.alternates.add(self.root_name)

    def merge(self, nameset: 'NameSet') -> 'NameSet':
        if nameset.root_name in self.alternates:
            return self
        self.ns.union(nameset.ns)
        self.frequency = self.frequency + nameset.frequency
        self.alternates.union(nameset.alternates)
        self.alternates.add(nameset.root_name)
        return self

    def __repr__(self):
        return f"NameSet(root_name={self.root_name}, freq={self.frequency}, alt={self.alternates})"

    def size(self):
        return len(self.alternates)


__synonyms_table: Dict[str, str] = {
    "Jonathan": "John",
    "Jon": "Johnny",
    "Johnny": "John",
    "Kari": "Carrie",
    "Carleton": "Carlton"
}

__counts: Dict[str, int] = {
    "John": 10,
    "Jon": 3,
    "Davis": 2,
    "Kari": 3,
    'Johnny': 11,
    "Carlton": 8,
    "Carleton": 2,
    "Jonathan": 9,
    "Carrie": 5
}

if __name__ == "__main__":
    name_to_namesets_map: Dict[str, NameSet] = {}

    for (name, count) in __counts.items():
        name_to_namesets_map[name] = NameSet(root_name=name, freq=count)

    print(name_to_namesets_map)

    for (name, alternate) in __synonyms_table.items():
        name_entry = name_to_namesets_map[name]
        alternate_entry = name_to_namesets_map[alternate]

        # Merge smaller into bigger
        merge_into_name = True if name_entry.size() >= alternate_entry.size() else False

        if merge_into_name:
            new_name_set = name_to_namesets_map[name].merge(name_to_namesets_map[alternate])
        else:
            new_name_set = name_to_namesets_map[alternate].merge(name_to_namesets_map[name])

        name_to_namesets_map[name] = new_name_set
        name_to_namesets_map[alternate] = new_name_set

        # Udpate mappings of all
        for i in new_name_set.alternates:
            name_to_namesets_map[i] = new_name_set

    result = dict()
    for key, value in name_to_namesets_map.items():
        result[value.root_name] = value.frequency
    print(result)

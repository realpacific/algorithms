import re

DICT = {
    'family': 1,
    'person': 2,
    'firstName': 3,
    'lastName': 4,
    'state': 5,
    '>': 0
}


def value_extractor(value: str):
    mapped_values = {}
    search: list = re.findall(r"[\w]+=\"[\w]+\"", value)
    for i in search:
        if i:
            split_values = i.split('=')
            key = DICT[split_values[0]]
            value = str(split_values[1]).replace("\"", "")
            mapped_values[i] = f"{key} {value}"
    return mapped_values


def replacer(maps: dict, xml: str):
    result = xml
    for key, value in maps.items():
        result = result.replace(key, value)
    return result


def start_tag(value: str) -> dict:
    mapper = {}
    split = value.split(" ")
    for i in split:
        if i.startswith("<"):
            result = i[1:]
            mapper[i] = str(DICT[result])
    return mapper


def tag_formatter(value: str):
    value = re.sub(r"\">", " \"0 ", value)
    value = re.sub(r"</\w+>", " 0 ", value)
    value = value.replace(">", " 0 ")
    value = re.sub("[\\s]{2,}", " ", value)
    mapper = start_tag(value)
    return replacer(mapper, value)


def encode_xml(xml: str):
    one_line = xml.replace("\n", "")
    formatted = re.sub("[\\s]{2,}", " ", one_line)
    print(formatted)
    extractor = value_extractor(formatted)
    formatted = replacer(extractor, formatted)
    print(formatted)
    formatted = tag_formatter(formatted)
    print(formatted)


if __name__ == '__main__':
    encode_xml(
        xml="""
        <family lastName="McDowell" state="CA">
            <person firstName="Gayle">Some Message</person>
        </family>"""
    )

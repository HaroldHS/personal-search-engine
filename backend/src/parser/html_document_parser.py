from re import findall
from html.parser import HTMLParser

def parse_html_document_from_path(file_path, file_name):
    document_obj = open(file_path + file_name, "r")
    document_content = document_obj.read()

    parser = html_parser()
    parser.feed(document_content)
    return parser.result

class html_parser(HTMLParser):
    def __init__(self):
        HTMLParser.__init__(self)
        self.result = []

    def handle_data(self, data):
        curr_data = data.lower()
        curr_data = list(findall(r"\w+", curr_data))

        if (curr_data != []):
            for d in curr_data:
                self.result.append(d)
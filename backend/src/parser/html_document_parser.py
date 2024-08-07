from html.parser import HTMLParser

def parse_html_document_from_path(file_name):
    return

class html_parser(HTMLParser):
    def handle_data(self, data):
        print(data + "\n")
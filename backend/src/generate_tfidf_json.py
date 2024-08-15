from json import dumps
from hashlib import md5
from urllib.parse import urlparse

from src.parser.text_document_parser import parse_text_document_content

def generate_tfidf_json_file(data):
    data["page content"] = parse_text_document_content(data["page content"])

    page_loc_hostname = urlparse(data["page location"]).hostname
    md_hash = md5()
    md_hash.update(data["page location"].encode("utf-8"))
    page_loc_hash = md_hash.hexdigest()
    page_file_name = page_loc_hostname + "-" + page_loc_hash + ".json"

    page_json = dumps(data)
    try:
        with open("./tfidf_json/" + page_file_name, "w") as f:
            f.write(page_json)
            return True
    except:
        return False
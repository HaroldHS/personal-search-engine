from re import findall

def parse_text_document_from_path(file_path, file_name):
    document_obj = open(file_path + file_name, "r")
    document_content = document_obj.read().lower()
    document_tokens = list(findall(r"\w+", document_content))
    return document_tokens
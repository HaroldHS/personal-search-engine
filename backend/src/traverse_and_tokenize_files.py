from os import getcwd, name, path, scandir

from src.parser.text_document_parser import parse_text_document_from_path

def traverse_and_tokenize_files_to_be_indexed(query, file_path=""):
    # Check if file_path is not defined
    if file_path == "":
        # Check the type of  operating system
        if name == "nt":
            file_path = path.dirname(__file__)+"\\..\\files_to_index\\"
        else:
            file_path = path.dirname(__file__)+"/../files_to_index/"

    resp = []

    # Initialize
    dir_obj = scandir(file_path)
    file_flag = False
    curr_resp = {}
    doc_parsed = []

    for entry in dir_obj:
        if entry.is_file() and entry.name.endswith(".html"):
            # TODO: Implement and call html parser
            continue
        if entry.is_file() and entry.name.endswith(".txt"):
            file_flag = True
            doc_parsed = parse_text_document_from_path(file_path, entry.name)
        if entry.is_file() and entry.name.endswith(".pdf"):
            # TODO: Implement and call pdf parser
            continue
        
        if file_flag and doc_parsed != []:
            curr_resp["document name"] = entry.name
            curr_resp["document path"] = file_path + entry.name
            curr_resp["document tokens"] = doc_parsed
            resp.append(curr_resp)

            # Reinitialize
            file_flag = False
            curr_resp = {}
            doc_parsed = []

    return resp
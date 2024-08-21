def invert_index_files_tokens(query_terms, list_of_files_tokens):
    
    # Initialize
    result = {}
    for term in query_terms:
        result[term] = []

    # Invert index all terms
    for term in query_terms:
        for file_tokens in list_of_files_tokens:
            tokens = file_tokens["document tokens"]
            for index, token in enumerate(tokens):
                if term == token:
                    # Append document name and term position/index in the document
                    result[term].append([file_tokens["document name"], index])

    return result
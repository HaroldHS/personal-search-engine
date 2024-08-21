def get_document_path_from_name(tfidf_measurement_result, list_of_files_tokens):
    result = []

    for doc, tfidf in tfidf_measurement_result.items():
        for file in list_of_files_tokens:
            if doc == file["document name"]:
                result.append({
                    "document name": doc,
                    "document path": file["document path"]
                })

    return result
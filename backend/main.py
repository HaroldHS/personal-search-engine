from re import findall
from flask import Flask, jsonify, request

from src.generate_response import generate_basic_response
from src.traverse_and_tokenize_files import traverse_and_tokenize_files_to_be_indexed
from src.inverted_indexing import invert_index_files_tokens
from src.algo.tf_idf import TFIDF

app = Flask(__name__)

@app.route("/")
def main():
    return jsonify(
        generate_basic_response("200", "OK", "Welcome to Personal Search Engine")
    )

@app.route("/search", methods=["GET", "POST"])
def search():
    res = {}

    """
    Steps of PSE (Personal Search Engine) search mechanism:
        1. Traverse all files and get the tokens
        2. Invert indexing those files tokens
        3. Measure the importance of terms in each documents with TF-IDF algorithm and sort the result
    """

    if request.method == "GET":
        query = request.args["query"]
    elif request.method == "POST":
        query = request.form["query"]
    else:
        res = generate_basic_response("500", "Internal Server Error", "Invalid request method. GET & POST are the only available request methods.")
        return jsonify(res)

    query_terms = list(findall(r"\w+", query.lower()))
    list_of_files_tokens = traverse_and_tokenize_files_to_be_indexed(query)
    dict_of_term_indexes = invert_index_files_tokens(query_terms, list_of_files_tokens)
    measure_tfidf = TFIDF(dict_of_term_indexes, list_of_files_tokens)
    res = generate_basic_response("200", "OK", measure_tfidf)
    return jsonify(res)

if __name__ == "__main__":
    app.run()
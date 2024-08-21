
#
# TF-IDF (Term Frequency - Inverse Document Frequency)
#

from math import log
from collections import OrderedDict

def TFIDF(dict_of_term_indexes, list_of_files_tokens):

    #######################
    # Count all documents #
    #######################
    total_file = len(list_of_files_tokens)

    ############################
    # Check all required files #
    ############################
    req_files = set()
    for term, index in dict_of_term_indexes.items():
        for doc_and_post in index:
            doc = doc_and_post[0]
            if doc not in req_files:
                req_files.add(doc)

    #########################################
    # Count all terms in all requried files #
    #########################################
    tfidf_result = {}
    for f in req_files:
        tfidf_result[f] = {"DO_NOT_MODIFY_terms_occurences_total": 0} # Initialize
        for file_tokens in list_of_files_tokens:
            if file_tokens["document name"] == f:
                # Count terms occurences in the document
                term_occr = set()
                for term in file_tokens["document tokens"]:
                    if term not in term_occr:
                        term_occr.add(term)
                        tfidf_result[f][term] = 1
                    else:
                        tfidf_result[f][term] += 1
                    tfidf_result[f]["DO_NOT_MODIFY_terms_occurences_total"] += 1

    ##############################################################
    # Measure the importance of each file for each term in query #
    ##############################################################
    tf = {}
    idf = {}
    for term, index in dict_of_term_indexes.items():
        count_term_availability_in_doc = 0
        for doc, doc_freq in tfidf_result.items():
            # If the keyword is not found in any document, just continue / skip ahead
            try:
                tf[term + " " + doc] = (doc_freq[term] / doc_freq["DO_NOT_MODIFY_terms_occurences_total"])
            except:
                continue
            if doc_freq[term] > 0:
                count_term_availability_in_doc += 1
        # Invalid term (from try catch above) leads to 0 term availability (no tf).
        # So, the idf will also be 0, then idf is not needed
        if count_term_availability_in_doc > 0:
            idf[term] = log(total_file / count_term_availability_in_doc)

    tfidf = {}
    for term_and_doc, tf_value in tf.items():
        term, doc = term_and_doc.split(" ")
        for file in list(req_files):
            if doc == file:
                tfidf[term_and_doc] = tf[term_and_doc] * idf[term]

    ##########################################################################
    # Calculatre tf-idf score & Sort all documents based on the tf-idf value #
    ##########################################################################
    tfdif_doc_score = {}
    # Initialize
    for file in req_files:
        tfdif_doc_score[file] = 0

    # Calculate score by summing all tfidf values of a query
    for file in req_files:
        for term_and_doc, tfidf_val in tfidf.items():
            term, doc = term_and_doc.split(" ")
            if doc == file:
                tfdif_doc_score[doc] += tfidf_val

    # Sort the previous tf-idf score
    sorted_tfidf = dict(OrderedDict(reversed(list(tfdif_doc_score.items()))))

    return sorted_tfidf
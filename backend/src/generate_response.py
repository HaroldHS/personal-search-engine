def generate_basic_response(code, msg, body):
    res = {}
    res["response code"] = code
    res["response message"] = msg
    res["response body"] = body
    return res
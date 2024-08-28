#!/usr/bin/python3

import csv
import json
import argparse
import platform
from urllib.parse import urlparse
from http.client import HTTPConnection, HTTPSConnection

argument_parser = argparse.ArgumentParser(
    prog="SETUP.py",
    description="Program to hit backend API service in order to setup pages in database",
)

argument_parser.add_argument(
    "--url",
    type=str,
    default="http://localhost:8080/api/v1/add_url",
    help="Backend API service (e.g. http://localhost:8080/api/v1/add_url)"
)
argument_parser.add_argument(
    "--file",
    type=str,
    default="url_list.csv",
    help="CSV file that contains list of web pages url to be stored into database"
)
argument_parser.add_argument(
    "--method",
    type=str,
    choices=["GET", "POST", "PUT", "DELETE"],
    default="POST",
    help="HTTP method for hitting the backend API service"
)
argument_parser.add_argument(
    "--origin",
    type=str,
    default="http://localhost:5173",
    help="Origin header value"
)

args = argument_parser.parse_args()

print("[+] Backend API URL :", args.url)
print("[+] File            :", args.file)
print("\n[*] Hit API service...")

# Check the scheme (scheme must be HTTP/HTTPS)
scheme = urlparse(args.url).scheme
if scheme not in  ["http", "https"]:
    print("[-] Invalid scheme, http / https only\n")
    exit()

target = urlparse(args.url).netloc
api_service = urlparse(args.url).path

# Generate http.client object
if scheme == "http":
    conn = HTTPConnection(target)
elif scheme == "https":
    conn = HTTPSConnection(target)
else:
    print("[-] Invalid scheme while creating http.client object")
    exit()

# Setup headers
http_headers = {}
http_headers["Host"] = target
http_headers["User-Agent"] = "Python " + platform.python_version()
http_headers["Accept"] = "*/*"
http_headers["Accept-Encoding"] = "gzip, deflate, br"
http_headers["Connection"] = "keep-alive"
if args.method == "POST":
    http_headers["Content-Type"] = "application/json"
if args.origin:
    http_headers["Origin"] = args.origin

with open(args.file, newline='') as csv_obj:
    # Skip heading
    heading = next(csv_obj)

    # Read CSV
    read_csv_obj = csv.reader(csv_obj, delimiter="|")

    for row in read_csv_obj:
        document_name = row[0].strip()
        document_url = row[1].strip()
        request_body = {
            "name": document_name,
            "url": document_url
        }
        json_request_body = json.dumps(request_body)

        if args.method == "POST":
            # Set content length for HTTP POST request
            http_headers["Content-Length"] = str(len(json_request_body))
            conn.request(args.method, api_service, headers=http_headers, body=json_request_body)
        else:
            conn.request(args.method, api_service, headers=http_headers)

        response = conn.getresponse().read().decode()
        response_dict = json.loads(response)

        if response_dict["status"] == "success":
            print(f'[+] Successfully adding `{document_name}`')
        else:
            print(f'[-] Failed adding `{document_name}`')

print("[*] Closing http.client connection...")
try:
    conn.close()
    print("[+] Successfully close the connection")
except:
    print("[-] Failed to close the connection")

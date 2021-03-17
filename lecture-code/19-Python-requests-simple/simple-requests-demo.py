import requests

# processing simple text
url_numbers = "http://numbersapi.com/"
number = 33
response_num = requests.get(url_numbers + str(number))
print(response_num.text)

# processing text -> json

url = "http://api.dataatwork.org/v1/jobs"
response = requests.get(url) # send get request
print("Code:", response.status_code)
print("Content", response.content)
print("Headers:", response.headers)

# processing json text

import json

json_response = json.loads(response.text) # string to json
print(json_response)

for entry in json_response:
  if 'title' in entry.keys():
    print(entry['title'])

# json to string
string_response = json.dumps(json_response)
print(string_response)

#

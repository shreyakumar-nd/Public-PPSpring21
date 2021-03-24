import unittest
import requests
import json

class TestCherrypyPrimer(unittest.TestCase):

        SITE_URL = 'http://localhost:510XX' #'http://student04.cse.nd.edu:510XX' #Replace this your port number and machine
        DICT_URL = SITE_URL + '/dictionary/'

        def reset_data(self):
                r = requests.delete(self.DICT_URL)

        def is_json(self, resp):
                try:
                        json.loads(resp)
                        return True
                except ValueError:
                        return False

        def test_dict_get_key(self):
                self.reset_data()
                key = 'HarryPotter'
                r = requests.get(self.DICT_URL + key) # performing get
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'error')

        def test_dict_index_get(self):
                self.reset_data()

                key = 'HarryPotter'
                m = {}
                m['value'] = 'Gryffindor'
                r = requests.put(self.DICT_URL + key, data = json.dumps(m)) # uses put
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.get(self.DICT_URL) # performing get
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                entries = resp['entries']
                mkv = entries[0]
                self.assertEqual(mkv['key'], key)
                self.assertEqual(mkv['value'], m['value'])


        def test_dict_put_key(self):
                self.reset_data()
                key = 'HarryPotter'

                m = {}
                m['value'] = 'Gryffindor'
                r = requests.put(self.DICT_URL + key, data = json.dumps(m)) # uses put
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.get(self.DICT_URL + key)
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['value'], m['value'])

        def test_dict_delete_key(self):
                self.reset_data()
                key = 'HarryPotter'

                m = {}
                m['value'] = 'Gryffindor'
                r = requests.put(self.DICT_URL + key, data = json.dumps(m)) # uses put
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.delete(self.DICT_URL + key) # testing delete
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.get(self.DICT_URL + key)
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'error')

        def test_dict_index_delete(self):
                self.reset_data()

                key1 = 'HarryPotter'
                m1 = {}
                m1['value'] = 'Gryffindor'
                r = requests.put(self.DICT_URL + key1, data = json.dumps(m1)) # uses put
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                key2 = 'GinnyWeasley'
                m2 = {}
                m2['value'] = 'Gryffindor'
                r = requests.put(self.DICT_URL + key2, data = json.dumps(m2)) # uses put
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.delete(self.DICT_URL) # delete index
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.get(self.DICT_URL + key1) # uses get
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'error')

                r = requests.get(self.DICT_URL + key2) # uses get
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'error')


        def test_dict_index_post(self):
                self.reset_data()

                m = {}
                m['key'] = 'HarryPotter'
                m['value'] = 'Gryffindor'

                r = requests.post(self.DICT_URL, data = json.dumps(m)) # performing post
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                r = requests.get(self.DICT_URL) # uses get
                self.assertTrue(self.is_json(r.content.decode()))
                resp = json.loads(r.content.decode())
                self.assertEqual(resp['result'], 'success')

                entries = resp['entries']
                mkv = entries[0]
                self.assertEqual(mkv['key'], m['key'])
                self.assertEqual(mkv['value'], m['value'])

if __name__ == "__main__":
        unittest.main()

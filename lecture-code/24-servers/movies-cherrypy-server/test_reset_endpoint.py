import unittest
import requests
import json

class TestReset(unittest.TestCase):

	SITE_URL = 'http://localhost:510XX'
	print('Testing for server: ' + SITE_URL)
	RESET_URL = SITE_URL + '/reset/'

	def test_put_reset_index(self):
		m = {}
		r = requests.put(self.RESET_URL, json.dumps(m))
		resp = json.loads(r.content.decode())
		self.assertEqual(resp['result'], 'success')
		r = requests.get(self.SITE_URL + '/movies/')
		resp = json.loads(r.content.decode())
		movies = resp['movies']
		self.assertEqual(movies[30]['title'], 'Dangerous Minds (1995)')

	def test_put_reset_key(self):
		m = {}
		r = requests.put(self.RESET_URL, json.dumps(m))

		# Change Movie Title and Genre
		movie_id = 31
		m['title'] = 'A New Title'
		m['genres'] = 'A New Genre(s)'
		r = requests.put(self.SITE_URL + '/movies/' + str(movie_id), data=json.dumps(m))

		# Reset the changed movie back to original
		m = {}
		r = requests.put(self.RESET_URL + str(movie_id), data=json.dumps(m))
		resp = json.loads(r.content.decode())	
		self.assertEqual(resp['result'], 'success')

		# Check if effective
		r = requests.get(self.SITE_URL + '/movies/')
		resp = json.loads(r.content.decode())	
		movies = resp['movies']
		self.assertEqual(movies[30]['title'], 'Dangerous Minds (1995)')




if __name__ == '__main__':
		unittest.main()






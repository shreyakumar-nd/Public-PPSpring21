import cherrypy
import re, json
from movies_library import _movie_database

class RatingsController(object):

	def __init__(self, mdb=None):
		if mdb is None:
			self.mdb= _movie_database()
			self.mdb.load_ratings('ratings.dat')
		else:
			self.mdb = mdb

	def GET_KEY(self, movie_id):
		output = {'result' : 'success'}
		movie_id = int(movie_id)
		output['movie_id'] = movie_id
		output['rating'] = self.mdb.get_rating(movie_id)

		return json.dumps(output)

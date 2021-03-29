import cherrypy
import re, json
from movies_library import _movie_database

class MovieController(object):

	def __init__(self, mdb=None):
		if mdb is None:
			self.mdb = _movie_database()
		else:
			self.mdb = mdb

		self.mdb.load_movies('movies.dat')


	def GET_KEY(self, movie_id):
		output = {'result' : 'success'}
		movie_id = int(movie_id)

		try:
			movie = self.mdb.get_movie(movie_id)
			if movie is not None:
				output['id'] = movie_id
				output['title'] = movie[0]
				output['genres'] = movie[1]

			else:
				output ['result'] = 'error'
				output['message'] = 'movie not found'

		except Exception as ex:
			output['result'] = 'error'
			output['message'] = str(ex)

		return json.dumps(output)

	def PUT_KEY(self, movie_id):
		output = {'result' : 'success'}
		movie_id = int(movie_id)

		data = json.loads(cherrypy.request.body.read().decode('utf-8'))

		movie = list()
		movie.append(data['title'])
		movie.append(data['genres'])

		self.mdb.set_movie(movie_id, movie)

		return json.dumps(output)

	def DELETE_KEY(self, movie_id):
		
		output = {'result' : 'success'}

		movie_id = int(movie_id)

		try:
			self.mdb.delete_movie(movie_id)
		except Exception as ex:
			output['result'] = 'failure'
			output['message'] = str(ex)

		return json.dumps(output)

	def GET_INDEX(self):
		output = {'result' : 'success'}
		output['movies'] = []

		try:
			for mid in self.mdb.get_movies():
				movie = self.mdb.get_movie(mid)
				dmovie = {'id': mid, 'title' : movie[0], 'genres' : movie[1]}
				output['movies'].append(dmovie)

		except Exception as ex:
			output['result'] = 'error'
			output['message'] = str(ex)

		return json.dumps(output)

	def POST_INDEX(self):
		output = { 'result' : 'success'}
		data = json.loads(cherrypy.request.body.read().decode('utf-8'))

		try:
			movies = sorted(list(self.mdb.get_movies()))
			newID = int(movies[-1]) + 1
			self.mdb.movie_names[newID] = data['title']
			self.mdb.movie_genres[newID] = data['genres']
			self.mdb.movie_ratings[newID] = dict()
			output['id'] = newID

		except Exception as ex:
			output['result'] = 'failure'
			output['message'] = str(ex)

		return json.dumps(output)



	def DELETE_INDEX(self):
		output = {'result' : 'success'}

		try:
			allMovies = list(self.mdb.get_movies())
			for movID in allMovies:
				self.mdb.delete_movie(movID)

		except Exception as ex:
			output['result'] ='failure'
			output['message'] = str(ex)

		return json.dumps(output)


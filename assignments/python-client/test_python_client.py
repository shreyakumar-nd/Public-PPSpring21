from python_rest_client import PythonRestClient
import unittest

# note that all the methods called test_* belonging to this class are unit tests.
# unit tests are supposed to be self contained, which means you do not know which order they will operate in, and need to contain all the setup and if needed breakdown before and after the test
class TestPythonRestClient(unittest.TestCase):
	MID = X # CHANGE THIS TO YOUR MID
	MNAME = 'Jumanji (1995)' # CHANGE TO YOUR MOVIE NAME, include full name with year
	pc = PythonRestClient()

	def reset_movie(self):
		'''reset_movie is needed because we cannot promise an execution order - each unit test is self contained'''
		#print("Entered reset movie!")
		self.pc.reset_movie(self.MID)

	def test_get_movie(self):
		'''test for get_movie method'''
		self.reset_movie()
		movie = self.pc.get_movie(self.MID)
		self.assertEqual(movie['title'], self.MNAME)

	def test_set_movie_title(self):
		self.reset_movie()
		movie = self.pc.get_movie(self.MID)
		movie['title'] = 'Something Else'
		self.pc.set_movie_title(self.MID, movie['title'])
		movie = self.pc.get_movie(self.MID)
		self.assertEqual(movie['title'], 'Something Else')

	def test_delete_movie(self):
		self.reset_movie()
		self.pc.delete_movie(self.MID)
		movie = self.pc.get_movie(self.MID)
		self.assertEqual(movie['result'], 'error')

if __name__ == "__main__":
	unittest.main() #runs the unit tests


import requests
import json

class PythonRestClient:
  def __init__(self): #constructor or initializer method
    self.SITE_URL = 'http://student04.cse.nd.edu:51001'
    self.MOVIES_URL = self.SITE_URL + '/movies/'
    self.RESET_URL = self.SITE_URL + '/reset/'
    

  def get_movie(self, mid):
    '''This method makes a get request to the /movies/ resource for a particular mid and returns a json object of the movie'''
	  # TODO make the request
    # TODO process the response to json
    return movie_response_json

  def reset_movie(self, mid):
    '''Sends a put request to the server to reset the movie id to its original value, receives a success message from server'''
    #TODO set up url and message
    # TODO make the put request
    return response.content

  def delete_movie(self, mid):
    '''Sends a delete request to the /movies/mid endpoint and returns the response'''
    #TODO set up url and message
    #TODO make request
    return resp_string

  def set_movie_title(self, mid, title):
    '''Makes a put request with message body that sets the new title as the movie title for that mid, returns response as json'''
    '''Message body is the string version of the movie json, but with the title added.'''
    #TODO set up url
    # TODO set up data - note here data is string version of a whole movie json object, but with the new title
    #TODO make request
    #TODO process response
    return response_json


  def display_movie(self, mid):
    '''Gets movie information from server and prints the title followed by a string Title: '''
    #TODO get movie information - use get_movie()
    movie_json = self.get_movie(mid)
    #TODO print movie title if the response is successful

if __name__ == "__main__":
  pc = PythonRestClient() #creating object from class
  #here we use the methods above to informally test them
  mid = xx # replace this with your assigned mid
  print(pc.get_movie(mid)) # calling class method through the object
  print("Resetting movie : ", pc.reset_movie(mid))
  print(pc.get_movie(mid))
  pc.display_movie(4)

  pc.display_movie(mid)
  print("Changing movie title: ", pc.set_movie_title(mid, 'Zooomanji (1995)'))
  pc.display_movie(mid)
  print("Resetting movie title: ", pc.reset_movie(mid))
  pc.display_movie(mid)
  print("Deleting movie: ", pc.delete_movie(mid))
  print("Reseting movie", pc.reset_movie(mid))
  pc.display_movie(mid)


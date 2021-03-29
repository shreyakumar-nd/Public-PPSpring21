#this file starts up the server and connects request/event types with event handlers
import routes
import cherrypy
from dictionary_controller import DictionaryController #getting our class

def start_service():
    #create object for DictionaryController
    dictionaryController = DictionaryController()

    #create dispatcher
    dispatcher = cherrypy.dispatch.RoutesDispatcher()

    #use dispatcher to connect resources to event handlers
    #connect(out_tag, http resource, class object with handler, event handler name, what type of HTTP request to serve)
    dispatcher.connect('dict_get_key','/dictionary/:key', controller=dictionaryController, action='GET_KEY', conditions=dict(method=['GET']))
    #get_index
    dispatcher.connect('dict_get_all','/dictionary/', controller=dictionaryController, action='GET_INDEX', conditions=dict(method=['GET']))
    #delete_key
    dispatcher.connect('dict_delete_key','/dictionary/:key', controller=dictionaryController, action='DELETE_KEY', conditions=dict(method=['DELETE']))
    #delete_index
    dispatcher.connect('dict_delete_all','/dictionary/', controller=dictionaryController, action='DELETE_INDEX', conditions=dict(method=['DELETE']))
    #post_index
    dispatcher.connect('dict_post_new','/dictionary/', controller=dictionaryController, action='POST_INDEX', conditions=dict(method=['POST']))
    #put_index
    dispatcher.connect('dict_put_key','/dictionary/:key', controller=dictionaryController, action='PUT_KEY', conditions=dict(method=['PUT']))
    #connect more resources to event handlers here

    # default OPTIONS handler for CORS, all direct to the same place
    dispatcher.connect('dict_options', '/dictionary/', controller=optionsController, action='OPTIONS', conditions=dict(method=['OPTIONS']))
    dispatcher.connect('dict_key_options', '/dictionary/:key', controller=optionsController, action='OPTIONS', conditions=dict(method=['OPTIONS']))

    #set up configuration
    conf = {
        'global' : {
            'server.socket_host' : 'localhost', #'student04.cse.nd.edu',
            'server.socket_port' : 510XX,
            },
        '/' : {
            'request.dispatch' : dispatcher,
            'tools.CORS.on' : True, # configuration for CORS
            }
    }

    #update with new configuration
    cherrypy.config.update(conf)
    app = cherrypy.tree.mount(None, config=conf) # create app
    cherrypy.quickstart(app)    # start app

# class for CORS
class optionsController:
    def OPTIONS(self, *args, **kwargs):
        return ""

# function for CORS
def CORS():
    cherrypy.response.headers["Access-Control-Allow-Origin"] = "*"
    cherrypy.response.headers["Access-Control-Allow-Methods"] = "GET, PUT, POST, DELETE, OPTIONS"
    cherrypy.response.headers["Access-Control-Allow-Credentials"] = "true"


if __name__ == '__main__':
    cherrypy.tools.CORS = cherrypy.Tool('before_finalize', CORS) # CORS
    start_service()


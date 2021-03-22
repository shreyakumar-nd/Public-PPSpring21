# simple server that does die rolls
import routes
import cherrypy
from number_controller import NumberController

def start_service():
    # create all server connections
    # create option to connect with controllers
    n_cont = NumberController()

    # create dispatcher
    dispatcher = cherrypy.dispatch.RoutesDispatcher()

    # use dispatcher to connect resource endpoints to event handler
    #connect(out_tag, http resource, class object with handler, event handler name, what type of HTTP request to serve)
    dispatcher.connect('one_roll', '/random/', controller=n_cont, action='GET_SIMPLE', conditions=dict(method=['GET']))
    # here, if someone connects with /random/ with GET request, then n_cont.GET_SIMPLE() method will be called.
    dispatcher.connect('special_roll', '/random/:dsize', controller=n_cont, action='GET_MAX', conditions=dict(method=['GET']))
    # here, if someone connects with /random/20 with GET request, then n_cont.GET_MAX(20) method will be called.

    # set up configuration
    conf = {
        'global' : {
            'server.socket_host': 'localhost',
            'server.socket_port': 51008, # my port, do not touch
            },
        '/' : {
            'request.dispatch': dispatcher,
        }
    } # end of conf

    # update with new configuration
    cherrypy.config.update(conf)
    app = cherrypy.tree.mount(None, config=conf)
    # start the server
    cherrypy.quickstart(app)

if __name__ == '__main__':
    start_service()

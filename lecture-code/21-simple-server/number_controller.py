import random
import json

class NumberController:
    # This class is used to hold event handlers

    def GET_SIMPLE(self):
        # just a regular method, that is being used as an event handler
        answer = random.randint(1, 6)

        response_json = {'result' : 'success'}
        response_json['roll'] = str(answer)
        response_string = json.dumps(response_json) # json -> str
        return response_string

    def GET_MAX(self, dsize):
        response_json = {'result' : 'success'}

        try:
            dsize = int(dsize)
            answer = random.randint(1, dsize)
            response_json['roll'] = str(answer)
        except:
            response_json['result'] = 'error'
            response_json['message'] = 'user argument ' + dsize + ' needs to be a number, try again with a number'
        response_string = json.dumps(response_json) # json -> str
        return response_string

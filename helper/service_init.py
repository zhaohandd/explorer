from model import *
from data import *
import cv2

import tornado.ioloop
import tornado.web
import argparse
import numpy as np


class MainHandler(tornado.web.RequestHandler):
    def post(self):
        img=self.request.files['im'][0]['body']
        argv = self.request.files['argv'][0]['body']

        img = cv2.imdecode(np.fromstring(img, np.uint8), cv2.IMREAD_GRAYSCALE)
        img = img / 255.

        img = trans.resize(img, (512,512))
        img = np.reshape(img, img.shape + (1,)) if (not False) else img
        img = np.reshape(img, (1,) + img.shape)

        results = model.predict(img)
        saveResult1(argv.split("/")[-1].split(".")[0] + "_result." + argv.split("/")[-1].split(".")[-1], results)
        self.set_header("Pragma", "no-cache")
        self.set_header("Expires", "0")
        self.set_header("Cache-Control", "no-cache")
        self.set_header("Cache-Control", "no-store")


def make_app():
    return tornado.web.Application([
        (r"/", MainHandler)])


def contains(target_str, search_arr):
    rv = False

    for search_str in search_arr:
        if search_str in target_str:
            rv = True
            break
    return rv


if __name__ == "__main__":

    # Loading the network
    print('Loading the network...')
    model = unet("unet_liverCT_fulliamge.hdf5")
    print('Done!')

    parser = argparse.ArgumentParser()
    parser.add_argument("--p", type=int, help="port, default is 9004", default=9005)
    params = parser.parse_args()
    app = make_app()
    app.listen(params.p)
    print("listen port %d" % params.p)
    tornado.ioloop.IOLoop.instance().start()


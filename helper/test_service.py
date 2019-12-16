import sys
import requests

url='http://127.0.0.1:9005'


def main(argv):

    img = open(argv, 'rb')
    files = {'im':img, 'argv': argv}
    requests.post(url,files=files)
    print "OK!"


if __name__ == '__main__':
    main(argv=sys.argv[1])
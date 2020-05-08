import requests
import re
import time
##
while 1:
# Waiting for the request
    url = "http://quotidian-twins.000webhostapp.com/rasp.php"
    payload = {'hall': '205'}
    r=requests.post(url, data=payload)
    #a = r.text.split("\n")
    a=r.text
    a = re.findall(r"[\w']+", a)
    #print (a)
    print(r.text)
    if (a[0] == "notvalid"):
        time.sleep(10)
    else:
        lecid = a[0]
        #print (a)
        #print("after")
        a.remove(lecid)
        print (a)
        for x in a:
            url = "http://quotidian-twins.000webhostapp.com/ent.php"
            payload = {'uid': x,'lecID': lecid}
            r=requests.post(url, data=payload)
            print(r.text)
        

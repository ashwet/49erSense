import socket
import os
import platform
host = '192.168.1.116'
port = 6789

def setupServer():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print("Socket created.")
    try:
        s.bind((host, port))
    except socket.error as msg:
        print(msg)
    print("Socket bind complete.")
    return s

def setupConnection():
	#target = open('/home/shwet/PiStatus.txt', 'w')
	
	if checkDevice()==False:
		with open('PiStatus.txt', 'w') as f:
			   f.write('false')
		f.close()
		return False
	try:	
		s.settimeout(10)
		#target.truncate()
		s.listen(1) # Allows one connection at a time.
		conn, address = s.accept()
		with open('PiStatus.txt', 'w') as f:
    			f.write('true')
		f.close()
		print("Connected to: " + address[0] + ":" + str(address[1]))
		return conn
	except socket.error as msg:
		with open('PiStatus.txt', 'w') as f:
	    		f.write('false')
		f.close()
		print(msg)
		return False
		


def GET():
    #f=open('/home/shwet/Downloads/newtimestamp.txt', 'r')
    #print("in get")
    reply =  open('/home/shwet/Downloads/newtimestamp.txt', 'r').read()
    print(reply)
    return reply

def REPEAT(dataMessage):
    reply = dataMessage[1]
    return reply

def dataTransfer(conn):
    # A big loop that sends/receives data until told not to.
	print "hello"
	while True:
	#Receive the data
		data = conn.recv(1024) # receive the data
		data = data.decode('utf-8')
 		#Split the data such that you separate the command
 		#from the rest of the data.
		dataMessage = data.split(' ', 1)
		command = dataMessage[0]
		print(command)
		if command == 'GET':
			reply = GET()
		elif command=='EXIT':
			print("Our Client has left us")
			break
		elif command=='KILL':
			print("server is shutting Down")
			s.close()
			break
 		#Send the reply back to the client
		open('/home/shwet/Downloads/newtimestamp.txt','w').close()
		if reply.strip() != "":
			conn.sendall(str.encode(reply))
			print("Data has been sent!")
		else:
			break
		if checkDevice()==False:
			print "in data transfer"
			break
	conn.close()
        print('here')

def checkDevice():
	try:
		searchfile =  open('/home/shwet/device.txt', 'r')
		#print "in check"
		for line in searchfile:
			#print line
			if "B8:27:EB:D7:2B:D8" in line: 
				out=True
			else: 
				out=False
		print out
		searchfile.close()
		return out
	except:
		return False

s = setupServer()
oldTstamp=0
while True:
    try:
        #newTstamp=os.stat('/home/shwet/Downloads/newtimestamp.txt').st_mtime
        #print(newTstamp)  
        #oldTstamp=newTstamp
	conn = setupConnection()
	if conn != False: 
		dataTransfer(conn)
    except Exception as ex:
	template = "An exception of type {0} occured. Arguments:\n{1!r}"
	message = template.format(type(ex).__name__, ex.args)
	print message
	break

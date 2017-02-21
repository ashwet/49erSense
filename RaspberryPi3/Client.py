#!/usr/bin/env python
import socket
from time import sleep
import MySQLdb as mysql
import time

host = '192.168.1.116'
port = 6789

def setupSocket():  
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host, port))
    return s

def sendReceive(s, message):
    s.send(str.encode(message))
    reply = s.recv(1024)
    print("We have received a reply")
    print("Send closing message.")
    s.send(str.encode("EXIT"))
    s.close()
    reply = reply.decode('utf-8')
    return reply

def transmit(message):
    s = setupSocket()
    response = sendReceive(s, message)
    return response

def mySQLConnection():
    connection=mysql.connect(host = "localhost", user = "root", passwd = "student", db = "users")
    return connection

while True:
    recvData=transmit('GET')
    print('GET:'+str(len(recvData)))
    if len(recvData.strip())!=0:
        con=mySQLConnection()
        if con is  None:
            print('Error DB Connection')
        else:
            cur=con.cursor()
            cur.execute(recvData)
            con.commit()
            con.close()

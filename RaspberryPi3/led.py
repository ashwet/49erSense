import RPi.GPIO as GPIO
import time

def blink(status):
    if status==1:    
        GPIO.output(3,GPIO.HIGH)
        GPIO.output(11,GPIO.LOW)
    else:
        GPIO.output(3,GPIO.LOW)
        GPIO.output(11,GPIO.HIGH)
    
    return
GPIO.setmode(GPIO.BOARD)
GPIO.setup(3,GPIO.OUT)
GPIO.setup(11,GPIO.OUT)

while True:
    command=open('command.txt','r')
    for line in command:
        if line.strip() == 'SecurityOff':
            SecurityStatus = 0
        elif line.strip() == 'SecurityOn':
            SecurityStatus = 1
        else:
            continue
        
        blink(SecurityStatus)
        
GPIO.cleanup()
 

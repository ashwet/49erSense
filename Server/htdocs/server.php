<?php
// set ip and port
$host = "192.168.1.3";
$port = 6789;
// don't timeout!
set_time_limit(0);
// create socket
$socket = socket_create(AF_INET, SOCK_STREAM, 0);
// bind socket to port
$result = socket_bind($socket, $host, $port);

while(1)
{
// start listening for connections
$result = socket_listen($socket, 3);

// accept incoming connections
// spawn another socket to handle communication
$spawn = socket_accept($socket) or die("Could not accept incoming connection\n");
// read client input
$input = socket_read($spawn, 1024) or die("Could not read input\n");
// clean up input string
$input = trim($input);
echo "Client Message : ".$input."<br />";
// reverse client input and send back
$output = strrev($input) ."<br />";
socket_write($spawn, $output, strlen ($output)) or die("Could not write output\n");
// close sockets
socket_close($spawn);
}
socket_close($socket);
?>

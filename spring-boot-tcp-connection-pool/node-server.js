const net = require('net');

const server = net.createServer(function(socket) {
	socket.write('Echo server\r\n');
	socket.pipe(socket);
});

server.on('connection', function(socket) {
	console.log('A new connection has been established.');

	// Now that a TCP connection has been established, the server can send data to
	// the client by writing to its socket.
	socket.write('Hello, client.');

	// The server can also receive data from the client by reading from its socket.
	socket.on('data', function(chunk) {
			console.log(`Data received from client: ${chunk.toString()}.`);
	});

	socket.on('end', function() {
			console.log('Closing connection with the client');
	});

	// Don't forget to catch error, for your own sake.
	socket.on('error', function(err) {
			console.log(`Error: ${err}`);
	});
});

server.listen(9000, '127.0.0.1');


import express, {Request, Response}  from 'express';
var http = require('http');

const app = express();

app.get('/users', (request: Request, response: Response) => {
    return response.send('Hello World!');
});

http.createServer(app).listen(process.env.HTTP_SERVER_PORT || 5454, () => {
    console.log('HTTP Server running!');
}); 

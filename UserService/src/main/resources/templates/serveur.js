const express = require('express');
const jwt = require('jsonwebtoken');
const app = express();

app.post('/login', (req, res) => {
    // Check user credentials (not shown in this example)
    const user = { id: 1, username: 'exampleUser', role: 'user' };

    // Create JWT
    const token = jwt.sign(user, 'yourSecretKey');

    res.json({ token });
});

// Handle protected route
app.get('/protected', verifyToken, (req, res) => {
    res.json({ message: 'Protected route accessed successfully!' });
});

function verifyToken(req, res, next) {
    const token = req.headers.authorization;

    if (!token) {
        return res.sendStatus(403); // Forbidden
    }

    jwt.verify(token, 'yourSecretKey', (err, user) => {
        if (err) {
            return res.sendStatus(403);
        }
        req.user = user;
        next();
    });
}

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});

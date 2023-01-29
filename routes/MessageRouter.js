const express = require('express');
const controller = require('../Controllers/MessageController');

const route = express.Router();

route.post('/', controller.addMessage);

route.get('/:chatId', controller.getMessages);

module.exports = route 
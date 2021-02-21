const express = require('express');
const bodyParser = require('body-parser');
const productRouter = require('./routes/products');

function init(services) {
    const app = express();

    app.use(bodyParser.urlencoded({ extended: false }));
    app.use(bodyParser.json());

    app.use('/api/products', productRouter.init(services));

    return app;
}

module.exports = init;

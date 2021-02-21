const express = require('express');
const bodyParser = require('body-parser');
const productRouter = require('./routes/products');
const shoppingCartRouter = require('./routes/shoppingCart');

function init(services) {
    const app = express();

    app.use(bodyParser.urlencoded({ extended: false }));
    app.use(bodyParser.json());

    app.use('/api/products', productRouter.init(services));
    app.use('/api/shoppingcarts', shoppingCartRouter.init(services));

    return app;
}

module.exports = init;

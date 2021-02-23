const mongoose = require('mongoose');
const schemasFactory = require('../../src/infrastructure/db/schemas');

function createProductsDB(mockedProducts) {
    const schemas = schemasFactory.create(mongoose);
    const db = { schemas };

    new db.schemas.Product(mockedProducts);

    return schemas;
}

function createShoppingCartsDb(mockedProducts) {
    const schemas = schemasFactory.create(mongoose);
    const db = { schemas };

    new db.schemas.ShoppingCart();
    new db.schemas.Product(mockedProducts);

    return schemas;
}

function resetModels() {
    Object.keys(mongoose.connection.models).forEach((key) => delete mongoose.connection.models[key]);
}

module.exports = { createProductsDB, createShoppingCartsDb, resetModels };

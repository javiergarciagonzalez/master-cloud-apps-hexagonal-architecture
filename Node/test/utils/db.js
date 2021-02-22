const mongoose = require('mongoose');
const schemasFactory = require('../../src/adapters/infrastructure/db/schemas');

function createProductsDB(mockedProducts) {
    const schemas = schemasFactory.create(mongoose);
    const db = { schemas };

    new db.schemas.Product(mockedProducts);

    return schemas;
}

function createShoppingCartsDb(mockedShoppingCarts, mockedProducts) {
    const schemas = schemasFactory.create(mongoose);
    const db = { schemas };

    new db.schemas.ShoppingCart(mockedShoppingCarts);
    new db.schemas.Product(mockedProducts);

    return schemas;
}

function resetModels() {
    Object.keys(mongoose.connection.models).forEach((key) => delete mongoose.connection.models[key]);
}

module.exports = { createProductsDB, createShoppingCartsDb, resetModels };

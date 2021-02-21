const mongoose = require('mongoose');
const schemasFactory = require('../../src/adapters/infrastructure/db/schemas');

function createProductsDB(mockedProducts) {
    const schemas = schemasFactory.create(mongoose);
    const db = { schemas };

    new db.schemas.Product(mockedProducts);

    return schemas;
}

function resetModel(model) {
    delete mongoose.connection.models[model];
}

module.exports = { createProductsDB, resetModel };

const productSchema = require('./Product');
const shoppingCartSchema = require('./ShoppingCart');

const create = (mongoose) => ({
    Product: productSchema(mongoose),
    ShoppingCart: shoppingCartSchema(mongoose)
});

module.exports = { create };

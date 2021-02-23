const productSchema = require('./Product');
const shoppingCartSchema = require('./ShoppingCart');
const shoppingCartItemSchema = require('./ShoppingCartItem');

const create = (mongoose) => {
    const ShoppingCartItem = shoppingCartItemSchema(mongoose);

    return {
        Product: productSchema(mongoose),
        ShoppingCart: shoppingCartSchema(mongoose, ShoppingCartItem.ShoppingCartItem),
        ShoppingCartItem: ShoppingCartItem
    };
};

module.exports = { create };

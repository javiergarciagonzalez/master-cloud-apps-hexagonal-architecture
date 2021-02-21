const mapper = require('../../mapper');
const shoppingCartModel = require('../../../domain/shoppingCart/model');

async function createShoppingCart() {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.create({ products: [], status: 'started' });

        if (!shoppingCart) {
            throw new Error("Shopping cart couldn't been created.");
        }

        return mapper.toDomainModel(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function finishShoppingCart({ id }) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.updateOne({ _id: id }, { $set: { status: 'finished' } });

        if (!shoppingCart) {
            throw new Error(`Shopping cart with id: ${_id} couldn't been updated.`);
        }

        return mapper.toDomainModel(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw new Error(`Error finishing shopping cart with id: ${id}`);
    }
}

async function getShoppingCart({ id }) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.findOne({ _id: id });

        if (!shoppingCart) {
            throw new Error(`Shopping cart with id: ${_id} couldn't been updated.`);
        }

        return mapper.toDomainModel(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw new Error(`Error finishing shopping cart with id: ${id}`);
    }
}

async function addProductToCart(product) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();

        const shoppingCart = await shoppingCartSchema.find({});
        if (!shoppingCarts) {
            throw new Error('Not found: all shopping carts');
        }

        const queryResult = await shoppingCartSchema.update({ _id: shoppingCart._id }, { $push: { products: product } });
        if (!queryResult) {
            throw new Error(`Couldn't add the product with name: ${product.name}.`);
        }

        return mapper.toDomainModel(queryResult, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function removeProductFromCart(product) {}
async function removeCart(product) {}
async function finishCart(product) {}

const shoppingCartStore = {
    createShoppingCart,
    finishShoppingCart,
    getShoppingCart,
    addProductToCart,
    removeProductFromCart,
    removeCart,
    finishCart
};

function init({ ShoppingCart }) {
    return {
        ...shoppingCartStore,
        getSchemas() {
            return { ShoppingCart };
        }
    };
}

module.exports = { init };

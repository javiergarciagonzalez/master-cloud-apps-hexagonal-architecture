const mapper = require('../../mapper');
const ShoppingCartDomainModel = require('../../../domain/shoppingCart/model');
const ProductsDomainModel = require('../../../domain/products/model');

async function createShoppingCart() {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.create({ products: [], status: 'started' });

        if (!shoppingCart) {
            throw new Error("Shopping cart couldn't been created.");
        }

        return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
        throw error;
    }
}

async function finishShoppingCart({ id }, orderValidatorRepository) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const updatedStatus = orderValidatorRepository.validateOrder({ id }) ? 'finished' : 'started';
        const shoppingCart = await shoppingCartSchema.updateOne({ _id: id }, { $set: { status: updatedStatus } });

        if (!shoppingCart) {
            throw new Error(`Shopping cart with id: ${_id} couldn't been updated.`);
        }

        return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
        throw error;
    }
}

async function getShoppingCart({ id }) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.findOne({ _id: id });

        if (!shoppingCart) {
            throw new Error(`Shopping cart with id: ${_id} couldn't been updated.`);
        }

        return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
        throw new Error(`Error finishing shopping cart with id: ${id}`);
    }
}

async function removeShoppingCart({ id }) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.findOne({ _id: id });

        if (!shoppingCart) {
            throw new Error(`Product with id ${id} not found.`);
        }

        const { ok } = await shoppingCartSchema.deleteOne({ _id: shoppingCart.id });
        if (!ok) {
            throw new Error(`Shopping cart with id: ${id} couldn't be removed.`);
        }

        return mapper.toDomainModel(shoppingCart, ShoppingCartDomainModel);
    } catch (error) {
        throw error;
    }
}

async function addProductToCart(product) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();

        const shoppingCart = await shoppingCartSchema.find({});
        if (!shoppingCarts) {
            throw new Error('Not found: all shopping carts');
        }

        const queryResult = await shoppingCartSchema.updateOne({ _id: shoppingCart._id }, { $push: { products: product } });
        if (!queryResult) {
            throw new Error(`Couldn't add the product with name: ${product.name}.`);
        }

        return mapper.toDomainModel(queryResult, ShoppingCartDomainModel);
    } catch (error) {
        throw error;
    }
}

async function addProductToShoppingCart({ shoppingCartId, productId, quantity }) {
    try {
        const { ShoppingCart: shoppingCartSchema, Product: productSchema } = this.getSchemas();
        const product = await productSchema.findOne({ _id: productId });

        if (!product) {
            throw new Error(`Product with id: ${productId} not found. It couldn't been added to the shopping cart with id: ${shoppingCartId}`);
        }

        const shoppingCart = await shoppingCartSchema.findOne({ _id: shoppingCartId });

        if (!shoppingCart) {
            throw new Error(`Product with id: ${productId} couldn't been added to a not found shopping cart with id: ${shoppingCartId}`);
        }

        const indexFound = shoppingCart.items.findIndex((item) => item.productId == productId);

        if (indexFound !== -1 && quantity > 0) {
            shoppingCart.items[indexFound].quantity += quantity;
        }

        if (indexFound === -1) {
            shoppingCart.items.push({
                productId,
                quantity
            });
        }

        const result = await shoppingCartSchema.updateOne({ _id: shoppingCartId }, shoppingCart);

        if (!result) {
            throw new Error(`Product with id: ${productId} was not added to shopping chart with id: ${shoppingCartId}`);
        }

        return mapper.toDomainModel(result, ShoppingCartDomainModel);
    } catch (error) {
        throw error;
    }
}

async function deleteProductFromShoppingCart({ shoppingCartId, productId }) {
    try {
        const { ShoppingCart: shoppingCartSchema, Product: productSchema } = this.getSchemas();
        const product = await productSchema.findOne({ _id: productId });

        if (!product) {
            throw new Error(`Product with id: ${productId} not found. It couldn't been added to the shopping cart with id: ${shoppingCartId}`);
        }

        const shoppingCart = await shoppingCartSchema.findOne({ _id: shoppingCartId });

        if (!shoppingCart) {
            throw new Error(`Product with id: ${productId} couldn't been added to a not found shopping cart with id: ${shoppingCartId}`);
        }

        const indexFound = shoppingCart.items.findIndex((item) => item.productId == productId);

        if (indexFound === -1) {
            return null;
        }

        const updatedShoppingCart = {
            status: 'started',
            items: shoppingCart.items.filter((item) => item.productId !== productId)
        };
        const result = await shoppingCartSchema.updateOne({ _id: shoppingCartId }, updatedShoppingCart);

        if (!result) {
            throw new Error(`Product with id: ${productId} was not added to shopping chart with id: ${shoppingCartId}`);
        }

        return mapper.toDomainModel(result, ShoppingCartDomainModel);
    } catch (error) {
        throw error;
    }
}

const shoppingCartStore = {
    createShoppingCart,
    finishShoppingCart,
    getShoppingCart,
    removeShoppingCart,
    addProductToShoppingCart,
    deleteProductFromShoppingCart
};

function init({ ShoppingCart, Product }) {
    return {
        ...shoppingCartStore,
        getSchemas() {
            return { ShoppingCart, Product };
        }
    };
}

module.exports = { init };

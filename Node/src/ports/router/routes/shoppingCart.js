const express = require('express');

const router = express.Router();

function init({ shoppingCartService }) {
    router.post('/', async (req, res) => {
        const newShoppingCart = await shoppingCartService.createShoppingCart();

        return res.send(newShoppingCart);
    });

    router.patch('/:id', async (req, res) => {
        const { id } = req.params;
        const shoppingCart = await shoppingCartService.finishShoppingCart({ id });

        res.send(shoppingCart);
    });

    router.get('/:id', async (req, res) => {
        const { id } = req.params;
        const shoppingCart = await shoppingCartService.getShoppingCart({ id });

        res.send(shoppingCart);
    });

    router.delete('/:id', async (req, res) => {
        const { id } = req.params;
        const result = await shoppingCartService.removeShoppingCart({ id });

        return res.send(result);
    });

    router.post('/:id/product/:productId/quantity/:quantity', async (req, res, next) => {
        const { id: shoppingCartId, productId, quantity } = req.params;
        const result = await shoppingCartService.addProductToShoppingCart({ shoppingCartId, productId, quantity: parseInt(quantity, 10) });
        return res.send(result);
    });

    router.delete('/:id/product/:productId', async (req, res, next) => {
        const { id: shoppingCartId, productId } = req.params;
        const result = await shoppingCartService.deleteProductFromShoppingCart({ shoppingCartId, productId });
        return res.send(result);
    });

    return router;
}

module.exports = { init };

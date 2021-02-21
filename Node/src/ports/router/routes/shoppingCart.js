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

    return router;
}

module.exports = { init };

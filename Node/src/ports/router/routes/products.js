const express = require('express');

const router = express.Router();

function init({ productsService }) {
    router.get('/', async (req, res) => {
        const productsList = await productsService.getAllProducts();

        return res.send(productsList);
    });

    router.post('/', async (req, res) => {
        const newProduct = await productsService.createProduct({
            name: req.body.name,
            price: req.body.price
        });

        return res.send(newProduct);
    });

    router.get('/:id', async (req, res) => {
        const { id } = req.params;
        const product = await productsService.getProduct({ id });

        return res.send(product);
    });

    router.put('/:id', async (req, res, next) => {
        const { id } = req.params;
        const productById = await productsService.getProduct({ id });

        if (!productById) {
            const error = new Error(
                `Sorry, product with id < ${id} > not found.`
            );
            return next(error);
        }

        const { name, price } = req.body;

        const productToUpdate = { ...productById, name, price };

        const product = await productsService.updateProduct(productToUpdate);

        return res.send(product);
    });

    router.delete('/:id', async (req, res, next) => {
        const { id } = req.params;
        const product = await productsService.getProduct({ id });

        if (!product) {
            const error = new Error(
                `Sorry, product with id < ${id} > not found.`
            );
            return next(error);
        }

        const result = await productsService.deleteProduct(product);

        return res.send(result);
    });

    return router;
}

module.exports = { init };

const shoppingCartRepositoryContainer = require('../../src/repositories/shoppingCartRepository');
const { createShoppingCartsDb, resetModels } = require('../utils/db');
const mockedProducts = require('./mockedData/products');

let shoppingCartRepository;
let schemas;

describe('Product repository tests', () => {
    beforeEach(() => {
        resetModels();
        schemas = createShoppingCartsDb(mockedProducts);
        shoppingCartRepository = shoppingCartRepositoryContainer.init(schemas);

        schemas.ShoppingCart.create = jest.fn();
        schemas.ShoppingCart.findOne = jest.fn();
        schemas.ShoppingCart.updateOne = jest.fn();
        schemas.Product.findOne = jest.fn();
    });

    test('Should create a new shopping cart in the DB', async () => {
        const _id = 1;
        const shoppingCart = {
            items: [],
            status: 'started'
        };

        schemas.ShoppingCart.create.mockReturnValueOnce({ ...shoppingCart, _id });
        const response = await shoppingCartRepository.createShoppingCart(shoppingCart);

        expect(response.id).toBeDefined();
        expect(response.items).toBeDefined();
        expect(response.status).toBeDefined();
        expect(response.id).toBe(_id);
        expect(response.items).toBe(shoppingCart.items);
        expect(response.status).toBe(shoppingCart.status);
        expect(schemas.ShoppingCart.create).toHaveBeenCalledWith({});
    });

    test('Should add a new product to an existing shopping cart in the DB', async () => {
        const _id = 1;
        const shoppingCart = {
            items: [],
            status: 'started'
        };

        const product = mockedProducts[Math.floor(Math.random() * mockedProducts.length)]; // get random mocked product

        const updatedShoppingCart = {
            items: [{ productId: product.id, quantity: 3 }],
            status: 'started'
        };

        schemas.ShoppingCart.create.mockReturnValueOnce({ ...shoppingCart, _id });
        schemas.Product.findOne.mockReturnValueOnce(product);
        schemas.ShoppingCart.findOne.mockReturnValueOnce({ ...shoppingCart, _id });
        schemas.ShoppingCart.updateOne.mockReturnValueOnce({ ...updatedShoppingCart, _id });

        const responseCreate = await shoppingCartRepository.createShoppingCart(shoppingCart);

        const quantity = 3;
        const responseUpdateOne = await shoppingCartRepository.addProductToShoppingCart({ shoppingCartId: _id, productId: product.id, quantity });

        expect(responseCreate.id).toBeDefined();
        expect(responseCreate.items).toBeDefined();
        expect(responseCreate.status).toBeDefined();
        expect(responseCreate.id).toBe(_id);
        expect(responseCreate.items).toEqual(shoppingCart.items);
        expect(responseCreate.status).toBe(shoppingCart.status);
        expect(schemas.ShoppingCart.create).toHaveBeenCalledWith({});

        expect(responseUpdateOne.id).toBeDefined();
        expect(responseUpdateOne.items).toBeDefined();
        expect(responseUpdateOne.status).toBeDefined();
        expect(responseUpdateOne.id).toBe(_id);
        expect(responseUpdateOne.items).toEqual(updatedShoppingCart.items); // toEqual instead of toBe because toBe uses `Object.is` and that breaks ref: https://jestjs.io/docs/en/using-matchers
        expect(responseUpdateOne.status).toBe(updatedShoppingCart.status);
        expect(schemas.ShoppingCart.updateOne).toHaveBeenCalledWith({ _id }, updatedShoppingCart);
    });
});

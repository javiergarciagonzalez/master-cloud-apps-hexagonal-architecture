const productRepositoryContainer = require('../../../src/repositories/productRepository');
const { createProductsDB, resetModels } = require('../../utils/db');
const mockedProducts = require('./mockedData/products');

let productRepository;
let schemas;

describe('Product repository tests', () => {
    beforeEach(() => {
        resetModels();
        schemas = createProductsDB(mockedProducts);
        productRepository = productRepositoryContainer.init(schemas);

        schemas.Product.create = jest.fn();
        schemas.Product.deleteOne = jest.fn();
        schemas.Product.findOne = jest.fn();
    });

    test('Should create a new product in the DB', async () => {
        const _id = 1;
        const product = {
            name: 'a very new product',
            price: 42
        };

        schemas.Product.create.mockReturnValueOnce({ ...product, _id });
        const response = await productRepository.createProduct(product);

        expect(response.id).toBeDefined();
        expect(response.name).toBeDefined();
        expect(response.price).toBeDefined();
        expect(response.id).toBe(_id);
        expect(response.name).toBe(product.name);
        expect(response.price).toBe(product.price);
        expect(schemas.Product.create).toHaveBeenCalledWith(product);
    });

    test('Should delete a product in the DB', async () => {
        const product = mockedProducts[Math.floor(Math.random() * mockedProducts.length)]; // get random mocked product

        schemas.Product.deleteOne.mockReturnValueOnce({ ok: 1 });
        schemas.Product.findOne.mockReturnValueOnce({
            ...product,
            _id: product.id
        });

        const response = await productRepository.deleteProduct(product);

        expect(response.id).toBeDefined();
        expect(response.name).toBeDefined();
        expect(response.price).toBeDefined();
        expect(response.id).toBe(product.id);
        expect(response.name).toBe(product.name);
        expect(response.price).toBe(product.price);
        expect(schemas.Product.findOne).toHaveBeenCalledWith({
            _id: product.id
        });
        expect(schemas.Product.deleteOne).toHaveBeenCalledWith({ _id: product.id });
    });
});

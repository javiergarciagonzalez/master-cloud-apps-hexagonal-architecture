const productRepositoryContainer = require('../../../src/adapters/repositories/productRepository');
const { createProductsDB, resetModel } = require('../../utils/db');
const mockedProducts = require('./mockedData/products');

let productRepository;

let schemas;

function mockReturnValueOnce(functionToMock, value) {
    functionToMock.mockReturnValue(value);
}

describe('Product repository tests', () => {
    beforeEach(() => {
        resetModel('Product');
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

        mockReturnValueOnce(schemas.Product.create, { ...product, _id });
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
        const product =
            mockedProducts[Math.floor(Math.random() * mockedProducts.length)]; // get random mocked product

        mockReturnValueOnce(schemas.Product.deleteOne, { ok: 1 });
        mockReturnValueOnce(schemas.Product.findOne, {
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
        expect(schemas.Product.deleteOne).toHaveBeenCalledWith(
            { _id: product.id },
            {
                name: product.name,
                price: product.price
            }
        );
    });
});

const productRepositoryContainer = require('../../../src/adapters/repositories/productRepository');
const { createProductsDB } = require('../../utils/db');
const mockedProducts = require('./mockedData/products');

let productRepository;

const schemas = createProductsDB(mockedProducts);

function mockReturnValueOnce(functionToMock, value) {
    functionToMock.mockReturnValue(value);
}

describe('Product repository tests', () => {
    beforeAll(() => {
        productRepository = productRepositoryContainer.init(schemas);

        schemas.Product.create = jest.fn();
        schemas.Product.deleteOne = jest.fn();
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
});

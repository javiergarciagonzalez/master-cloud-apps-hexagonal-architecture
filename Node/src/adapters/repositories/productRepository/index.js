const mapper = require('../../mapper');
const ProductDomainModel = require('../../../domain/products/model');

async function getAllProducts() {
    try {
        console.log('THIS SCHEMAS', this.getSchemas());
        const { Product: productSchema } = this.getSchemas();

        try {
            console.log(await productSchema.create({ name: 'foo', price: 2 }));
        } catch (err) {
            console.log(err);
        }

        const productsQuery = await productSchema.find({});

        if (!products) {
            throw new Error('Not found: all products');
        }

        return products.map((product) =>
            mapper.toDomainModel(product, ProductDomainModel)
        );
    } catch (error) {
        throw error;
    }
}

async function getProduct(options) {
    try {
        const { Product: productSchema } = this.getSchemas();
        const { id } = options;

        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product with id ${id} not found.`);
        }

        return mapper.toDomainModel(product, ProductDomainModel);
    } catch (error) {
        throw error;
    }
}

async function updateProduct(options) {
    try {
        const { Product: productSchema } = this.getSchemas();
        const { id, name, price } = options;

        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product with id ${id} not found.`);
        }
        const productToUpdate = { ...product, ...{ _id: id, name, price } };
        const updatedProduct = await productSchema.save(productToUpdate);

        return mapper.toDomainModel(updatedProduct, ProductDomainModel);
    } catch (error) {
        throw error;
        2;
    }
}

async function deleteProduct() {}

const productStore = {
    getAllProducts,
    getProduct,
    updateProduct,
    deleteProduct
};

function init({ Product }) {
    console.log('=-=-=-', Product);
    return {
        ...productStore,
        getSchemas() {
            return { Product };
        }
    };
}

module.exports = { init };

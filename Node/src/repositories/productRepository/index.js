const mapper = require('../../mapper');
const ProductsDomainModel = require('../../domain/products/model');

async function getAllProducts() {
    try {
        const { Product: productSchema } = this.getSchemas();

        const products = await productSchema.find({});

        if (!products) {
            throw new Error('Not found: all products');
        }

        return products.map((product) => mapper.toDomainModel(product, ProductsDomainModel));
    } catch (error) {
        throw error;
    }
}

async function getProduct({ id }) {
    try {
        const { Product: productSchema } = this.getSchemas();

        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product with id ${id} not found.`);
        }

        return mapper.toDomainModel(product, ProductsDomainModel);
    } catch (error) {
        throw error;
    }
}

async function createProduct({ name, price }) {
    const { Product: productSchema } = this.getSchemas();

    try {
        const product = await productSchema.create({ name, price });
        if (!product) {
            throw new Error(`Product with id ${id} not found.`);
        }
        return mapper.toDomainModel(product, ProductsDomainModel);
    } catch (error) {
        throw error;
    }
}

async function updateProduct({ id, name, price }) {
    const { Product: productSchema } = this.getSchemas();

    try {
        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product with id ${id} not found.`);
        }

        const { ok } = await productSchema.updateOne({ _id: product._id }, { name, price });

        if (!ok) {
            throw new Error(`Product with id: ${id} couldn't be updated.`);
        }

        return mapper.toDomainModel({ _id: id, name, price }, ProductsDomainModel);
    } catch (error) {
        throw error;
    }
}

async function deleteProduct({ id }) {
    const { Product: productSchema } = this.getSchemas();

    try {
        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product with id ${id} not found.`);
        }

        const { ok } = await productSchema.deleteOne({ _id: product._id });

        if (!ok) {
            throw new Error(`Product with id: ${id} couldn't be removed.`);
        }

        return mapper.toDomainModel({ _id: id, name: product.name, price: product.price }, ProductsDomainModel);
    } catch (error) {
        throw error;
    }
}

const productStore = {
    getAllProducts,
    getProduct,
    createProduct,
    updateProduct,
    deleteProduct
};

function init({ Product }) {
    return {
        ...productStore,
        getSchemas() {
            return { Product };
        }
    };
}

module.exports = { init };

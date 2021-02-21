const productSchema = require('./Product');

const create = (mongoose) => ({ Product: productSchema(mongoose) });

module.exports = { create };

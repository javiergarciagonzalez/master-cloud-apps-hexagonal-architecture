const create = (mongoose) => {
    const shoppingCartSchema = mongoose.Schema({
        products: {
            type: Array,
            default: [],
            required: true
        },
        status: {
            type: String,
            required: true
        }
    });

    return mongoose.model('ShoppingCart', shoppingCartSchema);
};

module.exports = create;

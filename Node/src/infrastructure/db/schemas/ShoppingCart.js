const create = (mongoose, ShoppingCartItem) => {
    const shoppingCartSchema = mongoose.Schema({
        items: {
            type: [ShoppingCartItem],
            default: [],
            required: true
        },
        status: {
            type: String,
            default: 'started',
            required: true
        }
    });

    return mongoose.model('ShoppingCart', shoppingCartSchema);
};

module.exports = create;

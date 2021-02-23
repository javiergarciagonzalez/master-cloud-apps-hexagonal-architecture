const create = (mongoose) => {
    const shoppingCartItemSchema = mongoose.Schema({
        productId: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'Product',
            required: true
        },
        quantity: {
            type: Number,
            min: [1, 'Quantity can not be less than 1'],
            required: true
        }
    });

    return mongoose.model('ShoppingCartItem', shoppingCartItemSchema);
};

module.exports = create;

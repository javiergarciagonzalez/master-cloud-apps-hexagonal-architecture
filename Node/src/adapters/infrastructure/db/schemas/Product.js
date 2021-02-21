const create = (mongoose) => {
    const productSchema = mongoose.Schema({
        name: {
            type: String,
            required: true
        },
        price: {
            type: Number,
            required: true
        }
    });

    return mongoose.model('Product', productSchema);
};

module.exports = create;

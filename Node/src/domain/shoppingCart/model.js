class ShoppingCart {
    constructor({ _id, products, status }) {
        this.id = _id;
        this.products = products;
        this.status = status;
    }
}

module.exports = ShoppingCart;

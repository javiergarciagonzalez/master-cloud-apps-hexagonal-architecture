function init({ shoppingCartRepository }) {
    return {
        createShoppingCart: () => shoppingCartRepository.createShoppingCart(),
        finishShoppingCart: (data) => shoppingCartRepository.finishShoppingCart(data),
        getShoppingCart: (data) => shoppingCartRepository.getShoppingCart(data),
        addProductToCart: (product) => shoppingCartRepository.addProductToCart(product),
        removeProductFromCart: (product) => shoppingCartRepository.removeProductFromCart(product),
        removeCart: (product) => shoppingCartRepository.removeCart(product),
        finishCart: (product) => shoppingCartRepository.finishCart(product)
    };
}

module.exports = { init };

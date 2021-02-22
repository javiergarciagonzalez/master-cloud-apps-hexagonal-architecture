function init({ shoppingCartRepository, orderValidatorRepository }) {
    return {
        createShoppingCart: () => shoppingCartRepository.createShoppingCart(),
        finishShoppingCart: (data) => shoppingCartRepository.finishShoppingCart(data, orderValidatorRepository),
        getShoppingCart: (data) => shoppingCartRepository.getShoppingCart(data),
        removeShoppingCart: (data) => shoppingCartRepository.removeShoppingCart(data),
        addProductToShoppingCart: (data) => shoppingCartRepository.addProductToShoppingCart(data),
        deleteProductFromShoppingCart: (data) => shoppingCartRepository.deleteProductFromShoppingCart(data)
    };
}

module.exports = { init };

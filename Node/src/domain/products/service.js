function init({ productsRepository }) {
    return {
        getAllProducts: () => productsRepository.getAllProducts(),
        getProduct: (data) => productsRepository.getProduct(data),
        createProduct: (data) => productsRepository.createProduct(data),
        updateProduct: (data) => productsRepository.updateProduct(data),
        deleteProduct: (data) => productsRepository.deleteProduct(data)
    };
}

module.exports = { init };

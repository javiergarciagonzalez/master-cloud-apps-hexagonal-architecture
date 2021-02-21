function init({ productsRepository }) {
    async function getAllProducts() {
        return productsRepository.getAllProducts();
    }

    return {
        getAllProducts
    };
}

module.exports = { init };

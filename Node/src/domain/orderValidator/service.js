function init({ orderValidatorRepository }) {
    return {
        validateOrder: (order) => orderValidatorRepository.validateOrder(order)
    };
}

module.exports = { init };

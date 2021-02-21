function validateOrder(order) {
    console.log(`Validating order with id: ${order.id}`);
    return Math.random() < 0.5;
}

const orderValidatorStore = {
    validateOrder
};

function init({ OrderValidator }) {
    return {
        ...orderValidatorStore,
        getSchemas() {
            return { OrderValidator };
        }
    };
}

module.exports = { init };

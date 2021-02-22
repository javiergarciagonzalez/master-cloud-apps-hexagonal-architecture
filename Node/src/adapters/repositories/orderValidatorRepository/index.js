function validateOrder(order) {
    console.log(`Validating order with id: ${order.id}`);
    return Math.random() < 0.5;
}

function init() {
    return {
        validateOrder
    };
}

module.exports = { init };

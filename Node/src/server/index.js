const { serverPort } = require('../config');
const { dbConnectionString } = require('../config');

const initAppContainer = require('../ports/router/app');
const { init: initDB } = require('../adapters/infrastructure/db');
const db = initDB()({ dbConnectionString });

const productRepositoryContainer = require('../adapters/repositories/productRepository');
const productServiceContainer = require('../domain/products/service');

console.log('DB schemas', db.schemas);

const productsRepository = productRepositoryContainer.init(db.schemas);
const productsService = productServiceContainer.init({ productsRepository });

const services = { productsService };

const app = initAppContainer(services);

const server = app.listen(serverPort);
console.log(`Server listening on port: ${serverPort}`);

(async () => {
    try {
        await db.connect();
    } catch (error) {
        await shutdown();
    }
})();

module.exports = server;

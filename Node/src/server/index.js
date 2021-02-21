const { serverPort } = require('../config');
const { dbConnectionString } = require('../config');

const initAppContainer = require('../ports/router/app');

const productRepositoryContainer = require('../adapters/repositories/productRepository');
const productServiceContainer = require('../domain/products/service');

const {
    init: initDB,
    connect: connectDB
} = require('../adapters/infrastructure/db');

const db = initDB()({ dbConnectionString });

const productsRepository = productRepositoryContainer.init(db.schemas);
const productsService = productServiceContainer.init({ productsRepository });

const services = { productsService };

const app = initAppContainer(services);

const server = app.listen(serverPort, () => {
    console.log(`Server listening on port: ${serverPort}`);
});

connectDB(db);

module.exports = server;

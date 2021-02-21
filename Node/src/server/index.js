const { serverPort } = require('../config');
const { dbConnectionString } = require('../config');

const initAppContainer = require('../ports/router/app');

const productsRepositoryContainer = require('../adapters/repositories/productRepository');
const productsServiceContainer = require('../domain/products/service');

const shoppingCartRepositoryContainer = require('../adapters/repositories/shoppingCartRepository');
const shoppingCartServiceContainer = require('../domain/shoppingCart/service');

const { init: initDB, connect: connectDB } = require('../adapters/infrastructure/db');

const db = initDB()({ dbConnectionString });

const productsRepository = productsRepositoryContainer.init(db.schemas);
const productsService = productsServiceContainer.init({ productsRepository });

const shoppingCartRepository = shoppingCartRepositoryContainer.init(db.schemas);
const shoppingCartService = shoppingCartServiceContainer.init({ shoppingCartRepository });

const services = { productsService, shoppingCartService };

const app = initAppContainer(services);

const server = app.listen(serverPort, () => {
    console.log(`Server listening on port: ${serverPort}`);
});

connectDB(db);

module.exports = server;

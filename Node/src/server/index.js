const { serverPort } = require('../config');
const { dbConnectionString } = require('../config');

const initAppContainer = require('../ports/router/app');

const productsRepositoryContainer = require('../adapters/repositories/productRepository');
const productsServiceContainer = require('../domain/products/service');

const shoppingCartRepositoryContainer = require('../adapters/repositories/shoppingCartRepository');
const shoppingCartServiceContainer = require('../domain/shoppingCart/service');

const orderValidatorRepositoryContainer = require('../adapters/repositories/orderValidatorRepository');
const orderValidatorServiceContainer = require('../domain/orderValidator/service');

const { init: initDB, connect: connectDB } = require('../adapters/infrastructure/db');

const db = initDB()({ dbConnectionString });

const productsRepository = productsRepositoryContainer.init(db.schemas);
const shoppingCartRepository = shoppingCartRepositoryContainer.init(db.schemas);
const orderValidatorRepository = orderValidatorRepositoryContainer.init();

const productsService = productsServiceContainer.init({ productsRepository });
const orderValidatorService = orderValidatorServiceContainer.init({ orderValidatorRepository });
const shoppingCartService = shoppingCartServiceContainer.init({ shoppingCartRepository, orderValidatorRepository });

const services = { productsService, shoppingCartService, orderValidatorService };

const app = initAppContainer(services);

const server = app.listen(serverPort, () => {
    console.log(`Server listening on port: ${serverPort}`);
});

connectDB(db);

module.exports = server;

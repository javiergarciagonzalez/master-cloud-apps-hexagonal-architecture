const { serverPort } = require('../config');
const { dbConnectionString } = require('../config');

const initAppContainer = require('../router/app');

const productsRepositoryContainer = require('../repositories/productRepository');
const productsServiceContainer = require('../domain/products/service');

const shoppingCartRepositoryContainer = require('../repositories/shoppingCartRepository');
const shoppingCartServiceContainer = require('../domain/shoppingCart/service');

const orderValidatorRepositoryContainer = require('../repositories/orderValidatorRepository');
const orderValidatorServiceContainer = require('../domain/orderValidator/service');

const { init: initDB, connect: connectDB } = require('../infrastructure/db');

const db = initDB()({ dbConnectionString });

const productsRepository = productsRepositoryContainer.init(db.schemas);
const shoppingCartRepository = shoppingCartRepositoryContainer.init(db.schemas);
const orderValidatorRepository = orderValidatorRepositoryContainer.init();

const productsService = productsServiceContainer.init({ productsRepository });
const shoppingCartService = shoppingCartServiceContainer.init({ shoppingCartRepository, orderValidatorRepository });
const orderValidatorService = orderValidatorServiceContainer.init({ orderValidatorRepository });

const services = { productsService, shoppingCartService, orderValidatorService };

const app = initAppContainer(services);

const server = app.listen(serverPort, () => {
    console.log(`Server listening on port: ${serverPort}`);
});

connectDB(db);

module.exports = server;

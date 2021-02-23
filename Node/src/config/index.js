require('dotenv').config();

const config = {
    serverPort: process.env.SERVER_PORT,
    dbConnectionString: process.env.DATABASE_URL
};

module.exports = config;

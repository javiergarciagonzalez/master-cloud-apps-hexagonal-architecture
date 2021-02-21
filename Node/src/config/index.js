require('dotenv').config();

const config = {
    serverPort: process.env.SERVER_PORT || 8080,
    dbConnectionString:
        process.env.DATABASE_URL || 'mongodb://localhost:27017/shop'
};

module.exports = config;

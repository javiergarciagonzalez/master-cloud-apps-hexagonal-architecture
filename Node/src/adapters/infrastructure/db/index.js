const mongoose = require('mongoose');

const schemasFactory = require('./schemas');

function init() {
    return function ({ dbConnectionString }) {
        if (!dbConnectionString) {
            throw new Error('dbConnectionString not provided');
        }

        mongoose.connection.on('error', (error) => {
            console.log(`Error! DB Connection failed. ${error.toString()}`);
        });

        mongoose.connection.once('open', () => {
            console.log('Connection to MongoDB established');
        });

        mongoose.connection.on('disconnected', () => {
            console.log('Connection to MongoDB closed');
            console.log('-------------------');
        });

        const schemas = schemasFactory.create(mongoose);

        console.log('SSSSSSSChemas', schemas.Product);

        return {
            ...{
                getConnection() {
                    return mongoose.connection;
                },
                connect() {
                    return mongoose.connect(dbConnectionString);
                },
                close() {
                    return mongoose.connection.close();
                }
            },
            schemas
        };
    };
}

module.exports = { init };

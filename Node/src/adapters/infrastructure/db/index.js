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

        return {
            ...{
                getConnection() {
                    return mongoose.connection;
                },
                connect() {
                    return mongoose.connect(dbConnectionString, {
                        useNewUrlParser: true,
                        useUnifiedTopology: true
                    });
                },
                close() {
                    return mongoose.connection.close();
                }
            },
            schemas
        };
    };
}

async function connect(db) {
    try {
        await db.connect();
    } catch (error) {
        await db.close();
    }
}

module.exports = { init, connect };

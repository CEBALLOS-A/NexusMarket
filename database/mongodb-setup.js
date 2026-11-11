// Run with mongosh. The application can also create these indexes when testing the connection.
use NexusMarket;
db.users.createIndex({ id: 1 }, { unique: true });
db.users.createIndex({ email: 1 }, { unique: true });
db.products.createIndex({ id: 1 }, { unique: true });

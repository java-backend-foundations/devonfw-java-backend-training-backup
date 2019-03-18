INSERT INTO Item(id, ModificationCounter, name, description, price) VALUES (21, 0, 'spaghetti bolognese', 'Italy', 250);
INSERT INTO Item(id, ModificationCounter, name, description, price) VALUES (22, 0, 'pizza margherita', 'Italy', 200);
INSERT INTO Item(id, ModificationCounter, name, description, price) VALUES (23, 0, 'tacos', 'Mexico', 50);
INSERT INTO Item(id, ModificationCounter, name, description, price) VALUES (24, 0, 'fish and chips', 'UK', 300);
INSERT INTO Item(id, ModificationCounter, name, description, price) VALUES (25, 0, 'dumplings', 'Poland', 199.99);

INSERT INTO Customer(id, ModificationCounter, firstname, lastname) VALUES (31, 0, 'John', 'Travolta');
INSERT INTO Customer(id, ModificationCounter, firstname, lastname) VALUES (32, 0, 'Marylin', 'Monroe');
INSERT INTO Customer(id, ModificationCounter, firstname, lastname) VALUES (33, 0, 'Elton', 'John');
INSERT INTO Customer(id, ModificationCounter, firstname, lastname) VALUES (34, 0, 'Elvis', 'Presley');

INSERT INTO OrderSummary(id, ModificationCounter, price, ownerId, creationDate, status) VALUES (41, 0, 671.10, 31, '2019-03-15', 'SERVED');
INSERT INTO OrderSummary(id, ModificationCounter, price, ownerId, creationDate, status) VALUES (42, 0, 425, 31, '2018-02-20', 'SERVED');
INSERT INTO OrderSummary(id, ModificationCounter, price, ownerId, creationDate, status) VALUES (43, 0, 45, 33, '2019-04-05', 'CANCELLED');
INSERT INTO OrderSummary(id, ModificationCounter, price, ownerId, creationDate, status) VALUES (44, 0, 250, 33, '2019-04-06', 'NEW');
INSERT INTO OrderSummary(id, ModificationCounter, price, ownerId, creationDate, status) VALUES (45, 0, 399.99, 32, '2019-04-02', 'PAID');
INSERT INTO OrderSummary(id, ModificationCounter, price, ownerId, creationDate, status) VALUES (46, 0, 210, 32, '2019-04-03', 'PAID');

INSERT INTO OrderPosition(orderId, itemId) VALUES (41, 22);
INSERT INTO OrderPosition(orderId, itemId) VALUES (41, 22);
INSERT INTO OrderPosition(orderId, itemId) VALUES (41, 24);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 24);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (42, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (43, 23);
INSERT INTO OrderPosition(orderId, itemId) VALUES (43, 23);
INSERT INTO OrderPosition(orderId, itemId) VALUES (44, 23);
INSERT INTO OrderPosition(orderId, itemId) VALUES (44, 21);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 25);
INSERT INTO OrderPosition(orderId, itemId) VALUES (45, 22);
INSERT INTO OrderPosition(orderId, itemId) VALUES (46, 22);










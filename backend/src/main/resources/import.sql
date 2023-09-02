-- @formatter:off
INSERT INTO client (username, password, balance) VALUES ('joao', '123abc', 1000);
INSERT INTO client (username, password, balance) VALUES ('vitor', 'abc123', 1000);
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('AAPL', 'Apple Inc.', 190, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('MSFT', 'Microsoft Corporation', 330, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('2222', 'SAUDI ARABIAN OIL CO.', 30, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('GOOG', 'Alphabet Inc.', 120, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('AMZN', 'Amazon.com, Inc.', 130, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('NVDA', 'NVIDIA Corporation', 425, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('TSLA', 'Tesla, Inc.', 270, NOW());
INSERT INTO stock (symbol, name, price, modified_at) VALUES ('META', 'Meta Platforms, Inc.', 290, NOW());
INSERT INTO transaction (client_id, stock_id, shares, total, created_at) VALUES (1, 1, 5, -950, NOW());
INSERT INTO transaction (client_id, stock_id, shares, total, created_at) VALUES (1, 2, 2, -660, NOW());
INSERT INTO transaction (client_id, stock_id, shares, total, created_at) VALUES (2, 3, 1, -190, NOW());
INSERT INTO client_stock (client_id, stock_id, shares) VALUES (1, 1, 5);
INSERT INTO client_stock (client_id, stock_id, shares) VALUES (1, 2, 2);
INSERT INTO client_stock (client_id, stock_id, shares) VALUES (2, 3, 1);

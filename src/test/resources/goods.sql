DELETE FROM shelved_sku;
DELETE FROM shelve;
DELETE FROM sku;
DELETE FROM hibernate_sequences;

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('default', 100);

INSERT INTO sku(`id`, `name`, `price`, `quantity`, `sold`, `status`)
VALUES
(1, '铁三角头戴式耳机', '1350.00', 10, 0, 0),
(2, 'MacBook Pro 15.5 in', '15000', 10, 0, 0),
(3, 'Samsung Galaxy Tablet', '3100', 10, 0, 0),
(4, 'Laugh创意茶杯', '20', 10, 0, 0);

INSERT INTO shelve (`id`, `name`) VALUES (1, '1212精品推荐');
INSERT INTO shelved_sku (`id`, `shelve_id`, `sku_id`, `status`, `created_date`, `modified_date`)
VALUES
(1, 1, 1, 0, '2017-12-11 21:59:00', '2017-12-11 21:59:00'),
(2, 1, 2, 0, '2017-12-11 21:59:00', '2017-12-11 21:59:00'),
(3, 1, 3, 0, '2017-12-11 21:59:00', '2017-12-11 21:59:00');
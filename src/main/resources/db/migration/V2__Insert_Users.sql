
INSERT INTO t_user (id, username, email, password) VALUES (1, 'Hlib', 'glego@mail.com', 'Gleg0');
INSERT INTO t_user (id, username, email, password) VALUES (2, 'Pips', 'pips@mail.com', '123Ivan123');

SELECT pg_catalog.setval(pg_get_serial_sequence('t_user', 'id'), 3, true);

INSERT INTO t_user_role (user_id, role) VALUES (1, 'USER');
INSERT INTO t_user_role (user_id, role) VALUES (2, 'USER');
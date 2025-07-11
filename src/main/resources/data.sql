INSERT INTO users (username, password, enabled) VALUES (
    'Maga', '1005', TRUE
) ON CONFLICT (username) DO NOTHING;
INSERT INTO authorities (username, authority) VALUES(
    'Maga', 'ROLE_ADMIN'
) ON CONFLICT (username, authority) DO NOTHING;
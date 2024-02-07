INSERT INTO users(id, archive, email, name, password, role)
values (1, false, 'mail@mail', 'admin', '$2a$12$n0siDuuwCO3z9OZvc1NGGOvclVlmq9uTWCcKqtP3Misyw1ThQrqgO', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;
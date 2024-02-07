INSERT INTO users(id, archive, email, name, password, role, bucket_id)
values (1, false, 'mail@mail', 'admin', 'pass', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;
INSERT INTO user (username, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enable)
VALUES ( -- password
           'user1', '{bcrypt}$2a$12$JG6r8yi2yHSYHNgoaQHJOeEhTS9uKavwaNWiNaEFXGVfpJU4l4MIe', 1, 1, 1, 1);

INSERT INTO user_authority (user_id, authority)
VALUES (1, 'ADMIN'),
       (1, 'USER');
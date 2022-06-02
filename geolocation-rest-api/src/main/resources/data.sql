INSERT INTO DEVICE_TYPE (Name) VALUES ('Mobile phone');
INSERT INTO DEVICE_TYPE (Name) VALUES ('Notebook');
INSERT INTO DEVICE_TYPE (Name) VALUES ('GPS');
INSERT INTO DEVICE_TYPE (Name) VALUES ('Tablet');

INSERT INTO DEVICE (Name, device_type_id) VALUES ('Iphone 13', 1);
INSERT INTO DEVICE (Name, device_type_id) VALUES ('Lenovo y540', 2);
INSERT INTO DEVICE (Name, device_type_id) VALUES ('Navi', 3);
INSERT INTO DEVICE (Name, device_type_id) VALUES ('Ipad Pro', 3);

INSERT INTO authorities (name)
values ('ROLE_USER');

INSERT INTO users (username, password, enabled)
  values ('user',
    '$2y$10$dGlkyfKzipeFW9b6x35jGeavb7yfAPB774fJgJE.2c97uzpA1mnk2',
    1);

INSERT INTO user_authority (user_id, authorities_id)
values (1,1);






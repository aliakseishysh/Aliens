CREATE DATABASE aliens_web_project;

USE aliens_web_project;

CREATE TABLE aliens (
    alien_id INT AUTO_INCREMENT NOT NULL,
    _name VARCHAR(255),
    description_small VARCHAR(255),
    description_full VARCHAR(3000),
    image_url VARCHAR(255),
    PRIMARY KEY (alien_id)
);

CREATE TABLE roles (
    role_id INT AUTO_INCREMENT NOT NULL,
    _name VARCHAR(255),
    PRIMARY KEY (role_id)
);

CREATE TABLE users (
    user_id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) UNIQUE,
    login_name VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255),
    image_url VARCHAR(255),
    role_id INT,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE ratings (
    rate_id INT AUTO_INCREMENT NOT NULL,
    alien_id INT,
    user_id INT,
    rate_value INT,
    PRIMARY KEY (rate_id),
    FOREIGN KEY (alien_id) REFERENCES aliens(alien_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    
);



INSERT INTO aliens (_name, description_small, description_full, image_url) values 
        (
            'BABA_YAGA', 
            'Very good woman', 
            'Very good woman with coockies!', 
            '/images/alien/baba_yaga.png'
        );
INSERT INTO aliens (_name, description_small, description_full, image_url) values  
    (
        'LESHIY',
        'Lives in forest',
        'Lives in forest of despair!',
        '/images/alien/leshiy.png'
    );
INSERT INTO aliens (_name, description_small, description_full, image_url) values 
    (
        'SONIC_X',
        'Very fast',
        'Very fast! Speeed, speeeeeeeeeeeeeeed, SPEEEEEEEEEEEEEEEEEEEEEED!!!',
        '/images/alien/sonic_x.png'
    );
INSERT INTO aliens (_name, description_small, description_full, image_url) values 
    (
        'ODIN',
        'He is a god',
        'With a fork in the eye!',
        '/images/alien/odin.png'
    );
INSERT INTO aliens (_name, description_small, description_full, image_url) values 
    (
        'DOOMGUY',
        'RUN',
        'RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR!!!',
        '/images/alien/doomguy.png'
    );

INSERT INTO roles (_name) values ('admin');
INSERT INTO roles (_name) values ('user');

INSERT INTO users (email, login_name, password_hash, image_url, role_id) values ('admin@gmail.com', 'admin', 'admin', '/images/profile/image1.png', 1);
INSERT INTO users (email, login_name, password_hash, image_url, role_id) values ('user@gmail.com', 'user', 'user', '/images/profile/image2.png', 2);

INSERT INTO ratings (alien_id, user_id, rate_value) values (1, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (2, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (3, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (4, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (5, 1, 5);



-- DROP DATABASE aliens_web_project;
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

CREATE TABLE users (
    user_id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) UNIQUE,
    login_name VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255),
    image_url VARCHAR(255),
    role_type ENUM('ADMIN', 'USER'),
    _status ENUM('NORMAL', 'BANNED'),
    banned_to_datetime DATETIME,
    PRIMARY KEY (user_id)
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

CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT NOT NULL,
    alien_id INT,
    user_id INT,
    comment VARCHAR(3000),
    comment_status ENUM('NORMAL','DELETED'),
    PRIMARY KEY (comment_id),
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

INSERT INTO aliens (_name, description_small, description_full, image_url) values 
    (
        'DOOMGUY2',
        'RUN',
        'RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR!!!',
        '/images/alien/doomguy.png'
    );

INSERT INTO aliens (_name, description_small, description_full, image_url) values 
    (
        'DOOMGUY3',
        'RUN',
        'RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR!!!',
        '/images/alien/doomguy.png'
    );

INSERT INTO users (email, login_name, password_hash, image_url, role_type, _status) values ('admin@gmail.com', 'admin', 'admin', '/images/profile/image1.png', 'ADMIN', 'NORMAL');
INSERT INTO users (email, login_name, password_hash, image_url, role_type, _status) values ('user@gmail.com', 'user', 'user', '/images/profile/image2.png', 'USER', 'NORMAL');
INSERT INTO users (email, login_name, password_hash, image_url, role_type, _status) values ('user2@gmail.com', 'user2', 'user2', '/images/profile/image2.png', 'USER', 'NORMAL');

INSERT INTO ratings (alien_id, user_id, rate_value) values (1, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (2, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (3, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (4, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (5, 1, 5);

INSERT INTO comments (alien_id, user_id, comment, comment_status) values (1, 3, 'First comment', 'NORMAL');
INSERT INTO comments (alien_id, user_id, comment, comment_status) values (1, 3, 'Second comment', 'NORMAL');
INSERT INTO comments (alien_id, user_id, comment, comment_status) values (1, 3, 'Third comment - should be deleted', 'DELETED');



-- DROP DATABASE aliens_web_project;
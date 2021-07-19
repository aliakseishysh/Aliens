CREATE DATABASE aliens_web_project;

USE aliens_web_project;

CREATE TABLE aliens (
    alien_id INT AUTO_INCREMENT NOT NULL,
    _name VARCHAR(255),
    _status ENUM('NORMAL', 'UNDER_CONSIDERATION', 'DECLINED'),
    description_small VARCHAR(255),
    description_full VARCHAR(3000),
    image_url VARCHAR(255),
    PRIMARY KEY (alien_id)
);

CREATE TABLE aliens_images (
    alien_image_id INT AUTO_INCREMENT NOT NULL,
    alien_id INT,
    image_url VARCHAR(255),
    _status ENUM('NORMAL', 'UNDER_CONSIDERATION', 'DECLINED'),
    PRIMARY KEY (alien_image_id),
    FOREIGN KEY (alien_id) REFERENCES aliens(alien_id)
);

CREATE TABLE users (
    user_id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) UNIQUE,
    login_name VARCHAR(255) UNIQUE,
    password_hash VARCHAR(64),
    salt VARCHAR(32),
    image_url VARCHAR(255),
    role_type ENUM('ADMIN', 'USER'),
    _status ENUM('NORMAL', 'BANNED', 'CONFIRMATION_AWAITING'),
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

CREATE TABLE tokens (
    token_id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255),
    token VARCHAR(255),
    _status ENUM('NORMAL', 'EXPIRED'),
    expiration_date DATETIME,
    PRIMARY KEY (token_id)
);


INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
        (
            'BABA_YAGA', 
            'NORMAL',
            'Very good woman', 
            'Very good woman with coockies!', 
            '/images/alien/baba_yaga.png'
        );
INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
    (
        'LESHIY',
        'NORMAL',
        'Lives in forest',
        'Lives in forest of despair!',
        '/images/alien/leshiy.png'
    );
INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
    (
        'SONIC_X',
        'NORMAL',
        'Very fast',
        'Very fast! Speeed, speeeeeeeeeeeeeeed, SPEEEEEEEEEEEEEEEEEEEEEED!!!',
        '/images/alien/sonic_x.png'
    );
INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
    (
        'ODIN',
        'NORMAL',
        'He is a god',
        'With a fork in the eye!',
        '/images/alien/odin.png'
    );
INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
    (
        'DOOMGUY',
        'NORMAL',
        'RUN',
        'RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR!!!',
        '/images/alien/doomguy.png'
    );

INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
    (
        'DOOMGUY2',
        'NORMAL',
        'RUN',
        'RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR!!!',
        '/images/alien/doomguy.png'
    );

INSERT INTO aliens (_name, _status, description_small, description_full, image_url) values 
    (
        'DOOMGUY3',
        'NORMAL',
        'RUN',
        'RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! 
        RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR! RIP AND TEAR!!!',
        '/images/alien/doomguy.png'
    );

INSERT INTO aliens_images (alien_id, image_url, _status) values (1, '/images/alien/baba_yaga.png', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (1, '/images/alien/baba_yaga_2.jpg', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (2, '/images/alien/leshiy.png', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (3, '/images/alien/sonic_x.png', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (4, '/images/alien/odin.png', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (5, '/images/alien/doomguy.png', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (6, '/images/alien/doomguy.png', 'NORMAL');
INSERT INTO aliens_images (alien_id, image_url, _status) values (7, '/images/alien/doomguy.png', 'NORMAL');





INSERT INTO users (email, login_name, password_hash, salt, image_url, role_type, _status) values 
    ('adminadmin@gmail.com', 'adminadmin', 
    '4B550B61BEFF0E6FDB2180D2BADF33C17302393E774E8F002C35374999763851', 
    '7DF1AFE4D9A87B5C478AC62F7AE9F0DD', 
    '/images/profile/image1.png', 'ADMIN', 'NORMAL');
INSERT INTO users (email, login_name, password_hash, salt, image_url, role_type, _status) values 
    ('useruser@gmail.com', 'useruser', 
    '9D082D077A7BD762FF8F44D49629A692E4B5E49C547A7876777BE3F307DE47D7', 
    'B91831BF494C8EFD1B61D61CF2AEBB6B', 
    '/images/profile/image2.png', 'USER', 'NORMAL');
INSERT INTO users (email, login_name, password_hash, salt, image_url, role_type, _status) values 
    ('useruser2@gmail.com', 'useruser2', 
    'B44146E14699B80F806D03A55FDA9E5C866B6A9BB5BDA59CBFA9428BE7C55C1E', 
    '6DDBB7844D456355DA46ED15BE604CA7', 
    '/images/profile/image2.png', 'USER', 'NORMAL');

INSERT INTO ratings (alien_id, user_id, rate_value) values (1, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (2, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (3, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (4, 1, 5);
INSERT INTO ratings (alien_id, user_id, rate_value) values (5, 1, 5);

INSERT INTO comments (alien_id, user_id, comment, comment_status) values (1, 3, 'First comment', 'NORMAL');
INSERT INTO comments (alien_id, user_id, comment, comment_status) values (1, 3, 'Second comment', 'NORMAL');
INSERT INTO comments (alien_id, user_id, comment, comment_status) values (1, 3, 'Third comment - should be deleted', 'DELETED');



-- DROP DATABASE aliens_web_project;
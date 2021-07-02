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
    password_hash VARCHAR(64),
    salt VARCHAR(32),
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

INSERT INTO users (email, login_name, password_hash, salt, image_url, role_type, _status) values 
    ('admin@gmail.com', 'admin', 
    'A306D6868E3278FE292D68DB6C0094C807E3740BFD65790E78A6F8AB8D6C5A81', 
    'BCB199973DF5D469C5F7BF85A8E7B1D9', 
    '/images/profile/image1.png', 'ADMIN', 'NORMAL');
INSERT INTO users (email, login_name, password_hash, salt, image_url, role_type, _status) values 
    ('user@gmail.com', 'user', 
    '06FF9839BB393F7F33956A63109838BA38ECD65402C57A3DCC47CCDFCB1D0149', 
    '15EC1E7E078AE352E74E8716F526CA04', 
    '/images/profile/image2.png', 'USER', 'NORMAL');
INSERT INTO users (email, login_name, password_hash, salt, image_url, role_type, _status) values 
    ('user2@gmail.com', 'user2', 
    '9D551E262A06D29F9742DE4B10A5F722B350E9EDAE10CB55246619166EF0F160', 
    'B890E012C1DD8F1B32CB4384ADD85F37', 
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

CREATE TABLE usuarios (
    userid UUID PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    isactive BOOLEAN,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastlogin TIMESTAMP
);

CREATE TABLE fonos (
    phoneid BIGINT PRIMARY KEY AUTO_INCREMENT,
    userid UUID,
    VARCHAR (5) countrycode,
    SMALLINT citycode,
    BIGINT number,
    FOREIGN KEY (userid) REFERENCES usuarios(userid)
);
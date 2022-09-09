CREATE TABLE product
(
    id               BIGINT NOT NULL,
    amount_available INT    NOT NULL,
    cost             INT    NOT NULL,
    product_name     VARCHAR(255),
    seller_id        BIGINT,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE role
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE transaction
(
    id                 BIGINT NOT NULL,
    transaction_type   VARCHAR(255),
    user_id            BIGINT,
    transaction_amount INT    NOT NULL,
    CONSTRAINT "pk_transactıon" PRIMARY KEY (id)
);

CREATE TABLE user_ent
(
    id       BIGINT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    deposit  INT    NOT NULL,
    CONSTRAINT pk_userent PRIMARY KEY (id)
);

CREATE TABLE user_ent_role
(
    user_ent_id BIGINT NOT NULL,
    role_id     BIGINT NOT NULL,
    CONSTRAINT pk_userent_role PRIMARY KEY (user_ent_id, role_id)
);

ALTER TABLE user_ent_role
    ADD CONSTRAINT "uc_user_ent_role_role_ıd" UNIQUE (role_id);

ALTER TABLE user_ent_role
    ADD CONSTRAINT fk_useentrol_on_role FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE user_ent_role
    ADD CONSTRAINT fk_useentrol_on_user_ent FOREIGN KEY (user_ent_id) REFERENCES user_ent (id);
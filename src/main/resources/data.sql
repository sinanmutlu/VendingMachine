INSERT INTO role (id, name ) VALUES
  (1,'SELLER'),
  (2, 'BUYER');

INSERT INTO USER_ENT (id, USERNAME, PASSWORD, DEPOSIT, ROLE) VALUES
                                                           (1,'sinan', 'mutlu', 500, "BUYER,SELLER"),
                                                           (2, 'snn', 'mtl', 700, "BUYER");

INSERT INTO PRODUCT (ID, AMOUNT_AVAILABLE, COST, PRODUCT_NAME, SELLER_ID) VALUES ( 1, 100, 20, 'water', 1 );

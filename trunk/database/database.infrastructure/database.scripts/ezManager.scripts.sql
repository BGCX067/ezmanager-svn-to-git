-- Generated by Oracle SQL Developer Data Modeler Version: 2.0.0 Build: 584
--   at:        2010-04-16 21:30:12
--   site:      Oracle Database 10g
--   type:      Oracle Database 10g



CREATE TABLE address 
    ( 
     address_id NUMBER (10)  NOT NULL , 
     street_id NUMBER (10)  NOT NULL , 
     street_number NUMBER (5) , 
     remark VARCHAR2 (500) , 
     zip VARCHAR2 (20)  NOT NULL , 
     b_active CHAR (1)  NOT NULL 
    ) 
;



ALTER TABLE address 
    ADD CONSTRAINT address_PK PRIMARY KEY ( address_id ) ;


CREATE TABLE base_currency 
    ( 
     currency_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL 
    ) 
;



ALTER TABLE base_currency 
    ADD CONSTRAINT base_currency_PK PRIMARY KEY ( currency_id ) ;


CREATE TABLE base_status 
    ( 
     status_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL 
    ) 
;



ALTER TABLE base_status 
    ADD CONSTRAINT base_status_PK PRIMARY KEY ( status_id ) ;


CREATE TABLE base_vat 
    ( 
     vat_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL , 
     value NUMBER (4,2)  NOT NULL , 
     currency_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE base_vat 
    ADD CONSTRAINT base_vat_PK PRIMARY KEY ( vat_id ) ;


CREATE TABLE city 
    ( 
     city_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL , 
     state_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE city 
    ADD CONSTRAINT city_PK PRIMARY KEY ( city_id ) ;


CREATE TABLE cost 
    ( 
     cost_id NUMBER (10)  NOT NULL , 
     task_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100) , 
     amount_proposed NUMBER (11,2) , 
     vat_id NUMBER (10)  NOT NULL , 
     total_hours NUMBER (8,2) 
    ) 
;



ALTER TABLE cost 
    ADD CONSTRAINT cost_PK PRIMARY KEY ( cost_id ) ;


CREATE TABLE country 
    ( 
     country_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL 
    ) 
;



ALTER TABLE country 
    ADD CONSTRAINT country_PK PRIMARY KEY ( country_id ) ;


CREATE TABLE customer 
    ( 
     customer_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL 
    ) 
;



ALTER TABLE customer 
    ADD CONSTRAINT customer_PK PRIMARY KEY ( customer_id ) ;


CREATE TABLE customer_address 
    ( 
     customer_id NUMBER (10)  NOT NULL , 
     address_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE customer_address 
    ADD CONSTRAINT customer_address_PK PRIMARY KEY ( customer_id, address_id ) ;


CREATE TABLE executed_task 
    ( 
     executed_task_id NUMBER (10)  NOT NULL , 
     task_id NUMBER (10)  NOT NULL , 
     total_hours NUMBER (8,2)  NOT NULL , 
     amount NUMBER (11,2) , 
     start_date DATE , 
     end_date DATE , 
     executed_by_user_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE executed_task 
    ADD CONSTRAINT executed_task_PK PRIMARY KEY ( executed_task_id ) ;


CREATE TABLE invoice 
    ( 
     invoice_id NUMBER (10)  NOT NULL , 
     executed_task_id NUMBER (10)  NOT NULL , 
     position NUMBER (10) 
    ) 
;



ALTER TABLE invoice 
    ADD CONSTRAINT invoice_PK PRIMARY KEY ( invoice_id ) ;


CREATE TABLE invoice_customer 
    ( 
     invoice_id NUMBER (10)  NOT NULL , 
     customer_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE invoice_customer 
    ADD CONSTRAINT invoice_customer_PK PRIMARY KEY ( invoice_id, customer_id ) ;


CREATE TABLE project 
    ( 
     project_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL , 
     status_id NUMBER (10)  NOT NULL , 
     start_date DATE , 
     end_date DATE 
    ) 
;



ALTER TABLE project 
    ADD CONSTRAINT project_PK PRIMARY KEY ( project_id ) ;


CREATE TABLE project_customer 
    ( 
     customer_id NUMBER (10)  NOT NULL , 
     project_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE project_customer 
    ADD CONSTRAINT project_customer_PK PRIMARY KEY ( customer_id, project_id ) ;


CREATE TABLE role_identity 
    ( 
     role_identity_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (20)  NOT NULL 
    ) 
;



ALTER TABLE role_identity 
    ADD CONSTRAINT role_identity_PK PRIMARY KEY ( role_identity_id ) ;


CREATE TABLE state 
    ( 
     state_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (50)  NOT NULL , 
     country_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE state 
    ADD CONSTRAINT state_PK PRIMARY KEY ( state_id ) ;


CREATE TABLE street 
    ( 
     street_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (200)  NOT NULL , 
     city_id NUMBER (10)  NOT NULL , 
     remark VARCHAR2 (500) 
    ) 
;



ALTER TABLE street 
    ADD CONSTRAINT street_PK PRIMARY KEY ( street_id ) ;


CREATE TABLE task 
    ( 
     task_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (100)  NOT NULL , 
     project_id NUMBER (10)  NOT NULL , 
     status_id NUMBER (10)  NOT NULL , 
     position NUMBER (10)  NOT NULL , 
     start_date DATE , 
     end_date DATE 
    ) 
;



ALTER TABLE task 
    ADD CONSTRAINT task_PK PRIMARY KEY ( task_id ) ;


CREATE TABLE user_identity 
    ( 
     user_identity_id NUMBER (10)  NOT NULL , 
     name VARCHAR2 (50)  NOT NULL , 
     password VARCHAR2 (50)  NOT NULL , 
     is_active CHAR (1)  NOT NULL 
    ) 
;



ALTER TABLE user_identity 
    ADD CONSTRAINT user_identity_PK PRIMARY KEY ( user_identity_id ) ;


CREATE TABLE user_role 
    ( 
     user_identity_user_identity_id NUMBER (10)  NOT NULL , 
     role_identity_role_identity_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE user_role 
    ADD CONSTRAINT user_role_PK PRIMARY KEY ( user_identity_user_identity_id, role_identity_role_identity_id ) ;


CREATE TABLE user_task 
    ( 
     task_task_id NUMBER (10)  NOT NULL , 
     user_identity_user_identity_id NUMBER (10)  NOT NULL 
    ) 
;



ALTER TABLE user_task 
    ADD CONSTRAINT user_task_PK PRIMARY KEY ( task_task_id, user_identity_user_identity_id ) ;



ALTER TABLE address 
    ADD CONSTRAINT address_street_FK FOREIGN KEY 
    ( 
     street_id
    ) 
    REFERENCES street 
    ( 
     street_id
    ) 
;


ALTER TABLE base_vat 
    ADD CONSTRAINT base_vat_base_currency_FK FOREIGN KEY 
    ( 
     currency_id
    ) 
    REFERENCES base_currency 
    ( 
     currency_id
    ) 
;


ALTER TABLE city 
    ADD CONSTRAINT city_state_FK FOREIGN KEY 
    ( 
     state_id
    ) 
    REFERENCES state 
    ( 
     state_id
    ) 
;


ALTER TABLE cost 
    ADD CONSTRAINT cost_base_vat_FK FOREIGN KEY 
    ( 
     vat_id
    ) 
    REFERENCES base_vat 
    ( 
     vat_id
    ) 
;


ALTER TABLE cost 
    ADD CONSTRAINT cost_task_FK FOREIGN KEY 
    ( 
     task_id
    ) 
    REFERENCES task 
    ( 
     task_id
    ) 
;


ALTER TABLE customer_address 
    ADD CONSTRAINT customer_address_address_FK FOREIGN KEY 
    ( 
     address_id
    ) 
    REFERENCES address 
    ( 
     address_id
    ) 
;


ALTER TABLE customer_address 
    ADD CONSTRAINT customer_address_customer_FK FOREIGN KEY 
    ( 
     customer_id
    ) 
    REFERENCES customer 
    ( 
     customer_id
    ) 
;


ALTER TABLE executed_task 
    ADD CONSTRAINT executed_task_task_FK FOREIGN KEY 
    ( 
     task_id
    ) 
    REFERENCES task 
    ( 
     task_id
    ) 
;


ALTER TABLE executed_task 
    ADD CONSTRAINT executed_task_user_identity_FK FOREIGN KEY 
    ( 
     executed_by_user_id
    ) 
    REFERENCES user_identity 
    ( 
     user_identity_id
    ) 
;


ALTER TABLE invoice_customer 
    ADD CONSTRAINT invoice_customer_customer_FK FOREIGN KEY 
    ( 
     customer_id
    ) 
    REFERENCES customer 
    ( 
     customer_id
    ) 
;


ALTER TABLE invoice_customer 
    ADD CONSTRAINT invoice_customer_invoice_FK FOREIGN KEY 
    ( 
     invoice_id
    ) 
    REFERENCES invoice 
    ( 
     invoice_id
    ) 
;


ALTER TABLE invoice 
    ADD CONSTRAINT invoice_executed_task_FK FOREIGN KEY 
    ( 
     executed_task_id
    ) 
    REFERENCES executed_task 
    ( 
     executed_task_id
    ) 
;


ALTER TABLE project 
    ADD CONSTRAINT project_base_status_FK FOREIGN KEY 
    ( 
     status_id
    ) 
    REFERENCES base_status 
    ( 
     status_id
    ) 
;


ALTER TABLE project_customer 
    ADD CONSTRAINT project_customer_customer_FK FOREIGN KEY 
    ( 
     customer_id
    ) 
    REFERENCES customer 
    ( 
     customer_id
    ) 
;


ALTER TABLE project_customer 
    ADD CONSTRAINT project_customer_project_FK FOREIGN KEY 
    ( 
     project_id
    ) 
    REFERENCES project 
    ( 
     project_id
    ) 
;


ALTER TABLE state 
    ADD CONSTRAINT state_country_FK FOREIGN KEY 
    ( 
     country_id
    ) 
    REFERENCES country 
    ( 
     country_id
    ) 
;


ALTER TABLE street 
    ADD CONSTRAINT street_city_FK FOREIGN KEY 
    ( 
     city_id
    ) 
    REFERENCES city 
    ( 
     city_id
    ) 
;


ALTER TABLE task 
    ADD CONSTRAINT task_base_status_FK FOREIGN KEY 
    ( 
     status_id
    ) 
    REFERENCES base_status 
    ( 
     status_id
    ) 
;


ALTER TABLE task 
    ADD CONSTRAINT task_project_FK FOREIGN KEY 
    ( 
     project_id
    ) 
    REFERENCES project 
    ( 
     project_id
    ) 
;


ALTER TABLE user_role 
    ADD CONSTRAINT user_role_role_identity_FK FOREIGN KEY 
    ( 
     role_identity_role_identity_id
    ) 
    REFERENCES role_identity 
    ( 
     role_identity_id
    ) 
;


ALTER TABLE user_role 
    ADD CONSTRAINT user_role_user_identity_FK FOREIGN KEY 
    ( 
     user_identity_user_identity_id
    ) 
    REFERENCES user_identity 
    ( 
     user_identity_id
    ) 
;


ALTER TABLE user_task 
    ADD CONSTRAINT user_task_task_FK FOREIGN KEY 
    ( 
     task_task_id
    ) 
    REFERENCES task 
    ( 
     task_id
    ) 
;


ALTER TABLE user_task 
    ADD CONSTRAINT user_task_user_identity_FK FOREIGN KEY 
    ( 
     user_identity_user_identity_id
    ) 
    REFERENCES user_identity 
    ( 
     user_identity_id
    ) 
;


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            21
-- CREATE INDEX                             0
-- ALTER TABLE                             44
-- CREATE VIEW                              0
-- CREATE PROCEDURE                         0
-- CREATE TRIGGER                           0
-- CREATE STRUCTURED TYPE                   0
-- CREATE COLLECTION TYPE                   0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE SNAPSHOT                          0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

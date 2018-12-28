
create table BANK_USER(
    USER_ID NUMBER(10) PRIMARY KEY,
    FIRST_NAME VARCHAR2(50) not null,
    LAST_NAME varchar2(50) not null,
    USER_NAME varchar2(50) not null unique,
    PASS_WORD varchar2(50) not null
);

create table ACCOUNT_TYPE(
    TYPE_ID NUMBER(10) primary key,
    ACCOUNT_TYPE varchar2(50) not null
);

create table BANK_ACCOUNT(
    ACCOUNT_ID number(10) primary key,
    TYPE_ID number(10) not null,
    USER_ID number(10) not null,
    BALANCE number(10,2) not null,
    FOREIGN KEY(USER_ID) REFERENCES BANK_USER(USER_ID),
    foreign key(TYPE_ID) references ACCOUNT_TYPE(TYPE_ID)
);

select * from bank_account;
select * from bank_user;
delete from account_type where type_id = 1;
delete from bank_account where account_id = 1040;
delete from bank_user where user_id = 42;
select * from customer;
commit;
create sequence ACCOUNT_TYPE_SEQ
start with 1
increment by 1;

create or replace trigger ACCOUNT_TYPE_TRIG
before insert on ACCOUNT_TYPE
for each row
begin
    
    select ACCOUNT_TYPE_SEQ.nextval into : NEW.TYPE_ID from dual;
    
end;
/

create sequence bank_user_seq
start with 1
increment by 1;

create or replace trigger bank_user_trig
before insert on bank_user
for each row
begin
    select bank_user_seq.nextval into : NEW.user_id from dual;
end;
/




insert into account_type(account_type) values('Saving');
commit;
select * from bank_account;


select * from bank_user;

commit;


create sequence bank_account_seq
start with 1000
increment by 1;

create or replace trigger bank_account_trig
before insert on bank_account
for each row
begin
    select bank_account_seq.nextval into : NEW.account_id from dual;
end;
/



----stored procedure to return all books
create or replace procedure get_all_books
(book_cursor OUT SYS_REFCURSOR)
AS
BEGIN
OPEN book_cursor FOR select * from bn_book;
end;
/

create or replace procedure get_account_type (TYPEID IN Number, TYPE_CURSOR OUT SYS_REFCURSOR)
AS
BEGIN
open type_cursor for select type_id, account_type from account_type where type_id = TYPEID;
end;
/


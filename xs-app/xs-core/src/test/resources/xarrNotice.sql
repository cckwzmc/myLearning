call drop_table('X_ARRNOTICE')
\

create table X_ARRNOTICE
(
  ID              NUMBER not null,
  USER_ID         NUMBER,
  USER_NAME       VARCHAR2(255),
  CATENTRY_ID     NUMBER not null,
  CHECKED_CONTENT VARCHAR2(256) not null,
  CHECKED_TYPE    NUMBER(1) not null,
  STATUS          NUMBER(1) default 0 not null,
  RECOMMEND_FLAG  NUMBER(1) default 0 not null,
  CREATE_DATE     DATE default sysdate not null,
  SEND_DATE       DATE,
  SEND_TIMES      NUMBER(2) default 0,
  REMARK          VARCHAR2(200),
  FIELD1          NUMBER,
  FIELD2          VARCHAR2(255),
  FIELD3          VARCHAR2(50),
  OPTCOUNTER      INTEGER,
  SELLTYPE        INTEGER default 5
)
\

alter table X_ARRNOTICE add constraint X_ARRNOTICE_PK primary key (ID)
\

BEGIN
  EXECUTE IMMEDIATE 'drop sequence x_arrnotice_seq';
EXCEPTION
  WHEN OTHERS THEN
    IF sqlcode != -2289 THEN RAISE; END IF;
END;
\

create sequence x_arrnotice_seq
\


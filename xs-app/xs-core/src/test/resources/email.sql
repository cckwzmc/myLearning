call drop_table('X_EMAIL_RECORD')
\

create table X_EMAIL_RECORD
(
  ID               NUMBER(12) not null,
  USER_ID          NUMBER(12),
  EMAIL_TYPE       INTEGER,
  EMAIL_ADDRESS    VARCHAR2(100),
  ACTIVESTATUS     INTEGER,
  EMAIL_TOPIC      VARCHAR2(256),
  EMAIL_CONTENT    VARCHAR2(512),
  SEND_TIME        TIMESTAMP(6),
  LAST_UPDATE_TIME TIMESTAMP(6)
)
\

alter table X_EMAIL_RECORD add constraint X_EMAIL_RECORD_PK primary key (ID)
\

BEGIN
  EXECUTE IMMEDIATE 'drop sequence X_EMAIL_RECORD_SEQ';
EXCEPTION
  WHEN OTHERS THEN
    IF sqlcode != -2289 THEN RAISE; END IF;
END;
\

create sequence X_EMAIL_RECORD_SEQ
\

create index inx_email_EMAILADDRESS on X_EMAIL_RECORD(EMAIL_ADDRESS)
\
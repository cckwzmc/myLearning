call drop_table('X_SMS_RECORD')
\

create table X_SMS_RECORD
(
  ID               NUMBER not null,
  TYPE             INTEGER,
  MOBILE           VARCHAR2(11),
  USER_ID          NUMBER(12) not null,
  ACTIVESTATUS     INTEGER,
  SMS_CONTENT      VARCHAR2(256),
  SEND_TIME        TIMESTAMP(6),
  LAST_UPDATE_TIME TIMESTAMP(6)
)
\

alter table X_SMS_RECORD add constraint X_SMS_RECORD_PK primary key (ID)
\

BEGIN
  EXECUTE IMMEDIATE 'drop sequence X_SMS_RECORD_SEQ';
EXCEPTION
  WHEN OTHERS THEN
    IF sqlcode != -2289 THEN RAISE; END IF;
END;
\

create sequence X_SMS_RECORD_SEQ
\

create index inx_sms_mobile on x_sms_record (MOBILE)
\
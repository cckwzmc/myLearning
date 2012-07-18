call drop_table('IITEM')
\
create table IITEM
(
  CATENTRY_ID  NUMBER not null,
  IITEMLIST_ID NUMBER not null,
  MEMBER_ID    NUMBER not null,
  STOREENT_ID  INTEGER not null,
  LASTUPDATE   TIMESTAMP(6),
  SEQUENCE     NUMBER default 0 not null,
  FIELD1       INTEGER,
  FIELD2       NUMBER(20,5),
  QUANTITY     NUMBER,
  FIELD3       VARCHAR2(254),
  OPTCOUNTER   INTEGER
)
\
alter table IITEM
  add primary key (IITEMLIST_ID, CATENTRY_ID)
\

create index I0000584 on IITEM (CATENTRY_ID)
\
create index I0000585 on IITEM (STOREENT_ID)
\
create index I0000586 on IITEM (MEMBER_ID)
\
BEGIN
  EXECUTE IMMEDIATE 'drop sequence iitem_seq';
EXCEPTION
  WHEN OTHERS THEN
    IF sqlcode != -2289 THEN RAISE; END IF;
END;
\

create sequence iitem_seq
minvalue 1
maxvalue 999999999999999999999999999
start with 191
increment by 1
cache 10
\

call drop_table('IITEMLIST')
\

create table IITEMLIST
(
  IITEMLIST_ID NUMBER not null,
  MEMBER_ID    NUMBER not null,
  DESCRIPTION  VARCHAR2(254) default '' not null,
  SEQUENCE     NUMBER default 0 not null,
  LASTUPDATE   TIMESTAMP(6),
  OPTCOUNTER   INTEGER
)
\
alter table IITEMLIST add primary key (IITEMLIST_ID)
\
create unique index I0000132 on IITEMLIST (IITEMLIST_ID, SEQUENCE, MEMBER_ID)
\
create unique index I0000133 on IITEMLIST (IITEMLIST_ID, MEMBER_ID)
\
create index I0000587 on IITEMLIST (MEMBER_ID)
\

BEGIN
  EXECUTE IMMEDIATE 'drop sequence iitemlist_seq';
EXCEPTION
  WHEN OTHERS THEN
    IF sqlcode != -2289 THEN RAISE; END IF;
END;
\

create sequence iitemlist_seq
minvalue 1
maxvalue 999999999999999999999999999
start with 191
increment by 1
cache 10
\
alter table IITEM
  add constraint F_379 foreign key (IITEMLIST_ID)
  references IITEMLIST (IITEMLIST_ID) on delete cascade
\
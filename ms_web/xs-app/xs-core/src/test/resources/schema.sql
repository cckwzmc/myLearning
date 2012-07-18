create or replace procedure drop_table(tab_name in varchar2) is
  qry_string varchar2(4000);
  cnt        number;
begin
  select count(1)
    into cnt
    from user_tables
   where lower(table_name) = lower(tab_name);
  if cnt > 0 then
    qry_string := 'DROP TABLE ' || tab_name;
    begin
      EXECUTE IMMEDIATE qry_string;
    exception
      when others then
        dbms_output.put_line('Error occured while dropping ' || sqlerrm);
    end;
  else
    dbms_output.put_line('TABLE ' || tab_name || ' does not exist');
  end if;
end;
\

call drop_table('users')
\

create table users (
    id number(8) not null,
    username varchar(20) not null unique,
    password varchar(50),
    email varchar(50) unique,
    phone_number varchar(20),
    version integer not null,
    primary key (id)
)
\

BEGIN
  EXECUTE IMMEDIATE 'drop sequence users_seq';
EXCEPTION
  WHEN OTHERS THEN
    IF sqlcode != -2289 THEN RAISE; END IF;
END;
\

create sequence users_seq
\

call drop_table('appconfigs')
\

 create table appconfigs (
     id number not null,
     name  varchar2(20) not null,
     key   varchar2(50) not null,
     value varchar2(254),
     description varchar2(254),
     primary key (id),
     constraint sys_uq_key_appconfigs unique (name, key)
 )
\


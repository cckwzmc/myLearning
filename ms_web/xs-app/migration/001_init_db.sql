 create table appconfigs (
     id number not null,
     name  varchar2(20) not null,
     key   varchar2(50) not null,
     value varchar2(254),
     description varchar2(254),
     primary key (id),
     constraint sys_uq_key_appconfigs unique (name, key)
 );



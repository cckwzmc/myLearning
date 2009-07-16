create table fetchurls(
id int(10) not null primary key,
url char(255) not null,
status char(50) not null,
type char(40) not null,
fromsitename char(100),
lasterarticle char(120)
)ENGINE=MyISAM DEFAULT CHARSET=utf8;
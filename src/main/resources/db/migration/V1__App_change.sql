Create table securities(
    id integer primary key,
    secid varchar(66) ,
    regnumber varchar(66),
    name varchar(120),
    emitent_title varchar(240),
    unique(secid)
);

Create table histories(
    id integer primary key auto_increment,
    secid varchar(66),
    tradedate date,
    numtrades integer,
    open double,
    close double,
    foreign key (secid) references securities(secid) on delete cascade
);
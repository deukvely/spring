drop table tbl_temp;
create table tbl_temp
    (temp number);

select * from tbl_temp;
delete from tbl_temp;
commit;
drop table tbl_board;

create table tbl_board(
    bno varchar2(50) primary key,
    title varchar2(1000) not null,
    contents varchar2(4000),
    writer varchar2(500) not null,
    regdate date default sysdate not null,
    updatedate date default sysdate,
    image varchar2(4000)
);

insert into tbl_board(bno, title, contents, writer) values ('002', 'Hello, World!', 'Common Comment', 'Any');

commit;
select * from tbl_board where bno = '001' or bno = '001' and writer = 'Any';
select * from tbl_board;

update tbl_board
set image = 'potter.png'
where bno = '002';
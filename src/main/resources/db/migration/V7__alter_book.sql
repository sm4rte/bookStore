delete from book;
alter table book rename column author to author_id;
alter table book alter column author_id type bigint USING author_id::bigint;
alter table book add constraint FK_AuthorId foreign key (author_id) references author(id);
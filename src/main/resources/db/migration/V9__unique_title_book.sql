delete from book;
alter table book add constraint UQTitle unique(title);
CREATE TABLE supply_activity_log
(
    id                UUID                 DEFAULT uuid_generate_v4(),
    quantity          int,
    update_mode       text,
    created_at        timestamptz not null DEFAULT now(),
    source_updated_at timestamptz not null DEFAULT now(),
    created_day       date        not null,
    constraint supply_activity_log_primary_for_partition primary key (id, created_at)
) partition by range (created_at);
-- Instead of created_at, if we put created day, then we can test the whole thing partitioned properly

create table supply_activity_log_y2024m03 partition of supply_activity_log
    for values from ('2024-03-01') TO ('2024-04-01');

create table supply_activity_log_y2024m02 partition of supply_activity_log
    for values from ('2024-02-01') TO ('2024-03-01');

create table supply_activity_log_y2024m01 partition of supply_activity_log
    for values from ('2024-01-01') TO ('2024-02-01');

insert into supply_activity_log (quantity,
                                 update_mode,
                                 created_day)
values (8, 'FULL_SYNC', '2024-03-20');

insert into supply_activity_log (quantity,
                                 update_mode,
                                 created_day)
values (2, 'SUPPLY_UPDATE', '2024-02-12');

insert into supply_activity_log (quantity,
                                 update_mode,
                                 created_day)
values (4, 'SUPPLY_UPDATE', '2024-02-1');


insert into supply_activity_log (quantity,
                                 update_mode,
                                 created_day)
values (18, 'FULL_SYNC', '2024-01-1');

select *
from supply_activity_log_y2024m03;
select *
from supply_activity_log_y2024m02;
select *
from supply_activity_log_y2024m01;
select *
from supply_activity_log;

insert into supply_activity_log (quantity,
                                 update_mode,
                                 created_day)
values (8, 'FULL_SYNC', '2024-03-20');


alter table supply_activity_log
    detach partition supply_activity_log4_y2024m01;

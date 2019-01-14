insert into OJUG_MEMB (member_id, first_name, last_name, join_date)
values
(1, 'Babe', 'Cafe', '1992-01-01'),
(2, 'Jessica', 'Codr', '2009-10-01'),
(3, 'Sun', 'Oracle', '2010-01-27');

insert into OJUG_MEETINGS (meeting_id, meeting_date, meeting_topic)
values
(1, '2018-12-01', 'Advent of Code'),
(2, '2019-01-15', 'Lightning Talks'),
(3, '2019-02-19', 'Something Awesome');

insert into OJUG_MEET_ATTEND (meeting_id, member_id, presented)
values
(1, 1, 'Y'),
(1, 2, 'N'),
(2, 1, 'N'),
(2, 2, 'Y'),
(2, 3, 'Y');
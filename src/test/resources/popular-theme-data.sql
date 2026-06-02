-- 조회 기간: 2026-04-29 ~ 2026-05-05
-- 기대 순위:
-- Theme 1: 기간 내 예약 10개
-- Theme 2: 기간 내 예약 9개
-- Theme 3: 기간 내 예약 8개
-- Theme 4: 기간 내 예약 7개
-- Theme 5: 기간 내 예약 6개
-- Theme 6: 기간 내 예약 5개
-- Theme 7: 기간 내 예약 4개
-- Theme 8: 기간 내 예약 3개
-- Theme 9: 기간 내 예약 2개
-- Theme 10: 기간 내 예약 1개
-- Theme 11: 기간 밖 예약만 있음
-- Theme 12: 예약 없음

INSERT INTO theme (id, name, description, thumbnail)
VALUES (1, 'Theme 1', 'Popular theme rank 1', 'https://example.com/theme-1.png'),
       (2, 'Theme 2', 'Popular theme rank 2', 'https://example.com/theme-2.png'),
       (3, 'Theme 3', 'Popular theme rank 3', 'https://example.com/theme-3.png'),
       (4, 'Theme 4', 'Popular theme rank 4', 'https://example.com/theme-4.png'),
       (5, 'Theme 5', 'Popular theme rank 5', 'https://example.com/theme-5.png'),
       (6, 'Theme 6', 'Popular theme rank 6', 'https://example.com/theme-6.png'),
       (7, 'Theme 7', 'Popular theme rank 7', 'https://example.com/theme-7.png'),
       (8, 'Theme 8', 'Popular theme rank 8', 'https://example.com/theme-8.png'),
       (9, 'Theme 9', 'Popular theme rank 9', 'https://example.com/theme-9.png'),
       (10, 'Theme 10', 'Popular theme rank 10', 'https://example.com/theme-10.png'),
       (11, 'Theme 11', 'Out of range reservations only', 'https://example.com/theme-11.png'),
       (12, 'Theme 12', 'No reservations', 'https://example.com/theme-12.png');

INSERT INTO reservation_time (id, start_at)
VALUES (1, '10:00:00'),
       (2, '12:00:00'),
       (3, '14:00:00'),
       (4, '16:00:00'),
       (5, '18:00:00'),
       (6, '20:00:00');

-- Theme 1: 기간 내 예약 10개
INSERT INTO reservation_slot (id, date, time_id, theme_id) VALUES
    (1, '2026-04-29', 1, 1),
    (2, '2026-04-29', 2, 1),
    (3, '2026-04-30', 1, 1),
    (4, '2026-04-30', 2, 1),
    (5, '2026-05-01', 1, 1),
    (6, '2026-05-01', 2, 1),
    (7, '2026-05-02', 1, 1),
    (8, '2026-05-03', 1, 1),
    (9, '2026-05-04', 1, 1),
    (10, '2026-05-05', 1, 1),
    (11, '2026-04-29', 1, 2),
    (12, '2026-04-29', 2, 2),
    (13, '2026-04-30', 1, 2),
    (14, '2026-04-30', 2, 2),
    (15, '2026-05-01', 1, 2),
    (16, '2026-05-01', 2, 2),
    (17, '2026-05-02', 1, 2),
    (18, '2026-05-03', 1, 2),
    (19, '2026-05-04', 1, 2),
    (20, '2026-04-29', 1, 3),
    (21, '2026-04-29', 2, 3),
    (22, '2026-04-30', 1, 3),
    (23, '2026-04-30', 2, 3),
    (24, '2026-05-01', 1, 3),
    (25, '2026-05-01', 2, 3),
    (26, '2026-05-02', 1, 3),
    (27, '2026-05-03', 1, 3),
    (28, '2026-04-29', 1, 4),
    (29, '2026-04-29', 2, 4),
    (30, '2026-04-30', 1, 4),
    (31, '2026-04-30', 2, 4),
    (32, '2026-05-01', 1, 4),
    (33, '2026-05-01', 2, 4),
    (34, '2026-05-02', 1, 4),
    (35, '2026-04-29', 1, 5),
    (36, '2026-04-29', 2, 5),
    (37, '2026-04-30', 1, 5),
    (38, '2026-04-30', 2, 5),
    (39, '2026-05-01', 1, 5),
    (40, '2026-05-01', 2, 5),
    (41, '2026-04-29', 1, 6),
    (42, '2026-04-29', 2, 6),
    (43, '2026-04-30', 1, 6),
    (44, '2026-04-30', 2, 6),
    (45, '2026-05-01', 1, 6),
    (46, '2026-04-29', 1, 7),
    (47, '2026-04-29', 2, 7),
    (48, '2026-04-30', 1, 7),
    (49, '2026-04-30', 2, 7),
    (50, '2026-04-29', 1, 8),
    (51, '2026-04-29', 2, 8),
    (52, '2026-04-30', 1, 8),
    (53, '2026-04-29', 1, 9),
    (54, '2026-04-29', 2, 9),
    (55, '2026-04-29', 1, 10),
    (56, '2026-04-28', 1, 11),
    (57, '2026-05-06', 1, 11),
    (58, '2026-05-06', 2, 11);

INSERT INTO reservation (
    id,
    guest_name,
    slot_id,
    status,
    last_modified_at
) VALUES
    (1, 'theme-1-guest-1', 1, 'CONFIRMED', '2026-04-29 09:00:00'),
    (2, 'theme-1-guest-2', 2, 'CONFIRMED', '2026-04-29 09:01:00'),
    (3, 'theme-1-guest-3', 3, 'WAITING', '2026-04-29 09:02:00'),
    (4, 'theme-1-guest-4', 4, 'CONFIRMED', '2026-04-29 09:03:00'),
    (5, 'theme-1-guest-5', 5, 'CONFIRMED', '2026-04-29 09:04:00'),
    (6, 'theme-1-guest-6', 6, 'WAITING', '2026-04-29 09:05:00'),
    (7, 'theme-1-guest-7', 7, 'CONFIRMED', '2026-04-29 09:06:00'),
    (8, 'theme-1-guest-8', 8, 'CANCELED', '2026-04-29 09:07:00'),
    (9, 'theme-1-guest-9', 9, 'CONFIRMED', '2026-04-29 09:08:00'),
    (10, 'theme-1-guest-10', 10, 'WAITING', '2026-04-29 09:09:00'),
    (11, 'theme-2-guest-1', 11, 'CONFIRMED', '2026-04-29 09:10:00'),
    (12, 'theme-2-guest-2', 12, 'WAITING', '2026-04-29 09:11:00'),
    (13, 'theme-2-guest-3', 13, 'CONFIRMED', '2026-04-29 09:12:00'),
    (14, 'theme-2-guest-4', 14, 'CONFIRMED', '2026-04-29 09:13:00'),
    (15, 'theme-2-guest-5', 15, 'WAITING', '2026-04-29 09:14:00'),
    (16, 'theme-2-guest-6', 16, 'CONFIRMED', '2026-04-29 09:15:00'),
    (17, 'theme-2-guest-7', 17, 'CANCELED', '2026-04-29 09:16:00'),
    (18, 'theme-2-guest-8', 18, 'CONFIRMED', '2026-04-29 09:17:00'),
    (19, 'theme-2-guest-9', 19, 'WAITING', '2026-04-29 09:18:00'),
    (20, 'theme-3-guest-1', 20, 'CONFIRMED', '2026-04-29 09:19:00'),
    (21, 'theme-3-guest-2', 21, 'WAITING', '2026-04-29 09:20:00'),
    (22, 'theme-3-guest-3', 22, 'CONFIRMED', '2026-04-29 09:21:00'),
    (23, 'theme-3-guest-4', 23, 'CONFIRMED', '2026-04-29 09:22:00'),
    (24, 'theme-3-guest-5', 24, 'WAITING', '2026-04-29 09:23:00'),
    (25, 'theme-3-guest-6', 25, 'CONFIRMED', '2026-04-29 09:24:00'),
    (26, 'theme-3-guest-7', 26, 'CANCELED', '2026-04-29 09:25:00'),
    (27, 'theme-3-guest-8', 27, 'CONFIRMED', '2026-04-29 09:26:00'),
    (28, 'theme-4-guest-1', 28, 'CONFIRMED', '2026-04-29 09:27:00'),
    (29, 'theme-4-guest-2', 29, 'WAITING', '2026-04-29 09:28:00'),
    (30, 'theme-4-guest-3', 30, 'CONFIRMED', '2026-04-29 09:29:00'),
    (31, 'theme-4-guest-4', 31, 'CONFIRMED', '2026-04-29 09:30:00'),
    (32, 'theme-4-guest-5', 32, 'WAITING', '2026-04-29 09:31:00'),
    (33, 'theme-4-guest-6', 33, 'CANCELED', '2026-04-29 09:32:00'),
    (34, 'theme-4-guest-7', 34, 'CONFIRMED', '2026-04-29 09:33:00'),
    (35, 'theme-5-guest-1', 35, 'CONFIRMED', '2026-04-29 09:34:00'),
    (36, 'theme-5-guest-2', 36, 'WAITING', '2026-04-29 09:35:00'),
    (37, 'theme-5-guest-3', 37, 'CONFIRMED', '2026-04-29 09:36:00'),
    (38, 'theme-5-guest-4', 38, 'WAITING', '2026-04-29 09:37:00'),
    (39, 'theme-5-guest-5', 39, 'CONFIRMED', '2026-04-29 09:38:00'),
    (40, 'theme-5-guest-6', 40, 'CANCELED', '2026-04-29 09:39:00'),
    (41, 'theme-6-guest-1', 41, 'CONFIRMED', '2026-04-29 09:40:00'),
    (42, 'theme-6-guest-2', 42, 'WAITING', '2026-04-29 09:41:00'),
    (43, 'theme-6-guest-3', 43, 'CONFIRMED', '2026-04-29 09:42:00'),
    (44, 'theme-6-guest-4', 44, 'WAITING', '2026-04-29 09:43:00'),
    (45, 'theme-6-guest-5', 45, 'CONFIRMED', '2026-04-29 09:44:00'),
    (46, 'theme-7-guest-1', 46, 'CONFIRMED', '2026-04-29 09:45:00'),
    (47, 'theme-7-guest-2', 47, 'WAITING', '2026-04-29 09:46:00'),
    (48, 'theme-7-guest-3', 48, 'CONFIRMED', '2026-04-29 09:47:00'),
    (49, 'theme-7-guest-4', 49, 'CANCELED', '2026-04-29 09:48:00'),
    (50, 'theme-8-guest-1', 50, 'CONFIRMED', '2026-04-29 09:49:00'),
    (51, 'theme-8-guest-2', 51, 'WAITING', '2026-04-29 09:50:00'),
    (52, 'theme-8-guest-3', 52, 'CONFIRMED', '2026-04-29 09:51:00'),
    (53, 'theme-9-guest-1', 53, 'CONFIRMED', '2026-04-29 09:52:00'),
    (54, 'theme-9-guest-2', 54, 'WAITING', '2026-04-29 09:53:00'),
    (55, 'theme-10-guest-1', 55, 'CONFIRMED', '2026-04-29 09:54:00'),
    (56, 'theme-11-out-1', 56, 'CONFIRMED', '2026-04-29 09:55:00'),
    (57, 'theme-11-out-2', 57, 'WAITING', '2026-04-29 09:56:00'),
    (58, 'theme-11-out-3', 58, 'CANCELED', '2026-04-29 09:57:00');

ALTER TABLE reservation_slot ALTER COLUMN id RESTART WITH 59;

-- Theme 12: 예약 없음

ALTER TABLE theme
    ALTER COLUMN id RESTART WITH 13;
ALTER TABLE reservation_time
    ALTER COLUMN id RESTART WITH 7;
ALTER TABLE reservation
    ALTER COLUMN id RESTART WITH 59;

DROP TABLE IF EXISTS player CASCADE;
DROP TABLE IF EXISTS player_credit CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS player_inventory CASCADE;

CREATE TABLE player
(
    id            bigint      NOT NULL AUTO_INCREMENT, --사용자 PK
    name          varchar(10) NOT NULL,                --사용자명
    email         varchar(50) NOT NULL,                --로그인 이메일
    passwd        varchar(90) NOT NULL,                --로그인 비밀번호
    created_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (id),
    UNIQUE KEY unq_user_email (email)
);

CREATE TABLE player_credit
(
    player_id       bigint      NOT NULL,                --사용자 id
    paid_credit   bigint     NOT NULL DEFAULT 0,    -- 유상재화
    free_credit   bigint     NOT NULL DEFAULT 0,    -- 무상재화
    created_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    modified_at    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (player_id)
);

CREATE TABLE item
(
    id            bigint      NOT NULL AUTO_INCREMENT, --아이템 PK
    price         bigint      NOT NULL,                --가격
    created_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (id)
);

CREATE TABLE player_inventory
(
    player_id            bigint      NOT NULL,             --사용자 id
    item_id          bigint      NOT NULL,                --아이템 id
    item_count      int         NOT NULL,                 -- 아이템 보유 수량량
   created_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    modified_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (player_id, item_id)
);

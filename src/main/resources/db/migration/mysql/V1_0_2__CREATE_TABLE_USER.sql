create table user
(
    id                    BIGINT auto_increment NOT NULL PRIMARY KEY,
    username              varchar(36)  NOT NULL,
    password              varchar(128) NOT NULL,
    accountNonExpired     boolean      NOT NULL,
    accountNonLocked      boolean      NOT NULL,
    credentialsNonExpired boolean      NOT NULL,
    enable                boolean      NOT NULL,
    constraint UC_User_Username unique (username)
);

create table user_authority
(
    user_id    BIGINT       NOT NULL,
    authority varchar(100) NOT NULL,
    constraint UC_UserAuthority_User_Authority unique (user_id, authority),
    constraint FK_UserAuthority_UserId foreign key (user_id)
        references user (id) on delete cascade
);


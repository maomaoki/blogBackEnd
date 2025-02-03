create table user
(
    id               bigint auto_increment comment 'id'
        primary key,
    userAccount      varchar(256)                           not null comment '账号',
    userEmail        varchar(256)                           null comment '用户邮箱',
    userPhone        varchar(64)                            null comment '用户手机号',
    userPassword     varchar(512)                           not null comment '密码',
    userName         varchar(256)                           null comment '用户昵称',
    userRole         varchar(256) default 'user'            not null comment '用户角色：user/admin/blackUser',
    userAvatar       varchar(1024)                          null comment '用户头像',
    userTags         varchar(512)                           null comment '用户标签 - json字符串',
    userIntroduction varchar(512)                           null comment '用户简介',
    userFGender      tinyint      default 0                 not null comment '用户性别：0-男，1-女',
    userAge          int          default 0                 null comment '用户年龄',
    userStatus       tinyint      default 0                 not null comment '用户状态：0-正常，1-封禁',
    registeredSource varchar(32)                            not null comment '用户注册来源：account/email/admin',
    editTime         datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime       datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint      default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_unicode_ci;

create index idx_userAccount
    on user (userAccount);

create index idx_userName
    on user (userName);

create index idx_userTags
    on user (userTags);

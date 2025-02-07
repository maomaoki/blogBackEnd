create table picture
(
    id                  bigint auto_increment comment 'id'
        primary key,
    pictureUrl          varchar(512)                          not null comment '图片 url',
    picturePath         varchar(128)                          null comment '图片物理存放地址',
    pictureUpload       varchar(32) default 'local'           not null comment '图片上传方式',
    pictureName         varchar(128)                          not null comment '图片名称',
    pictureIntroduction varchar(512)                          null comment '简介',
    pictureCategory     varchar(64)                           null comment '分类',
    pictureTags         varchar(512)                          null comment '标签（JSON 数组）',
    pictureSize         bigint                                null comment '图片体积',
    pictureWidth        int                                   null comment '图片宽度',
    pictureHeight       int                                   null comment '图片高度',
    pictureScale        double                                null comment '图片宽高比例',
    pictureFormat       varchar(32)                           null comment '图片格式',
    createUserId        bigint                                not null comment '创建用户 id',
    usedUserId          bigint                                null comment '使用人 id',
    reviewUserId        bigint                                null comment '审核人 id',
    reviewStatus        tinyint     default 0                 not null comment '审核状态 0待审核 1 审核通过 2 审核拒绝',
    reviewReason        varchar(512)                          null comment '审核原因',
    reviewTime          datetime    default CURRENT_TIMESTAMP not null comment '审核时间',
    createTime          datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime            datetime    default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime          datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete            tinyint     default 0                 not null comment '是否删除'
)
    comment '图片' collate = utf8mb4_unicode_ci;
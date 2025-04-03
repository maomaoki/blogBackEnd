-- auto-generated definition
create table comment
(
    id                     bigint auto_increment comment '评论主键id'
        primary key,
    articleId              bigint                             not null comment '文章ID,用于关联文章',
    content                text                               not null comment '评论内容',
    userId                 bigint                             not null comment '评论人ID',
    isHot                  tinyint(1) default 0                 null comment '是否热门评论 0未热门 1热门',
    isSticky               tinyint(1) default 0                 null comment '是否置顶评论 0未置顶 1置顶',
    isShow                 tinyint(1) default 1                 null comment '是否展示 0未展示 1展示',
    likeNumber             int null comment '点赞数',
    replyNumber            int null comment '回复数',
    commentPositionAddress varchar(64) null comment '评论定位地址',
    commentDevice          varchar(64) null comment '评论的设备',
    commentBrowserDevice   varchar(64) null comment '评论的浏览器设备',
    reviewUserId           bigint null comment '审核人 id',
    reviewStatus           tinyint  default 0                 not null comment '审核状态 0待审核 1 审核通过 2 审核拒绝',
    reviewReason           varchar(512) null comment '审核原因',
    reviewTime             datetime default CURRENT_TIMESTAMP not null comment '审核时间',
    editTime               datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime             datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime             datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete               tinyint  default 0                 not null comment '是否删除'
) comment '评论表' collate = utf8mb4_unicode_ci;


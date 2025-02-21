-- auto-generated definition
create table comment
(
    id          bigint auto_increment comment '评论主键id'
        primary key,
    articleId   bigint                               not null comment '文章ID,用于关联文章',
    content     text                                 not null comment '评论内容',
    userId      bigint                               not null comment '评论人ID',
    replyUserId bigint                               null comment '回复人的id(回复某个人的评论)',
    forebearId  bigint                               null comment '祖先评论id(最高层id-用于快速删除,可能没有用)',
    fatherId    bigint                               null comment '父级评论Id(一般只有回复评论才会有)',
    isHot       tinyint(1) default 0                 null comment '是否热门评论 0未热门 1热门',
    isSticky    tinyint(1) default 0                 null comment '是否置顶评论(最高层有最高层的,次级有次级,需要配合fatherId使用) 0未置顶 1置顶',
    isShow      tinyint(1) default 1                 null comment '是否展示 0未展示 1展示',
    editTime    datetime   default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint    default 0                 not null comment '是否删除'
)
    comment '评论表' collate = utf8mb4_unicode_ci;


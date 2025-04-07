create table article_collect
(
    id            bigint auto_increment comment '默认记录id'
        primary key,
    articleId     bigint                             not null comment '文章id',
    userId        bigint                             not null comment '用户id',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime      datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 not null comment '是否删除'
)
    comment '文章收藏表' collate = utf8mb4_unicode_ci;
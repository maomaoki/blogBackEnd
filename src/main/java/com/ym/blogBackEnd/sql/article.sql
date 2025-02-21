-- auto-generated definition
create table article
(
    id                   bigint auto_increment comment '文章id'
        primary key,
    articleBgImage       varchar(512)                       null comment '文章背景图',
    articleTitle         varchar(128)                       null comment '文章标题',
    articleIntroduction  varchar(128)                       null comment '文章主题(简介)',
    articleContent       text                               null comment '文章内容',
    articleTags          varchar(512)                       null comment '文章标签 （JSON 数组）',
    articleCategory      varchar(64)                        null comment '文章分类',
    articleLikeNumber    int      default 0                 null comment '文章点赞数',
    articleCollectNumber int      default 0                 null comment '文章收藏数',
    articleLookNumber    int      default 0                 null comment '文章观看数',
    articleCommentNumber int      default 0                 null comment '文章评论数',
    articleAuthor        varchar(64)                        null comment '文章作者',
    isEncrypt            tinyint  default 0                 null comment '文章是否加密 0不加密(默认),1加密',
    encryptPassword      varchar(255)                       null comment '文章加密密码',
    isRecommend          tinyint  default 0                 null comment '文章是否推荐 0不推荐(默认),1推荐',
    isHot                tinyint  default 0                 null comment '文章是否热门 0不推荐(默认),1推荐',
    articleStatus        tinyint                            null comment '文章状态 0草稿 1未发布 2发布',
    createTime           datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime             datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime           datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete             tinyint  default 0                 not null comment '是否删除'
)
    comment '文章表' collate = utf8mb4_unicode_ci;


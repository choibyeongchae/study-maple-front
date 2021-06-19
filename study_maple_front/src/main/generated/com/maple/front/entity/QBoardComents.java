package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardComents is a Querydsl query type for BoardComents
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardComents extends EntityPathBase<BoardComents> {

    private static final long serialVersionUID = -1649130525L;

    public static final QBoardComents boardComents = new QBoardComents("boardComents");

    public final NumberPath<Integer> boader_seq = createNumber("boader_seq", Integer.class);

    public final StringPath com_contents = createString("com_contents");

    public final NumberPath<Integer> coment_seq = createNumber("coment_seq", Integer.class);

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QBoardComents(String variable) {
        super(BoardComents.class, forVariable(variable));
    }

    public QBoardComents(Path<? extends BoardComents> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardComents(PathMetadata metadata) {
        super(BoardComents.class, metadata);
    }

}


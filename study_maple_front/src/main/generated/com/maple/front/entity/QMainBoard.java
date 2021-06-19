package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMainBoard is a Querydsl query type for MainBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMainBoard extends EntityPathBase<MainBoard> {

    private static final long serialVersionUID = -1957781267L;

    public static final QMainBoard mainBoard = new QMainBoard("mainBoard");

    public final StringPath boader_contents = createString("boader_contents");

    public final NumberPath<Integer> boader_seq = createNumber("boader_seq", Integer.class);

    public final StringPath boader_title = createString("boader_title");

    public final DatePath<java.sql.Date> boader_type = createDate("boader_type", java.sql.Date.class);

    public final StringPath boader_viewcnt = createString("boader_viewcnt");

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QMainBoard(String variable) {
        super(MainBoard.class, forVariable(variable));
    }

    public QMainBoard(Path<? extends MainBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMainBoard(PathMetadata metadata) {
        super(MainBoard.class, metadata);
    }

}


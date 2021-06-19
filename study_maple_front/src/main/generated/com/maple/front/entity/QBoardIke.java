package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardIke is a Querydsl query type for BoardIke
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardIke extends EntityPathBase<BoardIke> {

    private static final long serialVersionUID = -2045674883L;

    public static final QBoardIke boardIke = new QBoardIke("boardIke");

    public final NumberPath<Integer> boader_seq = createNumber("boader_seq", Integer.class);

    public final NumberPath<Integer> history_seq = createNumber("history_seq", Integer.class);

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QBoardIke(String variable) {
        super(BoardIke.class, forVariable(variable));
    }

    public QBoardIke(Path<? extends BoardIke> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardIke(PathMetadata metadata) {
        super(BoardIke.class, metadata);
    }

}


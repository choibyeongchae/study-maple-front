package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemTake is a Querydsl query type for ItemTake
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemTake extends EntityPathBase<ItemTake> {

    private static final long serialVersionUID = 1816059386L;

    public static final QItemTake itemTake = new QItemTake("itemTake");

    public final StringPath caharacter_name = createString("caharacter_name");

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QItemTake(String variable) {
        super(ItemTake.class, forVariable(variable));
    }

    public QItemTake(Path<? extends ItemTake> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemTake(PathMetadata metadata) {
        super(ItemTake.class, metadata);
    }

}


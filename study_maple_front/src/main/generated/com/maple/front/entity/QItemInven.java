package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemInven is a Querydsl query type for ItemInven
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemInven extends EntityPathBase<ItemInven> {

    private static final long serialVersionUID = 453505351L;

    public static final QItemInven itemInven = new QItemInven("itemInven");

    public final StringPath caharacter_name = createString("caharacter_name");

    public final NumberPath<Integer> item_seq = createNumber("item_seq", Integer.class);

    public final StringPath item_type = createString("item_type");

    public final NumberPath<Integer> iven_seq = createNumber("iven_seq", Integer.class);

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QItemInven(String variable) {
        super(ItemInven.class, forVariable(variable));
    }

    public QItemInven(Path<? extends ItemInven> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemInven(PathMetadata metadata) {
        super(ItemInven.class, metadata);
    }

}


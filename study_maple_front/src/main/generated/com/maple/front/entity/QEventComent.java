package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventComent is a Querydsl query type for EventComent
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEventComent extends EntityPathBase<EventComent> {

    private static final long serialVersionUID = 245267268L;

    public static final QEventComent eventComent = new QEventComent("eventComent");

    public final StringPath even_coments = createString("even_coments");

    public final NumberPath<Integer> even_op = createNumber("even_op", Integer.class);

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QEventComent(String variable) {
        super(EventComent.class, forVariable(variable));
    }

    public QEventComent(Path<? extends EventComent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventComent(PathMetadata metadata) {
        super(EventComent.class, metadata);
    }

}


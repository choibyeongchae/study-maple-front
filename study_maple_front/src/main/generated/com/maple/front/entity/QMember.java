package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -956434854L;

    public static final QMember member = new QMember("member1");

    public final StringPath mbr_adress = createString("mbr_adress");

    public final StringPath mbr_born = createString("mbr_born");

    public final StringPath mbr_email = createString("mbr_email");

    public final StringPath mbr_name = createString("mbr_name");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final StringPath mbr_pass = createString("mbr_pass");

    public final StringPath mbr_phone = createString("mbr_phone");

    public final StringPath mbr_role = createString("mbr_role");

    public final StringPath mbr_zip = createString("mbr_zip");

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


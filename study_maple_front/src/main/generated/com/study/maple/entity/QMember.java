package com.study.maple.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.maple.front.entity.Member;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1432467718L;

    public static final QMember member = new QMember("member1");

    public final StringPath address = createString("address");

    public final StringPath birth = createString("birth");

    public final StringPath email = createString("email");

    public final StringPath mbrnm = createString("mbrnm");

    public final NumberPath<Integer> mbrno = createNumber("mbrno", Integer.class);

    public final StringPath mbrpoint = createString("mbrpoint");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath roles = createString("roles");

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


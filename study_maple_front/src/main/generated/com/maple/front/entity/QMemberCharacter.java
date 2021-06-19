package com.maple.front.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberCharacter is a Querydsl query type for MemberCharacter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberCharacter extends EntityPathBase<MemberCharacter> {

    private static final long serialVersionUID = 248932847L;

    public static final QMemberCharacter memberCharacter = new QMemberCharacter("memberCharacter");

    public final StringPath caharacter_name = createString("caharacter_name");

    public final StringPath cha_job = createString("cha_job");

    public final NumberPath<Integer> cha_level = createNumber("cha_level", Integer.class);

    public final StringPath cha_server = createString("cha_server");

    public final StringPath mbr_email = createString("mbr_email");

    public final NumberPath<Integer> mbr_no = createNumber("mbr_no", Integer.class);

    public final DatePath<java.sql.Date> regdate = createDate("regdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> upddate = createDate("upddate", java.sql.Date.class);

    public QMemberCharacter(String variable) {
        super(MemberCharacter.class, forVariable(variable));
    }

    public QMemberCharacter(Path<? extends MemberCharacter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCharacter(PathMetadata metadata) {
        super(MemberCharacter.class, metadata);
    }

}


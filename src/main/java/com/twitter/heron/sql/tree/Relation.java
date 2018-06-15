package com.twitter.heron.sql.tree;

public abstract class Relation
    extends Node
{
    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitRelation(this, context);
    }
}

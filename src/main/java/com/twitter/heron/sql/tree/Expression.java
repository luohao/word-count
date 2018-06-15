package com.twitter.heron.sql.tree;

public abstract class Expression
        extends Node
{
    protected Expression() { super(); }

    @Override
    protected <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitExpression(this, context);
    }
}

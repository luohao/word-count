package com.twitter.heron.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Statement
    extends Node
{
    private final QuerySpecification querySpecification;

    public Statement(QuerySpecification querySpecification) {
        this.querySpecification = querySpecification;
    }

    public QuerySpecification getQuerySpecification()
    {
        return querySpecification;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitStatement(this, context);
    }

    @Override
    public List<? extends Node> getChildren()
    {
        return ImmutableList.of(querySpecification);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(querySpecification);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Statement that = (Statement) obj;
        return Objects.equals(querySpecification, that.querySpecification);
    }

    @Override
    public String toString()
    {
        return querySpecification.toString();
    }
}

package com.twitter.heron.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

public class Identifier
        extends Expression
{
    public final String value;

    public Identifier(String value)
    {
        this.value = value;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitIdentifier(this, context);
    }

    @Override
    public List<? extends Node> getChildren()
    {
        return ImmutableList.of();
    }

    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Identifier that = (Identifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString()
    {
        return value;
    }
}

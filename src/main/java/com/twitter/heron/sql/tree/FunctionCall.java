package com.twitter.heron.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class FunctionCall
    extends Expression
{
    private final String name;

    public FunctionCall(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitFunctionCall(this, context);
    }

    @Override
    public List<? extends Node> getChildren()
    {
        return ImmutableList.of();
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
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
        FunctionCall that = (FunctionCall) obj;

        return Objects.equals(name, that.name);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("functionCall", name)
                .toString();
    }
}

package com.twitter.heron.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class GroupBy
        extends Node
{
    private final Expression groupingElement;

    public GroupBy(Expression expression)
    {
        this.groupingElement = expression;
    }

    public Expression getGroupingElement() { return groupingElement; }

    @Override
    protected <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitGroupBy(this, context);
    }

    @Override
    public List<? extends Node> getChildren() { return ImmutableList.of(groupingElement); }

    @Override
    public int hashCode()
    {
        return Objects.hash(groupingElement);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }

        GroupBy that = (GroupBy) o;
        return Objects.equals(groupingElement, that.getGroupingElement());
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("groupingElement", groupingElement)
                .toString();
    }
}

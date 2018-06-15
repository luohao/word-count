package com.twitter.heron.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static java.util.Objects.requireNonNull;

public class QuerySpecification
        extends Relation
{
    private final Expression select;
    private final Relation from;
    private final GroupBy groupBy;

    public QuerySpecification(
            Expression select,
            Relation from,
            GroupBy groupBy)
    {
        requireNonNull(select, "select is null");
        requireNonNull(from, "from is null");
        requireNonNull(groupBy, "groupBy is null");

        this.select = select;
        this.from = from;
        this.groupBy = groupBy;
    }

    public Expression getSelect()
    {
        return select;
    }

    public Relation getFrom()
    {
        return from;
    }

    public GroupBy getGroupBy()
    {
        return groupBy;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitQuerySpecification(this, context);
    }

    @Override
    public List<? extends Node> getChildren()
    {
        return ImmutableList.of(select, from, groupBy);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(select, from, groupBy);
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
        QuerySpecification that = (QuerySpecification) obj;
        return Objects.equals(select, that.select) &&
                Objects.equals(from, that.from) &&
                Objects.equals(groupBy, that.groupBy);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("select", select)
                .add("from", from)
                .add("groupBy", groupBy)
                .toString();
    }
}

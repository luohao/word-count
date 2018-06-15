package com.twitter.heron.sql.tree;

public abstract class AstVisitor<R, C>
{
    public R process(Node node)
    {
        return process(node, null);
    }

    public R process(Node node, C context)
    {
        return node.accept(this, context);
    }


    protected R visitExpression(Node node, C context)
    {
        return null;
    }

    protected R visitFunctionCall(FunctionCall functionCall, C context)
    {
        return null;
    }

    protected R visitGroupBy(GroupBy groupBy, C context)
    {
        return null;
    }

    protected R visitIdentifier(Identifier identifier, C context)
    {
        return null;
    }

    protected R visitNode(Node node, C context)
    {
        return null;
    }


    protected R visitStatement(Statement statement, C context)
    {
        return null;
    }

    protected R visitRelation(Relation relation, C context)
    {
        return null;
    }

    public R visitQuerySpecification(QuerySpecification querySpecification, C context)
    {
        return null;
    }
}

package com.twitter.heron.sql.parser;

import com.google.common.collect.ImmutableList;
import com.twitter.heron.sql.tree.Expression;
import com.twitter.heron.sql.tree.FunctionCall;
import com.twitter.heron.sql.tree.GroupBy;
import com.twitter.heron.sql.tree.Identifier;
import com.twitter.heron.sql.tree.Node;
import com.twitter.heron.sql.tree.QuerySpecification;
import com.twitter.heron.sql.tree.Relation;
import com.twitter.heron.sql.tree.Statement;
import com.twitter.heron.sql.tree.Table;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

import static java.util.stream.Collectors.toList;

class AstBuilder
        extends HeronQLBaseBaseVisitor<Node>
{
    @Override
    public Node visitSingleStatement(HeronQLBaseParser.SingleStatementContext context)
    {
        return visit(context.statement());
    }

    @Override
    public Node visitStatementDefault(HeronQLBaseParser.StatementDefaultContext context)
    {
         QuerySpecification querySpecification = (QuerySpecification) visit(context.querySpecification());
         return new Statement(querySpecification);
    }

    @Override
    public Node visitQuerySpecification(HeronQLBaseParser.QuerySpecificationContext context)
    {
        Expression select = visit(ImmutableList.of(context.expression()), Expression.class);
        Relation from = visit(ImmutableList.of(context.relation()), Relation.class);
        GroupBy groupBy = visit(ImmutableList.of(context.groupBy()), GroupBy.class);

        return new QuerySpecification(select, from, groupBy);
    }

    @Override
    public Node visitGroupBy(HeronQLBaseParser.GroupByContext context)
    {
        String identifier = context.identifier().getText();
        return new GroupBy(new Identifier(identifier));
    }

    @Override
    public Node visitTable(HeronQLBaseParser.TableContext context)
    {
        return new Table(context.identifier().getText());
    }

    @Override
    public Node visitFunctionCall(HeronQLBaseParser.FunctionCallContext context)
    {
        String name = context.identifier().getText();
        return new FunctionCall(name);
    }

    @Override
    public Node visitIdentifier(HeronQLBaseParser.IdentifierContext context)
    {
        return new Identifier(context.getText());
    }

    private <T> T visit(List<? extends ParserRuleContext> contexts, Class<T> clazz)
    {
        // TODO: fix this awkward logic
        return contexts.stream()
                .map(this::visit)
                .map(clazz::cast)
                .collect(toList())
                .get(0);
    }
}

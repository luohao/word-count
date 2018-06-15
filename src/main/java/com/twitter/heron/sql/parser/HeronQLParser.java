package com.twitter.heron.sql.parser;

import com.twitter.heron.sql.tree.Node;
import com.twitter.heron.sql.tree.Statement;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;

import java.util.function.Function;

public class HeronQLParser
{
    public Statement createStatement(String sql)
    {
        return (Statement) invokeParser(sql);
    }

    private Node invokeParser(String sql)
    {
        try {
            HeronQLBaseLexer lexer = new HeronQLBaseLexer(new ANTLRInputStream(sql));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            HeronQLBaseParser parser = new HeronQLBaseParser(tokenStream);
            Function<HeronQLBaseParser, ParserRuleContext> parseFunction = HeronQLBaseParser::singleStatement;

            tokenStream.reset();
            parser.reset();

            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            ParserRuleContext tree = parseFunction.apply(parser);

            return new AstBuilder().visit(tree);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

grammar SqlBase;

tokens {
    DELIMITER
}

singleStatement
    : statement EOF
    ;

statement
    : query                     #statementDefault
    ;

query
    : queryNoWith
    ;

queryNoWith
    : queryTerm
    ;

queryTerm
    : queryPrimary              #queryTermDefault
    ;

queryPrimary
    : querySpecification        #queryPrimaryDefault
    ;

querySpecification
    : SELECT selectItem
      FROM relation
      GROUP BY groupBy
    ;
selectItem
    : expression                #selectSingle
    ;

relation
    : sampledRelation           #relationDefault
    ;

sampledRelation
    : aliasedRelation
    ;

aliasedRelation
    : relationPrimary
    ;

relationPrimary
    : qualifiedName             #tableName
    ;

qualifiedName
    : identifier
    ;

groupBy
    : groupingElement
    ;

groupingElement
    : groupingExpressions       #singleGroupingSet
    ;

groupingExpressions
    : expression
    ;

expression
    : booleanExpression
    ;

booleanExpression
    : valueExpression
    ;

valueExpression
    : primaryExpression         #valueExpressionDefault
    ;

primaryExpression
    : identifier                #columnReference
    ;

identifier
    : IDENTIFIER                #unquotedIdentifier
    ;

IDENTIFIER
    : (LETTER | '_') (LETTER | DIGIT | '_' | '@' | ':')*
    ;

INTEGER_VALUE
    : DIGIT+
    ;

TRUE: 'TRUE';
FALSE: 'FALSE';

SELECT: 'SELECT';
GROUP: 'GROUP';
BY: 'BY';
FROM: 'FROM';

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Z]
    | [a-z]
    ;

WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer
UNRECOGNIZED
    : .
    ;

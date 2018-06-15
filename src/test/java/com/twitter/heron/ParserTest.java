package com.twitter.heron;

import static org.junit.Assert.assertTrue;

import com.twitter.heron.sql.parser.HeronQLParser;
import com.twitter.heron.sql.tree.Statement;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ParserTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testParser() {
        String sql = "SELECT COUNT(*) FROM A GROUP BY B";
        HeronQLParser parser = new HeronQLParser();

        Statement statement = parser.createStatement(sql);
    }
}

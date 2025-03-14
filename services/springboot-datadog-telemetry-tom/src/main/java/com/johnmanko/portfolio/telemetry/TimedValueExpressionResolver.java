package com.johnmanko.portfolio.telemetry;

import io.micrometer.common.annotation.ValueExpressionResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class TimedValueExpressionResolver implements ValueExpressionResolver {

    /**
     * Resolves the expression using Spring Expression Language
     * @see <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html#page-title">Spring Expression Language</a>
     * @param s the expression coming from an annotation
     * @param o parameter annotated with a {@link KeyValue} related annotation
     * @return the resolved expression
     */
    @Override
    public String resolve(String s, Object o) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(s);
        return (String) exp.getValue(o);
    }
}

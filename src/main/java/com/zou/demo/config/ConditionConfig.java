package com.zou.demo.config;


import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.context.annotation.Condition;

public class ConditionConfig implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        final String name = conditionContext.getEnvironment().getProperty("name");
        if (name != null){
            System.out.println(name);
            return true;
        }
        return false;
    }


}

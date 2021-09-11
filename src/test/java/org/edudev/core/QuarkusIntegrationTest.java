package org.edudev.core;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import javax.enterprise.inject.Stereotype;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@QuarkusTest
@Stereotype
@QuarkusTestResource(MongoResource.class)
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface QuarkusIntegrationTest { }

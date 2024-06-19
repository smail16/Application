package com.soprasteria;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameEndingWith;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@UnitTest
class AnnotationArchTest {

    private static final String ROOT_PACKAGE = "com.soprasteria";
    private static final String ROOT_PACKAGE_PROJECT = ROOT_PACKAGE + "..";

    //@formatter:off
  JavaClasses classes = new ClassFileImporter()
    .importPackages(ROOT_PACKAGE)
    .that(
      are(
        not(equivalentTo(UnitTest.class))
        .and(not(equivalentTo(IntegrationTest.class))
        .and(not(equivalentTo(ComponentTest.class))))
      )
    );

  //@formatter:on

    @Test
    void shouldHaveUnitTestOrComponentTestAnnotation() {
        //@formatter:off
    classes()
      .that()
        .resideInAnyPackage(ROOT_PACKAGE_PROJECT)
        .and().haveSimpleNameEndingWith("Test")
        .and(not(simpleNameEndingWith("IT")))
        .and().areTopLevelClasses()
      .should().beAnnotatedWith(UnitTest.class)
      .orShould().beAnnotatedWith(ComponentTest.class)
      .orShould().beInterfaces()
      .check(classes);
    //@formatter:on
    }

    @Test
    void shouldHaveIntegrationTestAnnotation() {
        //@formatter:off
    classes()
      .that()
        .resideInAnyPackage(ROOT_PACKAGE_PROJECT)
        .and().haveSimpleNameEndingWith("IT")
        .and().areTopLevelClasses()
      .should().beAnnotatedWith(IntegrationTest.class)
      .check(classes);
    //@formatter:on
    }
}

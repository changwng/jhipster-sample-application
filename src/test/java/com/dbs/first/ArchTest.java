package com.dbs.first;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.dbs.first");

        noClasses()
            .that()
            .resideInAnyPackage("com.dbs.first.service..")
            .or()
            .resideInAnyPackage("com.dbs.first.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.dbs.first.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}

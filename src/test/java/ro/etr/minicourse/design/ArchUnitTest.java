package ro.etr.minicourse.design;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public class ArchUnitTest {
    JavaClasses allClasses = new ClassFileImporter().importPackages("ro.etr.minicourse");

    @Test
    void piecesShouldDependOnBoard() {
        classes()
            .that().resideInAPackage("..pieces..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage("..board..", "java..", "lombok..")
            .check(allClasses);
    }

    @Test
    void boardShouldNotDependOnPieces() {
        classes().that()
            .resideInAPackage("..board..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..board..", "java..", "lombok..")
            .check(allClasses);
    }

}

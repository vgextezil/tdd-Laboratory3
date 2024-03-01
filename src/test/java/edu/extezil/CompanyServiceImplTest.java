package edu.extezil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author   hakmandul
  @project   Laboratory3
  @class  CompanyServiceImplTest
  @version  1.0.0 
  @since 3/1/2024 - 09.53
*/
class CompanyServiceImplTest {
    private final Company main = new Company(null,2);
    private final Company book = new Company(main,3);
    private final Company manager = new Company(main,4);
    private final Company developer = new Company(manager,8);
    private final Company design = new Company(manager,6);
    private final Company lawer = new Company(null,1);

    private final List<Company> list = List.of(main, book, manager,developer, design);

    private final ICompanyService companyService = new CompanyServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void whenCompanyIsNullThenNull() {
        Company result = companyService.getTopLevelParent(null);
        Assertions.assertNull(result);
    }

    @Test
    void whenCompanyHasNoParentItIsOnTop() {
        Company result = companyService.getTopLevelParent(main);
        Assertions.assertEquals(main, result);
    }

    @Test
    void whenCompanyIsSingleItIsOnTop() {
        Company result = companyService.getTopLevelParent(lawer);
        Assertions.assertEquals(lawer, result);
    }
    @Test
    void whenCompanyHasOneStepToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(book);
        Assertions.assertEquals(main, result);
    }
    @Test
    void whenCompanyHasTwoStepsToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(developer);
        Assertions.assertEquals(main, result);
    }
    @Test
    void whenCompanyIsNullThen0() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(null, Collections.emptyList());
        assertEquals(0, result);
    }

    @Test
    void whenCompaniesListIsNullThen0(){
        long result = companyService.getEmployeeCountForCompanyAndChildren(main, null);
        assertEquals(0, result);
    }

    @Test
    void whenCompanyListIsEmptyThen0() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(main, Collections.emptyList());
        assertEquals(0, result);
    }
    @Test
    void whenCompanyOnLowLevelThenFindCountEmployees(){
        assertEquals(8,companyService.getEmployeeCountForCompanyAndChildren(developer, list));
        assertEquals(6,companyService.getEmployeeCountForCompanyAndChildren(design, list));
    }
    @Test
    void whenCompanyHasOneOrMoreChildThenFindCountEmployees(){
        assertEquals(18,companyService.getEmployeeCountForCompanyAndChildren(manager, list));
    }
    @Test
    void whenCompanyOnTonAndHasChildAndChildHasChildThenFindCountEmployees(){
        assertEquals(23,companyService.getEmployeeCountForCompanyAndChildren(main, list));
    }



}
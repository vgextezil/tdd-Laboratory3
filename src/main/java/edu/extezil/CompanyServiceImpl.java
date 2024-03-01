package edu.extezil;/*
  @author   hakmandul
  @project   Laboratory3
  @class  CompanyServiceImpl
  @version  1.0.0 
  @since 3/1/2024 - 09.51
*/

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {
    @Override
    public Company getTopLevelParent(Company child) {
        if (child == null) {
            return null;
        }

        Company parent = child.getParent();
        while (parent != null) {
            child = parent;
            parent = child.getParent();
        }

        return child;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        if (company == null || companies == null || companies.isEmpty()) {
            return 0;
        }

        long employeeCount = getEmployeeCount(company);

        for (Company child : companies) {
            if (isChild(company, child)) {
                employeeCount += getEmployeeCountForCompanyAndChildren(child, companies);
            }
        }

        return employeeCount;
    }

    private long getEmployeeCount(Company company) {
        return company.getEmployeesCount();
    }

    private boolean isChild(Company parent, Company child) {
        return child.getParent() == parent;
    }
}

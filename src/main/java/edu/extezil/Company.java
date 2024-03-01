package edu.extezil;/*
  @author   hakmandul
  @project   Laboratory3
  @class  Company
  @version  1.0.0 
  @since 3/1/2024 - 09.51
*/

public class Company {
    // parent for this company nullable, when there is no parent for this company
    private Company parent;
    private long employeesCount;

    public Company(Company parent, long employeesCount) {
        this.parent = parent;
        this.employeesCount = employeesCount;
    }

    public Company getParent() {
        return parent;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }
}
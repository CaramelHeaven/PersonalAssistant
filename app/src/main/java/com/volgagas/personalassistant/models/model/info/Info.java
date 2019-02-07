package com.volgagas.personalassistant.models.model.info;

/**
 * Created by CaramelHeaven on 14:25, 07/02/2019.
 */
public class Info {

    private Salary salary;
    private Vacation vacation;

    public Info() {
        salary = new Salary();
        vacation = new Vacation();
    }

    public class Salary {
        private String salaryUser = "";

        public String getSalaryUser() {
            return salaryUser;
        }

        public void setSalaryUser(String salaryUser) {
            this.salaryUser = salaryUser;
        }
    }

    public class Vacation {
        private String freeDays = "";

        public String getFreeDays() {
            return freeDays;
        }

        public void setFreeDays(String freeDays) {
            this.freeDays = freeDays;
        }
    }

    public Salary getSalary() {
        return salary;
    }

    public Vacation getVacation() {
        return vacation;
    }
}

package pl.io4.model.machines;

import java.util.List;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Employee;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class EmployeesMachine extends CachableObject {
    private CachableList<Employee> employees;

    public EmployeesMachine() {
        employees = new CachableArrayList<>(Employee.class);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(String pesel) {
        for (Employee employee : employees) {
            if (employee.getPesel().equals(pesel)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeesMachine that = (EmployeesMachine) o;
        return employees.equals(that.employees);
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("employees", employees.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        employees.load(data.getJSONArray("employees"));
    }
}

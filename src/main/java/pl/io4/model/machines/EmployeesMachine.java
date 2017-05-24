package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Employee;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class EmployeesMachine extends Cachable {
    private List<Employee> employees;

    public EmployeesMachine() {
        employees = new ArrayList<Employee>();
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
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("employees", Cachable.cache(employees));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(employees, Employee.class, data.getJSONArray("employees"));
    }
}

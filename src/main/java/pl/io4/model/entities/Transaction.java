package pl.io4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Basic;
import java.sql.Timestamp;

/**
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "Transaction", schema = "dbo", catalog = "io4")
public class Transaction {
    private int id;
    private Timestamp date;
    private String employee;

    private static final int LENGTH_PESEL = 11;

    @Id
    @Column(name = "id", nullable = false)
    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transaction that = (Transaction) o;

        if (id != that.id) {
            return false;
        }
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public final int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "employee", nullable = false, length = LENGTH_PESEL)
    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}

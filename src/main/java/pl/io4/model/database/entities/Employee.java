package pl.io4.model.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Basic;

/**
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "Employee", schema = "dbo", catalog = "io4")
public final class Employee {
    private String pesel;
    private String firstName;
    private String lastName;
    private String email;
    private String hashSha1;
    private String hashMd5;

    private static final int LENGTH_PESEL = 11;
    private static final int LENGTH_MAX = 2147483647;
    private static final int LENGTH_SHA1 = 40;
    private static final int LENGTH_MD5 = 32;

    public Employee() {

    }

    public Employee(String pesel, String firstName, String lastName, String email, String hs, String hmd) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashSha1 = hs;
        this.hashMd5 = hmd;
    }

    @Id
    @Column(name = "PESEL", nullable = false, length = LENGTH_PESEL)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "firstName", nullable = false, length = LENGTH_MAX)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = false, length = LENGTH_MAX)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = LENGTH_MAX)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "hashSHA1", nullable = false, length = LENGTH_SHA1)
    public String getHashSha1() {
        return hashSha1;
    }

    public void setHashSha1(String hashSha1) {
        this.hashSha1 = hashSha1;
    }

    @Basic
    @Column(name = "hashMD5", nullable = false, length = LENGTH_MD5)
    public String getHashMd5() {
        return hashMd5;
    }

    public void setHashMd5(String hashMd5) {
        this.hashMd5 = hashMd5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee that = (Employee) o;

        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (hashSha1 != null ? !hashSha1.equals(that.hashSha1) : that.hashSha1 != null) {
            return false;
        }
        return hashMd5 != null ? hashMd5.equals(that.hashMd5) : that.hashMd5 == null;
    }

    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (hashSha1 != null ? hashSha1.hashCode() : 0);
        result = 31 * result + (hashMd5 != null ? hashMd5.hashCode() : 0);
        return result;
    }
}

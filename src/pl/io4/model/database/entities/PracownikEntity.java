package pl.io4.model.database.entities;

import javax.persistence.*;

/**
 * Created by jacob on 25.03.2017.
 */
@Entity
@Table(name = "Pracownik", schema = "dbo", catalog = "io4")
public class PracownikEntity {
    private int id;
    private String imie;
    private String nazwisko;
    private String hashSha1;
    private String hashMd5;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 2147483647)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 2147483647)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "hashSHA1", nullable = false, length = 40)
    public String getHashSha1() {
        return hashSha1;
    }

    public void setHashSha1(String hashSha1) {
        this.hashSha1 = hashSha1;
    }

    @Basic
    @Column(name = "hashMD5", nullable = false, length = 32)
    public String getHashMd5() {
        return hashMd5;
    }

    public void setHashMd5(String hashMd5) {
        this.hashMd5 = hashMd5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PracownikEntity that = (PracownikEntity) o;

        if (id != that.id) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
        if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
        if (hashSha1 != null ? !hashSha1.equals(that.hashSha1) : that.hashSha1 != null) return false;
        if (hashMd5 != null ? !hashMd5.equals(that.hashMd5) : that.hashMd5 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (hashSha1 != null ? hashSha1.hashCode() : 0);
        result = 31 * result + (hashMd5 != null ? hashMd5.hashCode() : 0);
        return result;
    }
}

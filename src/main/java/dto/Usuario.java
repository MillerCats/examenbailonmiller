package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodiUsua", query = "SELECT u FROM Usuario u WHERE u.codiUsua = :codiUsua"),
    @NamedQuery(name = "Usuario.findByNdniUsua", query = "SELECT u FROM Usuario u WHERE u.ndniUsua = :ndniUsua"),
    @NamedQuery(name = "Usuario.findByLogUsua", query = "SELECT u FROM Usuario u WHERE u.logUsua = :logUsua"),
    @NamedQuery(name = "Usuario.validar", query = "SELECT u FROM Usuario u WHERE u.logUsua = :logUsua AND u.passUsua = :passUsua"),
    @NamedQuery(name = "Usuario.findByPassUsua", query = "SELECT u FROM Usuario u WHERE u.passUsua = :passUsua"),
    @NamedQuery(name = "Usuario.findByNivelUsua", query = "SELECT u FROM Usuario u WHERE u.nivelUsua = :nivelUsua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiUsua")
    private Integer codiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ndniUsua")
    private String ndniUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "logUsua")
    private String logUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "passUsua")
    private String passUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nivelUsua")
    private String nivelUsua;

    public Usuario() {
    }

    public Usuario(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Usuario(Integer codiUsua, String ndniUsua, String logUsua, String passUsua, String nivelUsua) {
        this.codiUsua = codiUsua;
        this.ndniUsua = ndniUsua;
        this.logUsua = logUsua;
        this.passUsua = passUsua;
        this.nivelUsua = nivelUsua;
    }

    public Usuario(String logUsua, String passUsua) {
        this.logUsua = logUsua;
        this.passUsua = passUsua;
    }

    public Integer getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getNdniUsua() {
        return ndniUsua;
    }

    public void setNdniUsua(String ndniUsua) {
        this.ndniUsua = ndniUsua;
    }

    public String getLogUsua() {
        return logUsua;
    }

    public void setLogUsua(String logUsua) {
        this.logUsua = logUsua;
    }

    public String getPassUsua() {
        return passUsua;
    }

    public void setPassUsua(String passUsua) {
        this.passUsua = passUsua;
    }

    public String getNivelUsua() {
        return nivelUsua;
    }

    public void setNivelUsua(String nivelUsua) {
        this.nivelUsua = nivelUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ codiUsua=" + codiUsua + " ]";
    }

}

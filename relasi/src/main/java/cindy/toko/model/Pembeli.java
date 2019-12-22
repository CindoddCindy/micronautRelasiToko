package cindy.toko.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "pembelis")
public class Pembeli{

    @OneToMany(
        mappedBy = "pembeli",
        cascade = CascadeType.ALL
    )
    private transient List<Transaksi> transaksis = new ArrayList<>();

    public List<Transaksi> getTransaksis(){
        return transaksis;
    }

    public void setTransaksis(List<Transaksi> transaksis){
        this.transaksis = transaksis; 
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    

    @NotNull (message = "nama harus diisi.")
    @Column(name = "nama")
    private String nama;

    @NotNull (message = "nama harus diisi.")
    @Column(name = "id_pembeli")
    private String id_pembeli;


    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getNama() {
        return nama;
    }

    public void setNama(String nama){
        this.nama=nama;
    }

    public String getIdPembeli(){
        return id_pembeli;
    }

    public void setIdPembeli(String id_pembeli){
        this.id_pembeli=id_pembeli;
    }
    

    public Date getCreated_At() {
        return created_at;
    }

    public void setCreated_At(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_At() {
        return updated_at;
    }

    public void setUpdated_At(Date updated_at) {

        this.updated_at = updated_at;
    }

    public Date getDeleted_At() {
        return deleted_at;
    }
    
    public void setDeleted_At(Date deleted_at) {

        this.deleted_at = deleted_at;
    }

}
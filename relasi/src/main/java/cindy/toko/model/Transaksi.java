package cindy.toko.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "transaksi")
public class Transaksi{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pembeli")
    private Pembeli pembeli;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_barang")
    private Barang barang;


public Barang getBarang(){
    return barang;
}

public void setBarang(Barang barang){
    this.barang=barang;
}

    public Pembeli getPembeli(){
        return pembeli;
    }

    public void setPembeli(Pembeli pembeli){
        this.pembeli = pembeli;
    }


    @NotNull (message = "password harus diisi.")
    @Column(name = "jumlah")
    private String jumlah;

    @NotNull (message = "password harus diisi.")
    @Column(name = "tanggal")
    private String tanggal;

    @NotNull (message = "password harus diisi.")
    @Column(name = "harga")
    private String harga;

    @NotNull (message = "password harus diisi.")
    @Column(name = "total")
    private String total;

    
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

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal(){
        return tanggal;
    }

    public void setTanggal(String tanggal){
        this.tanggal=tanggal;
    }

    public String getHarga(){
        return harga;
    }

    public void setHarga(String harga){
        this.harga=harga;
    }

    public String getTotal(){
        return total;
    }

    public void setTotal(String total){
        this.total=total;
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
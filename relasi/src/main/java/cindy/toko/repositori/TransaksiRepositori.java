package cindy.toko.repositori;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import cindy.toko.model.Transaksi;

@Singleton
public class TransaksiRepositori implements TransaksiInterface {

    @PersistenceContext
    private EntityManager manager;

    public TransaksiRepositori(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Transaksi where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional
    public List<Transaksi> findAll(int page, int limit) {
        TypedQuery<Transaksi> query = manager
                .createQuery("from Transaksi where deleted_at IS NULL", Transaksi.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Transaksi findById(@NotNull Long id) {
        Transaksi query = manager.find(Transaksi.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean save(@NotNull Transaksi transaksi) {
        try {
            manager.persist(transaksi);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String id_barang, String id_pembeli, String jumlah, String tanggal, String harga, String total) {
        try {

            Transaksi c = manager.find(Transaksi.class, id);
            if (id_barang.equals("-")==false) c.setIdBarang(id_barang);
            if (id_pembeli.equals("-")==false) c.setIdPembeli(id_pembeli);
            if (jumlah.equals("-")==false) c.setJumlah(jumlah);
            if (tanggal.equals("-")==false) c.setTanggal(tanggal);
            if (harga.equals("-")==false) c.setHarga(harga);
            if (total.equals("-")==false) c.setTotal(total);
          
          //  if (grade != 0) c.setGrade(grade);
            c.setUpdated_At(new Date());
            return true;
        } catch (Exception e){
            return false;
        }
    }
    */

    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Transaksi c = manager.find(Transaksi.class, id);
            c.setDeleted_At(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
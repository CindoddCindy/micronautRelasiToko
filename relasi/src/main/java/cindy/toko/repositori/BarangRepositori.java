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
import cindy.toko.model.Barang;

@Singleton
public class BarangRepositori implements BarangInterface {

    @PersistenceContext
    private EntityManager manager;

    public BarangRepositori(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Barang where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional
    public List<Barang> findAll(int page, int limit) {
        TypedQuery<Barang> query = manager
                .createQuery("from Barang where deleted_at IS NULL", Barang.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Barang findById(@NotNull Long id) {
        Barang query = manager.find(Barang.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean save(@NotNull Barang barang) {
        try {
            manager.persist(barang);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String id_barang, String nama) {
        try {

            Barang c = manager.find(Barang.class, id);
            if (id_barang.equals("-")==false) c.setIdBarang(id_barang);
            if (nama.equals("-")==false) c.setNama(nama);
           // if (jam_pulang.equals("-")==false) c.setJamPulang(jam_pulang);
          
          //  if (grade != 0) c.setGrade(grade);
            c.setUpdated_At(new Date());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Barang c = manager.find(Barang.class, id);
            c.setDeleted_At(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
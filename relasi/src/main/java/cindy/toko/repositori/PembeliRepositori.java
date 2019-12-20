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
import cindy.toko.model.Pembeli;

@Singleton
public class PembeliRepositori implements PembeliInterface {

    @PersistenceContext
    private EntityManager manager;

    public PembeliRepositori(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Pembeli where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional
    public List<Pembeli> findAll(int page, int limit) {
        TypedQuery<Pembeli> query = manager
                .createQuery("from Pembeli where deleted_at IS NULL", Pembeli.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Pembeli findById(@NotNull Long id) {
        Pembeli query = manager.find(Pembeli.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean save(@NotNull Pembeli pembeli) {
        try {
            manager.persist(pembeli);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String id_pembeli, String nama) {
        try {

            Pembeli c = manager.find(Pembeli.class, id);
            if (id_pembeli.equals("-")==false) c.setIdPembeli(id_pembeli);
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
            Pembeli c = manager.find(Pembeli.class, id);
            c.setDeleted_At(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
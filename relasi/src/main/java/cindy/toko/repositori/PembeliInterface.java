package cindy.toko.repositori;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.toko.model.Pembeli;

public interface PembeliInterface {

    Long size();
    List<Pembeli> findAll (int page, int limit);
    Pembeli findById (@NotNull Long id);
    boolean save(@NotNull Pembeli pembeli);
    boolean update(@NotNull Long id, @NotBlank String id_pembeli, @NotBlank String nama); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}
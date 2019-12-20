package cindy.toko.repositori;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.toko.model.Barang;

public interface BarangInterface {

    Long size();
    List<Barang> findAll (int page, int limit);
    Barang findById (@NotNull Long id);
    boolean save(@NotNull Barang barang);
    boolean update(@NotNull Long id, @NotBlank String id_barang, @NotBlank String nama); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}
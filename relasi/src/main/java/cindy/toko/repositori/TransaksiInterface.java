package cindy.toko.repositori;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.toko.model.Transaksi;

public interface TransaksiInterface {

    Long size();
    List<Transaksi> findAll (int page, int limit);
    Transaksi findById (@NotNull Long id);
    boolean save(@NotNull Transaksi transaksi);
   // boolean update(@NotNull Long id, @NotBlank String id_barang, @NotBlank String id_pembeli, @NotBlank String jumlah, @NotBlank String tanggal, @NotBlank String harga, @NotBlank String total); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}
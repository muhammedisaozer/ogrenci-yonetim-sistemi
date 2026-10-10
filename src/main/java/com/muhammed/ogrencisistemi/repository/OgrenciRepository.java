package com.muhammed.ogrencisistemi.repository;

import com.muhammed.ogrencisistemi.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    @Query("SELECT o FROM Ogrenci o WHERE CONCAT(o.ad, ' ', o.soyad, ' ', o.email, ' ', o.bolum) LIKE %?1%")
    List<Ogrenci> aramaYap(String keyword);

}
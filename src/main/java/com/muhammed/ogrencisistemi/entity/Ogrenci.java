package com.muhammed.ogrencisistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ogrenciler")
public class Ogrenci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Öğrenci adı boş bırakılamaz")
    @Size(min = 2, message = "Ad en az 2 karakter olmalıdır")
    @Column(name = "ad", nullable = false)
    private String ad;

    @NotBlank(message = "Öğrenci soyadı boş bırakılamaz")
    @Column(name = "soyad", nullable = false)
    private String soyad;

    @NotBlank(message = "E-posta adresi boş bırakılamaz")
    @Email(message = "Lütfen geçerli bir e-posta formatı giriniz (örn: ornek@mail.com)")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Bölüm alanı boş bırakılamaz")
    @Column(name = "bolum")
    private String bolum;

    // 1. Parametresiz Kurucu Metot (Spring Boot ve JPA'nın arka planda çalışması için zorunludur)
    public Ogrenci() {
    }

    // 2. Parametreli Kurucu Metot (Yeni öğrenci oluştururken kullanacağız - ID hariç)
    public Ogrenci(String ad, String soyad, String email, String bolum) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.bolum = bolum;
    }

    // 3. Getter ve Setter Metotları (Verilere dışarıdan erişmek ve değiştirmek için)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBolum() { return bolum; }
    public void setBolum(String bolum) { this.bolum = bolum; }
}
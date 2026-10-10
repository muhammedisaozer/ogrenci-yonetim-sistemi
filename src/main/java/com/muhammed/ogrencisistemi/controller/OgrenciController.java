package com.muhammed.ogrencisistemi.controller;

import com.muhammed.ogrencisistemi.entity.Ogrenci;
import com.muhammed.ogrencisistemi.repository.OgrenciRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OgrenciController {

    private final OgrenciRepository ogrenciRepository;

    public OgrenciController(OgrenciRepository ogrenciRepository) {
        this.ogrenciRepository = ogrenciRepository;
    }

    // 1. Listeleme ve Arama
    @GetMapping("/ogrenciler")
    public String ogrencileriListele(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("ogrenciler", ogrenciRepository.aramaYap(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("ogrenciler", ogrenciRepository.findAll());
        }
        return "ogrenciler";
    }

    // 2. Yeni Öğrenci Formu Gösterme
    @GetMapping("/ogrenciler/yeni")
    public String ogrenciOlusturmaFormu(Model model) {
        model.addAttribute("ogrenci", new Ogrenci());
        return "ogrenci_olustur";
    }

    // 3. Yeni Öğrenci Kaydetme (Validation Kontrollü)
    @PostMapping("/ogrenciler")
    public String ogrenciKaydet(@Valid @ModelAttribute("ogrenci") Ogrenci ogrenci,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "ogrenci_olustur";
        }
        ogrenciRepository.save(ogrenci);
        return "redirect:/ogrenciler";
    }

    // 4. Güncelleme Formu Gösterme
    @GetMapping("/ogrenciler/duzenle/{id}")
    public String ogrenciDuzenlemeFormu(@PathVariable Long id, Model model) {
        model.addAttribute("ogrenci", ogrenciRepository.findById(id).orElse(null));
        return "ogrenci_duzenle";
    }

    // 5. Güncelleme Kaydetme
    @PostMapping("/ogrenciler/{id}")
    public String ogrenciGuncelle(@PathVariable Long id, @ModelAttribute("ogrenci") Ogrenci ogrenci) {
        Ogrenci varOlanOgrenci = ogrenciRepository.findById(id).orElse(null);
        if (varOlanOgrenci != null) {
            varOlanOgrenci.setAd(ogrenci.getAd());
            varOlanOgrenci.setSoyad(ogrenci.getSoyad());
            varOlanOgrenci.setEmail(ogrenci.getEmail());
            varOlanOgrenci.setBolum(ogrenci.getBolum());
            ogrenciRepository.save(varOlanOgrenci);
        }
        return "redirect:/ogrenciler";
    }

    // 6. Silme İşlemi
    @GetMapping("/ogrenciler/sil/{id}")
    public String ogrenciSil(@PathVariable Long id) {
        ogrenciRepository.deleteById(id);
        return "redirect:/ogrenciler";
    }
}
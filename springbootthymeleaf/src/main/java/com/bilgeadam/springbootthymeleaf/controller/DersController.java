package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Ders;
import com.bilgeadam.springbootthymeleaf.repo.IDersRepository;
import com.bilgeadam.springbootthymeleaf.repo.IKonuRepository;
import com.bilgeadam.springbootthymeleaf.repo.IOgretmenRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "ders")
@AllArgsConstructor
public class DersController
{
	private IDersRepository dersRepository;

	private IOgretmenRepository ogretmenRepository;

	private IKonuRepository konuRepository;

	@GetMapping(path = "")
	public ModelAndView ders()
	{
		ModelAndView indexHtml = new ModelAndView("ders");
		indexHtml.addObject("ders_list", dersRepository.findAll(Sort.by("id").ascending()));
		return indexHtml;
	}

	@GetMapping(path = "sil")
	public ModelAndView sil()
	{
		ModelAndView indexHtml = new ModelAndView("ders_sil");
		indexHtml.addObject("ders_list", dersRepository.findAll());
		return indexHtml;
	}

	@GetMapping(path = "ekle")
	public ModelAndView ekle()
	{
		ModelAndView indexHtml = new ModelAndView("ders_ekle");
		indexHtml.addObject("ders", new Ders());
		indexHtml.addObject("ogretmen_list", ogretmenRepository.findAll());
		indexHtml.addObject("konu_list", konuRepository.findAll());
		return indexHtml;
	}

	@PostMapping(path = "ders_ekle")
	public ModelAndView ders_ekle(@ModelAttribute(name = "ders") Ders ders)
	{
		dersRepository.save(ders);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/ders");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") long id)
	{
		ModelAndView indexHtml = new ModelAndView("ders_guncelle");
		Ders ders = dersRepository.findById(id).get();
		indexHtml.addObject("ogretmen_list", ogretmenRepository.findAll());
		indexHtml.addObject("konu_list", konuRepository.findAll());
		indexHtml.addObject("ders", ders);
		return indexHtml;
	}

	@PostMapping(path = "ders_guncelle")
	public ModelAndView ders_guncelle(@ModelAttribute(name = "ders") Ders ders)
	{
		// form içerisinden post edilen verilerle ders parametresi oluşuyor
		dersRepository.save(ders);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/ders");
	}

	@PostMapping(path = "ders_sil")
	public ModelAndView ders_sil(@RequestParam(name = "id") long id)
	{
		dersRepository.deleteById(id);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/ders");
	}
}

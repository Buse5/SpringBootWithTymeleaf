package com.bilgeadam.springbootthymeleaf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Konu;
import com.bilgeadam.springbootthymeleaf.repo.IKonuRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "konu")
@AllArgsConstructor
public class KonuController
{
	private IKonuRepository konuRepository;

	@GetMapping(path = "")
	public ModelAndView konu(HttpServletRequest request)
	{
		ModelAndView indexHtml = new ModelAndView("konu");
		indexHtml.addObject("konu_list", konuRepository.findAll());
		return indexHtml;
	}

	@GetMapping(path = "sil")
	public ModelAndView sil()
	{
		ModelAndView indexHtml = new ModelAndView("konu_sil");
		indexHtml.addObject("konu_list", konuRepository.findAll());
		return indexHtml;
	}

	@GetMapping(path = "ekle")
	public ModelAndView ekle()
	{
		ModelAndView indexHtml = new ModelAndView("konu_ekle");
		indexHtml.addObject("yeni_konu", new Konu());
		return indexHtml;
	}

	@PostMapping(path = "konu_ekle")
	public ModelAndView konu_ekle(@ModelAttribute(name = "yeni_konu") Konu konu)
	{
		konuRepository.save(konu);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/konu");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") long id)
	{
		ModelAndView indexHtml = new ModelAndView("konu_guncelle");
		Konu konu = konuRepository.findById(id).get();
		indexHtml.addObject("konu_guncel", konu);
		return indexHtml;
	}

	@PostMapping(path = "konu_guncelle")
	public ModelAndView konu_guncelle(@ModelAttribute(name = "konu_guncel") Konu konu)
	{
		// form içerisinden post edilen verilerle konu parametresi oluşuyor
		konuRepository.save(konu);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/konu");
	}

	@PostMapping(path = "konu_sil")
	public ModelAndView konu_sil(@RequestParam(name = "id") long id)
	{
		konuRepository.deleteById(id);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/konu");
	}
}

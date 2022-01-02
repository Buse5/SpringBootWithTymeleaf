package com.bilgeadam.springbootthymeleaf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Ogretmen;
import com.bilgeadam.springbootthymeleaf.repo.IOgretmenRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "ogretmen")
@AllArgsConstructor
public class OgretmenController
{
	private IOgretmenRepository ogretmenRepository;

	@GetMapping(path = "")
	public ModelAndView ogretmen(HttpServletRequest request)
	{
		if (request.getUserPrincipal() != null)
		{
			// kullanıcı girişi yapılmış ise kısmı
			// System.err.println(request.getUserPrincipal().getClass().getName());
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
			// System.err.println(token.getDetails());
			// System.err.println(token.getName());
			// System.err.println(token.getPrincipal());
		}
		request.getSession().setAttribute("SK", "Session değişkeni");
		ModelAndView indexHtml = new ModelAndView("ogretmen");
		indexHtml.addObject("ogretmen_list", ogretmenRepository.findAll());
		return indexHtml;
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") long id)
	{
		ModelAndView indexHtml = new ModelAndView("ogretmen_detay");
		Ogretmen ogretmen = ogretmenRepository.findById(id).get();
		indexHtml.addObject("ogretmen", ogretmen);
		return indexHtml;
	}

	@GetMapping(path = "sil")
	public ModelAndView sil()
	{
		ModelAndView indexHtml = new ModelAndView("ogretmen_sil");
		indexHtml.addObject("ogretmen_list", ogretmenRepository.findAll());
		return indexHtml;
	}

	@GetMapping(path = "ekle")
	public ModelAndView ekle(HttpServletRequest request)
	{
		// session örneği
		System.err.println(request.getSession().getAttribute("SK"));
		ModelAndView indexHtml = new ModelAndView("ogretmen_ekle");
		indexHtml.addObject("ogretmen", new Ogretmen());
		return indexHtml;
	}

	@PostMapping(path = "ogretmen_ekle")
	public ModelAndView ogretmen_ekle(@ModelAttribute(name = "ogretmen") Ogretmen ogretmen)
	{
		ogretmenRepository.save(ogretmen);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/ogretmen");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") long id)
	{
		ModelAndView indexHtml = new ModelAndView("ogretmen_guncelle");
		Ogretmen ogretmen = ogretmenRepository.findById(id).get();
		indexHtml.addObject("ogretmen", ogretmen);
		return indexHtml;
	}

	@PostMapping(path = "ogretmen_guncelle")
	public ModelAndView ogretmen_guncelle(@ModelAttribute(name = "ogretmen") Ogretmen ogretmen)
	{
		// form içerisinden post edilen verilerle ogretmen parametresi oluşuyor
		ogretmenRepository.save(ogretmen);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/ogretmen");
	}

//	@PostMapping(path = "ogretmen_sil")
//	public ModelAndView ogretmen_sil(@ModelAttribute(name = "ogrId") Long id)
//	{
//		// html sayfasında name ile belirttiğimiz parametreler bu şekilde alınabilir
//		System.err.println(id);
//		return new ModelAndView("redirect:/ogretmen/sil");
//	}

	@PostMapping(path = "ogretmen_sil")
	public ModelAndView ogretmen_sil(@RequestParam(name = "id") long id)
	{
		ogretmenRepository.deleteById(id);
		// form tekrar gönderilmek durumunda olmasın diye redirect
		return new ModelAndView("redirect:/ogretmen");
	}
}

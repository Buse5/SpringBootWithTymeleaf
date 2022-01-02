package com.bilgeadam.springbootthymeleaf.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController
{
	private ApplicationContext context;

	@GetMapping(path =
	{ "", "index", "index.html" })
	public ModelAndView index(HttpServletRequest request)
	{
		// eski projelerde prefix ve suffix ayarları olabilir
		ModelAndView indexHtml = new ModelAndView("index");
		indexHtml.addObject("welcomemessage", "Spring boot ile thymeleaf öğreniyoruz");
//		if (new Random().nextBoolean())
//		{
		indexHtml.addObject("hello", context.getBeanDefinitionCount());
//		}
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		System.err.println("------------" + beanNames.length + "-----------");
		for (String string : beanNames)
		{
//			System.err.println(string + " -> " + context.getBean(string).getClass().getName());
		}
		System.err.println("--------------------------");
		return indexHtml;
	}
}

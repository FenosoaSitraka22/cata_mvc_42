package org.sid.web;

 import java.util.List;

import javax.naming.ldap.PagedResultsControl;
import javax.print.attribute.standard.PageRanges;


import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/index")
	public String index(Model model,@RequestParam(name="page",defaultValue = "0")int p,
			@RequestParam(name="size",defaultValue = "5")int s, @RequestParam(name="mc", defaultValue="")String mc) {
		Page<Produit> pageproduits = produitRepository.ProduitParMC("%"+mc+"%",PageRequest.of(p, s));
		model.addAttribute("listProduits",pageproduits.getContent() );
		int[] pagenum = new int[pageproduits.getTotalPages()];
		model.addAttribute("pages",pagenum);
		model.addAttribute("currentPage",p);
		model.addAttribute("size",s);
		model.addAttribute("mc",mc);
		return "produits";
	}
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(Long id,@RequestParam(name="page",defaultValue = "0")int p,
			@RequestParam(name="size",defaultValue = "5")int s, @RequestParam(name="mc", defaultValue="")String mc) {
			produitRepository.deleteById(id);
		return "redirect:/index?page="+p+"&size="+s+"&mc="+mc;
	}
	@RequestMapping(value="/edit",method =RequestMethod.GET)
	public String edit(Model model, Long id, @RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5")int s, @RequestParam(name="mc", defaultValue="")String mc) {
		Produit produit = produitRepository.getOne(id);
		model.addAttribute("produit",produit);
		model.addAttribute("currentPage",p);
		model.addAttribute("size",s);
		model.addAttribute("mc",mc);
		return "edit";
	}
}


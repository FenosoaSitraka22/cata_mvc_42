package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CataMvc42Application {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(CataMvc42Application.class, args);
		
		ProduitRepository pr = ac.getBean(ProduitRepository.class);
		for (int i = 0; i < 30; i++) {
			String s =String.format("%d", i);
			Double prix = (double) (i*10);
			Produit p = new Produit(s,prix,i);
			pr.save(p);
			System.out.println("execute");
		}
	}

}

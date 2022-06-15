package br.edu.ifms.taf.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.taf.model.Cma;
import br.edu.ifms.taf.model.Companhia;
import br.edu.ifms.taf.model.Exercicio;
import br.edu.ifms.taf.model.Exercito;
import br.edu.ifms.taf.model.Gu;
import br.edu.ifms.taf.model.Militar;
import br.edu.ifms.taf.model.MilitarExercicio;
import br.edu.ifms.taf.model.Omds;
import br.edu.ifms.taf.model.Pelotao;
import br.edu.ifms.taf.model.Taf;
import br.edu.ifms.taf.repository.CmaRepository;
import br.edu.ifms.taf.repository.CompanhiaRepository;
import br.edu.ifms.taf.repository.ExercicioRepository;
import br.edu.ifms.taf.repository.ExercitoRepository;
import br.edu.ifms.taf.repository.GuRepository;
import br.edu.ifms.taf.repository.MilitarExercicioRepository;
import br.edu.ifms.taf.repository.MilitarRepository;
import br.edu.ifms.taf.repository.OmdsRepository;
import br.edu.ifms.taf.repository.PelotaoRepository;
import br.edu.ifms.taf.repository.TafRepository;

@Service
public class DBService {

	@Autowired
	private ExercitoRepository exercitoRepository;
	@Autowired
	private CmaRepository cmaRepository;
	@Autowired
	private GuRepository guRepository;
	@Autowired
	private OmdsRepository omdsRepository;
	@Autowired
	private CompanhiaRepository companhiaRepository;
	@Autowired
	private PelotaoRepository pelotaoRepository;
	
	@Autowired
	private MilitarRepository militarRepository;
	@Autowired
	private TafRepository tafRepository;
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	@Autowired
	private MilitarExercicioRepository militarExercicioRepository;

	

	public void instantiateTestDatabase() throws ParseException {

		Exercito exe1 = new Exercito(null, "Exercito Brasileiro");

		Cma cma1 = new Cma(null, "Comando militar de area Centro-oeste", exe1);
		Cma cma2 = new Cma(null, "Comando militar de area Nordeste", exe1);
		Cma cma3 = new Cma(null, "Comando militar de area Norte", exe1);

		exe1.getCmas().addAll(Arrays.asList(cma1, cma2, cma3));
		
		Gu gu1 = new Gu(null, "Quarta brigada de cavalaria mecanizada", cma1);
		Gu gu2 = new Gu(null, "Décima oitava brigada de infantaria do pantanal", cma1);
		Gu gu3 = new Gu(null, "Décima terceira brigada de infantaria motorizada", cma1);
		

		Gu gu4 = new Gu(null, "Sétima brigada de infantaria motorizada", cma2);
		Gu gu5 = new Gu(null, "Decima brigada de infantaria motorizada", cma2);

		Gu gu6 = new Gu(null, "Vegésima terceira brigada de infantaria de selva", cma3);

		cma1.getGus().addAll(Arrays.asList(gu1, gu2, gu3));
		cma2.getGus().addAll(Arrays.asList(gu4, gu5));
		cma3.getGus().addAll(Arrays.asList(gu6));
		
		
		
		Omds om1 = new Omds(null,"Decimo regimento de cavalaria mecanizada",gu1);
		Omds om2 = new Omds(null,"Decimo primeiro regimento de cavalaria mecanizada",gu1);
		Omds om3 = new Omds(null,"Decimo setimo regimento de cavalaria mecanizada",gu1);
		
		Omds om4 = new Omds(null,"Decimo oitava pelotao de policia do exercito",gu2);
		Omds om5 = new Omds(null,"Decimo setimo batalhão de fronteira",gu2);
		Omds om6 = new Omds(null,"Quadragesimo setimo batalha de fronteira",gu2);
		Omds om7 = new Omds(null,"Decimo oitavo pelotão de comunicações",gu2);
		Omds om8 = new Omds(null,"Companhia de comando da 18º brigada de infantaria do pantanal",gu2);
		
		Companhia cp1 = new Companhia(null,"Primeira companhia de fuzileiros",om5);
		Companhia cp2 = new Companhia(null,"Segunda companhia de fuzileiros",om5);
		Companhia cp3 = new Companhia(null,"Terceira companhia de fuzileiros",om5);
		Companhia cp4 = new Companhia(null,"Companhia de comando de apoio",om5);
		
		om5.getCompanhias().addAll(Arrays.asList(cp1,cp2,cp3,cp4));
		
		Pelotao pl1 = new Pelotao(null,"Primeiro pelotao de fuzileiros",cp1);
		Pelotao pl2 = new Pelotao(null,"Segundo pelotao de fuzileiros",cp1);
		Pelotao pl3 = new Pelotao(null,"Terceiro pelotao de fuzileiros",cp1);
		Pelotao pl4 = new Pelotao(null,"Seção de  comando",cp1);
		
		cp1.getPelotoes().addAll(Arrays.asList(pl1,pl2,pl3,pl4));
		
		Militar m1 = new Militar(null, "Clodoaldo de Oliveira Silva", "111111111","555555555-55", pl4);	
		Militar m2 = new Militar(null, "Jose Aparecido da Silva", "111111111","555555555-55", pl1);	
		Militar m3 = new Militar(null, "Mario Velasquez dos Santos", "111111111","555555555-55", pl3);	
		Militar m4 = new Militar(null, "Jose Carlos Silva", "111111111","555555555-55", pl4);	
		Militar m5 = new Militar(null, "Edis Barreto de Jesus", "111111111","555555555-55", pl1);	
		
		pl4.getMilitares().addAll(Arrays.asList(m1,m4));
		pl1.getMilitares().addAll(Arrays.asList(m2,m5));
		pl3.getMilitares().addAll(Arrays.asList(m3));
		
		exercitoRepository.saveAll(Arrays.asList(exe1));
		
		cmaRepository.saveAll(Arrays.asList(cma1, cma2, cma3));
		guRepository.saveAll(Arrays.asList(gu1,gu2,gu3,gu4,gu5,gu6));
		omdsRepository.saveAll(Arrays.asList(om1,om2,om3,om4,om5,om6,om7,om8));
		companhiaRepository.saveAll(Arrays.asList(cp1,cp2,cp3,cp4));
		pelotaoRepository.saveAll(Arrays.asList(pl1,pl2,pl3,pl4));
		
		//militarRepository.saveAll(Arrays.asList(m1,m2,m3,m4,m5));	
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Taf t1 = new Taf(null, "Primeira TAF", sdf.parse("01/04/2021 10:32"));
		Taf t2 = new Taf(null, "Segunda TAF", sdf.parse("01/07/2021 10:32"));
		Taf t3 = new Taf(null, "Terceira TAF", sdf.parse("01/10/2021 10:32"));
		
		Exercicio ex1 = new Exercicio(null, "Flexao de braço");
		Exercicio ex2 = new Exercicio(null, "Corrida de 12 min");
		Exercicio ex3 = new Exercicio(null, "Abdominal");
		Exercicio ex4 = new Exercicio(null, "Flexao");
		Exercicio ex5 = new Exercicio(null, "Pista de Pentatlo Militar");
		
		t1.getExercicios().addAll(Arrays.asList(ex1,ex2,ex3,ex4,ex5));
		t2.getExercicios().addAll(Arrays.asList(ex1,ex2,ex3,ex4,ex5));
		t3.getExercicios().addAll(Arrays.asList(ex1,ex2,ex3,ex4,ex5));
		
		exercicioRepository.saveAll(Arrays.asList(ex1,ex2,ex3,ex4,ex5));
		tafRepository.saveAll(Arrays.asList(t1,t2,t3));
		
		militarRepository.saveAll(Arrays.asList(m1,m2,m3,m4,m5));	
		
		MilitarExercicio me1 = new MilitarExercicio(null, "B", m1, ex1);
		MilitarExercicio me2 = new MilitarExercicio(null, "MB",m1, ex2);
		MilitarExercicio me3 = new MilitarExercicio(null, "B", m1, ex3);
		MilitarExercicio me4 = new MilitarExercicio(null, "B", m1, ex4);
		MilitarExercicio me5 = new MilitarExercicio(null, "B", m1, ex5);
		
		MilitarExercicio me6 = new MilitarExercicio(null, "E", m2, ex1);
		MilitarExercicio me7 = new MilitarExercicio(null, "MB", m2, ex2);
		MilitarExercicio me8 = new MilitarExercicio(null, "MB", m2, ex3);
		MilitarExercicio me9 = new MilitarExercicio(null, "B", m2, ex4);
		MilitarExercicio me10 = new MilitarExercicio(null, "MB", m2, ex5);
		   
		MilitarExercicio me11 = new MilitarExercicio(null, "MB", m3, ex1);
		MilitarExercicio me12 = new MilitarExercicio(null, "MB", m3, ex2);
		MilitarExercicio me13 = new MilitarExercicio(null, "MB", m3, ex3);
		MilitarExercicio me14 = new MilitarExercicio(null, "E", m3, ex4);
		MilitarExercicio me15 = new MilitarExercicio(null, "MB", m3, ex5);
		
		militarExercicioRepository.saveAll(Arrays.asList(me1,me2,me3,me4,me5,me6,me7,me8,me9,me10,me11,me12,me13,me14,me15));

	}
}

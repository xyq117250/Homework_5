package model;

import java.io.*;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.*;
import javax.print.attribute.standard.Media;

import model.Site;

@Path("/site")
public class SiteDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Homework4");
	EntityManager em = null;

	//public Site findSite(int siteId) - returns an instance of Site for the given siteId
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId) {
		Site site = null;

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);

		em.getTransaction().commit();
		em.close();

		return site;
	}

	//public List<Site> findAllSites() : returns a list of Site instances
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		List<Site> sitelist = new ArrayList<Site>();
		em = factory.createEntityManager();
		em.getTransaction().begin();

		sitelist =em.createNamedQuery("findAllSites").getResultList();
		

		em.getTransaction().commit();
		em.close();

		return sitelist;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site){
		List<Site> s = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site.setId(siteId);
		em.merge(site);    //problem!!

		Query query = em.createNamedQuery("findAllSites");
		s = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return s;
	}
    
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId)
	{
		List<Site> sitelist = new ArrayList<Site>();

		Site site = null;

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);
		em.remove(site);

		Query query = em.createNamedQuery("findAllSites");
		sitelist = query.getResultList();

		em.getTransaction().commit();
		em.close();

		return sitelist;
		
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site){
		List<Site> sitelist = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(site);
		Query query = em.createNamedQuery("findAllSites");
		sitelist = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return sitelist;
	}
	
	
	
 /* below is cited from https://github.com/jannunzi/CS5200/blob/master/JWS_JPA/src/example/SolutionDao.java*/
 /* should use Class Sites */	
/*	
	public void exportSiteDatabaseToXmlFile(Sites list, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Sites.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(list, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/    
	 /* below is cited from https://github.com/jannunzi/CS5200/blob/master/JWS_JPA/src/example/SolutionDao.java*/
/*
	public void convertXmlFileToOutputFile(String inputXmlFileName,String outputXmlFileName,String xsltFileName)
	{
		//open file
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);
		//read file
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt   = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);

		TransformerFactory factory = TransformerFactory.newInstance();
		
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// i
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
This part is not required in the Homework description*/
	
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		dao.removeSite(1);
		//test findAllSites()
		List<Site> sites = dao.findAllSites();
		for(Site sitelist : sites) {
			System.out.println(sitelist.getName());
			System.out.println(sitelist.getId());
		}

	//  Do not be required in the Homework description. cited from Class Resources
		
	//	dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
	//	dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
		
	}
}

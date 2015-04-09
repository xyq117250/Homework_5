package model;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Sites {
	@XmlElement(name="site")
	private List<Site> sites;

	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	public Sites(List<Site> sites) {
		super();
		this.sites = sites;
	}
	public Sites() {
		super();
	}

}

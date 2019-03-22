package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Site;

public interface SiteDao {

	void createSite(Site site);

	List<Site> list();

	Site findSite(int id);

	void updateSite(Site site);

	void deleteSite(int id);

}

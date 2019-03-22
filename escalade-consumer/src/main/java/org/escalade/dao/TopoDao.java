package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Topo;

public interface TopoDao {

	void createTopo(Topo topo);

	List<Topo> list();

	Topo findTopo(int id);

	Topo findTopoBySite(int siteId);

	Topo findTopoByUtilisateur(int utilisateurId);

	void updateTopo(Topo topo);

	void deleteTopo(int id);

}

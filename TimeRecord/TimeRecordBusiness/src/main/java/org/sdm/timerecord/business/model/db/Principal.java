package org.sdm.timerecord.business.model.db;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.sdm.timerecord.business.model.ResourceType;

@Entity
@Table(name = "TR_PRINCIPALS", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@NamedQueries({
		@NamedQuery(name = "Principal.listAll", query = "SELECT p FROM Principal p"),
		@NamedQuery(name = "Principal.getRoot", query = "SELECT p FROM Principal p WHERE p.name='root'") })
public class Principal extends Resource implements Serializable,
		java.security.Principal {
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", nullable = false)
	private String name;

	public Principal() {
		super();
	}

	public Principal(String name, Principal parent) throws Exception {
		super();
		this.name = name;
		this.resourceType = ResourceType.PRINCIPAL;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void reset(Map<String, String[]> params) throws Exception {
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			if ("name".equals(entry.getKey()))
				name = entry.getValue()[0];
		}
	}

}

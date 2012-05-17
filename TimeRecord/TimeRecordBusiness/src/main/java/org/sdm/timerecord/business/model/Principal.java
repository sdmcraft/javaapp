package org.sdm.timerecord.business.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue("PRINCIPAL")
@Table(name = "TR_PRINCIPAL", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
public class Principal extends Resource implements Serializable, java.security.Principal  {
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", nullable = false)
	private String name;

	public Principal() {
		super();
	}

	public Principal(String name) throws Exception {
		super();
		this.name = name;
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

package org.sdm.timerecord.business.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TR_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "IMAGE", nullable = true)
	private Blob image;

	@Column(name = "NAME", nullable = false)
	private String name;

	public User() {
		super();		
	}
	
	public User(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public void reset(Map<String, String[]> params) {
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			if ("name".equals(entry.getKey())) {
				setName(entry.getValue()[0]);
			}
		}
	}

}

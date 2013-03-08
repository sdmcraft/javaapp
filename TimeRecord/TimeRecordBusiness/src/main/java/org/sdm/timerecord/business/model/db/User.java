package org.sdm.timerecord.business.model.db;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.sdm.timerecord.business.Constants;
import org.sdm.timerecord.business.Utils;

@Entity
@Table(name = "TR_USERS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"NAME", "PASSWORD" }) })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "IMAGE", nullable = true)
	private Blob image;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@OneToOne(optional = false)
	@JoinColumn(name = "PRINCIPAL_ID", unique = true, nullable = false, updatable = false)
	Principal principal;

	public User() {
		super();
	}

	public User(String name, String password) throws Exception {
		super();
		this.name = name;
		this.password = Utils.encrypt(password, Constants.ENCRYPTION_ALGORITHM,
				Constants.ENCRYPTION_ENCODING);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public void reset(Map<String, String[]> params) throws Exception {
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			if ("name".equals(entry.getKey()))
				name = entry.getValue()[0];
			else if ("password".equals(entry.getKey()))
				password = Utils.encrypt(entry.getValue()[0],
						Constants.ENCRYPTION_ALGORITHM,
						Constants.ENCRYPTION_ENCODING);
		}
	}

}

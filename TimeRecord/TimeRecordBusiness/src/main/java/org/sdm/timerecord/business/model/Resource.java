package org.sdm.timerecord.business.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "RESOURCE_TYPE")
@Table(name = "TR_RESOURCE")
public abstract class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	private Resource parent;

	@OneToMany(mappedBy = "parent")
	private Collection<Resource> children;

	public Resource() {
		super();
	}

	public Integer getId() {
		return id;
	}

}

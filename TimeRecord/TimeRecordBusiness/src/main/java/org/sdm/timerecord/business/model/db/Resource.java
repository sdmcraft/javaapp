package org.sdm.timerecord.business.model.db;

import org.sdm.timerecord.business.model.Acl;
import org.sdm.timerecord.business.model.ResourceType;

import java.io.Serializable;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "TR_RESOURCES")
public abstract class Resource implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ManyToOne(optional = false)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    protected Resource parent;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "RESOURCE_TYPE", nullable = false)
    protected ResourceType resourceType;
    @OneToMany(mappedBy = "resource")
    private Collection<org.sdm.timerecord.business.model.db.AclEntry> acl;
    @OneToMany(mappedBy = "parent")
    private Collection<Resource> children;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    public Resource()
    {
        super();
    }

    public Acl getAcl()
    {
        return new Acl(acl);
    }

    public Integer getId()
    {
        return id;
    }
}

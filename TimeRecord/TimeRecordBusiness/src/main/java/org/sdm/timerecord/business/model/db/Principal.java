package org.sdm.timerecord.business.model.db;

import org.sdm.timerecord.business.model.ResourceType;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "TR_PRINCIPALS", uniqueConstraints = 
{
    @UniqueConstraint(columnNames = 
    {
        "NAME"}
    )
}
)
@NamedQueries({@NamedQuery(name = "Principal.listAll", query = "SELECT p FROM Principal p")
    , @NamedQuery(name = "Principal.getRoot", query = "SELECT p FROM Principal p WHERE p.name='root'")
	, @NamedQuery(name = "Principal.getMembersGroup", query = "SELECT p FROM Principal p WHERE p.name='members'")
})
public class Principal extends Resource implements Serializable, java.security.Principal
{
    private static final long serialVersionUID = 1L;
    @ManyToMany
    @JoinTable(name = "TR_PRINCIPAL_GROUP_MEMBERS", joinColumns = @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    , inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    )
    private Set<Principal> groups;
    @ManyToMany(mappedBy = "groups")
    private Set<Principal> members;
    @Column(name = "NAME", nullable = false)
    private String name;

    public Principal()
    {
        super();
    }

    public Principal(String name, Resource parent) throws Exception
    {
        super();
        this.name = name;
        this.resourceType = ResourceType.PRINCIPAL;
        this.parent = parent;
    }

    public Set<Principal> getMembers()
    {
        return members;
    }

    public Set<Principal> getGroups()
    {
        return groups;
    }
    public void setGroups(Set<Principal> groups)
    {
        this.groups = groups;
    }

    public String getName()
    {
        return name;
    }

    public void reset(Map<String, String[]> params) throws Exception
    {
        for (Map.Entry<String, String[]> entry : params.entrySet())
        {
            if ("name".equals(entry.getKey()))
            {
                name = entry.getValue()[0];
            }
        }
    }
}

package org.sdm.timerecord.business.model.db;

import org.sdm.timerecord.business.Constants;
import org.sdm.timerecord.business.Utils;

import java.io.Serializable;

import java.sql.Blob;

import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "TR_USERS")
public class User extends Principal implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Column(name = "IMAGE", nullable = true)
    private Blob image;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public User()
    {
        super();
    }

    public User(String name, String password, Resource parent) throws Exception
    {
        super(name, parent);
        this.password = Utils.encrypt(password, Constants.ENCRYPTION_ALGORITHM, Constants.ENCRYPTION_ENCODING);
    }

    @Lob
    @Basic(fetch = FetchType.EAGER)
    public Blob getImage()
    {
        return image;
    }

    public void setImage(Blob image)
    {
        this.image = image;
    }

    public void reset(Map<String, String[]> params) throws Exception
    {
        for (Map.Entry<String, String[]> entry : params.entrySet())
        {
            if ("password".equals(entry.getKey()))
            {
                password = Utils.encrypt(entry.getValue()[0], Constants.ENCRYPTION_ALGORITHM, Constants.ENCRYPTION_ENCODING);
            }
        }
    }
}

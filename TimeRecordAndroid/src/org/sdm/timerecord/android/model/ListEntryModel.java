package org.sdm.timerecord.android.model;

public class ListEntryModel
{
    private String entryTime;
    private long id;
    private long listId;
    private long value;

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return the entryTime
     */
    public String getEntryTime()
    {
        return entryTime;
    }

    /**
     * @param entryTime the entryTime to set
     */
    public void setEntryTime(String entryTime)
    {
        this.entryTime = entryTime;
    }

    /**
     * @return the listId
     */
    public long getListId()
    {
        return listId;
    }

    /**
     * @param listId the listId to set
     */
    public void setListId(long listId)
    {
        this.listId = listId;
    }

    /**
     * @return the value
     */
    public long getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(long value)
    {
        this.value = value;
    }
}

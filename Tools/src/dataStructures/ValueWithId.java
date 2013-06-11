package dataStructures;

public class ValueWithId
{
    private final String id;
    private final double value;

    public ValueWithId(String id, double value)
    {
        super();
        this.id = id;
        this.value = value;
    }

    public String getId()
    {
        return id;
    }

    public double getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "(" + id + "," + value + ")";
    }
}

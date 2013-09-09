package misc;

import java.io.Serializable;

import java.security.Principal;


class MyPrincipal implements Principal, Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getName()
    {
        return "root";
    }
}

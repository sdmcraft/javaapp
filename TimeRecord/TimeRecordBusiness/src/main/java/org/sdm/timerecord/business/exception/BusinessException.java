package org.sdm.timerecord.business.exception;

public class BusinessException extends Exception
{
    private final FailureCode code;

    public BusinessException(FailureCode code)
    {
        this.code = code;
    }

    public FailureCode getCode()
    {
        return code;
    }
}

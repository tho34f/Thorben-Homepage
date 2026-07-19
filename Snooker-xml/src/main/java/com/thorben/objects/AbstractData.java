package com.thorben.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractData {

    protected long id;

    protected long creationDate;
    protected String creationDateAsString;

    protected long changeDate;
    protected String changeDateAsString;
}

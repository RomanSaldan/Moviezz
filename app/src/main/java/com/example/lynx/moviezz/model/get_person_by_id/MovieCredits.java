package com.example.lynx.moviezz.model.get_person_by_id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 28.12.2015.
 */
public class MovieCredits implements Serializable {
    public List<PersonCreditsCast> cast;
    public List<PersonCreditsCrew> crew;
}

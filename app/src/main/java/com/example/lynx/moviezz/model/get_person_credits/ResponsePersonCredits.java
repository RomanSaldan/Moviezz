package com.example.lynx.moviezz.model.get_person_credits;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 10.12.2015.
 */
public class ResponsePersonCredits implements Serializable {

    public int id;
    public List<PersonCreditInfo> cast;
}

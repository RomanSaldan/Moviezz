package com.example.lynx.moviezz.model.get_certifications;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lynx on 09.12.2015.
 */

public class Certifications implements Serializable {
    @SerializedName("US")
    public List<Certification> us;
}

package com.learnsyc.appweb.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
public class CanjePK implements Serializable {
    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
    @JoinColumns({
            @JoinColumn(name="id_premio", referencedColumnName="id_premio")
    })
    @ManyToOne
    Premio premio;
}

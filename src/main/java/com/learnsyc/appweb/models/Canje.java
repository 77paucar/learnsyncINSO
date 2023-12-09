package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.time.LocalDate;
import java.util.Date;

@Data
@Table(name ="canjes")
@IdClass(value = CanjePK.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Canje {
    @Id
    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    private Usuario usuario;
    @Id
    @JoinColumns({
            @JoinColumn(name="id_premio", referencedColumnName="id_premio")
    })
    @ManyToOne
    private Premio premio;
    @Column(name = "precio", nullable = false)
    private int precio;
    @Column(name = "fecha_canjeo", nullable = false)
    private final LocalDate fechaCanjeo = LocalDate.now();
}

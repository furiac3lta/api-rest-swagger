package com.gestion.gestion.inmobiliaria.entities;
import com.gestion.gestion.inmobiliaria.enums.Tipo;
import com.gestion.gestion.inmobiliaria.models.response.UserModelResponse;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InmuebleEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inmuebleId;
    private String titulo;
    private String direccion;
    private String ciudad;
    private String contacto;
    private Long telefono;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private Boolean activo = true;
    @CreatedDate
    private Date createAt;
    @CreatedDate
    private Date updateAt;
    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
}
package co.vinni.soapproyectobase.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String nivel;
    private String logro1;
    private String logro2;
    private String logro3;
    private String logro4;
    private String logro5;

}

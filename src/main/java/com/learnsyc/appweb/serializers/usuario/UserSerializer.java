package com.learnsyc.appweb.serializers.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSerializer {
    String user;
    String email;
}

package com.coderhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResumenDTO {
   private String nombre;
   private String apellido;
   private String email;
}

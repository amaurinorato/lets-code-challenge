package br.com.letscode.star.wars.dto.response;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StandardErrorResponse implements Serializable {

    private String codeDescription;
    private String message;

}
